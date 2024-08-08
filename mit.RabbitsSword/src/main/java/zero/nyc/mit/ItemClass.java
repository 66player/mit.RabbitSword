package zero.nyc.mit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class ItemClass {

    public static Material material;
    public static Map<Enchantment, Integer> enchants = new HashMap<>();

    public static ItemStack createRabbitsSword() {
        Plugin plugin = RabbitSword.getPlugin(RabbitSword.class);
        String name = plugin.getConfig().getString("name", "&4Króliczy miecz");
        List<String> lore = new ArrayList<>();
        
        ItemStack rabbitSword = new ItemStack(material);

        ItemMeta itemMeta = rabbitSword.getItemMeta();

        rabbitSword.addUnsafeEnchantments(enchants);
        
        if (itemMeta != null) {
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "rabbitsword"), PersistentDataType.BYTE, (byte)0);

            for (int i = 0; i < plugin.getConfig().getStringList("lore").size(); i++) {
                lore.add(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getStringList("lore").get(i)));
            }
            itemMeta.setLore(lore);
        }

        rabbitSword.setItemMeta(itemMeta);
        return rabbitSword;
    }
}
