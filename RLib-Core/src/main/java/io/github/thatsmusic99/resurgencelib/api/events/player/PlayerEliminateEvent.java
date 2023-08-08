package io.github.thatsmusic99.resurgencelib.api.events.player;

/**
 * Used when a player is eliminated within a game.
 */
public class PlayerEliminateEvent {

    /**
     * The reasoning for a player being eliminated from a game.
     */
    public enum EliminationReason {
        /**
         * When a player has been disqualified by the plugin itself, e.g. due to quitting.
         */
        DISQUALIFICATION,
        /**
         * When a player has been eliminated by a moderator, e.g. due to rule breaking.
         */
        MANUAL_ELIMINATION,
        /**
         * When a player has been eliminated normally, such as due to death.
         */
        NORMAL_ELIMINATION,
    }
}
