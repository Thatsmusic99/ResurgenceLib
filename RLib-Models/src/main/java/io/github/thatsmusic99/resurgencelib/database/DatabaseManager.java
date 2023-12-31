package io.github.thatsmusic99.resurgencelib.database;

import io.github.thatsmusic99.resurgencelib.models.Model;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public abstract class DatabaseManager {

    public abstract @NotNull Connection connect() throws SQLException;

    public abstract void loadDriver() throws ClassNotFoundException;

    public abstract void loadDataTypes();

    public abstract boolean doesTableExist(@NotNull String tableName) throws SQLException;

    public abstract void createTableIfNotExists(
            @NotNull String tableName,
            @NotNull List<Field> primaryFields,
            @NotNull List<Field> fields) throws SQLException;

    public abstract void checkAndMigrateColumns(@NotNull Model model) throws SQLException;

    public abstract void insertOrUpdateRow(
            @NotNull String tableName,
            @NotNull HashMap<String, Object> where,
            @NotNull HashMap<String, Object> update) throws SQLException;

}
