package io.github.thatsmusic99.resurgencelib;

import io.github.thatsmusic99.resurgencelib.database.DatabaseManager;
import io.github.thatsmusic99.resurgencelib.database.MySQLDatabaseManager;
import io.github.thatsmusic99.resurgencelib.models.ModelManager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

public class GamePlayerTest {

  @Test
  public void test_gamePlayerStorage()
      throws ClassNotFoundException, SQLException, IllegalAccessException {

        DatabaseManager manager = new MySQLDatabaseManager("mysql", "127.0.0.1", "30000", "mysql", "root", "hehe");
        manager.loadDriver();
        manager.loadDataTypes();
        ModelManager modelManager = new ModelManager("aaa", manager);
        modelManager.registerModel(ModelPlayer.class);

        ModelPlayer player = new ModelPlayer(modelManager, UUID.fromString("29f30610-346d-4391-a5ad-f6fe5979d772"), 10);
        player.save();

        player.setPoints(5);
        player.save();
    }
}
