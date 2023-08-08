package io.github.thatsmusic99.resurgencelib.map;

import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;

import java.util.ArrayDeque;
import java.util.Queue;

public class Map<P extends IGamePlayer> implements IMap {

    private final Queue<P> queuedPlayers;
    private final String id;

    public Map(String id) {
        this.id = id;
        this.queuedPlayers = new ArrayDeque<>();
    }

    @Override
    public String getId() {
        return this.id;
    }
}
