package io.github.thatsmusic99.resurgencelib.game.settings;

import io.github.thatsmusic99.resurgencelib.game.Game;

public interface EndGameHandler<G extends Game> {

    void onGameEnd(G game);
}
