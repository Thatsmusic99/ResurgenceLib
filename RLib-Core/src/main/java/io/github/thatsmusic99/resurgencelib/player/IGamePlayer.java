package io.github.thatsmusic99.resurgencelib.player;

import io.github.thatsmusic99.resurgencelib.game.Game;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An interface representing
 *
 * @param <E> an enum covering the player states.
 */
public interface IGamePlayer<E extends Enum<E>> {

    /**
     * Fetches the Bukkit player object of this object.
     *
     * @return the player object in question, or null if they have been disconnected.
     */
    @Nullable Player getPlayer();

    /**
     * Refreshes the player in the game player object with a new one, often after joining the server.
     *
     * @param player the player to be stored.
     */
    void relog(@NotNull Player player);

    /**
     * Prompts the game to resurrect the player.
     */
    void resurrect();

    /**
     * Prompts the game to eliminate the player with an optional cause.
     *
     * @param cause the player causing the elimination.
     */
    void eliminate(@Nullable Player cause);

    /**
     * Fetches the game the player is currently in. Can be null if the player is not involved in the game. They can be dead, alive or spectating.
     *
     * @return the game in question. Returns null if there is no game.
     */
    @Nullable Game<?, ?> getGame();

    /**
     * Determines whether the player is in a game.
     *
     * @return true if the player is in a game, false if not.
     */
    boolean isInGame();

    /**
     * Determines whether the player is still eligible to win in the game.
     *
     * @return true if the player is still able to win ("alive"), or false if not.
     */
    boolean isAlive();

    /**
     * Determines whether the player is unable to win a game despite being able to before.
     *
     * @return true if the player can no longer win but was previously able to ("dead"), false if they can still win or in an alternative state.
     */
    boolean isDead();

    /**
     * Whether the player participated in the game at all or not. Specifically excludes players in spectator mode.
     *
     * @return true if the player participated in the ongoing game, false if they are in a different state.
     */
    boolean isActive();

    void setGame(@Nullable Game<?, ?> game);

    E getState();

    void setState(E state);
}
