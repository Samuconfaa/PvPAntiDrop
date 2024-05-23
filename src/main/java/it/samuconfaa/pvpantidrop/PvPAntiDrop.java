package it.samuconfaa.pvpantidrop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import it.samuconfaa.pvpantidrop.GUIListener.*;

public final class PvPAntiDrop extends JavaPlugin {

    private GUIListener guiListener;
    private ConfigurationManager configManager;

    @Override
    public void onEnable() {
        instance = this;
        guiListener = new GUIListener(this);
        getCommand("drop").setExecutor(new Command(this));
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
