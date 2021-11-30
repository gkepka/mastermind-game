import controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Board;

import java.io.IOException;

public class MasterMind extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // load layout from FXML file
            var loader = new FXMLLoader();
            loader.setLocation(MasterMind.class.getResource("view/mainView.fxml"));
            BorderPane rootLayout = loader.load();

            // set initial data into controller
            MainController controller = loader.getController();
            controller.setModel(new Board());
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
        scene.getStylesheets().add(MasterMind.class.getResource("css/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("MasterMind \uD83E\uDD76"); // 🥶

//        primaryStage.minWidthProperty().bind(rootLayout.minWidthProperty());
//        primaryStage.minHeightProperty().bind(rootLayout.minHeightProperty());
    }
}
