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
 * /rank addperm <rank> <permission> - Add a permission to a rank
 */
public class RankAddPermCommand extends AbstractAsyncCommand {

    private final RequiredArg<String> rankArg;
    private final RequiredArg<String> permArg;

    public RankAddPermCommand() {
        super("addperm", "Add a permission to a rank");
        rankArg = withRequiredArg("rank", "The rank name", ArgTypes.STRING);
        permArg = withRequiredArg("permission", "The permission to add", ArgTypes.STRING);
    }

    @Override
    protected boolean canGeneratePermission() {
        return false;
    }

    @Override
    protected CompletableFuture<Void> executeAsync(CommandContext context) {
        return CompletableFuture.runAsync(() -> {
            String rankName = rankArg.get(context);
            String permission = permArg.get(context);

            var rankManager = RankGUIPlugin.getInstance().getRankManager();

            if (!rankManager.hasRank(rankName)) {
                context.sender().sendMessage(Message.raw("Rank '" + rankName + "' does not exist!").color("red"));
                return;
            }

            Rank rank = rankManager.getRank(rankName);
            rank.addPermission(permission);
            rankManager.save();

            context.sender().sendMessage(
                    Message.raw("Added permission '" + permission + "' to rank '" + rankName + "'").color("green"));
        });
    }
}
