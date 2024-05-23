package it.samuconfaa.pvpantidrop;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigurationManager {
    private final PvPAntiDrop plugin;
    private static FileConfiguration config;
    private static File configFile;

    public ConfigurationManager(PvPAntiDrop plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        configFile = new File(plugin.getDataFolder(), "config.yml");
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static int altezzaGUI() {
        return config.getInt("GUIHeight", 27);
    }

    public static int glassColor() {
        return config.getInt("glassColor", 7);
    }

    public static int melePos() {
        return config.getInt("position.mele", 11);
    }

    public static int frecciaPos() {
        return config.getInt("position.freccia", 13);
    }

    public static int cannaPos() {
        return config.getInt("position.canna", 15);
    }

    public static String glass() {
        return config.getString("glassName");
    }

    public static String meleName(){
        return config.getString("itemname.mele");
    }
    public static String frecciaName(){
        return config.getString("itemname.freccia");
    }
    public static String cannaName(){
        return config.getString("itemname.canna");
    }
    public static String enable(){
        return config.getString("status.enable");
    }
    public static String disable(){
        return config.getString("status.disable");
    }

    public static String noPerms(){
        return config.getString("noPerms");
    }

    public static String reload(){
        return config.getString("reload");
    }


    public static String guiName() {
        return config.getString("nomeGUI");
    }
}
