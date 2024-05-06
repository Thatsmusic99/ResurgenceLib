package io.github.thatsmusic99.resurgencelib.game.settings.endgame;

import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.game.settings.EndGameHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeleportToLobby<G extends Game<?, ?>> implements EndGameHandler<G> {

    private @Nullable Location lobbyLocation;

    public TeleportToLobby() {
        this.lobbyLocation = null;
    }

    public TeleportToLobby(@NotNull Location location) {
        this.lobbyLocation = location;
    }

    @Override
    public void onGameEnd(G game) {

        // Main world
        World world = Bukkit.getWorlds().get(0);

        // Grab everyone and yeet them
        game.getPlayers().forEach(player -> player.getPlayer().teleport(
                this.lobbyLocation == null ? world.getSpawnLocation() : this.lobbyLocation));
    }
}
