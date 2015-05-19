package pong;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Created by Charles on 5/18/2015.
 */
public class Ball {

    // Kinematic properties of the Ball
    private double xVelocity;
    private double yVelocity;

    // The representation of the Ball
    private Circle circle;

    // Constructor
    public Ball(double radius, double x, double y, double dX, double dY) {
        this.xVelocity = dX;
        this.yVelocity = dY;
        this.circle = new Circle(x, y, radius, Paint.valueOf("black"));
    }

    public void replace(double x, double y, double dX, double dY) {
        setX(x);
        setY(y);
        setXVelocity(dX);
        setYVelocity(dY);
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
        circle.setCenterX(x);
    }

    /**
     * Set the Y Position of the Ball
     * @param y the new Y Position of the Ball
     */
    public void setY(double y) {
        circle.setCenterY(y);
    }

    public double getX() {
        return circle.getCenterX();
    }

    public double getY() {
        return circle.getCenterY();
    }
}
