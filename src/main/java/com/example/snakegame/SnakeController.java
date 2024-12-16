package com.example.snakegame;

import javafx.scene.input.KeyEvent;
/**
 * The Controller class acts as the controller in the Model-View-Controller (MVC) architecture of the Snake game.
 * It handles user input and communicates with the Model and View components.
 * @author Samiksha Vanga
 */

public class SnakeController {
    private SnakeModel snakeModel;
    private SnakeView snakeView;
    /**
     * Constructs a Controller instance with a reference to the Model component.
     *
     * @param snakeModel The Model component to associate with the controller.
     */

    public SnakeController(SnakeModel snakeModel){
        this.snakeModel = snakeModel; //this initializes the model.

    }
    /**
     * Handles the key pressed event by delegating it to the static method in the MySnake class.
     * This method serves as the entry point for processing user input related to the game.
     *
     * @param e The KeyEvent representing the key pressed event.
     */

    public static void keyPressed(KeyEvent e){
        MySnake.keyPressed(e);
    }


}


