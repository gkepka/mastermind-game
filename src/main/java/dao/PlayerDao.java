package dao;

import model.Player;

import java.sql.*;
import java.util.Optional;

public class PlayerDao {

    private final String SAVE_SQL = "INSERT INTO player(login, email, password) VALUES (?, ?, ?);";
    private final String UPDATE_SQL = "UPDATE player SET login = ?, email = ?, password = ? WHERE player_id = ?;";

    public void save(Player player) {
        Connection conn = ConnectionProvider.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)){
            statement.setObject(1, player.getLogin());
            statement.setObject(2, player.getEmail());
            statement.setObject(3, player.getPassword());
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
        this.save(player);
        return Optional.of(player);
    }

    public boolean checkIfTaken(final String login, final String email){
        String sql = "SELECT * FROM player WHERE login = (?) OR email = (?)";
        Connection conn = ConnectionProvider.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setObject(1, login);
            statement.setObject(2, email);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Optional<Player> loginCheck(final String login, final String password){
        String sql = "SELECT * FROM player WHERE login = (?) AND password = (?)";
        Connection conn = ConnectionProvider.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(sql)){
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
            //e.printStackTrace();
            System.out.println("Konto nie istnieje");
        }
        return Optional.empty();
    }
}
