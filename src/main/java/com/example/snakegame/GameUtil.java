package com.example.snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

import javafx.scene.transform.Rotate;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;

/**
 * The GameUtil class provides utility methods for handling images and transformations in the Snake game.
 * @author Samiksha vanga-modified
 */
public class GameUtil {
    /**
     * default constructor to initialise the gameUtil.
     */

    /**
     * Retrieves an Image from the specified image path.
     *
     * @param imagePath The path to the image resource.
     * @return The Image object loaded from the specified path.
     */
    public static Image getImage(String imagePath) {
        InputStream inputStream = GameUtil.class.getClassLoader().getResourceAsStream(imagePath);

        Image image = null;
        try {
            image = new Image(inputStream);
        } catch (Exception e) {
            System.err.println("ERROR: SPECIFIC IMAGE NOT FOUND !\n");
            e.printStackTrace();
        }
        return image;
    }
    /**
     * Rotates the given Image by the specified degree.
     *
     * @param bufferedImage The Image to be rotated.
     * @param degree        The degree of rotation.
     * @return The rotated Image.
     */
    public static Image rotateImage(Image bufferedImage, int degree) {
        ImageView imageView = new ImageView(bufferedImage);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        Rotate rotate = new Rotate(degree, imageView.getFitWidth() / 2, imageView.getFitHeight() / 2);
        imageView.getTransforms().add(rotate);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        return imageView.snapshot(params, null);
    }

}
