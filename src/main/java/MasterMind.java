import controller.GameOverController;
import controller.GameSelectionController;
import controller.LoginRegisterController;
import controller.GameController;

import dao.ConnectionProvider;
import dao.DatabaseInitializer;
import events.GameSelectionEvent;
import events.PlayerLoginEvent;
import events.GameFinishedEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;
import model.Player;

import java.sql.SQLException;
import java.util.Objects;

public class MasterMind extends Application {
    private GameController gameController;
    private LoginRegisterController loginRegisterController;
    private GameSelectionController gameSelectionController;
    private GameOverController gameOverController;
    private Stage primaryStage;
    private Player player;

    private final EventHandler<PlayerLoginEvent> onLogin = e -> {
        this.player = e.getPlayer();
        this.configureStage(this.primaryStage, this.gameSelectionController);
    };

    private final EventHandler<GameSelectionEvent> onNewGame = e -> {
        if (this.player != null) {
            var game = new Game(this.player, e.getGameDifficulty());
            this.gameController.setModel(game);
            this.configureStage(this.primaryStage, this.gameController);
        }
    };

    private final EventHandler<GameFinishedEvent> onGameOver = e ->
            this.configureStage(this.primaryStage, this.gameOverController);

    public MasterMind() {
        this.gameController = new GameController();
        this.loginRegisterController = new LoginRegisterController();
        this.gameSelectionController = new GameSelectionController();
        this.gameOverController = new GameOverController();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            DatabaseInitializer.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        primaryStage.addEventHandler(PlayerLoginEvent.PLAYER_LOGIN, onLogin);
        primaryStage.addEventHandler(GameSelectionEvent.GAME_SELECTION, onNewGame);
        primaryStage.addEventHandler(GameFinishedEvent.GAME_FINISHED, onGameOver);
        configureStage(primaryStage, loginRegisterController);
    }

    private void configureStage(Stage primaryStage, Parent rootLayout) {
        var scene = new Scene(rootLayout);

        var url = getClass().getResource("css/styles.css");
        var stylesheet = Objects.requireNonNull(url).toExternalForm();
        scene.getStylesheets().add(stylesheet);

        primaryStage.setScene(scene);
        primaryStage.setTitle("MasterMind \uD83E\uDD76"); // 🥶
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });
    }

    private void logout(Stage stage) {
        ConnectionProvider.close();
        stage.close();
    }
}