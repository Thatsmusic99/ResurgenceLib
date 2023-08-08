package io.github.thatsmusic99.resurgencelib.game.settings;

import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @param <P>
 * @param <G>
 */
public interface FullGameHandler<P extends IGamePlayer, G extends Game<P, ?>> {

    void onGameJoinAttempt(@NotNull P player, @NotNull G game);
}
