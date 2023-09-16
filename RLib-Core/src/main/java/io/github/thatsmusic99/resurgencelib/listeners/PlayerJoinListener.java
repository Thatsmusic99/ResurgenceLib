package io.github.thatsmusic99.resurgencelib.listeners;

import io.github.thatsmusic99.resurgencelib.ResurgencePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoinListener implements Listener {

    private final @NotNull ResurgencePlugin<?, ?, ?> plugin;

    public PlayerJoinListener(@NotNull ResurgencePlugin<?, ?, ?> plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {

    }
}
