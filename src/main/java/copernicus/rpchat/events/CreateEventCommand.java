package copernicus.rpchat.events;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

public class CreateEventCommand extends SubCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Create a new event";
    }

    @Override
    public String getSyntax() {
        return "/event create <message>";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission("rpchat.command.event.create") || commandSender.hasPermission("rpchat.admin")) {
            if (strings.length > 1) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 1; i < strings.length - 2; i++) {
                    stringBuilder.append(strings[i]).append(" ");
                }
                stringBuilder.append(strings[strings.length - 2]);

                EventsManager.createEvent((Player) commandSender, stringBuilder.toString(), strings[strings.length - 1], ((Player) commandSender).getWorld().getUID(), ((Player) commandSender).getLocation().getX(), ((Player) commandSender).getLocation().getY(), ((Player) commandSender).getLocation().getZ(), ((Player) commandSender).getLocation().getYaw(), ((Player) commandSender).getLocation().getPitch());
                commandSender.sendMessage(ChatColor.GREEN + "Event \"" + stringBuilder.toString() + "\" Created!");
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
