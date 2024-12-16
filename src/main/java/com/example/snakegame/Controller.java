package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The HelloController class is responsible for controlling the main menu of the Snake game.
 * It handles user interactions, such as starting the game, showing game rules, and managing the player name.
 * @author Samiksha vanga-modified
 */
public class Controller {
    /**
     * default constructor to initialise the contructor.
     */
    /**
     * the text field for the name
     */
    @FXML
    public  TextField nameField;

    @FXML
    private Label welcomeText;
    /**
     * variable of SnakeView
     */
    public SnakeView snakeView;
    /**
     * the name of the player
     */
    public static String name;
    /**
     * the scene for the main game
     */
    public static Scene GameScene;
    /**
     * the stage for the main game
     */
    public static Stage snakeGameStage;

    /**
     * Gets the player name entered in the name field.
     *
     * @return The player name.
     */

    public static String getPlayerName() {
        return name;
    }

    /**
     * Handles the button click event to start the Snake game.
     */
    @FXML
    protected void onHelloButtonClick() {
        SnakeModel snakeModel = new SnakeModel();
        SnakeController controller = new SnakeController(snakeModel);
        snakeView = new SnakeView(controller, snakeModel);
        snakeView.loadFrame();
        String playerName = nameField.getText();
        name=playerName;
        System.out.println(playerName);
        if(playerName.isEmpty()){ //if the player does not enter a name
            snakeView.stopGameLoop();
            snakeView.musicPlayer.stopMusic();
            Alert alert = new Alert(Alert.AlertType.WARNING); // an alert message shows to prompt the user.
            alert.setContentText("Please write a player name");
            alert.show();
            return;
        }
        //this adds the background to the frame
        snakeView.setBackground(new Background(new BackgroundImage(
                new Image("/background.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        //A new Scene is created and the dimentions are set
        GameScene = new Scene(snakeView, 870, 560);
        snakeGameStage = new Stage();
        snakeGameStage.setTitle("Snakee Yipee");
        snakeGameStage.setScene(GameScene);
        snakeGameStage.setOnCloseRequest(event1 -> {
            //when the stage is closed the game loop stops
            snakeView.stopGameLoop();

        });
        GameScene.setOnKeyPressed(e -> SnakeController.keyPressed(e));
        snakeGameStage.show();
        //this starts the game loop
        snakeView.startGameLoop();
        Stage currentStage = (Stage) welcomeText.getScene().getWindow();
        currentStage.hide();
    }
    /**
     * Shows the game rules in an alert dialog.
     *
     * @throws IOException If an error occurs while showing the game rules.
     */

    public void showGameRules() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Set the title and header text
        alert.setTitle("Game Rules");
        alert.setHeaderText(null);

        // Set the content text with the game rules
        alert.setContentText(
                "Game Rules:\n" +
                        "• When the snake eats a fruit the score increases by 1.\n" +
                        "• When the snake eats a bomb the score decreases by 2 and the snake dies.\n" +
                        "• When the snake eats itself or touches the border of the screen then the snake dies.\n" +
                        "• Every multiple of 10 for the score the number of potential bombs increases by 4.\n"+
                        "• There is a Restart button that allows the player to restart the game\n"+
                        "• There is an End Gamae button that takes the player back to the main menu\n"+
                        "• You can mute and unmute the music by pressing music toggle\n"  );
        // Show the alert
        alert.showAndWait();
    }
}

