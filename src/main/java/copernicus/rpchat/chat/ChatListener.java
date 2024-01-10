package copernicus.rpchat.chat;

import copernicus.rpchat.*;
import copernicus.rpchat.data.*;
import me.kodysimpson.simpapi.colors.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

import java.util.*;

public class ChatListener implements Listener {


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        net.md_5.bungee.api.ChatColor primaryColor = net.md_5.bungee.api.ChatColor.DARK_PURPLE;
        net.md_5.bungee.api.ChatColor secondaryColor = net.md_5.bungee.api.ChatColor.WHITE;

        int localChatRadius = Config.getConfig().getInt("local-chat-radius");
        String localPrefix = ColorTranslator.translateColorCodes(Config.getConfig().getString("localPrefix"));
        String roleplayPrefix = ColorTranslator.translateColorCodes(Config.getConfig().getString("roleplayPrefix"));
        String staffFormat = ColorTranslator.translateColorCodes(Config.getConfig().getString("staffFormat"));


        if (RPChat.getInstance().primaryRoleplayColors.containsKey(playerUUID)) {
            primaryColor = RPChat.getInstance().primaryRoleplayColors.get(playerUUID);
        }
        if (RPChat.getInstance().secondaryRoleplayColors.containsKey(playerUUID)) {
            secondaryColor = RPChat.getInstance().secondaryRoleplayColors.get(playerUUID);
        }

        if (!RPChat.getInstance().localChat.contains(playerUUID) && !RPChat.getInstance().staffChat.contains(playerUUID) && RPChat.getInstance().globalChat.contains(playerUUID)) {
            event.setFormat(player.getDisplayName() + ": " + event.getMessage());
            event.getRecipients().removeIf(p -> !RPChat.getInstance().globalChat.contains(p.getUniqueId()));
        }

        if (!RPChat.getInstance().localChat.contains(playerUUID) && RPChat.getInstance().staffChat.contains((playerUUID))) {
            event.setFormat(staffFormat  + player.getName() + ": " + event.getMessage());
            event.getRecipients().removeIf(p -> !RPChat.getInstance().staffChat.contains(p.getUniqueId()));
        }
        if (RPChat.getInstance().localChat.contains(player.getUniqueId())) {
            // Player is using local chat
            String message = event.getMessage();
            if (message.startsWith("+")) {
                event.setFormat(roleplayPrefix + ChatColor.RESET + player.getDisplayName() + primaryColor + ChatColor.ITALIC + " " + event.getMessage().substring(1));
            } else if (message.startsWith("\"")) {
                // Roleplay action
                event.setFormat(roleplayPrefix + ChatColor.RESET + player.getDisplayName() + primaryColor + ChatColor.ITALIC + " says, " + ChatColor.RESET + secondaryColor + event.getMessage() + "\"");
            } else if (message.startsWith("!")) {
                event.setFormat(roleplayPrefix + ChatColor.RESET + player.getDisplayName() + primaryColor + ChatColor.ITALIC + "shouts, " + ChatColor.RESET + secondaryColor + "\"" + event.getMessage().substring(1) + "\"");
            } else if (message.startsWith("*")) {
                event.setFormat(roleplayPrefix + ChatColor.RESET + player.getDisplayName() + primaryColor + ChatColor.ITALIC + "whispers," + ChatColor.RESET + secondaryColor + "\"" + event.getMessage().substring(1) + "\"");
            } else if (message.startsWith("=")) {
                event.setFormat(roleplayPrefix + ChatColor.RESET + player.getDisplayName() + primaryColor + ChatColor.ITALIC + "thinks " + ChatColor.RESET + secondaryColor + "|| " + event.getMessage().substring(1) + " ||");
            } else {
                // Regular message
                event.setFormat(localPrefix + ChatColor.GRAY + player.getName() + ": " + event.getMessage());
            }

            // Only send message to nearby players
            event.getRecipients().removeIf(p -> p.getLocation().distance(player.getLocation()) > localChatRadius);

        }
        if (!RPChat.getInstance().localChat.contains(playerUUID) && !RPChat.getInstance().globalChat.contains(playerUUID) && !RPChat.getInstance().staffChat.contains(playerUUID)) {
            event.getRecipients().removeAll(Bukkit.getOnlinePlayers());
            player.sendMessage(net.md_5.bungee.api.ChatColor.RED + "You are not in a chat channel!");
        }
    }
}
