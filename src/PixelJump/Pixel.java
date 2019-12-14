package PixelJump;


import cantrell.animations.completed.GravityAnimation;
import comp127graphics.*;
import comp127graphics.Point;
import comp127graphics.Rectangle;

import java.awt.*;

/* Julia Kispert and Weishi Ding
 * This class controls the pixel (its characteristics and movements)
 * Help Received From Professor Paul Cantrell */

public class Pixel {
    private static final double GRAVITY = .4,
            JUMP_VELOCITY = -10;
    public GraphicsGroup pixel = new GraphicsGroup();
    private Rectangle pixelBody;
    private Ellipse eye1, eye2, eyeBall1, eyeBall2;
    private Arc mouth;
    private Color bodyColor = new Color(100, 150, 200);
    private double yVelocity, previousBottomPixelY;

    /**
     * sets up body parts of pixel at locations where the pixel lines up at the bottom and center of screen
     **/

    public Pixel() {
        pixel.setPosition(0, 0);
        pixelBody = new Rectangle(0, 0, 50, 50);
        eye1 = new Ellipse(2, 0, 22, 30);
        eye2 = new Ellipse(26, 0, 22, 30);
        eyeBall1 = new Ellipse(2, 5, 11, 15);
        eyeBall2 = new Ellipse(26, 5, 11, 15);
        mouth = new Arc(20, 30, 10, 10, 180, 180);
        drawPixel();
    }

    /**
     *  adds pixel to canvas
     **/
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(pixel);
    }

    /** adds pixel body parts to graphics group to make a pixel. Sets the previous bottom of the pixel to 10
     * above the bottom of the canvas.
     **/
    private void drawPixel() {
        pixelBody.setFillColor(bodyColor);
        pixelBody.setFilled(true);
        pixel.add(pixelBody);

        eye1.setFillColor(Color.white);
        eye2.setFillColor(Color.white);
        eye1.setFilled(true);
        eye2.setFilled(true);
        pixel.add(eye1);
        pixel.add(eye2);

        eyeBall1.setFillColor(Color.LIGHT_GRAY);
        eyeBall2.setFillColor(Color.LIGHT_GRAY);
        eyeBall1.setFilled(true);
        eyeBall2.setFilled(true);
        pixel.add(eyeBall1);
        pixel.add(eyeBall2);

        mouth.setStrokeWidth(10);
        mouth.setStrokeColor(Color.lightGray);
        mouth.setStroked(true);
        pixel.add(mouth);

        pixel.setPosition(PixelJump.CANVAS_WIDTH / 2, 0);

        previousBottomPixelY = pixel.getY() + 590;
    }

    /** makes pixel always jump in place, no matter what platform it is on.
     * Used in animate call in PixelJump
     **/
    public void pixelContinuousJump() {
        previousBottomPixelY = getCurrentBottomPixel();
        pixel.moveBy(0, yVelocity);
        yVelocity += GRAVITY;
    }

    /**
     *  moves pixel based on mouse position
     **/
    public void pixelMove(Point mousePosition) {
        pixel.setCenter(mousePosition.getX(), pixel.getCenter().getY());
        changeEyeBallPosition(mousePosition);
    }

    /**
     *  moves pixel's eye balls based on if mouse position is on left side or right side of canvas
     * @param mousePosition - position of mouse
    **/
    public void changeEyeBallPosition(Point mousePosition) {
        if (mousePosition.getX() < (PixelJump.CANVAS_WIDTH / 2) + 25) {
            eyeBall1.setPosition(2, 5);
            eyeBall2.setPosition(26, 5);
        } else {
            eyeBall1.setPosition(13, 5);
            eyeBall2.setPosition(37, 5);
        }

    }

    /**
     *  returns bottom y position of pixel
     **/
    public double getCurrentBottomPixel() {
        return pixel.getY() + pixel.getHeight();
    }

    /** changes yVelocity to initial velocity so pixel can jump at the same pace through out the game and
     *  does not travel through a platform
     * @param platform - platform pixel has hit and must bounce off
     **/
    public void bounceOff(Platform platform) {
        yVelocity = JUMP_VELOCITY;

        // preventing the pixel from moving inside the platform
        pixel.setPosition(pixel.getX(), platform.getTopYPosition() - pixel.getHeight());
    }

    /** checks if pixel has hit a platform, by comparing its previous and current position to a platforms location.
     * This works as before a pixel hits a platform its is located above it and after it is located beyond the platform
     * or with in it.
     * @param platform - platform being check is on it
     **/
    public boolean didJustCrossPlatform(Platform platform) {
        return platform.getRightX() > pixel.getX()
                && platform.getLeftX() < pixel.getWidth() + pixel.getX()
                && previousBottomPixelY <= platform.getTopYPosition()
                && getCurrentBottomPixel() >= platform.getTopYPosition();
    }

    /** when canvas needs to move up to show more platforms,
     * this moves the pixel so it does not go off screen and move up motion works once it is necessary for the pixel to move up.
     * The pixel moves down based on the positive difference between it and half way the canvas.
     * @param difference - difference between the pixels location and half way up the canvas.
     **/
    public void pixelPositionWhenMovingUp(double difference) {
        pixel.moveBy(0, difference);
    }

}




