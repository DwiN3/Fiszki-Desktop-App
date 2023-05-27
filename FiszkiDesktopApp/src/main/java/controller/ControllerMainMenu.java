package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import other.Token;

import java.io.IOException;

public class ControllerMainMenu {

    @FXML
    private Label nick_user_menu;

    @FXML
    private ChoiceBox<String> category_choice_box_menu;

    private Token token  = Token.getInstance();
    private String selectedCategory;

    @FXML
    private void switchActivity() throws IOException {
        App.setRoot("activity_first_screen");
    }

    public void initialize(){
        nick_user_menu.setText("Witaj "+token.getUserName());
        category_choice_box_menu.getItems().addAll("zwierzęta", "miejsca", "rzeczy", "zawody", "inne");
        category_choice_box_menu.setValue("zwierzęta");
        category_choice_box_menu.setOnAction(event -> {
            String selectedFunction = category_choice_box_menu.getSelectionModel().getSelectedItem();
            switch (selectedFunction) {
                case "zwierzęta":
                    selectedCategory = "zwierzęta";
                    break;
                case "miejsca":
                    selectedCategory = "miejsca";
                    break;
                case "rzeczy":
                    selectedCategory = "rzeczy";
                    break;
                case "zawody":
                    selectedCategory = "zawody";
                    break;
                case "inne":
                    selectedCategory = "inne";
                    break;
            }
            System.out.println("Selected category: " + selectedCategory);
        });

    }
}
