package model.game;

import java.util.ArrayList;

public class Rank {
    public static final int GAMES_IN_TOP = 10;
    private ArrayList<Game> topGames = new ArrayList<>(GAMES_IN_TOP);

    public Rank(){
        // TODO pobieranie z bazy danych przykładowo 10 gier z najwyższymi wynikami i zapisuje do listy
    }

    public ArrayList<Game> getTopGames() {
        return topGames;
    }
}
