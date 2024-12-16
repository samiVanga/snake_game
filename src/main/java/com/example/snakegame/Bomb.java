package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
/**
 * The Bomb class represents a bomb object in the Snake game.
 * It extends the snakeObject class and defines behavior specific to bombs.
 * @author Samiksha Vanga
 */
public class Bomb extends snakeObject{
    /**
     * Constructs a new Bomb instance, initializing its position and appearance.
     * The bomb is randomly placed within the game area.
     */
    public Bomb() {
        this.l = true;
        this.i = ImageLoader.images.get("bomb"); //this loads the bomb image.
        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();
        this.x = (int) (Math.random() * (870 - w + 10)); //this creates a random x coordinate to place the image
        this.y = (int) (Math.random() * (560 - h - 40)); //this creates a random y coordinate.
    }
    /**
     * Handles the explosion of the bomb when the snake interacts with it.
     * If the snake collides with the bomb, the bomb is marked as exploded, the snake's score is decreased,
     * and the snake's status is set to 'not alive'.
     *
     * @param mySnake The snake object to check for collision.
     */

    public void exploded(MySnake mySnake) {
        if (mySnake.getRectangle().intersects(getBoundsInParents()) && mySnake.l && l) {
            this.l = false;  //if the snake touches the bomb it dies.
            mySnake.score -= 2; //reduces the score by 2.
            mySnake.l = false; //this kills the snake
            MySnake.checkScore(); // this checks if the score when it dies is a highscore
            }
        }
    /**
     * Draws the bomb on the specified GraphicsContext.
     *
     * @param g The GraphicsContext used for drawing.
     */

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(i, x, y);
    }
}
