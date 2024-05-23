package it.samuconfaa.pvpantidrop;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DropCommand implements CommandExecutor {
    private final PvPAntiDrop plugin;


    public DropCommand(PvPAntiDrop plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length == 1) {
            if (commandSender.hasPermission("drop.reload")) {
                ConfigurationManager.reloadConfig();
                commandSender.sendMessage(ConfigurationManager.reload());
            } else {
                commandSender.sendMessage(ConfigurationManager.noPerms());
            }
        } else if (strings.length == 0) {
            if (commandSender.hasPermission("drop.gui")) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    plugin.getGuiListener().openGui(player);
                    return true;
                }
            } else {
                commandSender.sendMessage(ConfigurationManager.noPerms());
            }

            return true;
        }
        return false;
    }
}
