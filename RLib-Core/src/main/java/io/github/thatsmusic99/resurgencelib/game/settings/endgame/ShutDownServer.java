package io.github.thatsmusic99.resurgencelib.game.settings.endgame;

import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.game.settings.EndGameHandler;
import org.bukkit.Bukkit;

public class ShutDownServer<T extends Game<?, ?>> implements EndGameHandler<T> {

    @Override
    public void onGameEnd(T game) {

        // Bye bye lmao
        Bukkit.getServer().shutdown();
    }
}
