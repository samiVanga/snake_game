/**
 * The com.example.snakegame module contains the main components of the Snake Game application.
 * This module requires JavaFX for user interface components, desktop support for certain features,
 * and JavaFX media for audio functionality.
 * It opens the com.example.snakegame package to JavaFX for FXML loading and exports the
 * com.example.snakegame package for use by other modules.
 *
 * <p>This module is part of the Snake Game application, providing essential functionality and
 * integration with JavaFX for graphical user interface features.

 */


module com.example.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;


    opens com.example.snakegame to javafx.fxml;
    exports com.example.snakegame;
}