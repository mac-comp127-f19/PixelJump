package PixelJump;


import cantrell.animations.completed.GravityAnimation;
import comp127graphics.*;
import comp127graphics.Point;
import comp127graphics.Rectangle;

import java.awt.*;
import java.sql.SQLOutput;


public class Pixel {
    private static final double GRAVITY = .01,
                                JUMP_VELOCITY = -1.3;
    public GraphicsGroup pixel = new GraphicsGroup();
    private Rectangle pixelBody;
    private Ellipse eye1, eye2, eyeBall1, eyeBall2;
    private Arc mouth;
    private Color bodyColor = new Color(100, 150, 200);
    private double yVelocity, previousBottomPixelY;

    /* sets up body parts of pixel at locations where the pixel lines up at the bottom and center of screen*/

    public Pixel() {
        pixel.setPosition(0,0);
        pixelBody = new Rectangle( 0,0, 50, 50);
        eye1 = new Ellipse( 2,0, 22, 30);
        eye2 = new Ellipse( 26, 0, 22, 30);
        eyeBall1 = new Ellipse( 2, 5, 11, 15);
        eyeBall2 = new Ellipse(  26, 5, 11, 15);
        mouth = new Arc(20, 30, 10, 10, 180, 180);
        bounce();
        drawPixel();
    }

    /* adds pixel to canvas */
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(pixel);
    }

    /* adds pixel body parts to graphics group to make a pixel*/
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
        pixel.setPosition(PixelJump.CANVAS_WIDTH / 2,PixelJump.CANVAS_HEIGHT - 60);

        previousBottomPixelY = pixel.getY() + 590;
    }

    public void pixelContinuousJump() {
        previousBottomPixelY = getCurrentBottomPixel();
        pixel.moveBy(0, yVelocity);
        yVelocity += GRAVITY;
    }


    public void pixelMove(Point mousePosition) {
        pixel.setCenter(mousePosition.getX(), pixel.getCenter().getY());
    }


    public double getCurrentBottomPixel() {
        return pixel.getY() + pixel.getHeight();
    }


    public void bounce() {
        yVelocity = JUMP_VELOCITY;
    }

    public boolean didJustCrossPlatform(Platform platform){
        return platform.getRightX() > pixel.getX()
                && platform.getLeftX() < pixel.getWidth()+pixel.getX()
                && previousBottomPixelY <= platform.getTopYPosition()
                && getCurrentBottomPixel() >= platform.getTopYPosition();
    }
}




