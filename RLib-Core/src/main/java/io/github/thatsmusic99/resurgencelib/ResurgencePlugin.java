package io.github.thatsmusic99.resurgencelib;

import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.game.GameSettings;
import io.github.thatsmusic99.resurgencelib.listeners.PlayerJoinListener;
import io.github.thatsmusic99.resurgencelib.map.Map;
import io.github.thatsmusic99.resurgencelib.player.IGamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * Represents a plugin using ResurgenceLib.
 */
public interface ResurgencePlugin<P extends IGamePlayer<?>, M extends Map<P>, G extends Game<P, M>> {

    /**
     * @return The plugin running ResurgenceLib.
     */
    Plugin getPlugin();

    /**
     * Contains the game settings that the internal library should use to function.
     *
     * @return
     */
    GameSettings<P, M, G> getSettings();

    /**
     * Used to initialise base listeners that ResurgenceLib uses.
     */
    default void initBaseListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), getPlugin());
    }
}
