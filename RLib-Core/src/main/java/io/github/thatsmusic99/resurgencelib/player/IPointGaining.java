package io.github.thatsmusic99.resurgencelib.player;

public interface IPointGaining {

    int getPoints();

    int getRoundPoints();

    void addPoints(int points);

    void addRoundPoints(int points);

    void removePoints(int points);

    void removeRoundPoints(int points);

    void setPoints(int points);

    void setRoundPoints(int points);

    default void resetPoints() {
        setPoints(0);
    }

    default void resetRoundPoints() {
        addPoints(getRoundPoints());
        setRoundPoints(0);
    }
}
