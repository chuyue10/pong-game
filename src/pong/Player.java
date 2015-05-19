package pong;

/**
 * Created by Charles on 5/18/2015.
 */
public class Player {

    private String name;
    private int score;
    private Paddle paddle;

    public Player(String name, int score, Paddle paddle) {
        this.name = name;
        this.score = score;
        this.paddle = paddle;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public String toString() {
        return name;
    }

}
