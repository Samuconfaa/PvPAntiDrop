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
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            plugin.getGuiListener().openGui(player);
            return true;
        }
        return false;
    }
}
