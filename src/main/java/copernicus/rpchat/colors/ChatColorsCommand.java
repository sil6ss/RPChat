package copernicus.rpchat.colors;

import copernicus.rpchat.events.*;
import me.kodysimpson.simpapi.command.*;
import me.kodysimpson.simpapi.exceptions.*;
import me.kodysimpson.simpapi.menu.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

public class ChatColorsCommand extends SubCommand {
    @Override
    public String getName() {
        return "colors";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "opens the chat colors selection GUI";
    }

    @Override
    public String getSyntax() {
        return "/rpchat colors";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission("rpchat.command.colors") || commandSender.hasPermission("rpchat.adminaaa")) {
            try {
                MenuManager.openMenu(PrimaryChatColorsMenu.class, (Player) commandSender);
            } catch (MenuManagerException e) {
                throw new RuntimeException(e);
            } catch (MenuManagerNotSetupException e) {
                throw new RuntimeException(e);
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + "You have no permission!");
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
