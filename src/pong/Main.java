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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.net.URL;

public class Main extends Application {

    public static final int HEIGHT = 480;
    public static final int WIDTH = 640;

    private Scene classicPong;

    private SoundManager soundManager;
    private TextFileManager textFileManager;

    public static final String SOUND_CLASSIC_PONG_PADDLE_COLLISION = "classic_pong_paddle";
    public static final String SOUND_CLASSIC_PONG_SIDE_COLLISION = "classic_pong_side";
    public static final String SOUND_CLASSIC_PONG_END_COLLISION = "classic_pong_end";

    public static final String GAME_LOG_ID = "gamelog";
    public static final String GAME_LOG_PATH = "gamelog.txt";

    public void initialize() throws Exception {
        soundManager = SoundManager.getInstance(4);
        textFileManager = TextFileManager.getInstance();
        setupClassicPong();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();

        primaryStage.setTitle("Classic Pong");
        primaryStage.setScene(classicPong);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                soundManager.shutdown();
            }
        });
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

        // Adding the line in the middle
        int height = HEIGHT / 21;
        for (int i = 0; i < 41; i += 2) {
            Rectangle line = new Rectangle(
                    WIDTH / 2 - WIDTH / 640,
                    i * HEIGHT / 41,
                    WIDTH / 320,
                    HEIGHT / 41
            );
            line.setFill(Paint.valueOf("white"));
            ((Pane) root).getChildren().add(line);
        }

        // Setup the controller
        ClassicPongController controller = (ClassicPongController)loader.getController();
        controller.setGame(game);

        // Finish setting up the scene;
        classicPong = new Scene(root, game.getWidth(), game.getHeight());

        // Load the sounds for ClassicPong
        soundManager.loadSound(SOUND_CLASSIC_PONG_PADDLE_COLLISION, getClass().getResource("resources/classic_paddle.wav"));
        soundManager.loadSound(SOUND_CLASSIC_PONG_END_COLLISION, getClass().getResource("resources/classic_lost_ball.mp3"));
        soundManager.loadSound(SOUND_CLASSIC_PONG_SIDE_COLLISION, getClass().getResource("resources/classic_side.wav"));

        // Load the paths for the TextFileManager
        textFileManager.loadFile(GAME_LOG_ID, GAME_LOG_PATH);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
