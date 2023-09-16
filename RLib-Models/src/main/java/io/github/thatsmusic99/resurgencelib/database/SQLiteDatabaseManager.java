package io.github.thatsmusic99.resurgencelib.database;

import io.github.thatsmusic99.resurgencelib.models.Model;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class SQLiteDatabaseManager extends DatabaseManager {
    @Override
    public @NotNull Connection connect() throws SQLException {
        return null;
    }

    @Override
    public void loadDriver() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
    }

    @Override
    public void loadDataTypes() {

    }

    @Override
    public boolean doesTableExist(@NotNull String tableName) throws SQLException {
        return false;
    }

    @Override
    public void createTableIfNotExists(@NotNull String tableName, @NotNull List<Field> primaryFields, @NotNull List<Field> fields) throws SQLException {

    }

    @Override
    public void checkAndMigrateColumns(@NotNull Model model) throws SQLException {

    }

    @Override
    public void insertOrUpdateRow(@NotNull String tableName, @NotNull HashMap<String, Object> where, @NotNull HashMap<String, Object> update) throws SQLException {

    }
}
