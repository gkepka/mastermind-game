package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {

    private static final String TABLE_PLAYER =
            """
            CREATE TABLE IF NOT EXISTS player(
                player_id INTEGER PRIMARY KEY,
                login TEXT,
                email TEXT,
                password TEXT
            );        
            """;

    private static final String TABLE_GAME =
            """
            CREATE TABLE IF NOT EXISTS game(
                game_id INTEGER PRIMARY KEY,
                result INTEGER NOT NULL,
                player_id INTEGER,
                FOREIGN KEY (player_id) REFERENCES player(player_id)
                );
            """;

    public static void init() throws SQLException {
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TABLE_PLAYER);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        preparedStatement = connection.prepareStatement(TABLE_GAME);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}
