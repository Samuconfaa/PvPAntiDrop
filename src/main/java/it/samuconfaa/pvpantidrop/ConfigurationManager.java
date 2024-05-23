package it.samuconfaa.pvpantidrop;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationManager {
    private final PvPAntiDrop plugin;
    private static FileConfiguration config;

    public ConfigurationManager(PvPAntiDrop plugin) {
        this.plugin = plugin;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
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

    public static String cannaON() {
        return config.getString("itemLore.canna.on");
    }

    public static String cannaOFF() {
        return config.getString("itemLore.canna.off");
    }

    public static String meleON() {
        return config.getString("itemLore.mele.on");
    }

    public static String meleOFF() {
        return config.getString("itemLore.mele.off");
    }

    public static String frecciaON() {
        return config.getString("itemLore.freccia.on");
    }

    public static String frecciaOFF() {
        return config.getString("itemLore.freccia.off");
    }

    public static String guiName() {
        return config.getString("nomeGUI");
    }
}
