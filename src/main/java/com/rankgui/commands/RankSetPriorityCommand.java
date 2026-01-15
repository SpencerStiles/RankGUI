package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.rankgui.RankGUIPlugin;
import com.rankgui.storage.Rank;

import java.util.concurrent.CompletableFuture;

/**
 * /rank setpriority <rank> <priority> - Set a rank's priority
 */
public class RankSetPriorityCommand extends AbstractAsyncCommand {

    private final RequiredArg<String> rankArg;
    private final RequiredArg<Integer> priorityArg;

    public RankSetPriorityCommand() {
        super("setpriority", "Set a rank's priority level");
        rankArg = withRequiredArg("rank", "The rank name", ArgTypes.STRING);
        priorityArg = withRequiredArg("priority", "The priority level (higher = more important)", ArgTypes.INTEGER);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            String rankName = rankArg.get(context);
            int priority = priorityArg.get(context);

            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            if (!rankManager.hasRank(rankName)) {
                context.sender().sendMessage(Message.raw("Rank '" + rankName + "' does not exist!").color("red"));
                return;
            }

            Rank rank = rankManager.getRank(rankName);
            rank.setPriority(priority);
            rankManager.save();

            context.sender()
                    .sendMessage(Message.raw("Set priority of rank '" + rankName + "' to " + priority).color("green"));
        });
    }
}
