package io.github.thatsmusic99.resurgencelib.player;

import io.github.thatsmusic99.resurgencelib.fields.ModelField;
import io.github.thatsmusic99.resurgencelib.game.Game;
import io.github.thatsmusic99.resurgencelib.models.Model;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.UUID;

public abstract class GamePlayer extends Model implements IGamePlayer<PlayerState>, IPointGaining {

    @ModelField(primary = true) private final UUID uuid;
    @ModelField private int points;
    @NotNull private WeakReference<Player> player;
    @NotNull private PlayerState state;
    @Nullable private Game<?, ?> game;
    private int roundPoints;

    public GamePlayer(Player player) {
        this.player = new WeakReference<>(player);
        this.uuid = player.getUniqueId();

        this.points = 0;
        this.roundPoints = 0;
        this.state = PlayerState.NOT_IN_GAME;
    }

    public GamePlayer(UUID uuid, int points) {
        this.uuid = uuid;
        this.points = points;
        this.player = new WeakReference<>(null);
        this.state = PlayerState.NOT_IN_GAME;
    }

    @Override
    public @Nullable Player getPlayer() {
        return this.player.get();
    }

    @Override
    public void relog(@NotNull Player player) {
        this.player = new WeakReference<>(player);
    }

    @Override
    public @Nullable Game<?, ?> getGame() {
        return this.game;
    }

    @Override
    public boolean isInGame() {
        return this.game != null;
    }

    @Override
    public boolean isActive() {
        return state == PlayerState.ALIVE || state == PlayerState.DEAD;
    }

    @Override
    public boolean isAlive() {
        return state == PlayerState.ALIVE;
    }

    @Override
    public boolean isDead() {
        return state == PlayerState.DEAD;
    }

    @Override
    public void setGame(@Nullable Game<?, ?> game) {
        this.game = game;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getRoundPoints() {
        return roundPoints;
    }

    @Override
    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public void addRoundPoints(int points) {
        this.roundPoints += points;
    }

    @Override
    public void removePoints(int points) {
        this.points -= points;
    }

    @Override
    public void removeRoundPoints(int points) {
        this.roundPoints -= points;
    }

    @Override
    public @NotNull PlayerState getState() {
        return state;
    }

    @Override
    public void setState(@NotNull PlayerState state) {
        this.state = state;
    }
}
