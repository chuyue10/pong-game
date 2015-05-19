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
    private final double EIGHTH;

    private Rectangle top;
    private Rectangle top1;
    private Rectangle top2;
    private Rectangle mid;
    private Rectangle bot2;
    private Rectangle bot1;
    private Rectangle bot;

    private int velocity;

    public Paddle(double width, double height, int x, int y) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.velocity = 0;

        this.EIGHTH = HEIGHT / 8;
        top = new Rectangle(WIDTH, EIGHTH);
        top1 = new Rectangle(WIDTH, EIGHTH);
        top2 = new Rectangle(WIDTH, EIGHTH);
        mid = new Rectangle(WIDTH, 2 * EIGHTH);
        bot2 = new Rectangle(WIDTH, EIGHTH);
        bot1 = new Rectangle(WIDTH, EIGHTH);
        bot = new Rectangle(WIDTH, EIGHTH);

        top.setX(x);
        top1.setX(x);
        top2.setX(x);
        mid.setX(x);
        bot2.setX(x);
        bot1.setX(x);
        bot.setX(x);

        setY(y);
    }

    public double getX() {
        return top.getX();
    }

    public double getY() {
        return top.getY();
    }

    public void setY(double y) {
        top.setY(y);
        top1.setY(y + EIGHTH);
        top1.setFill(Paint.valueOf("blue"));
        top2.setY(y + 2 * EIGHTH);
        mid.setY(y + 3 * EIGHTH);
        mid.setFill(Paint.valueOf("blue"));
        bot2.setY(y + 5 * EIGHTH);
        bot1.setY(y + 6 * EIGHTH);
        bot1.setFill(Paint.valueOf("blue"));
        bot.setY(y + 7 * EIGHTH);
    }

    public Set<Rectangle> getRectangles() {
        HashSet<Rectangle> set = new HashSet<>();
        set.add(top);
        set.add(top1);
        set.add(top2);
        set.add(mid);
        set.add(bot2);
        set.add(bot1);
        set.add(bot);
        return set;
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
