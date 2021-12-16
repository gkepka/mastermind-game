package dao;

import model.Player;

import java.sql.*;
import java.util.Optional;

public class PlayerDao {

    private final String SAVE_SQL = "INSERT INTO player(login, name) VALUES (?, ?);";
    private final String UPDATE_SQL = "UPDATE player SET login = ?, name = ? WHERE player_id = ?;";

    public void save(Player player) {
        Connection conn = ConnectionProvider.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
            statement.setObject(1, player.getLogin());
            statement.setObject(2, player.getName());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                player.setId(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Player player) {
        Connection conn = ConnectionProvider.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(UPDATE_SQL)){
            statement.setObject(1, player.getLogin());
            statement.setObject(2, player.getName());
            statement.setObject(3, player.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Player> create(final String login, final String name) {
        Player player = new Player(login, name);
        this.save(player);
        return Optional.of(player);
    }
}
