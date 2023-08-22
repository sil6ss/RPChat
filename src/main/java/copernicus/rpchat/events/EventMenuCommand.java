package copernicus.rpchat.events;

import me.kodysimpson.simpapi.command.*;
import me.kodysimpson.simpapi.exceptions.*;
import me.kodysimpson.simpapi.menu.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

public class EventMenuCommand extends SubCommand {
    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Open up the Event Menu";
    }

    @Override
    public String getSyntax() {
        return "/event menu";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission("rpchat.command.event.admin") || commandSender.hasPermission("rpchat.admin")) {
            try {
                MenuManager.openMenu(EventMenu.class, (Player) commandSender);
            } catch (MenuManagerException e) {
                throw new RuntimeException(e);
            } catch (MenuManagerNotSetupException e) {
                throw new RuntimeException(e);
            }
        } else if (commandSender.hasPermission("rpchat.command.event.list")) {
            try {
                MenuManager.openMenu(ListEvents.class, (Player) commandSender);
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
