package copernicus.rpchat.chat;

import copernicus.rpchat.*;
import copernicus.rpchat.data.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

public class StaffChat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("staff") || cmd.getName().equalsIgnoreCase("s")) {
            Player p = (Player) sender;
            String serverPrefix = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("serverPrefix"));
            if (sender instanceof Player) {
                if (p.hasPermission("rpchat.command.staff") || p.hasPermission("rpchat.admin")) {
                    if (!RPChat.getInstance().staffChat.contains(p.getUniqueId())) {
                        RPChat.getInstance().staffChat.add(p.getUniqueId());
                        p.sendMessage(serverPrefix + ChatColor.GREEN + "You have joined staff chat");
                    } else if (RPChat.getInstance().staffChat.contains(p.getUniqueId())) {
                        RPChat.getInstance().staffChat.remove(p.getUniqueId());
                        p.sendMessage(serverPrefix + ChatColor.RED + "You have left staff chat");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
            }
            return true;
        }
        return false;
    }
}
