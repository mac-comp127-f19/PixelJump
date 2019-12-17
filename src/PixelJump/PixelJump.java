/**
 * @author By Julia Kispert and Weishi Ding
 * acknowledgements:Professor Paul Cantrell
 * This class controls and runs PixelJump Game
 */


package PixelJump;

import comp127graphics.CanvasWindow;
import comp127graphics.FontStyle;
import comp127graphics.GraphicsText;

import java.awt.*;


public class PixelJump {
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;
    private static int score;
    private static GraphicsText scoreCount;
    CanvasWindow canvas = new CanvasWindow("", CANVAS_WIDTH, CANVAS_HEIGHT);
    Pixel pixel;
    PlatformManager platformManager;

    /**
     *  sets up game. Resets score to 0, sets background, adds all objects to canvas, begins game and adds a platform to the very bottom,
     * so the Pixel does not die on the first bounce
     **/
    public PixelJump() {
        score = 0;
        canvas.setBackground(Color.CYAN);
        scoreCount = new GraphicsText("Score: " + score);
        scoreCount.setFont(FontStyle.BOLD_ITALIC, 25);
        scoreCount.setCenter(CANVAS_WIDTH / 2, 25);
        scoreCount.setFillColor(Color.WHITE);
        scoreCount.setFilled(true);
        canvas.add(scoreCount);
        pixel = new Pixel();
        platformManager = new PlatformManager(canvas, pixel);
        pixel.addToCanvas(canvas);
        pixelJumpRun();
        Platform startingPlatform = platformManager.generateStartingPlatform();
        pixel.bounceOff(startingPlatform);
        platformManager.generatePlatforms();
    }

    /**
     *  runs game. Animates pixels continuous jump, until the pixel falls off the bottom of the screen where the game will end. Platforms are also animated. They are updated based on the pixel position
     * so when the pixel needs to move up the platform, the group adjusts so there are platforms located above it. Moving platforms are also animated here, and the pixels horizontal movement is controlled
     * by the mouses position.
     **/
    public void pixelJumpRun() {
        canvas.animate(() -> {
                    if (pixel.getCurrentBottomPixel() <= CANVAS_HEIGHT) {
                        pixel.pixelContinuousJump();
                        platformManager.pixelLands();
                    } else {
                        endGame();
                    }
                }
        );
        canvas.animate(() -> {
            platformManager.updatePlatforms(pixel.getCurrentBottomPixel() + 50);
        });
        canvas.animate(() -> platformManager.movingPlatforms());
        canvas.onMouseMove(event -> {
            if (pixel.getCurrentBottomPixel() < CANVAS_HEIGHT) {
                pixel.pixelMove(event.getPosition());
            }
        });
    }

    /**
     * ends Game. Canvas removes everything, but adds text giving player's final score and sharing that the game is over/the player lost.
     **/
    public void endGame() {
        canvas.removeAll();
        canvas.setBackground(Color.ORANGE);
        GraphicsText endGame = new GraphicsText("You Lost! Your Score Was: " + score);
        endGame.setCenter(175, CANVAS_HEIGHT / 2);
        endGame.setFont(FontStyle.BOLD_ITALIC, 30);
        endGame.setFillColor(Color.CYAN);
        endGame.setFilled(true);
        canvas.add(endGame);


    }
    /**
     *  score increases in increments of 10. Updates score board. This is called in platform manager.
     * The score is incremented when a platform has been removed, meaning that once the pixel surpasses a certain height, its score increases
     * **/

    public static void incrementScore() {
        score += 10;
        scoreCount.setText("Score: " + score);
    }

    /**
     * creates new game
     **/
    public static void main(String args[]) {
        new PixelJump();

    }
}
