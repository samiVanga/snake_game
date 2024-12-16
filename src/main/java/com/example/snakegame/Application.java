package com.example.snakegame;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The HelloApplication class serves as the entry point for the Snake game application.
 * It extends the JavaFX Application class and initializes the primary stage and scene.
 * @author Samiksha vanga-modified
 */
public class Application extends javafx.application.Application {
    /**
     * The start method is called when the application is launched.
     * It sets up the primary stage, loads the FXML file, and configures the scene.
     *
     * @param stage The primary stage of the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml")); //this load the FXML
        StackPane root = new StackPane();
        Image backgroundImage = new Image("/background.png"); //this sets the background image

        BackgroundImage backgroundIMG = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundIMG);
        // Set the background to the root node
        root.setBackground(background);
        Scene scene = new Scene(fxmlLoader.load(), 870, 560); //this creates a new scene and adds the dimentions.

        stage.setTitle("Snake Yipee!");
        stage.setScene(scene);
        stage.show();}
    /**
     * The main entry point for the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}