/**
 * @author By Julia Kispert and Weishi Ding
 * acknowledgements:Professor Paul Cantrell, Thy Nguyen, Logan Caraco
 * This class controls platforms displayed on canvas within Pixel Jump and relationship with the pixel
 */



package PixelJump;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PlatformManager {
    private CanvasWindow canvas;
    private GraphicsGroup platformCollection;
    private List<Platform> platforms;

    private double platformWidth;
    private double platformHeight;
    private static final double jumpRange = 100;
    private double maxX;
    private double maxY;
    private double currentY; // the position of the current platform
    private Random rand;
    private Pixel pixel;

    /**
     * sets up basic characteristics of platform group (size of each platform and where it should be place based on random).
     * Also initializes list where platforms will be kept
     * @param pixel - pixel in game
     * @param canvas - game canvas where platforms will be added
     **/
    public PlatformManager(CanvasWindow canvas, Pixel pixel) {

        this.canvas = canvas;
        this.platformWidth = (canvas.getWidth() * 0.2);
        this.platformHeight = (canvas.getHeight() * .03);
        this.maxX = canvas.getWidth();
        this.maxY = 60;
        this.currentY = canvas.getHeight() - 50;

        rand = new Random();
        this.pixel = pixel;

        platforms = new ArrayList<>();
        platformCollection = new GraphicsGroup();
        canvas.add(platformCollection);
    }

    /**
     * generates a platform at the very bottom of the canvas,
     * so pixel does not die on first bounce
     **/
    public Platform generateStartingPlatform() {
        Platform platform = new Platform(0, canvas.getHeight(), canvas.getWidth(), 1);
        platform.setStroked(false);
        platform.setFilled(false);
        platforms.add(platform);
        platformCollection.add(platform);

        return platform;
    }

    /**
     * generates platforms over canvas based on the canvas size, and adds them to the graphic group.
     * The yValue of the platform is altered in regards to the jump range of the pixel.
     * this ensures that the pixel will always be able to reach a platform
     **/
    public void generatePlatforms() {

        while (currentY > maxY) {
            double currentX = (rand.nextDouble() * maxX * 0.8) + 20;
            Platform platform = new Platform(currentX, currentY, platformWidth, platformHeight);
            platform.setStroked(true);
            platform.setStrokeColor(Color.ORANGE);
            platform.setFilled(true);
            platform.setFillColor(Color.ORANGE);
            platformCollection.add(platform);
            platforms.add(platform);
            System.out.println(platforms.size());
            currentY -= (rand.nextDouble() * jumpRange * 0.7 + 30);

        }

    }

    /**
     * checks if pixel is on a platform, and if so the pixel bounces off it
     **/
    public void pixelLands() {
        for (Platform platform : platforms) {
            if (pixel.didJustCrossPlatform(platform)) {
                pixel.bounceOff(platform);
            }
        }
    }

    /**
     * updates positions of platform group by shifting it down, so the pixel can continue to move up the screen. Platforms are moved
     * once the pixel gets half way up the canvas. Then the platforms are moved by the difference between the pixel and half way mark.
     * The pixels position is also called to be updated. CurrentY is updated and platforms are generated to be in their new locations.
     * Also keeps track of platforms that are no longer shown on the canvas and sends list of these platforms to removePlatforms()
     **/
    public void updatePlatforms(double pixelY) {
        double difference = canvas.getHeight() / 2 - pixelY;
        if (difference > 0) {
            List<Platform> platformsToBeRemoved = new ArrayList<>();
            for (Platform platform : platforms) {
                platform.moveBy(0, difference);
                if (platform.getTopYPosition() > canvas.getHeight()) {
                    platformsToBeRemoved.add(platform);
                }
            }
            removePlatforms(platformsToBeRemoved);
            pixel.pixelPositionWhenMovingUp(difference);
            currentY += difference;
            generatePlatforms();
        }
    }

    /**
     *  removes every platform that is in list. List is filled with platforms that are filled with platforms no longer on canvas
     * @param platformsToBeRemoved - list of platforms set in updatePlatforms class, where platforms are added to list if they
     *                             are located below the canvas
     **/

    public void removePlatforms(List<Platform> platformsToBeRemoved) {
        for (Platform platform : platformsToBeRemoved) {
            platformCollection.remove(platform);
            platforms.remove(platform);
        }
        PixelJump.incrementScore();
    }

    /**
     * for each platform in the moving platform list, move them back and forth on certain y-value
     */
    public void movingPlatforms() {
        platforms.forEach(movingPlatform -> {
            if (movingPlatform.getLeftX() <= 0 || (movingPlatform.getRightX()) >= canvas.getWidth()) {
                movingPlatform.changeDirection();
            }
            movingPlatform.move();
        });
    }


}


