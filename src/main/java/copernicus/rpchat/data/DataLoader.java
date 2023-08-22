package copernicus.rpchat.data;

import copernicus.rpchat.*;
import org.bukkit.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;

public class DataLoader {
    public void loadData() {
        File dataFile = new File(RPChat.getInstance().getDataFolder(), "data.json");
        if (dataFile.exists()) {
            JSONParser parser = new JSONParser();
            try (FileReader reader = new FileReader(dataFile)) {
                JSONObject jsonData = (JSONObject) parser.parse(reader);

                RPChat.getInstance().primaryRoleplayColors = loadUUIDChatColorMap(jsonData, "primaryRoleplayColors");
                RPChat.getInstance().secondaryRoleplayColors = loadUUIDChatColorMap(jsonData, "secondaryRoleplayColors");
                RPChat.getInstance().localChat = loadUUIDSet(jsonData, "localChat");
                RPChat.getInstance().globalChat = loadUUIDSet(jsonData, "globalChat");
                RPChat.getInstance().staffChat = loadUUIDSet(jsonData, "staffChat");
            } catch (IOException | ParseException e) {
                RPChat.getInstance().getLogger().warning("Failed to load data from file: " + dataFile.getName());
                RPChat.getInstance().primaryRoleplayColors = new HashMap<UUID, net.md_5.bungee.api.ChatColor>();
                RPChat.getInstance().secondaryRoleplayColors = new HashMap<UUID, net.md_5.bungee.api.ChatColor>();
                RPChat.getInstance().localChat = new HashSet<>();
                RPChat.getInstance().globalChat = new HashSet<>();
                RPChat.getInstance().staffChat = new HashSet<>();
            }
        } else {
            RPChat.getInstance().getLogger().warning("Data file does not exist: " + dataFile.getName());
            RPChat.getInstance().primaryRoleplayColors = new HashMap<UUID, net.md_5.bungee.api.ChatColor>();
            RPChat.getInstance().secondaryRoleplayColors = new HashMap<UUID, net.md_5.bungee.api.ChatColor>();
            RPChat.getInstance().localChat = new HashSet<>();
            RPChat.getInstance().globalChat = new HashSet<>();
            RPChat.getInstance().staffChat = new HashSet<>();
        }
    }
    public Map<UUID, net.md_5.bungee.api.ChatColor> loadUUIDChatColorMap(JSONObject jsonData, String key) {
        Map<UUID, net.md_5.bungee.api.ChatColor> map = new HashMap<>();
        JSONArray jsonArray = (JSONArray) jsonData.getOrDefault(key, new JSONArray());
        for (Object obj : jsonArray) {
            JSONObject entry = (JSONObject) obj;
            UUID uuid = UUID.fromString((String) entry.get("uuid"));
            net.md_5.bungee.api.ChatColor color = net.md_5.bungee.api.ChatColor.of((String) entry.get("color"));
            map.put(uuid, color);
        }
        return map;
    }
    public Set<UUID> loadUUIDSet(JSONObject jsonData, String key) {
        Set<UUID> set = new HashSet<>();
        JSONArray jsonArray = (JSONArray) jsonData.getOrDefault(key, new JSONArray());
        for (Object obj : jsonArray) {
            UUID uuid = UUID.fromString((String) obj);
            set.add(uuid);
        }
        return set;
    }
    public JSONArray saveUUIDChatColorMap(Map<UUID, net.md_5.bungee.api.ChatColor> map) {
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<UUID, net.md_5.bungee.api.ChatColor> entry : map.entrySet()) {
            JSONObject entryObj = new JSONObject();
            entryObj.put("uuid", entry.getKey().toString());
            entryObj.put("color", entry.getValue().name());
            jsonArray.add(entryObj);
        }
        return jsonArray;
    }
    public JSONArray saveUUIDSet(Set<UUID> set) {
        JSONArray jsonArray = new JSONArray();
        for (UUID uuid : set) {
            jsonArray.add(uuid.toString());
        }
        return jsonArray;
    }
    public void saveData() {
        File dataFile = new File(RPChat.getInstance().getDataFolder(), "data.json");
        JSONObject jsonData = new JSONObject();
        jsonData.put("primaryRoleplayColors", saveUUIDChatColorMap(RPChat.getInstance().primaryRoleplayColors));
        jsonData.put("secondaryRoleplayColors", saveUUIDChatColorMap(RPChat.getInstance().secondaryRoleplayColors));
        jsonData.put("localChat", saveUUIDSet(RPChat.getInstance().localChat));
        jsonData.put("globalChat", saveUUIDSet(RPChat.getInstance().globalChat));
        jsonData.put("staffChat", saveUUIDSet(RPChat.getInstance().staffChat));

        try (FileWriter writer = new FileWriter(dataFile)) {
            writer.write(jsonData.toJSONString());
        } catch (IOException e) {
            RPChat.getInstance().getLogger().warning("Failed to save data to file: " + dataFile.getName());
        }
    }
}
