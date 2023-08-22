package copernicus.rpchat;
import copernicus.rpchat.chat.*;
import copernicus.rpchat.colors.*;
import copernicus.rpchat.data.*;
import copernicus.rpchat.events.*;
import me.kodysimpson.simpapi.command.*;
import me.kodysimpson.simpapi.menu.*;
import net.md_5.bungee.api.*;
import org.bukkit.command.*;
import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.*;

public class RPChat extends JavaPlugin implements Listener, CommandExecutor {
    public Set<UUID> localChat;
    public Set<UUID> globalChat;
    public Set<UUID> staffChat;
    public Map<UUID, net.md_5.bungee.api.ChatColor> primaryRoleplayColors;
    public Map<UUID, ChatColor> secondaryRoleplayColors;

    public static final String FILE_NAME = "data.json";
    static RPChat instance;
    public ChatListener ChatListener;
    public StaffChat StaffChat;
    public DataLoader dataLoader;
    ChatCommands chatCommands;
    public RPChat() {
        primaryRoleplayColors = new HashMap<UUID, net.md_5.bungee.api.ChatColor>();
        secondaryRoleplayColors = new HashMap<UUID, net.md_5.bungee.api.ChatColor>();
        // Initialize the set to store players using local chat
        localChat = new HashSet<>();
        staffChat = new HashSet<>();

        ChatListener = new ChatListener();
        StaffChat = new StaffChat();
        dataLoader = new DataLoader();
        chatCommands = new ChatCommands();
        instance = this;
    }


    @Override
    public void onEnable() {
        Config.loadConfiguration();
        Config.getConfig().options().copyDefaults(true);
        try {
            Config.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataLoader.loadData();
        // Register the event listener and command executor
        getServer().getPluginManager().registerEvents(instance, instance);
        getServer().getPluginManager().registerEvents(ChatListener, instance);
        getCommand("global").setExecutor(chatCommands);
        getCommand("local").setExecutor(chatCommands);
        getCommand("staff").setExecutor(StaffChat);
        try {
            CommandManager.createCoreCommand(this, "events", "Create and list events", "/events", new CommandList() {
                @Override
                public void displayCommandList(CommandSender commandSender, List<SubCommand> list) {
                    commandSender.sendMessage("----------------------------------");
                    for (SubCommand subCommand : list) {
                        commandSender.sendMessage(subCommand.getSyntax() + " - " + subCommand.getDescription());
                    }
                    commandSender.sendMessage("----------------------------------");
                }
            }, CreateEventCommand.class, EventMenuCommand.class);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            CommandManager.createCoreCommand(this, "rpchat", "Anything to do with RPChat!", "/rpchat", new CommandList() {
                @Override
                public void displayCommandList(CommandSender commandSender, List<SubCommand> list) {
                    commandSender.sendMessage("----------------------------------");
                    for (SubCommand subCommand : list) {
                        commandSender.sendMessage(subCommand.getSyntax() + " - " + subCommand.getDescription());
                    }
                    commandSender.sendMessage("----------------------------------");
                }
            }, ChatColorsCommand.class, RPChatReloadCommand.class, RollCommand.class);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        MenuManager.setup(getServer(), this);
        try {
            EventsManager.loadEvents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void onDisable() {
        // Cleanup resources if needed
        try {
            EventsManager.saveEvents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataLoader.saveData();
    }
    public static RPChat getInstance(){
        return instance;
    }
}