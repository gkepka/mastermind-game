package dao;

import model.game.Player;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.Optional;

public class PlayerDao {

    private static final String SAVE_SQL = "INSERT INTO player(login, email, password) VALUES (?, ?, ?);";
    private static final String UPDATE_SQL = "UPDATE player SET login = ?, email = ?, password = ? WHERE player_id = ?;";
    private static final String TAKEN_CHECK_SQL = "SELECT * FROM player WHERE login = (?) OR email = (?);";
    private static final String LOGIN_CHECK_SQL = "SELECT * FROM player WHERE login = (?);";

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public boolean save(Player player) {
        var conn = ConnectionProvider.getConnection();

        try (var statement = conn.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, player.getLogin());
            statement.setObject(2, player.getEmail());
            statement.setObject(3, player.getPassword());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                player.setId(rs.getLong("player_id"));
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
        String passwordHash = bCryptPasswordEncoder.encode(password);
        Player player = new Player(login, email, passwordHash);

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
            ResultSet rs = statement.executeQuery();

            if(!rs.isClosed() && bCryptPasswordEncoder.matches(password, rs.getString("password"))) {
                return Optional.of(
                        new Player(
                                rs.getLong("player_id"),
                                rs.getString("login"),
                                rs.getString("email"),
                                rs.getString("password")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
