package copernicus.rpchat.chat;

import me.kodysimpson.simpapi.command.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

public class RollCommand extends SubCommand {

    @Override
    public String getName() {
        return "roll";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "rolls a number between 0 and specified number";
    }

    @Override
    public String getSyntax() {
        return "/rpchat roll <number>";
    }

    @Override
    public void perform(CommandSender commandSender, String[] strings) {
        if (commandSender.hasPermission("rpchat.command.roll") || commandSender.hasPermission("rpchat.admin")) {
            Random random = new Random();
            commandSender.sendMessage(ChatColor.GREEN + "You rolled a " + random.nextInt(0, Integer.parseInt(strings[strings.length - 1])) + "!");
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
