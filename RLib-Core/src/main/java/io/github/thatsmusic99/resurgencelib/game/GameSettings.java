package io.github.thatsmusic99.resurgencelib.game;

import io.github.thatsmusic99.resurgencelib.game.settings.CyclingMode;
import io.github.thatsmusic99.resurgencelib.game.settings.EndGameHandler;

/**
 * Represents a series of settings for the game cycling management as well as the game itself.
 */
public interface GameSettings<G extends Game> {

    CyclingMode getCyclingMode();

    EndGameHandler<G> getEndGameHandlingMode();


}
