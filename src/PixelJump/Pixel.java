package PixelJump;


import cantrell.animations.completed.GravityAnimation;
import comp127graphics.*;
import comp127graphics.Rectangle;

import java.awt.*;
import java.awt.Point;

public class Pixel {
    public static final double GRAVITY = -100;
    public GraphicsGroup pixel = new GraphicsGroup();
    private Rectangle pixelBody;
    private Ellipse eye1, eye2, eyeBall1, eyeBall2;
    private Arc mouth;
    private Color bodyColor = new Color(100, 150, 200);
    private double currentCenterX, currentCenterY, yVelocity, maxY, xVelocity, velocity, currentYVelocity, baseY;

    /* sets up body parts of pixel*/
    public Pixel() {
        pixelBody = new Rectangle(PixelJump.CANVAS_WIDTH / 2, PixelJump.CANVAS_HEIGHT - 60, 50, 50);
        eye1 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 2, PixelJump.CANVAS_HEIGHT - 60, 22, 30);
        eye2 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 26, PixelJump.CANVAS_HEIGHT - 60, 22, 30);
        eyeBall1 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 2, PixelJump.CANVAS_HEIGHT - 55, 11, 15);
        eyeBall2 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 26, PixelJump.CANVAS_HEIGHT - 55, 11, 15);
        mouth = new Arc(PixelJump.CANVAS_WIDTH / 2 + 20, PixelJump.CANVAS_HEIGHT - 28, 10, 10, 180, 180);
        velocity = -10;
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

        currentCenterX = pixel.getX();
        currentCenterY = pixel.getY();
        maxY = pixel.getY() - 100;
        yVelocity = velocity;
        baseY = 0;


    }

    /* moves pixel up and down, currently does not take in when the user drags the pixel to left or right or when it hits a platform*/
    /* graphics group initial position is 0,0, so to move up velocity must be negative. It moves up to a max height of 100, which means that the pixel each jump can only go up 100. right now it is set up so when the graphics group goes back to its initial position it bounces back up*/
    public void pixelContinuousJump() {
        pixel.setPosition(currentCenterX, currentCenterY);
        currentCenterY += yVelocity;
//        yVelocity += GRAVITY*.5;
        if (currentCenterY < maxY) {
            yVelocity *= -1;
        }
        if (currentCenterY > baseY) {
            yVelocity *= -1;
        }
        getCurrentBottomY();
    }


    public void pixelMove() {
        Point p = MouseInfo.getPointerInfo().getLocation();
       if(p.x <  50){
           currentCenterX = -1*PixelJump.CANVAS_WIDTH / 2;
       }
       else{
           if(p.x > PixelJump.CANVAS_WIDTH-50){
               currentCenterX = PixelJump.CANVAS_WIDTH/2 -50;
           }
           else {
               currentCenterX = p.x - PixelJump.CANVAS_WIDTH / 2 - 50;
           }
    }
        pixel.setPosition(currentCenterX, currentCenterY);
    }

    public void setMaxYAndBaseY(double newMaxY, double newBaseY){
        maxY = newMaxY;
        baseY = newBaseY;
    }

    public double getCurrentBottomY(){
        return currentCenterY-50;
    }
}




