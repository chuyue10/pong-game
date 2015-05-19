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

    /**
     * Set the score for the player
     * @param score the new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the current score of the player
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the Paddle of the player
     * @param paddle the Paddle for the player
     */
    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    /**
     * Returns the current Paddle of the Player
     * @return the current Paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Returns a string representation of the player.
     * @return a string representation of the player
     */
    public String toString() {
        return name;
    }

}
