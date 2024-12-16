package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * It handles the snake's movement, collision detection, and scoring.
 * this was moved from the provided model class.
 * @author Samiksha Vanga
 */

public  class MySnake extends snakeObject implements movable {
    private double speed_XY;
    private int length;
    private int num;
    /**
     * initializes the score
     */
    public static int score = 0;
    private static final Image IMG_SNAKE_HEAD = ImageLoader.images.get("snake-head-right");
    /**
     * used to store the body of the snake
     */
    public   List<Point> bodyPoints = new LinkedList<>();
    /**
     * the different directions of the snake
     */

    public static boolean up, down, left, right = true; //the directions of the snake
    private static Image newImgSnakeHead;
    Point point;
    /**
     * Constructs a new snake with the specified position.
     *
     * @param x The x-coordinate of the snake.
     * @param y The y-coordinate of the snake.
     */
    public MySnake(int x, int y) {
        this.l = true;
        this.x = x;
        this.y = y;
        this.i = ImageLoader.images.get("snake-body");
        this.w = (int) i.getWidth();
        this.h = (int) i.getHeight();
        this.speed_XY = 4;
        this.length = 1;
        this.num =  (this.h/(int)speed_XY)/2;
        newImgSnakeHead = IMG_SNAKE_HEAD;
        bodyPoints.add(new Point());
    }

    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength() {
        return length;
    }
    /**
     * Changes the length of the snake.
     *
     * @param length The new length of the snake.
     */
    public void changeLength(int length) {
        this.length = length;
    }
    /**
     * Handles key events for controlling the snake's direction.
     *
     * @param e The key event.
     */

    public static void keyPressed(KeyEvent e) {
            switch (e.getCode()) {
                case UP:
                    if (!down) {
                        up = true;
                        down = false;
                        left = false;
                        right = false;
                        newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, -90);
                    }
                    break;

                case DOWN:
                    if (!up) {
                        up = false;
                        down = true;
                        left = false;
                        right = false;
                        newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, 90);

                    }
                    break;
                case RIGHT:
                    if (!left) {
                        up = false;
                        down = false;
                        left = false;
                        right = true;
                        newImgSnakeHead = IMG_SNAKE_HEAD;
                    }
                    break;
                case LEFT:
                    if (!right) {
                        up = false;
                        down = false;
                        left = true;
                        right = false;
                        newImgSnakeHead = GameUtil.rotateImage(IMG_SNAKE_HEAD, 180);
                    }
                    break;
                default:
                    break;

            }

        }
    /**
     * Resets the snake's direction to right.
     */
    public static void resetDirectionToRight() {
        up = false;
        down = false;
        left = false;
        right = true;
        newImgSnakeHead = IMG_SNAKE_HEAD;
    }
    /**
     * Moves the snake based on its direction.
     */
    public void move() {
        if (up) {
            y -= speed_XY;

        } else if (down) {
            y += speed_XY;
        } else if (left) {
            x -= speed_XY;
        } else if (right) {
            x += speed_XY;
        }

    }


    @Override
    public void draw(GraphicsContext g) {
        outOfBounds();
        eatBody();

        bodyPoints.add(new Point(x, y));
        if (bodyPoints.size() == (this.length + 1) * num) {
            bodyPoints.remove(0);
        }
        g.drawImage(newImgSnakeHead, x, y);
        drawBody(g);
        move();


    }

    /**
     * Draws the snake's body on the canvas.
     *
     * @param g The graphics context.
     */

    public  void drawBody(GraphicsContext g) {
        int length = bodyPoints.size() - 1 - num;
        for (int i = length; i >= num; i -= num) {
            point = bodyPoints.get(i);
            g.drawImage(this.i, point.x, point.y);
        }
    }
    /**
     * Checks if the snake's body collides with itself.
     */
    public void eatBody() {
        for(Point point : bodyPoints){
            for(Point point2 : bodyPoints){
                if (point.equals(point2) && point != point2){
                    this.l=false;
                }
            }
        }
        }
    /**
     * Checks if the snake is out of bounds and updates the score.
     */
    public void outOfBounds() {
        boolean xOut = (x <= 0 || x >= (870 - w));
        boolean yOut = (y <= 0 || y >= (560 - h));
        if (xOut || yOut) {
            checkScore();
            l = false;
        }

    }
    /**
     * Checks the score and updates the high score if necessary.
     */
    public static void checkScore(){
        if(score > Integer.parseInt(SnakeModel.highScore.split(" : ")[1])){
            String name= Controller.getPlayerName();
            SnakeModel.highScore= name + " : " + String.valueOf(score);

            File scoreFile= new File("highScore.txt");
            if(!scoreFile.exists()){
                try{
                    scoreFile.createNewFile();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
            FileWriter writeFile=null;
            BufferedWriter writed = null;;
            try{
                writeFile=new FileWriter(scoreFile);
                writed=new BufferedWriter(writeFile);
                writed.write(SnakeModel.highScore);
            }
            catch(Exception e){
            }
            finally{
                try{
                    if(writed != null){
                        writed.close();
                    }
                }
                catch(Exception e){

                }
            }

        }
    }
    /**
     * Gets the current score of the snake.
     *
     * @return The score of the snake.
     */
    public int getScore() {
        return score;
    }
    /**
     * Sets the current score of the snake.
     *
     * @param Score The current score of the game .
     */
    public void setScore(int Score){
        this.score=score;
    }
}


