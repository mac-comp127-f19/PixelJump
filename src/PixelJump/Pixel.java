package PixelJump;

import comp127graphics.*;
import comp127graphics.Rectangle;

import java.awt.*;

public class Pixel {
//    public static final double GRAVITY = -9.8;
    public GraphicsGroup pixel = new GraphicsGroup();
    private Rectangle pixelBody;
    private Ellipse eye1, eye2, eyeBall1, eyeBall2;
    private Arc mouth;
    private Color bodyColor = new Color(100, 150, 200);
    private double currentCenterX, currentCenterY, yVelocity, maxY;
/* sets up body parts of pixel*/
    public Pixel() {
        pixelBody = new Rectangle(PixelJump.CANVAS_WIDTH / 2, PixelJump.CANVAS_HEIGHT - 60, 50, 50);
        eye1 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 2, PixelJump.CANVAS_HEIGHT - 60, 22, 30);
        eye2 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 26, PixelJump.CANVAS_HEIGHT - 60, 22, 30);
        eyeBall1 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 2, PixelJump.CANVAS_HEIGHT - 55, 11, 15);
        eyeBall2 = new Ellipse(PixelJump.CANVAS_WIDTH / 2 + 26, PixelJump.CANVAS_HEIGHT - 55, 11, 15);
        mouth = new Arc(PixelJump.CANVAS_WIDTH / 2+20, PixelJump.CANVAS_HEIGHT - 28, 10, 10,180,180);

       yVelocity = 10;
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

        pixel.setCenter(PixelJump.CANVAS_WIDTH/2, PixelJump.CANVAS_HEIGHT-100);
        currentCenterX = pixel.getX()+25;
        currentCenterY = pixel.getY()+25;
        maxY = pixel.getY()-100;
    }

    public void pixelContinuousJump(){
        pixel.setCenter(currentCenterX, currentCenterY);
        currentCenterY += yVelocity;
        if(currentCenterY>=maxY) {
            yVelocity *= -1;
        }
    }
}
