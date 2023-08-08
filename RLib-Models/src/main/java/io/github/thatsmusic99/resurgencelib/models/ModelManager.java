package io.github.thatsmusic99.resurgencelib.models;

import io.github.thatsmusic99.resurgencelib.fields.ModelField;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The class used to handle models and any required migrations.
 */
public class ModelManager {

    private final @NotNull String prefix;
    private final @NotNull HashMap<String, Class<Model>> models;
    private final @NotNull HashMap<Class<Model>, List<Field>> fields;

    public ModelManager(@NotNull String prefix) {
        this.prefix = prefix;
        this.models = new HashMap<>();
        this.fields = new HashMap<>();
    }

    public void registerModel(Class<Model> modelClass) {

        // Insert the model into the hashmap
        this.models.put(modelClass.getSimpleName(), modelClass);

        // Fetch the fields of the model
        this.fields.put(modelClass, Arrays.stream(modelClass.getFields()).filter(field -> field.isAnnotationPresent(ModelField.class)).toList());
    }

    public void generateMigrations() {



    }
}
