package it.samuconfaa.pvpantidrop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class GUIListener implements Listener {

    private final PvPAntiDrop plugin;
    private final Map<Player, Boolean> goldenApplePickupStatus = new HashMap<>();
    private final Map<Player, Boolean> cannaPickupStatus = new HashMap<>();
    private final Map<Player, Boolean> arrowPickupStatus = new HashMap<>();

    public GUIListener(PvPAntiDrop plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory inventory = e.getInventory();
        if (inventory.getHolder() == null && ConfigurationManager.guiName().equals(inventory.getName())) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
            int slot = e.getRawSlot();
            if (slot == ConfigurationManager.melePos()) {
                boolean isEnabledMela = goldenApplePickupStatus.getOrDefault(player, true);
                goldenApplePickupStatus.put(player, !isEnabledMela);
                openGui(player);
                player.sendMessage(isEnabledMela ? ConfigurationManager.meleOFF() : ConfigurationManager.meleON());
            } else if (slot == ConfigurationManager.frecciaPos()) {
                boolean isEnabledFreccia = arrowPickupStatus.getOrDefault(player, true);
                arrowPickupStatus.put(player, !isEnabledFreccia);
                openGui(player);
                player.sendMessage(isEnabledFreccia ? ConfigurationManager.frecciaOFF() : ConfigurationManager.frecciaON());
            } else if (slot == ConfigurationManager.cannaPos()) {
                boolean isEnabledCanna = cannaPickupStatus.getOrDefault(player, true);
                cannaPickupStatus.put(player, !isEnabledCanna);
                openGui(player);
                player.sendMessage(isEnabledCanna ? ConfigurationManager.cannaOFF() : ConfigurationManager.cannaON());
            }
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem().getItemStack();

        if (item.getType() == Material.GOLDEN_APPLE && !goldenApplePickupStatus.getOrDefault(player, true)) {
            event.setCancelled(true);
        } else if (item.getType() == Material.ARROW && !arrowPickupStatus.getOrDefault(player, true)) {
            event.setCancelled(true);
        } else if (item.getType() == Material.FISHING_ROD && !cannaPickupStatus.getOrDefault(player, true)) {
            event.setCancelled(true);
        }
    }

    public void openGui(Player player) {
        Inventory gui = Bukkit.createInventory(null, ConfigurationManager.altezzaGUI(), ConfigurationManager.guiName());

        ItemStack mele = createItem(Material.GOLDEN_APPLE, goldenApplePickupStatus.getOrDefault(player, true) ? ConfigurationManager.meleON() : ConfigurationManager.meleOFF());
        gui.setItem(ConfigurationManager.melePos(), mele);

        ItemStack freccia = createItem(Material.ARROW, arrowPickupStatus.getOrDefault(player, true) ? ConfigurationManager.frecciaON() : ConfigurationManager.frecciaOFF());
        gui.setItem(ConfigurationManager.frecciaPos(), freccia);

        ItemStack canna = createItem(Material.FISHING_ROD, cannaPickupStatus.getOrDefault(player, true) ? ConfigurationManager.cannaON() : ConfigurationManager.cannaOFF());
        gui.setItem(ConfigurationManager.cannaPos(), canna);

        ItemStack vetro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) ConfigurationManager.glassColor());
        ItemMeta meta = vetro.getItemMeta();
        meta.setDisplayName(ConfigurationManager.glass());
        vetro.setItemMeta(meta);

        for (int i = 0; i < ConfigurationManager.altezzaGUI() - 1; i++) {
            if (i != ConfigurationManager.melePos() && i != ConfigurationManager.frecciaPos() && i != ConfigurationManager.cannaPos()) {
                gui.setItem(i, vetro);
            }
        }

        player.openInventory(gui);
    }

    private ItemStack createItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}
