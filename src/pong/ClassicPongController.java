package pong;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Charles on 5/18/2015.
 */
public class ClassicPongController implements Initializable {

    // TODO REMOVE DEBUG LABELS HERE AND FXML

    private ClassicPong game;

    @FXML
    Pane table;

    @FXML
    Label wPressed;

    @FXML
    Label sPressed;

    @FXML
    Label upPressed;

    @FXML
    Label downPressed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert table != null : "Background Pane table is null.";
        assert game != null : "Game is null. Set a game for the controller.";

        setupControls();
        startAnimation();
    }

    private void setupControls() {

        System.out.println("Setting up controls...");

        table.setOnKeyPressed(
                (KeyEvent event) -> {
                    if (event.getCode() == KeyCode.W) {
                        game.getPlayer1().getPaddle().setVelocity(-1 * Paddle.PADDLE_VELOCITY);
                        wPressed.setText("W Pressed.");
                    }
                    if (event.getCode() == KeyCode.S) {
                        game.getPlayer1().getPaddle().setVelocity(Paddle.PADDLE_VELOCITY);
                        sPressed.setText("S Pressed");
                    }
                    if (event.getCode() == KeyCode.UP) {
                        game.getPlayer2().getPaddle().setVelocity(-1 * Paddle.PADDLE_VELOCITY);
                        upPressed.setText("Up Pressed");
                    }
                    if (event.getCode() == KeyCode.DOWN){
                        game.getPlayer2().getPaddle().setVelocity(Paddle.PADDLE_VELOCITY);
                        downPressed.setText("Down Pressed");
                    }
                    if (event.getCode() == KeyCode.SPACE) {
                        // DO NOTHING
                    }
                }
        );

        table.setOnKeyReleased(
                (KeyEvent event) -> {
                    if (event.getCode() == KeyCode.W) {
                        game.getPlayer1().getPaddle().setVelocity(0);
                        wPressed.setText("W Released.");
                    }
                    if (event.getCode() == KeyCode.S) {
                        game.getPlayer1().getPaddle().setVelocity(0);
                        sPressed.setText("S Released");
                    }
                    if (event.getCode() == KeyCode.UP) {
                        game.getPlayer2().getPaddle().setVelocity(0);
                        upPressed.setText("Up Released");
                    }
                    if (event.getCode() == KeyCode.DOWN) {
                        game.getPlayer2().getPaddle().setVelocity(0);
                        downPressed.setText("Down Released");
                    }
                    if (event.getCode() == KeyCode.SPACE) {
                        // DO NOTHING
                    }
                }
        );

    }

    private void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.update();
            }
        };
        timer.start();
    }

    public void setGame(ClassicPong game) {
        this.game = game;
    }
}
