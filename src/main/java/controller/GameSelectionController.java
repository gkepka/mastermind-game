package controller;

import dao.GameDao;
import events.GameSelectionEvent;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import model.game.Player;

import java.io.IOException;

public class GameSelectionController extends AnchorPane {

    private static final Integer[] difficultyLevels = { 12, 11, 10, 9, 8, 7, 6, 5 };

    private Player player;

    @FXML
    private Label playerLabel;
    @FXML
    private Button newGame;
    @FXML
    private ChoiceBox<Integer> difficulty;
    @FXML
    private Label playerAverage;
    @FXML
    private TableView<Pair<String, Integer>> playerRank;
    @FXML
    private TableView<Pair<String, Integer>> ranking;


    public GameSelectionController() {
        try {
            var url = getClass().getResource("/view/gameSelectionView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            newGame.addEventHandler(ActionEvent.ACTION, this::onNewGame);
            difficulty.getItems().addAll(difficultyLevels);
            difficulty.setValue(difficultyLevels[0]);

            this.initializeTable(playerRank);
            this.initializeTable(ranking);

            this.updateRanking();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public void updateRanking() {
        var gameDao = new GameDao();
        var ranking = gameDao.getRanking();

        this.ranking.getItems().clear();
        this.ranking.getItems().addAll(ranking);
    }

    private void initializeTable(TableView<Pair<String, Integer>> tableView) {
        TableColumn<Pair<String, Integer>, String> nameColumn = new TableColumn<>("Gracz");
        nameColumn.setCellValueFactory(cellData -> {
            var row = cellData.getValue();
            return new ReadOnlyStringWrapper(row.getKey());
        });

        TableColumn<Pair<String, Integer>, Number> resultColumn = new TableColumn<>("Wynik");
        resultColumn.setCellValueFactory(cellData -> {
            var row = cellData.getValue();
            return new ReadOnlyIntegerWrapper(row.getValue());
        });

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(resultColumn);
    }

    private void onNewGame(ActionEvent event) {
        var gameDifficulty = difficulty.getValue();
        var gameSelectionEvent = new GameSelectionEvent(gameDifficulty);

        this.fireEvent(gameSelectionEvent);
    }

    public Player getModel() {
        return player;
    }

    public void setModel(Player player) {
        var gameDao = new GameDao();
        this.player = player;
        playerLabel.setText(String.format("Witaj %s!", player.getLogin()));

        double average = gameDao.getPlayerAverage(player);
        playerAverage.setText(String.format("Twoja średnia: %f", average));

        var playerGames = gameDao.getPlayerGames(player);
        playerRank.getItems().clear();
        playerRank.getItems().addAll(playerGames);
    }
}
