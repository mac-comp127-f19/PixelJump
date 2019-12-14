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
    Pixel pixel;  // should be private

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

    public Platform generateStartingPlatform(){
        Platform platform = new Platform(0,canvas.getHeight(),canvas.getWidth(),1);
        platform.setStroked(false);
        platform.setFilled(false);

        platforms.add(platform);
        platformCollection.add(platform);

        return platform;
    }


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

    public void pixelLands() {
        for (Platform platform : platforms) {
            if (pixel.didJustCrossPlatform(platform)) {
                pixel.bounceOff(platform);
            }
        }
    }

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


