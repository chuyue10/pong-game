package pong;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Charles on 5/18/2015.
 */
public class Paddle {

    private final double WIDTH;
    private final double HEIGHT;

    private Rectangle rectangle;

    private int velocity;

    public Paddle(double width, double height, int x, int y) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.velocity = 0;

        this.rectangle = new Rectangle(x, y, width, height);

        setY(y);
    }

    public double getX() {
        return rectangle.getX();
    }

    public double getY() {
        return rectangle.getY();
    }

    public void setY(double y) {
        rectangle.setY(y);
    }

    public Rectangle getRectangle() {
       return rectangle;
    }

    public double getWidth() {
        return WIDTH;
    }

    public double getHeight() {
        return HEIGHT;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }

    public void processBallCollision(ClassicBall ball) {
        double vel = ball.getVelocity();

    }
}
