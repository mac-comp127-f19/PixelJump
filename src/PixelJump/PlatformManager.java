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
private List<Double> platformHeights;
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

 public PlatformManager(CanvasWindow canvas,Pixel pixel){

     this.canvas=canvas;
     this.platformWidth=(canvas.getWidth()*0.2);
     this.platformHeight=(canvas.getHeight()*0.03);
     this.maxX=canvas.getWidth();
     this.maxY=60;
     this.currentY=canvas.getHeight()-20;
     rand=new Random();
     this.pixel=pixel;
     platformHeights = new ArrayList<>();
     platforms = new ArrayList<>();
//     pixelY=pixel.getY();//assume we have the method to get y for now

 }

 public void generatePlatforms(){
     platformCollection=new GraphicsGroup();
//     if(pixelY>){
//
//     }
//     else(){
//
//     }
     while(currentY>maxY){
         currentX = rand.nextDouble()*maxX;
         Platform platform=new Platform(currentX,currentY,platformWidth,platformHeight);
         platform.setStroked(true);
         platform.setStrokeColor(Color.ORANGE);
         platform.setFilled(true);
         platform.setFillColor(Color.ORANGE);
         platformCollection.add(platform);
         platforms.add(platform);
         platformHeights.add(currentY);
         currentY-=(rand.nextDouble()* jumpRange);
     }
     canvas.add(platformCollection);
 }

 public void pixelLands() {
     for (Platform platform : platforms) {
         if (platform.getRightX() > pixel.getBottomLeftX() && platform.getLeftX() < pixel.getBottomRightX()) {
             if (pixel.getPreviousBottomPixelY() <= platform.getTopYPosition() && pixel.getCurrentBottomPixelY() >= platform.getTopYPosition()) {
//                 System.out.println("Hi");
                 pixel.setMaxYAndBaseY(platform.getTopYPosition());
             }
         }
     }
 }


 public void removeSinglePlatform(){

 }

    /**
     * Remove all platforms when the pixel is lower than the lower boundary
     */
 public void removeAllPlatform(){
     platformCollection.removeAll();
 }


}
