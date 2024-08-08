package zero.nyc.mit;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static zero.nyc.mit.ItemClass.enchants;
import static zero.nyc.mit.ItemClass.material;

public class CommandClass implements CommandExecutor {
    public CommandClass(RabbitSword rabbitSword) {

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player p && (p.hasPermission("rabitsword.command") || p.isOp())) {
            ItemStack item = p.getInventory().getItemInMainHand();
            Material itemMaterial = item.getType();
            List<Material> list = List.of(
                    Material.WOODEN_SWORD,
                    Material.STONE_SWORD,
                    Material.IRON_SWORD,
                    Material.GOLDEN_SWORD,
                    Material.DIAMOND_SWORD,
                    Material.NETHERITE_SWORD
            );
            if (list.contains(itemMaterial)) {
                material = item.getType();
                enchants = item.getEnchantments();
                p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                p.getInventory().setItemInMainHand(ItemClass.createRabbitsSword());
            }
        }
        return true;
    }
}
