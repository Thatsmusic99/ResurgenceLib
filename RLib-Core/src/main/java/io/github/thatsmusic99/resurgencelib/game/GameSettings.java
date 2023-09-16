package io.github.thatsmusic99.resurgencelib.game;

import io.github.thatsmusic99.resurgencelib.game.settings.CyclingMode;
import io.github.thatsmusic99.resurgencelib.game.settings.EndGameHandler;
import io.github.thatsmusic99.resurgencelib.game.settings.FullGameHandler;
import io.github.thatsmusic99.resurgencelib.game.settings.MapSelectionHandler;
import io.github.thatsmusic99.resurgencelib.map.Map;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;

/**
 * Represents a series of settings for the game cycling management as well as the game itself.
 */
public interface GameSettings<P extends IGamePlayer<?>, M extends Map<P>, G extends Game<P, M>> {

    CyclingMode getCyclingMode();

    EndGameHandler<G> getEndGameHandlingMode();

    FullGameHandler<P, G> getFullGameHandlingMode();

    MapSelectionHandler<M> getMapSelectionHandlingMode();

}
