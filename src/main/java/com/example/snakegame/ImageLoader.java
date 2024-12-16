package com.example.snakegame;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * The ImageLoader class is responsible for loading and storing images used in the Snake game.
 * It provides a static map containing key-value pairs where the key is a string identifier for an image,
 * and the value is the corresponding Image object.
 * @author Samiksha vanga-modified
 */

public class ImageLoader {

    /**
     * A map to store loaded images with their respective identifiers.
     */
    public static Map<String, Image> images = new HashMap<>();
    /**
     * Static block to initialize the map with predefined images.
     * Images are loaded using the GameUtil class's getImage method.
     */

    static {

        images.put("snake-head-right", GameUtil.getImage("snake-head-right.png"));
        images.put("snake-body", GameUtil.getImage("snake-body.png"));
        images.put("0", GameUtil.getImage("food-kiwi.png"));
        images.put("1", GameUtil.getImage("food-lemon.png"));
        images.put("2", GameUtil.getImage("food-litchi.png"));
        images.put("3", GameUtil.getImage("food-mango.png"));
        images.put("4", GameUtil.getImage("food-apple.png"));
        images.put("5", GameUtil.getImage("food-banana.png"));
        images.put("6", GameUtil.getImage("food-blueberry.png"));
        images.put("7", GameUtil.getImage("food-cherry.png"));
        images.put("8", GameUtil.getImage("food-durian.png"));
        images.put("9", GameUtil.getImage("food-grape.png"));
        images.put("10", GameUtil.getImage("food-grapefruit.png"));
        images.put("11", GameUtil.getImage("food-peach.png"));
        images.put("12", GameUtil.getImage("food-pear.png"));
        images.put("13", GameUtil.getImage("food-orange.png"));
        images.put("14", GameUtil.getImage("food-pineapple.png"));
        images.put("15", GameUtil.getImage("food-strawberry.png"));
        images.put("16", GameUtil.getImage("food-watermelon.png"));
        images.put("bomb", GameUtil.getImage("bombs.png"));
        images.put("UI-background", GameUtil.getImage("background.png"));
        images.put("game-scene-01", GameUtil.getImage("game-scene-01.png"));
    }
}

