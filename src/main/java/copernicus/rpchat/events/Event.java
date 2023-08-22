package copernicus.rpchat.events;

import org.bukkit.*;

import java.util.*;

public class Event {
    public String playerName;
    public String description;
    public Date dateCreated;
    public String id;
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;
    public UUID world;
    public String permission;

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public void setWorld(UUID world) {
        this.world = world;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public String getPermission() {
        return "rpchat.event." + permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Event(String playerName, String description, String permission, UUID world, double x, double y, double z, float yaw, float pitch, Date dateCreated) {
        this.playerName = playerName;
        this.description = description;
        this.permission = permission;
        this.dateCreated = new Date();
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.id = UUID.randomUUID().toString();
    }
}
