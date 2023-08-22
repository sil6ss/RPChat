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
        Objects.requireNonNull(Bukkit.getPluginManager().getPlugin(RPChat.getInstance().getPluginMeta().getName())).reloadConfig();
        Bukkit.getPluginManager().enablePlugin(RPChat.getInstance());
        RPChat.getInstance().dataLoader.loadData();
    }

}
