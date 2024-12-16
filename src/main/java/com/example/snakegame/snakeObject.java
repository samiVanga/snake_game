package com.example.snakegame;

import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * The abstract class snakeObject is the base class for objects
 * in the Snake game. It defines common attributes and methods for game objects.
 * this was moved from the provided model class.
 * @author Samiksha Vanga
 */

public abstract class snakeObject {
    int x;
    int y;
    Image i;
    int w;
    int h;
    /**
     * used to set if it is alive or dead
     */
    public boolean l; //this is it is alive or dead
    /**
     * default constructor is used to initialise the SnakeObject
     */
    /**
     * Draws the game object on the specified graphics context.
     *
     * @param g The graphics context to draw on.
     */
    public abstract void draw(GraphicsContext g);
    /**
     * Gets the bounding box of the game object.
     *
     * @return The bounding box of the game object.
     */
    public BoundingBox getBoundsInParents() {
        return new javafx.geometry.BoundingBox(x,y,w, h);
    }
    /**
     * Gets the rectangle representing the game object.
     *
     * @return The rectangle representing the game object.
     */
    public Rectangle getRectangle(){
        return new Rectangle(x,y,w,h);
    }
    /**
     * Gets the image of the game object.
     *
     * @return The image of the game object.
     */
    public Object getI() {
        return i;
    }
    /**
     * Gets the width of the game object.
     *
     * @return The width of the game object.
     */
    public int getW() {
        return w;
    }
    /**
     * Gets the height of the game object.
     *
     * @return The height of the game object.
     */
    public int getH() {
        return h;
    }
    /**
     * Gets the x-coordinate of the game object.
     *
     * @return The x-coordinate of the game object.
     */
    public int getX() {
        return x;
    }
    /**
     * Gets the y-coordinate of the game object.
     *
     * @return The y-coordinate of the game object.
     */
    public int getY() {
        return y;
    }
    /**
     * Checks if the game object is active.
     *
     * @return true if the game object is active, false otherwise.
     */
    public boolean isL() {
        return l;
    }
}

