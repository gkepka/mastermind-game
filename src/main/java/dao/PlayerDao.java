package dao;

import model.Player;

import java.sql.*;
import java.util.Optional;

public class PlayerDao {

    private static final String SAVE_SQL = "INSERT INTO player(login, email, password) VALUES (?, ?, ?);";
    private static final String UPDATE_SQL = "UPDATE player SET login = ?, email = ?, password = ? WHERE player_id = ?;";
    private static final String TAKEN_CHECK_SQL = "SELECT * FROM player WHERE login = (?) OR email = (?);";
    private static final String LOGIN_CHECK_SQL = "SELECT * FROM player WHERE login = (?) AND password = (?);";

    public boolean save(Player player) {
        var conn = ConnectionProvider.getConnection();

        try (var statement = conn.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, player.getLogin());
            statement.setObject(2, player.getEmail());
            statement.setObject(3, player.getPassword());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                player.setId(rs.getLong(1));
            }
            rs.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void update(Player player) {
        var conn = ConnectionProvider.getConnection();

        try (var statement = conn.prepareStatement(UPDATE_SQL)) {
            statement.setObject(1, player.getLogin());
            statement.setObject(2, player.getEmail());
            statement.setObject(3, player.getPassword());
            statement.setObject(4, player.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Player> create(final String login, final String email, final String password) {
        Player player = new Player(login, email, password);

        return this.save(player)
            ? Optional.of(player)
            : Optional.empty();
    }

    public boolean checkIfTaken(final String login, final String email) {
        var conn = ConnectionProvider.getConnection();

        try (var statement = conn.prepareStatement(TAKEN_CHECK_SQL)) {
            statement.setObject(1, login);
            statement.setObject(2, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<Player> loginCheck(final String login, final String password) {
        var conn = ConnectionProvider.getConnection();

        try (var statement = conn.prepareStatement(LOGIN_CHECK_SQL)) {
            statement.setObject(1, login);
            statement.setObject(2, password);

            ResultSet rs = statement.executeQuery();

            return Optional.of(
                    new Player(
                            rs.getString("login"),
                            rs.getString("email"),
                            rs.getString("password")
                    )
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
