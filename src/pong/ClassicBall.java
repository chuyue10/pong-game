package pong;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Created by Charles on 5/18/2015.
 */
public class ClassicBall {

    // Kinematic properties of the Ball
    private double xVelocity;
    private double yVelocity;

    // The representation of the Ball
    private Rectangle square;

    // Constructor
    public ClassicBall(double length, double x, double y, double dX, double dY) {
        this.xVelocity = dX;
        this.yVelocity = dY;
        this.square = new Rectangle(x, y, length, length);
        square.setFill(Paint.valueOf("white"));
    }

    /**
     * Sets a new X velocity for the Ball
     * @param newVelocity the new X velocity
     */
    public void setXVelocity(double newVelocity) {
        xVelocity = newVelocity;
    }

    /**
     * Sets a new Y velocity for the Ball
     * @param newVelocity the new Y velocity
     */
    public void setYVelocity(double newVelocity) {
        yVelocity = newVelocity;
    }

    /**
     * Returns the X Velocity of the Ball
     * @return the X Velocity
     */
    public double getXVelocity() {
        return xVelocity;
    }

    /**
     * Returns the Y Velocity of the Ball
     * @return the Y Velocity
     */
    public double getYVelocity(){
        return yVelocity;
    }

    /**
     * Set the X Position of the Ball
     * @param x the new X Position of the Ball
     */
    public void setX(double x) {
        square.setX(x);
    }

    /**
     * Set the Y Position of the Ball
     * @param y the new Y Position of the Ball
     */
    public void setY(double y) {
        square.setY(y);
    }

    public double getX() {
        return square.getX();
    }

    public double getY() {
        return square.getY();
    }

    public Rectangle getSquare() {
        return square;
    }

    public double getWidth() {
        return square.getHeight();
    }

    public void replace(double x, double y, double dX, double dY) {
        setX(x);
        setY(y);
        setXVelocity(dX);
        setYVelocity(dY);
    }

    public double getVelocity() {
        return Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
    }

    public int getDirection() {
        return xVelocity > 0 ? 1 : -1;
    }

    public double getCenterY() {
        return getY() + getWidth() / 2;
    }
}
