package io.github.thatsmusic99.resurgencelib.managers;

import io.github.thatsmusic99.resurgencelib.ResurgencePlugin;
import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.map.Map;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class GameCycleManager<P extends IGamePlayer<?>, M extends Map<P>, G extends Game<P, M>> {

    private final @NotNull ResurgencePlugin<P, M, G> plugin;
    private @Nullable M map;

    private @Nullable G game;

    public GameCycleManager(@NotNull ResurgencePlugin<P, M, G> plugin) {
        this.plugin = plugin;

        this.game = null;
    }

    @Contract(pure = true)
    public @NotNull CompletableFuture<M> getNextMap() {
        if (this.map != null) return CompletableFuture.completedFuture(this.map);
    }

    @Contract(pure = true)
    public void reset() {
        this.game = null;
        this.map = null;
    }
}
