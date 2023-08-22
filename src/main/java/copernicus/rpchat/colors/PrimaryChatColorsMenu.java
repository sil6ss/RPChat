package copernicus.rpchat.colors;

import copernicus.rpchat.*;
import copernicus.rpchat.data.*;
import me.kodysimpson.simpapi.exceptions.*;
import me.kodysimpson.simpapi.menu.*;
import org.bukkit.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;


import java.util.*;
import java.util.regex.*;

public class PrimaryChatColorsMenu extends Menu {
    public PrimaryChatColorsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "§1Primary Chat Colors";
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent) throws MenuManagerNotSetupException, MenuManagerException {


        switch (Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType()) {
            case BLACK_CONCRETE:
                if (p.hasPermission("rpchat.color.black") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.of("#7a7a7a"));
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case BLUE_CONCRETE:
                if (p.hasPermission("rpchat.color.darkblue") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.of("#122ea9"));
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case GREEN_CONCRETE:
                if (p.hasPermission("rpchat.color.darkgreen") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.DARK_GREEN);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case CYAN_CONCRETE:
                if (p.hasPermission("rpchat.color.darkaqua") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.DARK_AQUA);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case RED_CONCRETE:
                if (p.hasPermission("rpchat.color.darkred") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.of("#ad0b0b"));
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case PURPLE_CONCRETE:
                if (p.hasPermission("rpchat.color.darkpurple") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.of("#9a63e2"));
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case ORANGE_CONCRETE_POWDER:
                if (p.hasPermission("rpchat.color.gold") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.GOLD);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case LIGHT_GRAY_CONCRETE:
                if (p.hasPermission("rpchat.color.gray") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.GRAY);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case GRAY_CONCRETE:
                if (p.hasPermission("rpchat.color.darkgray") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.DARK_GRAY);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case BLUE_CONCRETE_POWDER:
                if (p.hasPermission("rpchat.color.blue") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.BLUE);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case LIME_CONCRETE:
                if (p.hasPermission("rpchat.color.green") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.GREEN);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case LIGHT_BLUE_CONCRETE:
                if (p.hasPermission("rpchat.color.aqua") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.AQUA);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case RED_CONCRETE_POWDER:
                if (p.hasPermission("rpchat.color.red") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.RED);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case MAGENTA_CONCRETE:
                if (p.hasPermission("rpchat.color.lightpurple") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.LIGHT_PURPLE);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case YELLOW_CONCRETE:
                if (p.hasPermission("rpchat.color.yellow") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.YELLOW);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case WHITE_CONCRETE:
                if (p.hasPermission("rpchat.color.white") || p.hasPermission("rpchat.color.admin")) {
                    RPChat.getInstance().primaryRoleplayColors.put(p.getUniqueId(), net.md_5.bungee.api.ChatColor.WHITE);
                    MenuManager.openMenu(SecondaryChatColorsMenu.class, p);
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
                break;
            case BARRIER:
                p.closeInventory();
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack black = makeItem(Material.BLACK_CONCRETE, net.md_5.bungee.api.ChatColor.of("#7a7a7a") + "Black", net.md_5.bungee.api.ChatColor.of("#7a7a7a") + "This is Black");
        ItemStack darkBlue = makeItem(Material.BLUE_CONCRETE, net.md_5.bungee.api.ChatColor.of("#122ea9") + "Dark Blue", net.md_5.bungee.api.ChatColor.of("#122ea9") + "This is Dark Blue");
        ItemStack darkGreen = makeItem(Material.GREEN_CONCRETE, "§2Dark Green", "§2This is Dark Green");
        ItemStack darkAqua = makeItem(Material.CYAN_CONCRETE, "§3Dark Aqua", "§3This is Dark Aqua");
        ItemStack darkRed = makeItem(Material.RED_CONCRETE, net.md_5.bungee.api.ChatColor.of("#ad0b0b") + "Dark Red", net.md_5.bungee.api.ChatColor.of("#ad0b0b") + "This is Dark Red");
        ItemStack darkPurple = makeItem(Material.PURPLE_CONCRETE, net.md_5.bungee.api.ChatColor.of("#9a63e2") + "Dark Purple", net.md_5.bungee.api.ChatColor.of("#9a63e2") + "This is Dark Purple");
        ItemStack gold = makeItem(Material.ORANGE_CONCRETE_POWDER, "§6Gold", "§6This is §6Gold");
        ItemStack gray = makeItem(Material.LIGHT_GRAY_CONCRETE, "§7Gray", "§7This is Gray");
        ItemStack darkGray = makeItem(Material.GRAY_CONCRETE, "§8Dark Gray", "§8This is Dark Gray");
        ItemStack blue = makeItem(Material.BLUE_CONCRETE_POWDER, "§9Blue", "§9This is Blue");
        ItemStack green = makeItem(Material.LIME_CONCRETE, "§aGreen", "§aThis is Green");
        ItemStack aqua = makeItem(Material.LIGHT_BLUE_CONCRETE, "§bAqua", "§bThis is Aqua");
        ItemStack red = makeItem(Material.RED_CONCRETE_POWDER, "§cRed", "§cThis is Red");
        ItemStack lightPurple = makeItem(Material.MAGENTA_CONCRETE, "§dLight Purple", "§dThis is Light Purple");
        ItemStack yellow = makeItem(Material.YELLOW_CONCRETE, "§eYellow", "§eThis is Yellow");
        ItemStack white = makeItem(Material.WHITE_CONCRETE, "§fWhite", "§fThis is White");
        ItemStack close  = makeItem(Material.BARRIER, "Close");


        inventory.setItem(9, black);
        inventory.setItem(10, darkBlue);
        inventory.setItem(11, darkGreen);
        inventory.setItem(12, darkAqua);
        inventory.setItem(13, darkRed);
        inventory.setItem(14, darkPurple);
        inventory.setItem(15, gold);
        inventory.setItem(16, gray);
        inventory.setItem(17, darkGray);
        inventory.setItem(18, blue);
        inventory.setItem(19, green);
        inventory.setItem(20, aqua);
        inventory.setItem(21, red);
        inventory.setItem(22, lightPurple);
        inventory.setItem(23, yellow);
        inventory.setItem(24, white);
        inventory.setItem(31, close);
        setFillerGlass();
    }
}
