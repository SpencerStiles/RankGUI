package com.rankgui.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for formatting permission strings nicely.
 */
public class PermissionFormatter {

    /**
     * Formats a permission node into a human-readable string.
     * Examples:
     * rankgui.admin -> Rank Admin
     * server.admin -> Server Admin
     * server.moderate -> Server Moderate
     * * -> All Permissions
     */
    public static String format(String permission) {
        if (permission == null || permission.isEmpty()) {
            return "";
        }

        if (permission.equals("*")) {
            return "All Permissions";
        }

        // Split by dots and capitalize each part
        String[] parts = permission.split("\\.");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            // Special case mappings
            if (part.equalsIgnoreCase("rankgui")) {
                part = "Rank";
            } else {
                // Capitalize first letter
                part = capitalize(part);
            }

            if (i > 0) {
                result.append(" ");
            }
            result.append(part);
        }

        return result.toString();
    }

    /**
     * Formats a list of permissions into a comma-separated string.
     */
    public static String formatList(List<String> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return "None";
        }

        return permissions.stream()
                .map(PermissionFormatter::format)
                .collect(Collectors.joining(", "));
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
