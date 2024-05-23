package it.samuconfaa.pvpantidrop;

import org.bukkit.plugin.java.JavaPlugin;

public final class PvPAntiDrop extends JavaPlugin {

    private GUIListener guiListener;
    private ConfigurationManager configManager;

    @Override
    public void onEnable() {
        guiListener = new GUIListener(this);
        getCommand("drop").setExecutor(new DropCommand(this));
        getServer().getPluginManager().registerEvents(guiListener, this);
        configManager = new ConfigurationManager(this);
        configManager.loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public GUIListener getGuiListener() {
        return guiListener;
    }
}
