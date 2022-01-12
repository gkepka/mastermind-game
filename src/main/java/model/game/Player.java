package model.game;

public class Player {
    // TODO: email, hash hasła
    private String login;
    private String email;
    private String password;
    private Long id;

    public Player(){}

    public Player(String login, String email, String password){
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {return email;}

    public String getPassword() {return password;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
