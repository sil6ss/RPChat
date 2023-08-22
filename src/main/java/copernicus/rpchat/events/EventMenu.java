package copernicus.rpchat.events;

import copernicus.rpchat.data.*;
import me.kodysimpson.simpapi.exceptions.*;
import me.kodysimpson.simpapi.menu.*;
import org.bukkit.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

import java.util.*;

public class EventMenu extends Menu {
    public EventMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Event Menu";
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
        String serverPrefix = ChatColor.translateAlternateColorCodes('&', Config.getConfig().getString("serverPrefix"));
        switch (Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType()) {
            case PAPER: if (p.hasPermission("rpchat.command.event.list"))
                MenuManager.openMenu(ListEvents.class, p.getPlayer());
                break;
            case LAVA_BUCKET:
                if (p.hasPermission("rpchat.command.event.delete") || p.hasPermission("rpchat.admin")) {
                    MenuManager.openMenu(DeleteEventMenu.class, p.getPlayer());
                } else {
                    p.sendMessage(ChatColor.RED + "You have no permission!");
                }
            break;
            case BARRIER: p.getPlayer().closeInventory();
            break;
            case WRITABLE_BOOK: p.sendMessage(serverPrefix + ChatColor.translateAlternateColorCodes('&', "&a/events create <name of event> <permission>"));
            p.sendMessage(serverPrefix + ChatColor.translateAlternateColorCodes('&', "&aThe last word in the command will always be the permission!"));
            p.sendMessage(serverPrefix + ChatColor.translateAlternateColorCodes('&', "&aThe permission will become \"rpchat.event.<permission>\""));
            p.closeInventory();
            break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack create = makeItem(Material.WRITABLE_BOOK, "Create Event");
        ItemStack list = makeItem(Material.PAPER, "List Events");
        ItemStack delete = makeItem(Material.LAVA_BUCKET, "Delete Event");
        ItemStack close = makeItem(Material.BARRIER, "Close");
        inventory.setItem(11, create);
        inventory.setItem(13, list);
        inventory.setItem(15, delete);
        inventory.setItem(31, close);

        setFillerGlass();
    }
}
