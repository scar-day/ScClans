package dev.scarday.scclans;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.scarday.scclans.configuration.Configuration;
import dev.scarday.scclans.configuration.data.ConnectionDatabase;
import dev.scarday.scclans.database.DatabaseManager;
import dev.scarday.scclans.database.impl.MySQLImpl;
import dev.scarday.scclans.database.impl.SQLiteImpl;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.val;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScClans extends JavaPlugin {
    BukkitAudiences adventure;

    LiteCommands<CommandSender> liteCommands;

    @Getter
    Configuration configuration;

    DatabaseManager databaseManager;

    @Override
    public void onEnable() {

        this.adventure = BukkitAudiences.create(this);
        loadConfig();
        registerCommands();
        registerDatabase(configuration.getDatabase());
    }

    private void registerCommands() {
        this.liteCommands = LiteBukkitFactory.builder(this)
                .build();
    }

    @Override
    public void onDisable() {
        if (this.databaseManager != null) {
            this.databaseManager.close();
            this.databaseManager = null;
        }

        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }

        if (this.liteCommands != null) {
            this.liteCommands.unregister();
            this.liteCommands = null;
        }

        HandlerList.unregisterAll(this);
    }

    public void reloadConfig() {
        this.configuration = (Configuration) configuration.load();
    }

    public void loadConfig() {
        try {
            this.configuration = ConfigManager.create(Configuration.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
                it.withBindFile(new File(getDataFolder(), "config.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Failed to load configuration:", e);
        }
    }

    private void registerDatabase(ConnectionDatabase database) {
        switch (configuration.getDatabase().getType()) {
            case "SQLITE" -> {
                val file = new File(database.getSqlitePath()
                        .replace("{absolutePath}", getDataFolder().getAbsolutePath()));
                this.databaseManager = new SQLiteImpl(file);
            }
            case "MYSQL", "MARIAD" -> this.databaseManager = new MySQLImpl(database);
            default -> {
                getPluginLoader().disablePlugin(this);
                throw new RuntimeException("database type is not defined.");
            }
        }
    }
}
