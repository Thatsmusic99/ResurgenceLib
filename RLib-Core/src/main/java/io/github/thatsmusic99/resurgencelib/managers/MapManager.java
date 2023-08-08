package io.github.thatsmusic99.resurgencelib.managers;

import io.github.thatsmusic99.resurgencelib.map.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Used to manage maps in the plugin.
 *
 * @param <M> the map class associated.
 */
public class MapManager<M extends Map<?>> {

    private final @NotNull HashMap<String, M> registeredMaps;

    public MapManager() {
        this.registeredMaps = new HashMap<>();
    }

    public void registerMap(@NotNull M map) {
        this.registeredMaps.put(map.getId(), map);
    }

    public @Nullable M getMap(@NotNull String id) {
        return this.registeredMaps.get(id);
    }
}
