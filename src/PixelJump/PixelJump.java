package PixelJump;

import comp127graphics.CanvasWindow;
import comp127graphics.FontStyle;
import comp127graphics.GraphicsText;

import java.awt.*;

public class PixelJump {
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;
    CanvasWindow canvas = new CanvasWindow("", CANVAS_WIDTH, CANVAS_HEIGHT);
    Pixel pixel;

    PlatformManager platformManager;

    public PixelJump() {
        canvas.setBackground(Color.CYAN);
        pixel = new Pixel();
        platformManager = new PlatformManager(canvas, pixel);
        pixel.addToCanvas(canvas);
        pixelJumpRun();
        platformManager.generatePlatforms();

    }

    public void pixelJumpRun() {
        canvas.animate(() -> {
            if(pixel.getCurrentBottomPixel()<CANVAS_HEIGHT) {
            pixel.pixelContinuousJump();
            platformManager.pixelLands();
                }
            else{
                endGame();
            }
                }
        );
        canvas.onMouseMove(event -> { if(pixel.getCurrentBottomPixel()<CANVAS_HEIGHT) {
            pixel.pixelMove(event.getPosition());
        }
        });
    }
    public void endGame(){
        canvas.removeAll();
        canvas.setBackground(Color.ORANGE);
        GraphicsText endGame = new GraphicsText("You Lost!");
        endGame.setCenter(175, CANVAS_HEIGHT/2);
        endGame.setFont(FontStyle.BOLD_ITALIC, 50);
        endGame.setFillColor(Color.CYAN);
        endGame.setFilled(true);
        canvas.add(endGame);


    }

    public static void main(String args[]) {
        new PixelJump();

    }
}
