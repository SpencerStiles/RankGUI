package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.rankgui.RankGUIPlugin;

import java.util.concurrent.CompletableFuture;

/**
 * /rank set <player> <rank> - Assigns a rank to a player
 */
public class RankSetCommand extends AbstractAsyncCommand {

    private final RequiredArg<String> playerArg;
    private final RequiredArg<String> rankArg;

    public RankSetCommand() {
        super("set", "Assign a rank to a player");

        playerArg = withRequiredArg("player", "The player name", ArgTypes.STRING);
        rankArg = withRequiredArg("rank", "The rank to assign", ArgTypes.STRING);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            String playerName = playerArg.get(context);
            String rankName = rankArg.get(context);

            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            if (!rankManager.hasRank(rankName)) {
                context.sender().sendMessage(Message.raw("Rank '" + rankName + "' does not exist!").color("red"));
                return;
            }

            rankManager.setPlayerRank(playerName, rankName);
            rankManager.save();

            context.sender()
                    .sendMessage(Message.raw("Assigned rank '" + rankName + "' to player '" + playerName + "'")
                            .color("green"));
        });
    }
}
