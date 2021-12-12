package model;

public class Player {
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
