package dao;

import javafx.util.Pair;
import model.game.Game;
import model.game.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameDao {

    private static final String SAVE_SQL = "INSERT INTO game(result, player_id) VALUES (?, ?);";
    private static final String UPDATE_SQL = "UPDATE game SET result = ?, player_id = ? WHERE game_id = ?;";
    private static final String PLAYER_AVG_SQL = "SELECT AVG(result) FROM game WHERE player_id = ?;";

    private static final String PLAYER_RANK_SQL = """
            SELECT login, result FROM game
                INNER JOIN player ON player.player_id = game.player_id
                WHERE player.player_id = ?
                ORDER BY game.result DESC
                LIMIT 10;
            """;

    private static final String RANKING_SQL = """
            SELECT login, result FROM game
                INNER JOIN player p on game.player_id = p.player_id
                ORDER BY game.result DESC
                LIMIT 10;
            """;

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

    public double getPlayerAverage(Player player) {
        var conn = ConnectionProvider.getConnection();
        double average = 0.0;

        try (var statement = conn.prepareStatement(PLAYER_AVG_SQL)) {
            statement.setObject(1, player.getId());
            var rs = statement.executeQuery();

            if (rs.next())
                average = rs.getDouble(1);

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return average;
    }

    public List<Pair<String, Integer>> getPlayerGames(Player player) {
        var conn = ConnectionProvider.getConnection();
        var result = new ArrayList<Pair<String, Integer>>();

        try (var statement = conn.prepareStatement(PLAYER_RANK_SQL)) {
            statement.setObject(1, player.getId());
            var rs = statement.executeQuery();

            while (rs.next())
                result.add(new Pair<>(rs.getString(1), rs.getInt(2)));

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Pair<String, Integer>> getRanking() {
        var conn = ConnectionProvider.getConnection();
        var result = new ArrayList<Pair<String, Integer>>();

        try (var statement = conn.prepareStatement(RANKING_SQL)) {
            try (var rs = statement.executeQuery()) {
                while (rs.next())
                    result.add(new Pair<>(rs.getString(1), rs.getInt(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Optional<Game> create(final Player player, final Object result) {
        Game game = new Game(player, (int) result);
        this.save(game);
        return Optional.of(game);
    }
}
