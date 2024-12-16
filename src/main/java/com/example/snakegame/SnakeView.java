package com.example.snakegame;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * The View class represents the graphical user interface of the Snake game.
 * It extends the JavaFX Pane class and includes methods to handle game events,
 * start and stop the game loop, and draw the game components on the canvas.
 * @author Samiksha Vanga

 */

public class SnakeView extends Pane {
    private SnakeController controller;
    private boolean processKeyEvents =true;
    private boolean isPaused=true;
    private SnakeModel snakeModel;
    private Canvas canvas;
    private AnimationTimer Loop;
    private long lastUpdate =0;
    private long speedDelay= 25_000_000;
    MusicPlayer musicPlayer;
    private Button quitButton;
    private Button restartButton;
    private Button pauseButton;
    private ToggleButton musicToggleButton;
    private ToggleButton backgroundToggleButton;
    private List<String> backgroundImages;
    private int currentBackgroundIndex;
    /**
     * Constructs a new View instance with the specified controller and model.
     *
     * @param controller The controller for handling user input.
     * @param snakeModel      The model representing the game state.
     */
    public SnakeView(SnakeController controller, SnakeModel snakeModel){
        this.controller=controller;
        this.snakeModel = snakeModel;
        initialiseMusic();
        loadFrame();

    }
    /**
     * Initializes the music player.
     */
    public void initialiseMusic(){
        musicPlayer =new MusicPlayer("frogger.mp3");
        musicPlayer.play();
    }
    /**
     * Loads the graphical components of the game frame, including buttons and canvas.
     */
    public void loadFrame() {
        String cssPath = getClass().getResource("/styling.css").toExternalForm();
        getStylesheets().add(cssPath);
        canvas= new Canvas(870,560);
        quitButton = new Button("END GAME");
        quitButton.setOnAction(event -> handleQuitButtonAction());
        quitButton.setLayoutX(750);
        quitButton.setLayoutY(10);
        restartButton = new Button("RESTART");
        restartButton.setOnAction(event -> handleRestartButtonAction());
        restartButton.setLayoutX(630);
        restartButton.setLayoutY(10);
        pauseButton = new Button("PAUSE");
        pauseButton.setOnAction(event -> handlePauseButtonAction());
        pauseButton.setLayoutX(530);
        pauseButton.setLayoutY(10);
        musicToggleButton = new ToggleButton("Toggle Music");
        musicToggleButton.getStyleClass().add("ToggleButton");
        musicToggleButton.setOnAction(event -> handleMusicToggleAction());
        musicToggleButton.setLayoutX(400);
        musicToggleButton.setLayoutY(10);

        getChildren().addAll(canvas,quitButton,restartButton,pauseButton,musicToggleButton);
        // Set focus to the game scene
        setFocusTraversable(true);
        requestFocus();
    }
    /**
     * Handles the action when the Pause button is clicked.
     */
    private void handlePauseButtonAction() {
        isPaused=!isPaused; //switches between paused and unpaused
        processKeyEvents =!isPaused;
        if(!isPaused){
            stopGameLoop();}
        else{
            startGameLoop();}
    }
    /**
     * Handles the action when the Music button is clicked.
     */
    private void handleMusicToggleAction() { //stops and starts the music
        if (musicToggleButton.isSelected()) {
            musicPlayer.stopMusic();
        } else {
            musicPlayer.play();
        }
        setFocusTraversable(true);
        requestFocus();
    }

    private void handleRestartButtonAction() {
        stopGameLoop();
        snakeModel = new SnakeModel();  // Create a new model
        MySnake mySnake = new MySnake(100,100);  // Get the new snake
        MySnake.score=0;
        SnakeModel.newBombCount=4;
        SnakeModel.minBombCount=0;
        mySnake.resetDirectionToRight();
        startGameLoop();
    }

    /**
     * this starts the game when the start button is pressed
     */

    public void startGameLoop(){
        Loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= speedDelay) {
                    snakeModel.update();
                    draw();
                    lastUpdate = now;
                }}};
        Loop.start();
        setFocusTraversable(true);
        requestFocus();
    }

    /**
     * handles the functionality to draw the canvas
     */
    public void draw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
        snakeModel.draw(gc);
        drawScore(gc, snakeModel.getScore());
    }
    /**
     * This draws the score on the screen.
     *
     * @param gc The GraphicsContext used for drawing.
     * @param score The score of the snake.
     */
    private void drawScore(GraphicsContext gc,int score){
        gc.setFont(new javafx.scene.text.Font(30));
        gc.setFill(Color.MAGENTA);
        gc.fillText("SCORE: " + score, 20,40);
        gc.fillText("HighScore: "+ SnakeModel.highScore,20,80);
        gc.fillText("Current Player: "+ Controller.getPlayerName(),20,120);
        gc.fillText("Level: "+SnakeModel.level,20,150);
    }

    /**
     * Handles the functionality to stop the game loop
     */
    public void stopGameLoop(){
        if (Loop != null){
            Loop.stop();
        }}
    /**
     * Handles the action when the Quit button is clicked.
     */
    public void handleQuitButtonAction() {
        MySnake.resetDirectionToRight();
        MySnake.score=0;
        SnakeModel.minBombCount=0;
        SnakeModel.newBombCount=4;
        stopGameLoop();
        musicPlayer.stopMusic();
        Stage currentStage = (Stage) getScene().getWindow();
        currentStage.close();  // Close the application
        try {
            // Load hello-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            StackPane root = loader.load();

            // Create a new stage and set the scene
            Stage helloStage = new Stage();
            Scene helloScene = new Scene(root);
            helloStage.setScene(helloScene);
            helloStage.setTitle("Hello View");
            // Show the helloStage
            helloStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }}

}
