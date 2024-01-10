package copernicus.rpchat.chat;

import copernicus.rpchat.*;
import copernicus.rpchat.data.*;
import me.kodysimpson.simpapi.colors.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

public class ChatCommands implements CommandExecutor {
    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        String serverPrefix = ColorTranslator.translateColorCodes(Config.getConfig().getString("serverPrefix"));
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("local")) {
            // Player wants to join local chat
            if (player.hasPermission("rpchat.commnand.local") || player.hasPermission("rpchat.admin")) {
                if (RPChat.getInstance().localChat.contains(player.getUniqueId())) {
                    RPChat.getInstance().localChat.remove(player.getUniqueId());
                    player.sendMessage(serverPrefix + ChatColor.GREEN + "You have left Local Chat");
                    return true;
                } else {
                    RPChat.getInstance().localChat.add(player.getUniqueId());
                    player.sendMessage(serverPrefix + ChatColor.GREEN + "You joined Local chat.");
                    return true;
                }

            } else {
                player.sendMessage(ChatColor.RED + "You have no permission!");
                return true;
            }
        }

            if (cmd.getName().equalsIgnoreCase("global")) {
                // Player wants to join global chat
                if (player.hasPermission("rpchat.command.global") || player.hasPermission("rpchat.admin")) {
                    if (RPChat.getInstance().globalChat.contains(player.getUniqueId())) {
                        RPChat.getInstance().globalChat.remove(player.getUniqueId());
                        player.sendMessage(serverPrefix + ChatColor.GREEN + "You have left Global Chat");
                        return true;
                    } else {
                        RPChat.getInstance().globalChat.add(player.getUniqueId());
                        player.sendMessage(serverPrefix + ChatColor.GREEN + "You joined Global chat.");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You have no permission!");
                    return true;
                }

            }
        return false;
    }
}