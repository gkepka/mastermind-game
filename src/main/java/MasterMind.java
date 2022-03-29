import controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
            // load layout from FXML file
            var loader = new FXMLLoader();
            loader.setLocation(MasterMind.class.getResource("view/mainView.fxml"));
            loader.setClassLoader(getClass().getClassLoader());
            BorderPane rootLayout = loader.load();

            // set initial data into controller
            MainController controller = loader.getController();
            // TODO: gra będzie tworzona po zalogowaniu się i wybraniu poziomu trudności w menu
            Game game = new Game(new Player("test123", "test"), 12);
            controller.setModel(game);
            // add layout to a scene and show them all
            configureStage(primaryStage, rootLayout);
            primaryStage.show();

        } catch (IOException e) {
            // don't do this in common apps
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