package io.github.thatsmusic99.resurgencelib.models;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A model that is represented as a table in the database.
 * <p>
 * All annotated ModelFields in the class and superclasses are gathered
 * together and compiled into a table. Any ModelFields added over time
 * are automatically added to the database, and vice versa for
 */
public abstract class Model {

    private final @NotNull ModelManager modelManager;
    private final @NotNull List<Field> fields;
    private final @NotNull List<Field> primaryFields;

    public Model(@NotNull ModelManager modelManager) {
        this.modelManager = modelManager;
        this.fields = new ArrayList<>();
        this.primaryFields = new ArrayList<>();

        this.setupFields();
    }

    private void setupFields() {


    }

    public @NotNull List<Field> getFields() {
        return this.fields;
    }

    public @NotNull List<Field> getPrimaryFields() {
        return primaryFields;
    }

    public void save() throws SQLException, IllegalAccessException {
        this.modelManager.save(this);
    }

    public void sync() throws IllegalAccessException {
        this.modelManager.sync(this);
    }
}
