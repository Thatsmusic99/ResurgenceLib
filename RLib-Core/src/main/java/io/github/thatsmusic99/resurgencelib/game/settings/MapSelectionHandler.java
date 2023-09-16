package io.github.thatsmusic99.resurgencelib.game.settings;

import io.github.thatsmusic99.resurgencelib.map.Map;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public interface MapSelectionHandler<M extends Map<?>> {

    @NotNull CompletableFuture<@NotNull M> selectMap();
}
