package io.github.thatsmusic99.resurgencelib.player;

import io.github.thatsmusic99.resurgencelib.team.ITeam;
import org.jetbrains.annotations.Nullable;

public interface ITeamed {

    @Nullable ITeam getTeam();

    void setTeam(@Nullable ITeam team);
}
