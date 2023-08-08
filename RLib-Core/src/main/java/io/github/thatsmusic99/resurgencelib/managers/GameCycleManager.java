package io.github.thatsmusic99.resurgencelib.managers;

import io.github.thatsmusic99.resurgencelib.ResurgencePlugin;
import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.game.GameSettings;
import io.github.thatsmusic99.resurgencelib.map.Map;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GameCycleManager<P extends IGamePlayer<?>, M extends Map<P>, G extends Game<P, M>> {

    private final @NotNull ResurgencePlugin<P, G> plugin;
    private final @NotNull GameSettings<G> settings;

    private @Nullable G game;

    public GameCycleManager(@NotNull ResurgencePlugin<P, G> plugin) {
        this.plugin = plugin;

        this.settings = plugin.getSettings();

        this.game = null;
    }
}
