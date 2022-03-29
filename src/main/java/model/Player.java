package model;

public class Player {
    // TODO: email, hash hasła
    private String login;
    private String name;

    public Player(){}

    public Player(String login, String name){
        this.login = login;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }
}
