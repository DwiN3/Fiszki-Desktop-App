package Controllers;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import Other.GameSettings;
import Other.Token;

import java.io.IOException;

public class ControllerMainMenu {
    @FXML
    private Label nick_user_menu;
    @FXML
    private ChoiceBox<String> category_choice_box_menu;
    @FXML
<<<<<<< Updated upstream
    private Button profile_button_menu,game_quiz_button_menu,game_wpis_button_menu,log_out_button_menu;
    private Token token  = Token.getInstance();
    private GameSettings gameSettings = GameSettings.getInstance();
=======
    private Button profile_button_menu, game_quiz_button_menu, game_wpis_button_menu, log_out_button_menu;
    @FXML
    private void switchActivity(String activity) throws IOException { App.setRoot(activity); }
    private TokenInstance tokenInstance = TokenInstance.getInstance();
    private GameSettingsInstance gameSettingsInstance = GameSettingsInstance.getInstance();
>>>>>>> Stashed changes
    private String selectedCategory;

    public void initialize(){
        nick_user_menu.setText("Witaj "+token.getUserName());
        selectedCategory = "zwierzęta";

        profile_button_menu.setOnAction(event -> {
            try {
                switchActivity("activity_profile");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        game_quiz_button_menu.setOnAction(event -> {
            try {
                gameSettings.setTypeGame("quiz");
                switchActivity("activity_quiz_game");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        game_wpis_button_menu.setOnAction(event -> {
            try {
                gameSettings.setTypeGame("wpis");
                switchActivity("activity_wpis_game");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        log_out_button_menu.setOnAction(event -> {
            token.setToken("");
            token.setUserName("");
            try {
                switchActivity("activity_first_screen");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        category_choice_box_menu.getItems().addAll("zwierzęta","dom", "zakupy", "praca", "zdrowie", "czlowiek", "turystyka","jedzenie","edukacja", "inne");
        category_choice_box_menu.setValue("zwierzęta");
        selectedCategory = "zwierzęta";
        gameSettings.setCategory(selectedCategory);
        category_choice_box_menu.setOnAction(event -> {
            selectedCategory = category_choice_box_menu.getSelectionModel().getSelectedItem();
            System.out.println(selectedCategory);
            gameSettings.setCategory(selectedCategory);
        });
    }
}
