package copernicus.rpchat.events;

import org.bukkit.entity.*;

public class PMU extends me.kodysimpson.simpapi.menu.PlayerMenuUtility {
    private static String eventToDelete;
    private static String eventToTeleport;

    public static String getEventToTeleport() {
        return PMU.eventToTeleport;
    }

    public void setEventToTeleport(String eventToTeleport) {
        PMU.eventToTeleport = eventToTeleport;
    }

    public static String getEventToDelete() {
        return PMU.eventToDelete;
    }

    public void setEventToDelete(String eventToDelete) {
        PMU.eventToDelete = eventToDelete;
    }

    public PMU(Player p) {
        super(p);
    }
}
