package pong;

import java.util.Random;

/**
 * Created by Charles on 5/18/2015.
 */
public class ClassicPong {

    // TODO IMPLEMENT SWITCHING SERVING TO PLAYERS

    private static ClassicPong game;
    private static SoundManager soundManager;

    private Player player1;
    private Player player2;

    private ClassicBall ball;

    private final int WIDTH;
    private final int HEIGHT;
    private final int BALL_LENGTH = 10;
    private final int PADDLE_HEIGHT = 40;
    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_OFFSET = 10;
    private final int PADDLE_LIMIT = 30;

    private final int BASE_BALL_SPEED = 4;
    private final Random RANDOM = new Random();

    private final int INITIAL_SCORE = 0;
    private final String PLAYER_1_NAME = "Player 1";
    private final String PLAYER_2_NAME = "Player 2";

    private final int WINNING_SCORE = 5;

    private GameState state;

    // Recorded in-game information

    private ClassicPong(int width, int height) {

        this.state = GameState.LOADING;

        soundManager = SoundManager.getInstance();

        this.WIDTH = width;
        this.HEIGHT = height;

        player1 = new Player(PLAYER_1_NAME, INITIAL_SCORE, new Paddle(
                PADDLE_WIDTH,
                PADDLE_HEIGHT,
                PADDLE_OFFSET,
                HEIGHT / 2 - PADDLE_HEIGHT / 2));
        player2 = new Player(PLAYER_2_NAME, INITIAL_SCORE, new Paddle(
                PADDLE_WIDTH,
                PADDLE_HEIGHT,
                WIDTH - PADDLE_WIDTH - PADDLE_OFFSET,
                HEIGHT / 2 - PADDLE_HEIGHT / 2));

        ball = new ClassicBall(BALL_LENGTH,
                WIDTH / 2 - BALL_LENGTH / 2,
                HEIGHT / 2 - BALL_LENGTH / 2,
                0, 0);

        this.state = GameState.ENDOFROUND;
        resetRound();
    }

