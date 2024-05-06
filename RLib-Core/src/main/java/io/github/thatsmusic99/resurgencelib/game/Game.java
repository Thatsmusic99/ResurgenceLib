package io.github.thatsmusic99.resurgencelib.game;

import io.github.thatsmusic99.resurgencelib.map.Map;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Game<P extends IGamePlayer<?>, M extends Map<P>> {

    @NotNull protected M map;
    @NotNull protected List<P> players;
    protected int timer;

    public Game(final @NotNull M map) {
        this.players = new ArrayList<>();
        this.map = map;
    }

    public @NotNull List<P> getPlayers() {
        return this.players;
    }
}
