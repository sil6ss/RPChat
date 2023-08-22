package copernicus.rpchat.events;

import copernicus.rpchat.*;
import me.kodysimpson.simpapi.exceptions.*;
import me.kodysimpson.simpapi.menu.*;
import org.bukkit.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.persistence.*;

import java.util.*;

public class ListEvents extends Menu {
    public ListEvents(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "List of Events";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent) throws MenuManagerNotSetupException, MenuManagerException {
        PMU pmu = new PMU(p.getPlayer());

        if (Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType() == Material.BARRIER) {
            if (p.hasPermission("rpchat.command.event.delete") || p.hasPermission("rpchat.admin")) {
                MenuManager.openMenu(EventMenu.class, p);
            } else {
                p.closeInventory();
            }

        } else if (Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType() == Material.PAPER) {
            PersistentDataContainer container = inventoryClickEvent.getCurrentItem().getItemMeta().getPersistentDataContainer();
            String eventID = container.get(new NamespacedKey(RPChat.getInstance(), "eventID"), PersistentDataType.STRING);
            if (p.hasPermission(EventsManager.findEvent(eventID).getPermission()) || p.hasPermission("rpchat.admin")) {
                pmu.setEventToTeleport(eventID);
                MenuManager.openMenu(EventTeleportMenu.class, p);
            } else {
                p.sendMessage(ChatColor.RED + "You have no permission!");
            }
        }
    }

    @Override
    public void setMenuItems() {
        List<Event> events = EventsManager.findAllEvents();
        for (Event event : events) {
            ItemStack item = makeItem(Material.PAPER, event.getDescription(), "Made by: " + event.getPlayerName(), "Created at: " + event.getDateCreated());
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.getPersistentDataContainer().set(new NamespacedKey(RPChat.getInstance(), "eventID"), PersistentDataType.STRING, event.getId());
            item.setItemMeta(itemMeta);
            if (p.getPlayer().hasPermission(event.getPermission()) || p.getPlayer().hasPermission("rpchat.admin")) {
                inventory.addItem(item);
            }
        }
        ItemStack close  = makeItem(Material.BARRIER, "Close");
        inventory.setItem(49, close);
    }
}
