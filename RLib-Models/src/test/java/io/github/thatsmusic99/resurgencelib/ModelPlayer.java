package io.github.thatsmusic99.resurgencelib;

import io.github.thatsmusic99.resurgencelib.fields.ModelField;
import io.github.thatsmusic99.resurgencelib.models.Model;
import io.github.thatsmusic99.resurgencelib.models.ModelManager;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ModelPlayer extends Model {

    private @ModelField(primary = true) UUID uuid;
    private @ModelField int points;

    public ModelPlayer(@NotNull ModelManager modelManager, UUID uuid, int points) {
        super(modelManager);

        this.uuid = uuid;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
