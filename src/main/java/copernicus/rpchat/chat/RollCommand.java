package copernicus.rpchat.chat;

import copernicus.rpchat.data.*;
import me.kodysimpson.simpapi.command.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;

import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


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
            if (strings.length < 2) {
                commandSender.sendMessage(ChatColor.RED + "Usage: " + getSyntax());
                return;
            }

            int maxNumber = Integer.parseInt(strings[1]);
            if (strings[1] == null) {
                maxNumber = 1;
            }
            if (maxNumber < 1 ) {
                commandSender.sendMessage(ChatColor.RED + "Please specify a number greater than 1.");
                return;
            }

            int rolledNumber = (int)(Math.random() * maxNumber);
            commandSender.sendMessage(ChatColor.GREEN + "You rolled a " + rolledNumber + "!");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getLocation().distance(((Player) commandSender).getLocation()) <= Config.getConfig().getInt("local-chat-radius") && !p.equals(commandSender)) {
                    p.sendMessage(ChatColor.GREEN + commandSender.getName() + " rolled a " + rolledNumber + "!");
                }
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] strings) {
        return null;
    }
}
