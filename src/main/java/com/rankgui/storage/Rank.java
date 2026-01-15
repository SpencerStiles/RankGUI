package com.rankgui.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rank with a name, prefix, and permissions.
 */
public class Rank {
    private String name;
    private String prefix;
    private List<String> permissions;
    private int priority;

    public Rank() {
        this.permissions = new ArrayList<>();
        this.priority = 0;
    }

    public Rank(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
        this.permissions = new ArrayList<>();
        this.priority = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void addPermission(String permission) {
        if (!permissions.contains(permission)) {
            permissions.add(permission);
        }
    }

    public void removePermission(String permission) {
        permissions.remove(permission);
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
