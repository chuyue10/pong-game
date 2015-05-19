package pong;

/**
 * Created by Charles on 5/18/2015.
 */
public class ClassicPong {

    private static ClassicPong game;

    private Player player1;
    private Player player2;

    private ClassicBall ball;

    private final int WIDTH;
    private final int HEIGHT;
    private final int BALL_LENGTH = 10;
    private final int PADDLE_HEIGHT = 160;
    private final int PADDLE_WIDTH = 10;
    private final int PADDLE_OFFSET = 10;
    private final int PADDLE_LIMIT = 30;

    private ClassicPong(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;

        player1 = new Player("Player 1", 0, new Paddle(
                PADDLE_WIDTH,
                PADDLE_HEIGHT,
                PADDLE_OFFSET,
                HEIGHT / 2 - PADDLE_HEIGHT / 2));
        player2 = new Player("Player 2", 0, new Paddle(
                PADDLE_WIDTH,
                PADDLE_HEIGHT,
                WIDTH - PADDLE_WIDTH - PADDLE_OFFSET,
                HEIGHT / 2 - PADDLE_HEIGHT / 2));

        ball = new ClassicBall(BALL_LENGTH,
                WIDTH / 2 - BALL_LENGTH / 2,
                HEIGHT / 2 - BALL_LENGTH / 2,
                -3, -3);
    }

    /**
     * Singleton pattern so there is ever only one instance of the game
     * @param width the required width of the game
     * @param height the required height of the game
     * @return the ClassicPong game
     */
    public static ClassicPong getInstance(int width, int height) {
        if (game == null) {
            return new ClassicPong(width, height);
        } else if (game.getHeight() != height ||
                game.getWidth() != width) {
            return new ClassicPong(width, height);
        } else {
            return game;
        }
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

    private void borderCollisionCheck() {
        if (ball.getX() < 0) {
            ball.setXVelocity(ball.getXVelocity() * -1);
            System.out.println("Player 1 dropped the ball.");
        }
        if (ball.getX() > WIDTH - ball.getWidth()) {
            ball.setXVelocity(ball.getXVelocity() * -1);
            System.out.println("Player 2 dropped the ball.");
        }
        if (ball.getY() < 0) {
            ball.setYVelocity(ball.getYVelocity() * -1);
        }
        if (ball.getY() > HEIGHT - ball.getWidth()) {
            ball.setYVelocity(ball.getYVelocity() * -1);
        }
    }

    private void paddleCollisionCheck() {
        player1.getPaddle().processBallCollision(ball);
        player2.getPaddle().processBallCollision(ball);
    }

}
