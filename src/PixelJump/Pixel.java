package PixelJump;


import cantrell.animations.completed.GravityAnimation;
import comp127graphics.*;
import comp127graphics.Point;
import comp127graphics.Rectangle;

import java.awt.*;
import java.sql.SQLOutput;


public class Pixel {
    private static final double GRAVITY = .004,
                                JUMP_VELOCITY = -1;
    public GraphicsGroup pixel = new GraphicsGroup();
    private Rectangle pixelBody;
    private Ellipse eye1, eye2, eyeBall1, eyeBall2;
    private Arc mouth;
    private Color bodyColor = new Color(100, 150, 200);
    private double yVelocity, previousBottomPixelY;

    /* sets up body parts of pixel*/
    public Pixel() {
        pixelBody = new Rectangle(PixelJump.CANVAS_WIDTH / 2, PixelJump.CANVAS_HEIGHT - 60, 50, 50);
        eye1 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 2, PixelJump.CANVAS_HEIGHT - 60, 22, 30);
        eye2 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 26, PixelJump.CANVAS_HEIGHT - 60, 22, 30);
        eyeBall1 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 2, PixelJump.CANVAS_HEIGHT - 55, 11, 15);
        eyeBall2 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 26, PixelJump.CANVAS_HEIGHT - 55, 11, 15);
        mouth = new Arc(PixelJump.CANVAS_WIDTH / 2 + 20, PixelJump.CANVAS_HEIGHT - 28, 10, 10, 180, 180);
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

        previousBottomPixelY = pixel.getY() + 590;
    }

    public void pixelContinuousJump() {
        //  System.out.println(baseY);
        previousBottomPixelY = getCurrentBottomPixel();
        pixel.moveBy(0, yVelocity);
        yVelocity += GRAVITY;
    }


    public void pixelMove(Point mousePosition) {
        pixel.setCenter(mousePosition.getX(), pixel.getCenter().getY());
        System.out.println(pixel.getCenter());
     //   pixel.setCenter(pixel.getCenter());
    }


    public double getCurrentBottomPixel() {
        return pixel.getY() + pixel.getHeight();
    }


    public void bounce() {
        yVelocity = JUMP_VELOCITY;
    }

    public boolean didJustCrossPlatform(Platform platform){
        System.out.println(platform.getRightX() + "   " + pixel.getX());
        return platform.getRightX() > pixel.getX()
                && platform.getLeftX() < pixel.getWidth()+pixel.getX()
                && previousBottomPixelY <= platform.getTopYPosition()
                && getCurrentBottomPixel() >= platform.getTopYPosition();
    }
}




