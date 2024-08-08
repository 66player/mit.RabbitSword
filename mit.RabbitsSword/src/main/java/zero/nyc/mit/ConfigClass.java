package zero.nyc.mit;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigClass {

    private FileConfiguration cf;

    public ConfigClass(FileConfiguration cf) {
        this.cf = cf;
        cf.options().copyDefaults(true);
    }

    public FileConfiguration getCf() {
        return this.cf;
    }

    public void reloadCf() {
        Plugin plugin = RabbitSword.getPlugin(RabbitSword.class);
        plugin.reloadConfig();
    }
}
