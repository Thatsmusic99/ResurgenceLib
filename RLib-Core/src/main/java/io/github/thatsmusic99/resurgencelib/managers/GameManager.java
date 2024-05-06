package io.github.thatsmusic99.resurgencelib.managers;

import io.github.thatsmusic99.resurgencelib.ResurgencePlugin;
import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.map.Map;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * The manager responsible for handling games.
 *
 * @param <P>
 * @param <M>
 * @param <G>
 */
public abstract class GameManager<P extends IGamePlayer<?>, M extends Map<P>, G extends Game<P, M>> {

    protected @NotNull HashMap<String, P> players;
    protected @NotNull ResurgencePlugin<P, M, G> plugin;
    protected @Nullable Game<P, M> currentGame;

    public GameManager(final @NotNull ResurgencePlugin<P, M, G> plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
        this.currentGame = null;
    }

    public @Nullable Game<P, M> getCurrentGame() {
        return this.currentGame;
    }

    public void setCurrentGame(final @Nullable Game<P, M> currentGame) {
        this.currentGame = currentGame;
    }

    public abstract @Nullable P getPlayer(final @NotNull String name);

    public abstract @NotNull P getPlayer(final @NotNull Player player);
}
