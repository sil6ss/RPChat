package copernicus.rpchat.data;

import copernicus.rpchat.*;
import org.bukkit.*;
import org.bukkit.configuration.file.*;

import java.io.*;
import java.util.*;

public class Config {

    private static FileConfiguration config;
    private static File cFile;



    public static void loadConfiguration() {
        cFile = new File(RPChat.getInstance().getDataFolder().getAbsolutePath() + "/config.yml");

        if (!cFile.exists()) {
            try {
                cFile.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(cFile);
        config.addDefault("local-chat-radius", 50);
        config.addDefault("serverPrefix", "&8&l[&cTest Server&8&l] ");
        config.addDefault("localPrefix", "&8[&7OOC&8] ");
        config.addDefault("roleplayPrefix", "&4[&cL&4] ");
        config.addDefault("staffFormat", "&7[&aS&7] ");
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void setConfig(FileConfiguration config) {
        Config.config = config;
    }

    public static File getcFile() {
        return cFile;
    }

    public static void setcFile(File cFile) {
        Config.cFile = cFile;
    }

    public static void save() throws IOException {
        config.save(cFile);
    }
    public static void reload() {
        RPChat.getInstance().dataLoader.saveData();
        Bukkit.getPluginManager().disablePlugin(RPChat.getInstance());
        Bukkit.getPluginManager().getPlugin("RPChat").reloadConfig();
        Bukkit.getPluginManager().enablePlugin(RPChat.getInstance());
        RPChat.getInstance().dataLoader.loadData();
    }

}
