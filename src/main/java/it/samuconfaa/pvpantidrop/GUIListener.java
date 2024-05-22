package it.samuconfaa.pvpantidrop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class GUIListener implements Listener {

    int melePos = ConfigurationManager.melePos();
    int cannaPos = ConfigurationManager.cannaPos();
    int frecciaPos = ConfigurationManager.frecciaPos();
    private final Map<Player, Boolean> goldenApplePickupStatus = new HashMap<>();
    private final Map<Player, Boolean> cannaPickupStatus = new HashMap<>();
    private final Map<Player, Boolean> arrowPickupStatus = new HashMap<>();

    private final JavaPlugin plugin;

    public GUIListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Inventory inventory = e.getInventory();
        if (inventory.getHolder() == null && "Scelta Drops".equals(inventory.getName())) {
            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();
            if (e.getRawSlot() == melePos) {
                boolean isEnabledMela = goldenApplePickupStatus.getOrDefault(player, true);
                goldenApplePickupStatus.put(player, !isEnabledMela);
                openGui(player);
                player.sendMessage(isEnabledMela ? ConfigurationManager.meleOFF() : ConfigurationManager.meleON());
            } else if (e.getRawSlot() == frecciaPos) {
                boolean isEnabledFreccia = arrowPickupStatus.getOrDefault(player, true);
                arrowPickupStatus.put(player, !isEnabledFreccia);
                openGui(player);
                player.sendMessage(isEnabledFreccia ? ConfigurationManager.frecciaOFF() : ConfigurationManager.frecciaON());
            } else if (e.getRawSlot() == cannaPos) {
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

        if (item.getType() == Material.GOLDEN_APPLE) {
            boolean isEnabledMele = goldenApplePickupStatus.getOrDefault(player, true);
            if (!isEnabledMele) {
                event.setCancelled(true);
            }
        }
        if (item.getType() == Material.ARROW) {
            boolean isEnabledFreccia = arrowPickupStatus.getOrDefault(player, true);
            if (!isEnabledFreccia) {
                event.setCancelled(true);
            }
        }
        if (item.getType() == Material.FISHING_ROD) {
            boolean isEnabledCanna = cannaPickupStatus.getOrDefault(player, true);
            if (!isEnabledCanna) {
                event.setCancelled(true);
            }
        }
    }

    public void openGui(Player player) {

        Inventory gui = Bukkit.createInventory(null, 27, "Scelta Drops");

        boolean isEnabledMela = goldenApplePickupStatus.getOrDefault(player, true);
        ItemStack mele = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta meta1 = mele.getItemMeta();
        meta1.setDisplayName(isEnabledMela ? ConfigurationManager.meleON() : ConfigurationManager.meleOFF());
        mele.setItemMeta(meta1);
        gui.setItem(melePos, mele);

        boolean isEnabledFreccia = arrowPickupStatus.getOrDefault(player, true);
        ItemStack freccia = new ItemStack(Material.ARROW);
        ItemMeta meta2 = freccia.getItemMeta();
        meta2.setDisplayName(isEnabledFreccia ? ConfigurationManager.frecciaON() : ConfigurationManager.frecciaOFF());
        freccia.setItemMeta(meta2);
        gui.setItem(frecciaPos, freccia);

        boolean isEnabledCanna = cannaPickupStatus.getOrDefault(player, true);
        ItemStack canna = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta3 = canna.getItemMeta();
        meta3.setDisplayName(isEnabledCanna ? ConfigurationManager.cannaON() : ConfigurationManager.cannaOFF());
        canna.setItemMeta(meta3);
        gui.setItem(cannaPos, canna);

        ItemStack vetro = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
        ItemMeta meta = vetro.getItemMeta();
        meta.setDisplayName(ConfigurationManager.glass());
        vetro.setItemMeta(meta);


        for (int i = 0; i < 27; i++) {
            if (i != melePos && i != frecciaPos && i != cannaPos) {
                gui.setItem(i, vetro);
            }
        }

        player.openInventory(gui);
    }
}
