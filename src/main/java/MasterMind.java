import controller.GameOverController;
import controller.GameSelectionController;
import controller.LoginRegisterController;
import controller.GameController;

import dao.ConnectionProvider;
import dao.DatabaseInitializer;
import dao.GameDao;
import events.ViewUpdateEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;
import model.Player;

import javax.swing.text.View;
import java.sql.SQLException;
import java.util.Objects;

public class MasterMind extends Application {
    private GameController gameController;
    private LoginRegisterController loginRegisterController;
    private GameSelectionController gameSelectionController;
    private GameOverController gameOverController;
    private Stage primaryStage;

    private EventHandler<ViewUpdateEvent> onLogin = e -> {
        this.configureStage(this.primaryStage, this.gameSelectionController);
    };

    private EventHandler<ViewUpdateEvent> onNewGame = e -> {
        // TODO: tutaj utworzenie nowej gry i zastosowanie modelu
        this.configureStage(this.primaryStage, this.gameController);
    };

    private EventHandler<ViewUpdateEvent> onGameOver = e -> {
        this.configureStage(this.primaryStage, this.gameOverController);
    };

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

        Game game = new Game(new Player("test123", "test"), 12);

        gameController.setModel(game);
        new GameDao().save(game);

        primaryStage.addEventHandler(ViewUpdateEvent.USER_LOGON, onLogin);
        primaryStage.addEventHandler(ViewUpdateEvent.NEW_GAME, onNewGame);
        primaryStage.addEventHandler(ViewUpdateEvent.GAME_FINISHED, onGameOver);
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

//        primaryStage.minWidthProperty().bind(rootLayout.minWidthProperty());
//        primaryStage.minHeightProperty().bind(rootLayout.minHeightProperty());
    }

    private void logout(Stage stage) {
        ConnectionProvider.close();
        stage.close();
    }
}