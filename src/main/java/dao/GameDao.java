package dao;

import model.game.Game;
import model.game.Player;

import java.sql.*;
import java.util.Optional;

public class GameDao {

    private static final String SAVE_SQL = "INSERT INTO game(result, player_id) VALUES (?, ?);";
    private static final String UPDATE_SQL = "UPDATE game SET result = ?, player_id = ? WHERE game_id = ?;";
    private static final String RANKING_SQL = "SELECT AVG(result) FROM game WHERE player_id = ?;";

    public void save(Game game) {
        var conn = ConnectionProvider.getConnection();

        try (var statement = conn.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, game.getResult());
            statement.setObject(2, game.getPlayer().getId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            while (rs.next()) {
                game.setId(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Game game) {
        var conn = ConnectionProvider.getConnection();

        try (var statement = conn.prepareStatement(UPDATE_SQL)) {
            statement.setObject(1, game.getResult());
            statement.setObject(2, game.getPlayer().getId());
            statement.setObject(3, game.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getPlayerStatistics(Player player) {
        var conn = ConnectionProvider.getConnection();
        try (var statement = conn.prepareStatement(RANKING_SQL)) {
            statement.setObject(1, player.getId());
            var rs = statement.executeQuery();

            if (rs.next())
                return rs.getDouble(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public Optional<Game> create(final Player player, final Object result) {
        Game game = new Game(player, (int) result);
        this.save(game);
        return Optional.of(game);
    }
}
