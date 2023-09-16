package io.github.thatsmusic99.resurgencelib.game.settings.fullgame;

import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.game.settings.FullGameHandler;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import io.github.thatsmusic99.resurgencelib.player.ISpectator;
import org.jetbrains.annotations.NotNull;

public class AddAsSpectator<P extends IGamePlayer<?> & ISpectator, G extends Game<P, ?>> implements FullGameHandler<P, G> {

    @Override
    public void onGameJoinAttempt(@NotNull P player, @NotNull G game) {
        player.setSpectator(true);
    }
}
