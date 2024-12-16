package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Model class represents the core logic and state management of the Snake game.
 * It handles the game elements, such as the snake, food, bombs, and scoring.
 * @author Samiksha vanga-modified
 */
public class SnakeModel {

    public static MySnake mySnake;
    public static Food food;
    /**
     * stores the bombs that should be displayed
     */
    public static List<Bomb> bombs;
    /**
     * used to store the background image of the game
     */
    public static Image background;
    /**
     * used to store the end game image
     */
    public static Image fail;
    private SnakeController controller;
    /**
     * used to store the high score of the player and their name
     */
    public static String highScore = "";
    /**
     * the minumum number of bombs that can be displayed
     */
    public static int minBombCount = 0;
    /**
     * the maximum number of bombs that can be diaplayed
     */
    public static int newBombCount = 4;
    /**
     * the levels of the game
     */
    public static int level = 0;

    /**
     * Constructs a new Model instance, initializing the game elements and setting the initial state.
     */
    public SnakeModel() {
        this.controller = controller;
        mySnake = new MySnake(100, 100);
        food = new Food();
        bombs = new ArrayList<>();
        background = ImageLoader.images.get("background");
        fail = ImageLoader.images.get("game-scene-01");
    }

    /**
     * Spawns bombs based on a random count.
     */
    private static void spawnBombs() {
        int numBombs = new Random().nextInt(newBombCount - minBombCount + 1) + minBombCount;
        bombs.clear();
        for (int i = 0; i < numBombs; i++) {
            bombs.add(new Bomb());
        }
    }

    /**
     * Updates the game state, including snake movement, collision detection, and bomb explosion.
     */
    public static void update() {
        if (mySnake.l) { //when the snake is alive
            mySnake.move();
            mySnake.outOfBounds();
            if (food.l) {
                food.eaten(mySnake);
            } else {
                food = new Food();
                bombs.clear();
                increaseBombCount();
                spawnBombs();
            }
            for (Bomb bomb : bombs) {
                if (bomb.l) {
                    bomb.exploded(mySnake);
                }
            }
        }
    }

    /**
     * Increases the bomb count when the snake's score is a multiple of 10.
     */
    public static void increaseBombCount() {
        if (mySnake.getScore() % 10 == 0) {
            bombs.clear();
            int increaseBy = 4; //the number of potential bombs increases by 4
            minBombCount = minBombCount + 2;
            newBombCount = newBombCount + increaseBy;
            level += 1;
            for (int i = 0; i < newBombCount; i++) {
                bombs.add(new Bomb());
            }
        }
    }

    /**
     * Draws the game elements, including the snake, food, bombs, background, and failure screen.
     *
     * @param gc The GraphicsContext used for drawing.
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(background, 0, 0);
        if (mySnake.l) {
            mySnake.draw(gc);
            if (food.l) {
                food.draw(gc);
            }
            for (Bomb bomb : bombs) {
                if (bomb.l) {
                    bomb.draw(gc);
                }
            }
            if (highScore.equals("")) {
                // initialize the high score
                highScore = this.getHighScore();
            }
        } else {
            // when the player loses, display the end image
            gc.drawImage(fail, 200, 50);
        }
    }

    /**
     * Gets the current score of the snake.
     *
     * @return The current score.
     */
    public static int getScore() {
        return mySnake.score;
    }

    /**
     * Retrieves the high score from the file.
     *
     * @return The high score as a String.
     */
    public String getHighScore() {
        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highScore.txt");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        } catch (IOException e) {
            return "0";
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
