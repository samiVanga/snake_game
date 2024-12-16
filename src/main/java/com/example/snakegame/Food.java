package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
/**
 * The Food class represents a food item in the Snake game.
 * It extends the snakeObject class and handles the logic related to the appearance and consumption of food.
 * this was moved from the provided model class.
 * @author Samiksha Vanga
 */

public class Food extends snakeObject{

    /**
     * Constructs a Food instance, initializing its properties, including position and appearance.
     * The appearance is randomly chosen from available images.
     */
    public Food(){
        this.l=true;
        //this gets a random image for the food
        this.i= ImageLoader.images.get(String.valueOf(new Random().nextInt(16)));
        this.w=(int) i.getWidth();
        this.h=(int) i.getHeight();
        this.x= (int) (Math.random() *(870 - w +10));
        this.y=(int)(Math.random()*(560-h-40));
    }

    /**
     * Checks for collision between the Snake and the Food.
     * If a collision is detected, the Food is marked as consumed, and the Snake's length and score are updated.
     *
     * @param mySnake The Snake object to check for collision with the Food.
     */
    public void eaten(MySnake mySnake){
        if(mySnake.getRectangle().intersects(getBoundsInParents())&& mySnake.l && l){
            this.l=false;
            //if there is a collision then the length of the snake increases by 1
            mySnake.changeLength(mySnake.getLength() +1);
            //the score is incremented by 1
            mySnake.score += 1;

        }
    }
    /**
     * Draws the Food on the specified GraphicsContext.
     *
     * @param g The GraphicsContext on which to draw the Food.
     */
    @Override
    public void draw(GraphicsContext g){g.drawImage(i,x,y);}


}

