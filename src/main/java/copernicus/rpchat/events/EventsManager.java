package copernicus.rpchat.events;

import com.google.gson.*;
import copernicus.rpchat.*;
import org.bukkit.entity.*;


import java.io.*;
import java.util.*;

public class EventsManager {

    public static ArrayList<Event> events = new ArrayList<>();

    public static Event createEvent(Player p, String message, String permission, UUID world, double x, double y, double z, float yaw, float pitch) {
        Event event = new Event(p.getName(), message, permission, world, x, y, z, yaw, pitch, new Date());
        events.add(event);
        try {
            saveEvents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return event;
    }

    public static void deleteEvent(String id) {
        for (Event event : events) {
            if (event.getId().equalsIgnoreCase(id)) {
                events.remove(event);
                break;
            }
        }
        try {
            saveEvents();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Event findEvent(String id) {
        for (Event event : events) {
            if (event.getId().equalsIgnoreCase(id)) {
                return event;
            }
        }
        return null;
    }

    public static Event updateEvent (String id, Event newEvent) {
        for (Event event : events) {
            if (event.getId().equalsIgnoreCase(id)) {
                event.setPlayerName(newEvent.playerName);
                event.setDescription(newEvent.description);
                event.setWorld(newEvent.world);
                event.setX(newEvent.x);
                event.setY(newEvent.y);
                event.setZ(newEvent.z);
                event.setYaw(newEvent.yaw);
                event.setPitch(newEvent.pitch);
                try {
                    saveEvents();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return event;
            }
        }
        return null;
    }

    public static void saveEvents() throws IOException {
        Gson gson = new Gson();
        File file = new File(RPChat.getInstance().getDataFolder().getAbsolutePath() + "/events.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(events, writer);
        writer.flush();
        writer.close();
        RPChat.getInstance().getLogger().info("Events have been saved!");

    }

    public static List<Event> findAllEvents() {
        return events;
    }
    public static void loadEvents() throws IOException {
        Gson gson = new Gson();
        File file = new File(RPChat.getInstance().getDataFolder().getAbsolutePath() + "/events.json");
        if (file.exists()) {
            Reader reader = new FileReader(file);
            Event[] e = gson.fromJson(reader, Event[].class);
            events = new ArrayList<>(Arrays.asList(e));
            RPChat.getInstance().getLogger().info("Events Loaded");
        }
    }
}
