package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;

import java.util.concurrent.CompletableFuture;

/**
 * /rank listperms - Shows all available permissions that can be added to ranks
 */
public class RankListPermsCommand extends AbstractAsyncCommand {

    public RankListPermsCommand() {
        super("listperms", "List all available permissions");
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            context.sender().sendMessage(Message.raw("=== Available Permissions ===").color("gold"));

            context.sender().sendMessage(Message.raw("--- Special ---").color("yellow"));
            context.sender().sendMessage(Message.raw("  * - All Permissions (full access)").color("gray"));

            context.sender().sendMessage(Message.raw("--- Rank Management ---").color("yellow"));
            context.sender().sendMessage(Message.raw("  rankgui.admin - Manage ranks and permissions").color("gray"));

            context.sender().sendMessage(Message.raw("--- Server Administration ---").color("yellow"));
            context.sender().sendMessage(Message.raw("  server.admin - Full server administration").color("gray"));
            context.sender()
                    .sendMessage(Message.raw("  server.moderate - Moderation tools (kick, mute, etc)").color("gray"));
            context.sender().sendMessage(Message.raw("  server.build - Building permissions").color("gray"));
            context.sender().sendMessage(Message.raw("  server.teleport - Teleportation commands").color("gray"));

            context.sender().sendMessage(Message.raw("--- Gameplay ---").color("yellow"));
            context.sender().sendMessage(Message.raw("  game.fly - Flight ability").color("gray"));
            context.sender().sendMessage(Message.raw("  game.creative - Creative mode access").color("gray"));
            context.sender().sendMessage(Message.raw("  game.spawn - Spawn items").color("gray"));

            context.sender().sendMessage(Message.raw("--- Chat ---").color("yellow"));
            context.sender().sendMessage(Message.raw("  chat.color - Use colors in chat").color("gray"));
            context.sender().sendMessage(Message.raw("  chat.broadcast - Server-wide announcements").color("gray"));

            context.sender().sendMessage(Message.raw("--- Custom Permissions ---").color("yellow"));
            context.sender()
                    .sendMessage(Message.raw("  You can create any permission using dot notation:").color("gray"));
            context.sender().sendMessage(Message.raw("  Example: /rank addperm VIP vip.kit").color("white"));
            context.sender().sendMessage(Message.raw("  Example: /rank addperm Admin my.custom.perm").color("white"));
            context.sender()
                    .sendMessage(Message.raw("  Other plugins can check these with hasPermission()").color("gray"));
        });
    }
}
