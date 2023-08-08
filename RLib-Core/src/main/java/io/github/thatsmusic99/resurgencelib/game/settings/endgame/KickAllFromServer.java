package io.github.thatsmusic99.resurgencelib.game.settings.endgame;

import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.game.settings.EndGameHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KickAllFromServer<T extends Game<?, ?>> implements EndGameHandler<T> {

    @Override
    public void onGameEnd(T game) {

        // Get everyone online and go yeet
        Bukkit.getOnlinePlayers().forEach(Player::kick);
    }
}
