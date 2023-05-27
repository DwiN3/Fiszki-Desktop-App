package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import other.Token;

import java.io.IOException;

public class ControllerMainMenu {

    @FXML
    private Label nick_user_menu;

    private Token token  = Token.getInstance();

    @FXML
    private void switchActivity() throws IOException {
        App.setRoot("activity_first_screen");
    }

    public void initialize(){
        nick_user_menu.setText("Witaj "+token.getUserName());

    }
}
