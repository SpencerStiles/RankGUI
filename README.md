# RankGUI - Hytale Rank Management Plugin

A comprehensive rank and permission management plugin for Hytale servers.

## Features

- **Rank Management** - Create, delete, and configure ranks
- **Permission System** - Assign permissions to ranks
- **Priority Levels** - Control rank hierarchy with numeric priorities
- **Player Assignments** - Assign ranks to players persistently
- **Persistent Storage** - All data saved to JSON files
- **First-Admin Claim** - First user can claim Owner rank

## Installation

1. Download `RankGUI-1.0.0.jar`
2. Copy to `%appdata%\Hytale\UserData\Mods\`
3. Launch Hytale and load a world
4. Use `/rank claim` to become the first admin

## Commands

| Command | Description |
|---------|-------------|
| `/rank help` | Show all commands |
| `/rank list` | List all ranks (sorted by priority) |
| `/rank info <name>` | Show details about a rank |
| `/rank create <name> [prefix]` | Create a new rank |
| `/rank delete <name>` | Delete a rank |
| `/rank set <player> <rank>` | Assign a rank to a player |
| `/rank addperm <rank> <perm>` | Add permission to a rank |
| `/rank setpriority <rank> <num>` | Set rank priority level |
| `/rank myrank` | Show your current rank |
| `/rank claim` | Claim Owner rank (first admin only) |

## Default Ranks

The plugin creates these ranks automatically:

| Rank | Priority | Prefix | Permissions |
|------|----------|--------|-------------|
| Owner | 100 | [Owner] | All Permissions |
| Admin | 90 | [Admin] | Rank Admin, Server Admin |
| Moderator | 50 | [Mod] | Server Moderate |
| VIP | 20 | [VIP] | None |
| Default | 0 | (none) | None |

## Data Storage

Data is stored in JSON files in your world's mods folder:

- `ranks.json` - Rank definitions
- `player_ranks.json` - Player-to-rank assignments

## Building from Source

```bash
./gradlew shadowJar
```

Output: `build/libs/RankGUI-1.0.0.jar`

## License

MIT License - Free to use and modify.

## Author

Created by Spencer
