package copernicus.rpchat.events;

import me.kodysimpson.simpapi.exceptions.*;
import me.kodysimpson.simpapi.menu.*;
import org.bukkit.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import java.util.*;

public class EventTeleportMenu extends Menu {
    public EventTeleportMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Confirm: Event Teleport?";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override @SuppressWarnings("deprecation ")
    public void handleMenu(InventoryClickEvent inventoryClickEvent) throws MenuManagerNotSetupException, MenuManagerException {
        PMU pmu = new PMU(p);
        switch (Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType()) {
            case RED_BANNER: MenuManager.openMenu(ListEvents.class, p.getPlayer());
            break;
            case GREEN_BANNER:
                    String eventID = PMU.getEventToTeleport();
                    if (p.hasPermission(EventsManager.findEvent(eventID).getPermission())) {
                        p.teleport(EventsManager.findEvent(eventID).getLocation());
                        p.closeInventory();
                        p.sendMessage(ChatColor.GREEN + "Teleport Successful!");
                    } else {
                        p.sendMessage(ChatColor.RED + "You have no permission!");
                        MenuManager.openMenu(ListEvents.class, p);
                    }
                    break;

        }
    }

    @Override
    public void setMenuItems() {
        ItemStack yes = makeItem(Material.GREEN_BANNER, "Teleport To Event");
        ItemStack no = makeItem(Material.RED_BANNER, "Nevermind");
        inventory.setItem(3, yes);
        inventory.setItem(5, no);
        setFillerGlass();
    }
}
