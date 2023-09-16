package io.github.thatsmusic99.resurgencelib.models;

import io.github.thatsmusic99.resurgencelib.database.DatabaseManager;
import io.github.thatsmusic99.resurgencelib.fields.ModelField;
import io.github.thatsmusic99.resurgencelib.utilities.DataConverter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;

/**
 * The class used to handle models and any required migrations.
 */
public class ModelManager {

    private final @NotNull String prefix;
    private final @NotNull DatabaseManager manager;
    private final @NotNull HashMap<String, Class<? extends Model>> models;
    private final @NotNull HashMap<Class<? extends Model>, List<Field>> fields;
    private final @NotNull HashMap<Class<? extends Model>, List<Field>> primaryFields;

    public ModelManager(@NotNull String prefix, @NotNull DatabaseManager manager) {
        this.prefix = prefix;
        this.manager = manager;
        this.models = new HashMap<>();
        this.fields = new HashMap<>();
        this.primaryFields = new HashMap<>();

    }

    public void registerModel(Class<? extends Model> modelClass) throws SQLException {

        // Insert the model into the hashmap
        this.models.put(modelClass.getSimpleName(), modelClass);

        // Fetch the fields of the model
        this.setupFields(modelClass);

        this.manager.createTableIfNotExists(modelClass.getSimpleName(), this.primaryFields.get(modelClass), this.fields.get(modelClass));
    }

    public void generateMigrations() {

        // TODO

        // Go through each model
        for (Class<? extends Model> model : this.models.values()) {

            // Get the fields
            for (Field field : this.fields.get(model)) {

                //
            }
        }
    }

    private void setupFields(Class<? extends Model> modelClass) {

        // Store primary and normal fields
        List<Field> primaryFields = new ArrayList<>();
        List<Field> fields = new ArrayList<>();

        // Get the class
        Class<?> clazz = modelClass;
        do {

            // Filter the fields
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isAnnotationPresent(ModelField.class)) continue;

                // If it's primary, add it to a separate list
                if (field.getAnnotation(ModelField.class).primary()) {
                    primaryFields.add(field);
                } else {
                    fields.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        } while (clazz != null);

        this.primaryFields.put(modelClass, primaryFields);
        this.fields.put(modelClass, fields);
    }

    public void save(@NotNull Model model) throws SQLException, IllegalAccessException {

        // Get the table
        String tableName = model.getClass().getSimpleName();

        // Columns to update
        HashMap<String, Object> update = new HashMap<>();
        for (Field field : this.fields.get(model.getClass())) {
            field.setAccessible(true);
            update.put(field.getName(), DataConverter.convertToSafe(field.get(model)));
        }

        // columns to search
        HashMap<String, Object> where = new HashMap<>();
        for (Field field : this.primaryFields.get(model.getClass())) {
            field.setAccessible(true);
            where.put(field.getName(), DataConverter.convertToSafe(field.get(model)));
        }

        // Talk to the database manager
        manager.insertOrUpdateRow(tableName, where, update);
    }

    public void sync(@NotNull Model model) throws IllegalAccessException {

        // Get the table
        String tableName = model.getClass().getSimpleName();

        // Columns to search
        HashMap<String, Object> search = new HashMap<>();
        for (Field field : this.fields.get(model.getClass())) {
            field.setAccessible(true);
            search.put(field.getName(), DataConverter.convertToSafe(field.get(model)));
        }

        // columns to search
        HashMap<String, Object> where = new HashMap<>();
        for (Field field : this.primaryFields.get(model.getClass())) {
            field.setAccessible(true);
            where.put(field.getName(), DataConverter.convertToSafe(field.get(model)));
        }


    }
}
