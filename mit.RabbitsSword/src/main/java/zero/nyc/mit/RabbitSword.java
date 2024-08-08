package zero.nyc.mit;

import org.bukkit.plugin.java.JavaPlugin;

public final class RabbitSword extends JavaPlugin {

    private ConfigClass configClass;
    @Override
    public void onEnable() {
        this.configClass = new ConfigClass(this.getConfig());
        this.getCommand("rabbitsword").setExecutor(new CommandClass(this));
        this.initCf();
    }

    @Override
    public void onDisable() {
        this.saveConfig();
    }

    public void startMessage() {
        getLogger().info("==================================================");
        getLogger().info("||                                              ||");
        getLogger().info("||             Plugin made by 0nyc              ||");
        getLogger().info("||            Every problem and bug             ||");
        getLogger().info("||             report on discord:               ||");
        getLogger().info("||        https://discord.gg/kZUJjD5297         ||");
        getLogger().info("||                                              ||");
        getLogger().info("||        Plugin name -> RabbitSword           ||");
        getLogger().info("||        Plugin version -> 1.0                 ||");
        getLogger().info("||                                              ||");
        getLogger().info("==================================================");
    }

    public void initCf() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }
}
