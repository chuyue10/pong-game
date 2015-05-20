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
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Charles on 5/18/2015.
 */
public class ClassicPongController implements Initializable {

    private ClassicPong game;

    @FXML
    Pane table;

    @FXML
    Label score1;

    @FXML
    Label score2;

    private EventHandler<KeyEvent> keyPressed;
    private EventHandler<KeyEvent> keyReleased;

    private boolean wPressed = false;
    private boolean sPressed = false;
    private boolean upPressed = false;
    private boolean downPressed = false;

    private final int ROUND_DELAY = 1000;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupControls();
        startAnimation();
    }

    private void setupControls() {

        keyPressed = (KeyEvent event) -> {
            if (event.getCode() == KeyCode.W) {
                wPressed = true;
            }
            if (event.getCode() == KeyCode.S) {
                sPressed = true;
            }
            if (event.getCode() == KeyCode.UP) {
                upPressed = true;
            }
            if (event.getCode() == KeyCode.DOWN) {
                downPressed = true;
            }
        };

        keyReleased = (KeyEvent event) -> {
            if (event.getCode() == KeyCode.W) {
                wPressed = false;
            }
            if (event.getCode() == KeyCode.S) {
                sPressed = false;
            }
            if (event.getCode() == KeyCode.UP) {
                upPressed = false;
            }
            if (event.getCode() == KeyCode.DOWN) {
                downPressed = false;
            }
        };

        table.setOnKeyPressed(keyPressed);
        table.setOnKeyReleased(keyReleased);
 }

    private void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (game.getState() == GameState.LOADING) {
                    // DO NOTHING
                    // THE GAME IS LOADING
                } else if (game.getState() == GameState.STANDBY) {
                    // WAIT FOR BOTH THE PLAYERS TO BE READY FOR THE ROUND
                    // WHEN BOTH MOVE-UP CONTROLS ARE ENGAGED THE ROUND STARTS
                    if (wPressed && upPressed) {
                        game.startRound();
                    }
                } else if (game.getState() == GameState.INGAME) {
                    // WHILE IN GAME THE PADDLES WILL RESPOND TO THE PLAYER COMMANDS
                    if (wPressed && !sPressed) {
                        if (game.getPlayer1().getPaddle().getVelocity() != -1 * Paddle.PADDLE_VELOCITY) {
                            game.getPlayer1().getPaddle().setVelocity(-1 * Paddle.PADDLE_VELOCITY);
                        }
                    } else if (!wPressed && sPressed) {
                        if (game.getPlayer1().getPaddle().getVelocity() != Paddle.PADDLE_VELOCITY) {
                            game.getPlayer1().getPaddle().setVelocity(Paddle.PADDLE_VELOCITY);
                        }
                    } else {
                        if (game.getPlayer1().getPaddle().getVelocity() != 0) {
                            game.getPlayer1().getPaddle().setVelocity(0);
                        }
                    }
                    if (upPressed && !downPressed) {
                        if (game.getPlayer2().getPaddle().getVelocity() != -1 * Paddle.PADDLE_VELOCITY) {
                            game.getPlayer2().getPaddle().setVelocity(-1 * Paddle.PADDLE_VELOCITY);
                        }
                    } else if (!upPressed && downPressed) {
                        if (game.getPlayer2().getPaddle().getVelocity() != Paddle.PADDLE_VELOCITY) {
                            game.getPlayer2().getPaddle().setVelocity(Paddle.PADDLE_VELOCITY);
                        }
                    } else {
                        if (game.getPlayer2().getPaddle().getVelocity() != 0) {
                            game.getPlayer2().getPaddle().setVelocity(0);
                        }
                    }
                } else if (game.getState() == GameState.ENDOFROUND) {
                    // WHEN THE ROUND ENDS THE GAME WILL PAUSE SO THE PLAYERS
                    // CAN GET READY AGAIN
                    score1.setText(game.getPlayer1().getScore() + "");
                    score2.setText(game.getPlayer2().getScore() + "");
                    try {
                        Thread.sleep(ROUND_DELAY);
                    } catch (Exception e) {

                    } finally {
                        game.resetRound();
                    }
                } else if (game.getState() == GameState.FINISH) {
                    // THIS GAME IS DONE
                    // FOR NOW DO NOTHING
                    score1.setText(game.getPlayer1().getScore() + "");
                    score2.setText(game.getPlayer2().getScore() + "");
                    // TODO END OF GAME STUFF
                }
                game.update();
            }
        };
        timer.start();
    }

    public void setGame(ClassicPong game) {
        this.game = game;
    }
}
