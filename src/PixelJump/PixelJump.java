package PixelJump;

import comp127graphics.CanvasWindow;

public class PixelJump {
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;
    CanvasWindow canvas = new CanvasWindow("", CANVAS_WIDTH, CANVAS_HEIGHT);
    Pixel pixel;

    PlatformManager platformManager;

    public PixelJump() {
        pixel = new Pixel();
        platformManager = new PlatformManager(canvas, pixel);
        pixel.addToCanvas(canvas);
        pixelJumpRun();
        platformManager.generatePlatforms();
        Platform startingPlatform = new Platform(CANVAS_WIDTH/2, CANVAS_HEIGHT-10, 100, 30);
        canvas.add(startingPlatform);

    }

    public void pixelJumpRun() {
        canvas.animate(() -> {
                   pixel.pixelContinuousJump();
                   platformManager.pixelLands();
                }
        );
        canvas.onMouseMove(event -> {
            pixel.pixelMove(event.getPosition());
        });
    }

    public static void main(String args[]) {
        new PixelJump();

    }
}