    /**
     * Singleton pattern so there is ever only one instance of the game
     * @param width the required width of the game
     * @param height the required height of the game
     * @return the ClassicPong game
     */
    public static ClassicPong getInstance(int width, int height) {
        if (game == null) {
            game = new ClassicPong(width, height);
        } else if (game.getHeight() != height ||
                game.getWidth() != width) {
            game = new ClassicPong(width, height);
        }
        return game;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidth() {
        return WIDTH;
    }

    public String toString() {
        return "Classic Pong";
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public ClassicBall getBall() {
        return ball;
    }

    public void update() {
        if (state == GameState.INGAME ||
                state == GameState.FINISH) {
            borderCollisionCheck();
            paddleCollisionCheck();
            ball.setX(ball.getXVelocity() + ball.getX());
            ball.setY(ball.getYVelocity() + ball.getY());
            if (player1.getPaddle().getY() + player1.getPaddle().getVelocity() > PADDLE_LIMIT &&
                    player1.getPaddle().getY() + player1.getPaddle().getVelocity() < HEIGHT - PADDLE_LIMIT - PADDLE_HEIGHT) {
                player1.getPaddle().setY(player1.getPaddle().getY() + player1.getPaddle().getVelocity());
            }
            if (player2.getPaddle().getY() + player2.getPaddle().getVelocity() > PADDLE_LIMIT &&
                    player2.getPaddle().getY() + player2.getPaddle().getVelocity() < HEIGHT - PADDLE_LIMIT - PADDLE_HEIGHT) {
                player2.getPaddle().setY(player2.getPaddle().getY() + player2.getPaddle().getVelocity());
            }
        }
    }

    /**
     * This starts the ball from a ready position
     */
    public void startRound() {
        if (state == GameState.STANDBY) {
            state = GameState.INGAME;

            // Generate an angle between -45 and 45
            int angle = RANDOM.nextInt(91) - 45;
            double rad = Math.toRadians(angle);
            double sin = Math.sin(rad);
            double cos = Math.cos(rad);

            ball.setXVelocity(BASE_BALL_SPEED * cos);
            ball.setYVelocity(BASE_BALL_SPEED * sin);

        } else {
            throw new IllegalStateException("Can only change to INGAME from STANBY.");
        }
    }

    private void borderCollisionCheck() {
        if (ball.getX() < 0) {
            if (state == GameState.INGAME) {
                soundManager.playSound(Main.SOUND_CLASSIC_PONG_END_COLLISION);
                endRound(true);
            } else {
                soundManager.playSound(Main.SOUND_CLASSIC_PONG_SIDE_COLLISION);
                ball.setXVelocity(-1 * ball.getXVelocity());
            }
        }
        if (ball.getX() > WIDTH - ball.getWidth()) {
            if (state == GameState.INGAME) {
                soundManager.playSound(Main.SOUND_CLASSIC_PONG_END_COLLISION);
                endRound(false);
            } else {
                soundManager.playSound(Main.SOUND_CLASSIC_PONG_SIDE_COLLISION);
                ball.setXVelocity(-1 * ball.getXVelocity());
            }
        }
        if (ball.getY() < 0) {
            soundManager.playSound(Main.SOUND_CLASSIC_PONG_SIDE_COLLISION);
            ball.setYVelocity(ball.getYVelocity() * -1);
        }
        if (ball.getY() > HEIGHT - ball.getWidth()) {
            soundManager.playSound(Main.SOUND_CLASSIC_PONG_SIDE_COLLISION);
            ball.setYVelocity(ball.getYVelocity() * -1);
        }
    }

    private void paddleCollisionCheck() {
        if (player1.getPaddle().processBallCollision(ball)) {
            soundManager.playSound(Main.SOUND_CLASSIC_PONG_PADDLE_COLLISION);
        } else if (player2.getPaddle().processBallCollision(ball)) {
            soundManager.playSound(Main.SOUND_CLASSIC_PONG_PADDLE_COLLISION);
        }
    }

    /**
     * Reset the state of the game by returning the ball to the center with no
     * motion.
     */
    public void resetRound() {
        if (state == GameState.ENDOFROUND) {
            state = GameState.STANDBY;
        } else {
            throw new IllegalStateException("Can only reset round from ENDOFROUND");
        }
        ball.setX(WIDTH / 2 - BALL_LENGTH / 2);
        ball.setY(HEIGHT / 2 - BALL_LENGTH / 2);
        ball.setXVelocity(0);
        ball.setYVelocity(0);
        player1.getPaddle().setY(HEIGHT / 2 - player1.getPaddle().getHeight() / 2);
        player2.getPaddle().setY(HEIGHT / 2 - player2.getPaddle().getHeight() / 2);
    }

    /**
     * Triggered when someone lost the ball. Points increased/decreased here.
     * @param player a boolean input indicating the winner of the round
     *               false is player 1
     *               true is player 2
     */
    public void endRound(boolean player) {
        Player winner = player ? player2 : player1;
        Player loser = !player ? player2 : player1;
        if (state == GameState.INGAME) {
            winner.setScore(winner.getScore() + 1);
            state = GameState.ENDOFROUND;
            if (winner.getScore() >= WINNING_SCORE) { // IF WINNING
                if (winner.getScore() > loser.getScore() + 1) { // HAS TO WIN BY TWO
                    endGame(winner);
                }
            }
        } else {
            throw new IllegalStateException("Can only end round from INGAME state.");
        }
    }

    public GameState getState() {
        return state;
    }

    /**
     * Run this to finish the game
     */
    public void endGame(Player winner) {
        if (state == GameState.ENDOFROUND) {
            state = GameState.FINISH;

            // END OF GAME ANIMATIONS
            ball.setX(WIDTH / 2 - BALL_LENGTH / 2);
            ball.setY(HEIGHT / 2 - BALL_LENGTH / 2);
            ball.setXVelocity(3);
            ball.setYVelocity(3);
            player1.getPaddle().setY(HEIGHT / 2 - player1.getPaddle().getHeight() / 2);
            player2.getPaddle().setY(HEIGHT / 2 - player2.getPaddle().getHeight() / 2);

        } else {
            throw new IllegalStateException("Can only end game from ENDOFROUND state.");
        }
    }

    public void recordGame() {
        // TODO SAVE THE INFORMATION FOR THIS GAME PERMANENTLY
    }

}
