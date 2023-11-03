package io.github.thatsmusic99.resurgencelib.database;

import io.github.thatsmusic99.resurgencelib.fields.ModelField;
import io.github.thatsmusic99.resurgencelib.models.Model;
import io.github.thatsmusic99.resurgencelib.utilities.DataConverter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class SQLiteDatabaseManager extends DatabaseManager {
    public final @NotNull HashMap<Class<?>, String> DATA_TYPES;
    private final @NotNull File SQLITE_FILE;

    public SQLiteDatabaseManager(@NotNull File sqliteFile) throws IOException {
        this.DATA_TYPES = new HashMap<>();
        SQLITE_FILE = sqliteFile;

        if (!sqliteFile.exists()) sqliteFile.createNewFile();
    }

    @Override
    public @NotNull Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + SQLITE_FILE);
    }

    @Override
    public void loadDriver() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
    }

    @Override
    public void loadDataTypes() {
        this.DATA_TYPES.put(byte.class, "TINYINT");
        this.DATA_TYPES.put(short.class, "SMALLINT");
        this.DATA_TYPES.put(int.class, "INTEGER");
        this.DATA_TYPES.put(boolean.class, "BOOLEAN");
        this.DATA_TYPES.put(UUID.class, "CHAR(36)");
        this.DATA_TYPES.put(String.class, "VARCHAR(%d)");
        this.DATA_TYPES.put(Enum.class, "ENUM(%s)");
        this.DATA_TYPES.put(float.class, "FLOAT");
        this.DATA_TYPES.put(double.class, "DOUBLE");
    }

    @Override
    public boolean doesTableExist(@NotNull String table) throws SQLException {
        try (Connection connection = connect()) {

            PreparedStatement statement = connection.prepareStatement("SELECT EXISTS(SELECT 1 FROM sqlite_master WHERE `type`='table' AND `name`=?)");
            statement.setString(1, table);

            // Parse results
            ResultSet set = statement.executeQuery();
            return set.next();
        }
    }

    @Override
    public void createTableIfNotExists(
            @NotNull String tableName,
            @NotNull List<Field> primaryFields,
            @NotNull List<Field> fields) throws SQLException {
        try (Connection connection = connect()) {

            // Set up fields for table creation
            List<String> columns = new ArrayList<>();
            List<String> indexed = new ArrayList<>();
            boolean idTaken = false;

            // If there are no primary keys, set up an auto-incrementing ID
            if (primaryFields.isEmpty()) {
                columns.add("`id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT");
                idTaken = true;
            } else {
                for (Field field : primaryFields) {

                    // Check if the field needs to be indexed.
                    ModelField modelField = field.getAnnotation(ModelField.class);
                    if (modelField.indexed()) indexed.add(field.getName());

                    // Check if the field is not null.
                    boolean nullable = !field.isAnnotationPresent(NotNull.class);

                    // Get the data type to be used
                    if (this.DATA_TYPES.containsKey(field.getType())) {
                        String dataType = String.format(this.DATA_TYPES.get(field.getType()), modelField.length());
                        String column = String.format("`%s` %s " + (nullable ? "" : "NOT NULL PRIMARY KEY"), field.getName(), dataType);
                        columns.add(column);
                    } else {

                        // If it's a model, attempt to get the primary key
                        if (field.getType().isAssignableFrom(Model.class)) {
                            // TODO
                        } else {
                            throw new IllegalArgumentException("Invalid ModelField type: " + field.getType().getSimpleName() + ".");
                        }
                    }
                }
            }

            for (Field field : fields) {

                // If it's trying to redeclare id, raise an error
                if (field.getName().equals("id") && idTaken)
                    throw new IllegalStateException(tableName +  " declared a field called ID " +
                            "when no other fields or itself has been marked as primary.");

                // Check if the field needs to be indexed.
                ModelField modelField = field.getAnnotation(ModelField.class);
                if (modelField.indexed()) indexed.add(field.getName());

                // Check if the field is not null.
                boolean nullable = !field.isAnnotationPresent(NotNull.class);

                // Get the data type to be used
                if (this.DATA_TYPES.containsKey(field.getType())) {
                    String dataType = String.format(this.DATA_TYPES.get(field.getType()), modelField.length());
                    String column = String.format("`%s` %s " + (nullable ? "" : "NOT NULL "), field.getName(), dataType);
                    columns.add(column);
                }
            }

            // Set the table name
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS " + tableName + " (" + String.join(", ", columns) + ")" +
                            (indexed.isEmpty() ? "" : " INDEX (" + String.join(", ", indexed) + ")") + ";");
            statement.executeUpdate();

        }
    }

    @Override
    public void checkAndMigrateColumns(@NotNull Model model) throws SQLException {

    }

    @Override
    public void insertOrUpdateRow(@NotNull String tableName, @NotNull HashMap<String, Object> where, @NotNull HashMap<String, Object> update) throws SQLException {
        try (Connection connection = connect()) {
            // Check if the model is already in the database
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE " + String.join(", ", where.keySet()));
            for (int i = 1; i <= where.size(); i++) {
                Object value = where.values().toArray()[i - 1 + update.size()];
                statement.setObject(i, value);
            }

            // If it exists, just update
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                set.close();
                statement = connection.prepareStatement("UPDATE " + tableName + " SET " +
                        String.join(", ", update.keySet()) + " WHERE " + String.join(", ", where.keySet()));

                // Add all the arguments
                int index = 1;
                for (Object value : update.values()) {
                    statement.setObject(index, DataConverter.convertToSafe(value));
                    index++;
                }
                for (Object value : where.values()) {
                    statement.setObject(index, DataConverter.convertToSafe(value));
                    index++;
                }

                statement.executeUpdate();

            } else {
                set.close();

                // Create the array for the prepared statements
                String[] qs = new String[update.size() + where.size()];
                Arrays.fill(qs, "?");

                // Get all columns
                HashMap<String, Object> all = new HashMap<>();
                all.putAll(update);
                all.putAll(where);

                // Otherwise, insert
                statement = connection.prepareStatement("INSERT INTO " + tableName +
                        " (" + String.join(", ", all.keySet()) + ")" +
                        " VALUES (" + String.join(", ", qs) + ");");

                int index = 1;
                for (Object value : all.values()) {
                    statement.setObject(index, value);
                    index++;
                }

                statement.executeUpdate();
            }
        }
    }
}
