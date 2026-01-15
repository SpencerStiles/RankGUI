package com.rankgui.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

/**
 * Main /rank command with subcommands for rank management.
 */
public class RankCommand extends AbstractCommandCollection {

    public RankCommand() {
        super("rank", "Manage server ranks and player permissions");

        // Add subcommands
        addSubCommand(new RankListCommand());
        addSubCommand(new RankCreateCommand());
        addSubCommand(new RankDeleteCommand());
        addSubCommand(new RankSetCommand());
        addSubCommand(new RankInfoCommand());
        addSubCommand(new RankClaimCommand());

        // TODO: Re-enable permission for production
        // requirePermission("rankgui.admin");
    }
}
