import controller.GameOverController;
import controller.GameSelectionController;
import controller.LoginRegisterController;
import controller.game.GameController;

import dao.ConnectionProvider;
import dao.DatabaseInitializer;
import dao.GameDao;
import events.GameExitEvent;
import events.GameSelectionEvent;
import events.PlayerLoginEvent;
import events.GameFinishedEvent;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.game.Game;
import notification.MailSender;

import java.sql.SQLException;
import java.util.Objects;

public class MasterMind extends Application {
    private final GameController gameController;
    private final LoginRegisterController loginRegisterController;
    private final GameSelectionController gameSelectionController;
    private final GameOverController gameOverController;
    private Stage primaryStage;

    public MasterMind() {
        this.gameController = new GameController();
        this.loginRegisterController = new LoginRegisterController();
        this.gameSelectionController = new GameSelectionController();
        this.gameOverController = new GameOverController();
    }

    private void onLogin(PlayerLoginEvent event) {
        this.gameSelectionController.setModel(event.getPlayer());
        this.configureStage(this.primaryStage, this.gameSelectionController);
    }

    private void onGameOver(GameFinishedEvent event) {
        this.gameOverController.setResultValue(event.getResult());
        this.configureStage(this.primaryStage, this.gameOverController);
    }

    private void onGameExit(GameExitEvent event) {
        var game = this.gameController.getModel();
        var gameDao = new GameDao();
        gameDao.save(game);

        this.gameSelectionController.updateRanking();
        this.configureStage(this.primaryStage, this.gameSelectionController);
        String address = this.gameSelectionController.getModel().getEmail();
        String gameOverMessage = "You earned " + game.getResult() + " points at Mrozon-Mastermind game.";
        MailSender.getInstance().sendMail(address, gameOverMessage);

    }

    private void onNewGame(GameSelectionEvent event) {
        var player = this.gameSelectionController.getModel();

        if (player != null) {
            var game = new Game(player, event.getGameDifficulty());
            this.gameController.setModel(game);
            this.configureStage(this.primaryStage, this.gameController);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            DatabaseInitializer.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        primaryStage.addEventHandler(PlayerLoginEvent.PLAYER_LOGIN, this::onLogin);
        primaryStage.addEventHandler(GameSelectionEvent.GAME_SELECTION, this::onNewGame);
        primaryStage.addEventHandler(GameFinishedEvent.GAME_FINISHED, this::onGameOver);
        primaryStage.addEventHandler(GameExitEvent.GAME_EXIT, this::onGameExit);
        primaryStage.setTitle("MasterMind \uD83E\uDD76"); // 🥶
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            logout(primaryStage);
        });

        configureStage(primaryStage, loginRegisterController);
    }

    private void configureStage(Stage primaryStage, Parent rootLayout) {
        var rootScene = rootLayout.getScene();

        if (rootScene == null) {
            rootScene = new Scene(rootLayout);

            var url = getClass().getResource("css/styles.css");
            var stylesheet = Objects.requireNonNull(url).toExternalForm();
            rootScene.getStylesheets().add(stylesheet);
        }

        primaryStage.setScene(rootScene);
        primaryStage.show();
    }

    private void logout(Stage stage) {
        ConnectionProvider.close();
        stage.close();
    }
}