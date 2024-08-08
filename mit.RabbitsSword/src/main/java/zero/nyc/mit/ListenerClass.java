package zero.nyc.mit;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ListenerClass implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER)) {
            Plugin plugin = RabbitSword.getPlugin(RabbitSword.class);
            Player p = (Player) e.getDamager();
            if (p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "rabbitsword"), PersistentDataType.BYTE)) {

                double cooldownChance = plugin.getConfig().getDouble("chance-for-jump-cooldown");
                Random random = new Random();
                double rv = random.nextDouble();

                if (rv < cooldownChance) {
                    int jumpCooldown = plugin.getConfig().getInt("jump-cooldown-in-seconds");
                    p.getPersistentDataContainer().set(new NamespacedKey(plugin, "jumpCancel"), PersistentDataType.BYTE, (byte)0);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            p.getPersistentDataContainer().remove(new NamespacedKey(plugin, "jumpCancel"));
                        }
                    }.runTaskLater(plugin, jumpCooldown * 20L);
                }
            }
        }
    }


    @EventHandler
    public void onJump(PlayerJumpEvent e) {
        Player p = e.getPlayer();
        if (p.getPersistentDataContainer().has(new NamespacedKey(RabbitSword.getPlugin(RabbitSword.class), "jumpCancel"), PersistentDataType.BYTE)) {
            e.setCancelled(true);
        }
    }
}
