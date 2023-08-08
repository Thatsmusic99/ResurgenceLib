package io.github.thatsmusic99.resurgencelib.game.settings;

import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import org.jetbrains.annotations.NotNull;

/**
 * Used to handle instances where the player leaves the server whilst in a game.
 *
 * @param <P> the player class
 * @param <G> the game class
 */
public interface LeaveGameHandler<P extends IGamePlayer, G extends Game<P, ?>> {

    void onPlayerLeave(@NotNull P player, @NotNull G game);
}
