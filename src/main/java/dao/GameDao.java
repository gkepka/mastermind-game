package dao;

import model.game.Game;
import model.game.Player;

import java.sql.*;
import java.util.Optional;

public class GameDao {

    private final String SAVE_SQL = "INSERT INTO game(result, player_id) VALUES (?, ?);";
    private final String UPDATE_SQL = "UPDATE game SET result = ?, player_id = ? WHERE game_id = ?;";

    public void save(Game game) {
        Connection conn = ConnectionProvider.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
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
        Connection conn = ConnectionProvider.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_SQL)){
            statement.setObject(1, game.getResult());
            statement.setObject(2, game.getPlayer().getId());
            statement.setObject(3, game.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Game> create(final Player player, final Object result) {
        Game game = new Game(player, (int)result);
        this.save(game);
        return Optional.of(game);
    }
}
