package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class LoginRegisterController extends TilePane {
    @FXML
    private LoginController loginController;
    @FXML
    private RegisterController registerController;

    public LoginRegisterController() {
        try {
            var url = getClass().getResource("/view/loginRegisterView.fxml");
            var loader = new FXMLLoader(url);

            loader.setRoot(this);
            loader.setController(this);
            loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
