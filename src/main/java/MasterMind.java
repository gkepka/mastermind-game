import controller.GameOverController;
import controller.GameSelectionController;
import controller.LoginRegisterController;
import controller.GameController;

import dao.ConnectionProvider;
import dao.DatabaseInitializer;
import events.GameExitEvent;
import events.GameSelectionEvent;
import events.PlayerLoginEvent;
import events.GameFinishedEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;

import java.sql.SQLException;
import java.util.Objects;

public class MasterMind extends Application {
    private GameController gameController;
    private LoginRegisterController loginRegisterController;
    private GameSelectionController gameSelectionController;
    private GameOverController gameOverController;
    private Stage primaryStage;

    private final EventHandler<PlayerLoginEvent> onLogin = e -> {
        this.gameSelectionController.setModel(e.getPlayer());
        this.configureStage(this.primaryStage, this.gameSelectionController);
    };

    private final EventHandler<GameSelectionEvent> onNewGame = e -> {
        var player = this.gameSelectionController.getModel();

        if (player != null) {
            var game = new Game(player, e.getGameDifficulty());
            this.gameController.setModel(game);
            this.configureStage(this.primaryStage, this.gameController);
        }
    };

    private final EventHandler<GameFinishedEvent> onGameOver = e -> {
        this.gameOverController.setResultValue(e.getResult());
        this.configureStage(this.primaryStage, this.gameOverController);
    };

    private final EventHandler<GameExitEvent> onGameExit = e ->
        this.configureStage(this.primaryStage, this.gameSelectionController);

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
        primaryStage.addEventHandler(GameExitEvent.GAME_EXIT, onGameExit);
        configureStage(primaryStage, loginRegisterController);
    }

    private void configureStage(Stage primaryStage, Parent rootLayout) {
        var rootScene = rootLayout.getScene();

        var scene = rootScene == null
                ? new Scene(rootLayout)
                : rootScene;

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