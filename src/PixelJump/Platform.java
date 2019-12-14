package PixelJump;

import comp127graphics.Rectangle;

import java.util.Random;

/* Julia Kispert and Weishi Ding
 * This class creates the basic format of a paddle and returns some of its characteristics.
 */

public class Platform extends Rectangle {

    private static final Random random = new Random();

    private double xVelocity;

    /**
     *  constructs basic platform, and gives 20% a velocity, so it can move
     * @param positionX - x position of platform
     * @param positionY - y position of platform
     * @param platformWidth - width of platform
     * @param platformHeight - height of platform
     **/
    public Platform(double positionX, double positionY, double platformWidth, double platformHeight) {
        super(positionX, positionY, platformWidth, platformHeight);

        if (random.nextDouble() * 100 <= 20) {
            xVelocity = random.nextDouble() * 10 - 5;
        }
    }

    /**
     *  returns top y position of the platform
     **/
    public double getTopYPosition() {
        return getY();
    }

    /**
     *  returns left x position of the platform
     **/
    public double getLeftX() {
        return getX();
    }

    /**
     * returns right x position of the platform
     **/
    public double getRightX() {
        return getX() + getWidth();
    }

    /**
     *  changes x velocity of the platform to reverse direction of movement
     **/
    public void changeDirection() {
        xVelocity *= -1;
    }

    /**
     * moves moving platforms
     **/
    public void move() {
        moveBy(xVelocity, 0);
    }
}
