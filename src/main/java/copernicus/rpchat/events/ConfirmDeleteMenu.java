package copernicus.rpchat.events;

import me.kodysimpson.simpapi.exceptions.*;
import me.kodysimpson.simpapi.menu.*;
import org.bukkit.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

import java.util.*;

public class ConfirmDeleteMenu extends Menu {
    public ConfirmDeleteMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Confirm: Delete Event?";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent) throws MenuManagerNotSetupException, MenuManagerException {
        switch (Objects.requireNonNull(inventoryClickEvent.getCurrentItem()).getType()) {
            case RED_BANNER: MenuManager.openMenu(DeleteEventMenu.class, p.getPlayer());
            break;
            case GREEN_BANNER:
                    String eventID = PMU.getEventToDelete();
                    EventsManager.deleteEvent(eventID);
                    p.sendMessage(ChatColor.GREEN + "Event has been Deleted!");
                    MenuManager.openMenu(DeleteEventMenu.class, p);
                break;
        }

    }

    @Override
    public void setMenuItems() {
        ItemStack yes = makeItem(Material.GREEN_BANNER, "Delete Event");
        ItemStack no = makeItem(Material.RED_BANNER, "Nevermind");
        inventory.setItem(3, yes);
        inventory.setItem(5, no);
        setFillerGlass();
    }
}
