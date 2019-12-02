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
private static final double jumpRange =100;
private double maxX;
private double maxY;
//private double pixelY;
private double currentY; // the position of the current platform
private double currentX;
private Random rand;
Pixel pixel;
private List<Double> platformHeightCollection;
 public PlatformManager(CanvasWindow canvas,Pixel pixel){

     this.canvas=canvas;
     this.platformWidth=(canvas.getWidth()*0.1);
     this.platformHeight=(canvas.getHeight()*0.01);
     this.maxX=canvas.getWidth();
     this.maxY=60;
     this.currentY=canvas.getHeight()-20;
     rand=new Random();
     this.pixel=pixel;
     platforms = new ArrayList<>();
//     platformCollection = new ArrayList<>();

 }

    /**
     * a method that generate platforms in random order and added on screen via a graphics group
     */
 public void generatePlatforms(){
     platformCollection=new GraphicsGroup();
     while(currentY>maxY){
         currentX = rand.nextDouble()*maxX;
         Platform platform=new Platform(currentX,currentY,platformWidth,platformHeight);
         platform.setStroked(true);
         platform.setStrokeColor(Color.ORANGE);
         platform.setFilled(true);
         platform.setFillColor(Color.ORANGE);
         platformCollection.add(platform);
         platforms.add(platform);
         currentY-=(rand.nextDouble()* jumpRange);
     }
     canvas.add(platformCollection);

 }

 public void getPlatformHeight(){

 }


    /**
     * a method that detects whether the pixel had land on it
     * this method then sets the Y position of the pixel
     *
     */
 public boolean pixelLands(){
    for(Platform platform: platforms){
        System.out.println(platform.getTopYPosition());
        if(pixel.getCurrentBottomY() == platform.getTopYPosition()){

            pixel.setMaxYAndBaseY(platform.getTopYPosition() - 100, platform.getTopYPosition());
            return true;
        }
    }
    return false;
 }

//    public void pixelLands(){
//
//
//    }
 public void removeSinglePlatform(){

 }

    /**
     * Remove all platforms when the pixel is lower than the lower boundary
     */
 public void removeAllPlatform(){
     platformCollection.removeAll();
 }


}
