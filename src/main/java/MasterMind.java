import controller.LoginRegisterController;
import controller.GameController;

import events.ViewUpdateEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Game;
import model.Player;
import java.util.Objects;

public class MasterMind extends Application {
    private GameController gameController;
    private LoginRegisterController loginRegisterController;
    private Stage primaryStage;

    private EventHandler<ViewUpdateEvent> onLogin = e -> {
        this.configureStage(this.primaryStage, this.gameController);
    };

    public MasterMind() {
        this.gameController = new GameController();
        this.loginRegisterController = new LoginRegisterController();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Game game = new Game(new Player("test123", "test"), 12);

        gameController.setModel(game);

        primaryStage.addEventHandler(ViewUpdateEvent.VIEW_UPDATE, onLogin);

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

//        primaryStage.minWidthProperty().bind(rootLayout.minWidthProperty());
//        primaryStage.minHeightProperty().bind(rootLayout.minHeightProperty());
    }
}