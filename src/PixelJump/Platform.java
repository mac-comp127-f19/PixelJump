package PixelJump;

import comp127graphics.CanvasWindow;
import comp127graphics.Rectangle;

import java.util.Random;

public class Platform extends Rectangle {

    private static final Random random = new Random();

    private double xVelocity;

    public Platform(double positionX, double positionY, double platformWidth, double platformHeight) {
        super(positionX, positionY, platformWidth, platformHeight);

        if(random.nextDouble() * 100 <= 50){
            xVelocity = random.nextDouble()*10 - 5;
        }
    }

    public double getTopYPosition() {
        return getY();
    }

    public double getLeftX() {
        return getX();
    }

    public double getRightX() {
        return getX() + getWidth();
    }

    public void changeDirection() {
        xVelocity *= -1;
    }

    public void move() {
        moveBy(xVelocity, 0);
    }
}
