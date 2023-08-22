package copernicus.rpchat.data;

import me.kodysimpson.simpapi.command.*;
import net.md_5.bungee.api.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

public class RPChatReloadCommand extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "reloads RPChat Config";
    }

    @Override
    public String getSyntax() {
        return "/rpchat reload";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission("rpchat.command.reload") || commandSender.hasPermission("rpchat.admin")) {
            Config.reload();
            commandSender.sendMessage(ChatColor.GREEN + "Config has been reloaded!");
        } else {
            commandSender.sendMessage(org.bukkit.ChatColor.RED + "You have no permission!");
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
