import controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Board;
import model.Game;
import model.Player;

import java.io.IOException;
import java.util.Objects;

public class MasterMind extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            var url = getClass().getResource("/view/mainView.fxml");
            var loader = new FXMLLoader(url);

            loader.setClassLoader(getClass().getClassLoader());
            BorderPane rootLayout = loader.load();

            MainController controller = loader.getController();
            // TODO: gra będzie tworzona po zalogowaniu się i wybraniu poziomu trudności w menu

            Game game = new Game(new Player("test123", "test"), 12);
            controller.setModel(game);

            configureStage(primaryStage, rootLayout);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureStage(Stage primaryStage, BorderPane rootLayout) {
        var scene = new Scene(rootLayout);
        scene.getStylesheets().add(
                Objects.requireNonNull(
                                MasterMind.class.getResource("css/styles.css"))
                        .toExternalForm()
        );

        primaryStage.setScene(scene);
        primaryStage.setTitle("MasterMind \uD83E\uDD76"); // 🥶

//        primaryStage.minWidthProperty().bind(rootLayout.minWidthProperty());
//        primaryStage.minHeightProperty().bind(rootLayout.minHeightProperty());
    }
}