/**
 * ================================= PONG ======================================
 * This project follows a model-view-controller pattern.
 * The view is the fxml file, which controls the way the UI looks.
 * The controller is the controller class, which takes in User input and changes
 * the model.
 * The model classes are the Pong classes, which are OOP ways to represent the
 * Pong games.
 */

package pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int HEIGHT = 480;
    public static final int WIDTH = 640;

    private Scene classicPong;

    public void initialize() throws Exception {
        setupClassicPong();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();

        primaryStage.setTitle("Classic Pong");
        primaryStage.setScene(classicPong);
        primaryStage.show();

        classicPong.getRoot().requestFocus();
    }

    private void setupClassicPong() throws Exception {

        // Setup the game
        ClassicPong game = ClassicPong.getInstance(WIDTH, HEIGHT);

        // Setup the Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("classic_pong.fxml"));
        Parent root = loader.load();

        // Adding Components to the Root
        ((Pane) root).getChildren().add(game.getBall().getSquare());
        ((Pane) root).getChildren().add(game.getPlayer1().getPaddle().getRectangle());
        ((Pane) root).getChildren().add(game.getPlayer2().getPaddle().getRectangle());

        // Setup the controller
        ClassicPongController controller = (ClassicPongController)loader.getController();
        controller.setGame(game);

        // Finish setting up the scene;
        classicPong = new Scene(root, game.getWidth(), game.getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
