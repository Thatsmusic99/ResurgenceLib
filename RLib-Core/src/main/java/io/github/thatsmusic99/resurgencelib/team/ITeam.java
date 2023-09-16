package io.github.thatsmusic99.resurgencelib.team;

import net.kyori.adventure.text.Component;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a team a player can be moved into in the game.
 */
public interface ITeam {

    @NotNull String getId();

    @NotNull Component getDisplayName();

    @NotNull Team getBukkitTeam();
}
