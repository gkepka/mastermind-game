package model.game;

public class Player {
    private final String login;
    private final String email;
    private final String password;
    private Long id;

    public Player(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Player(Long id, String login, String email, String password) {
        this(login, email, password);
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
