package PixelJump;
import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;

import java.util.Random;

public class PlatformManager {
private CanvasWindow canvas;
private GraphicsGroup platformCollection;
private double platformWidth;
private double platformHeight;
private static final double jumpRange =100;
private double maxX;
private double maxY;

private double currentY; // the position of the current platform
private double currentX;
private Random rand;


 public PlatformManager(CanvasWindow canvas){

     this.canvas=canvas;
     this.platformWidth=(canvas.getWidth()*0.1);
     this.platformHeight=(canvas.getHeight()*0.1);
     this.maxX=canvas.getWidth();
     this.maxY=canvas.getHeight()-60;
     this.currentY=10;
     rand=new Random();

 }

 public void generatePlatforms(){
     platformCollection=new GraphicsGroup();
     while(currentY<maxY){
         currentX = rand.nextDouble()*maxX;
         Platform platform=new Platform(currentX,currentY,platformWidth,platformHeight);
         currentY+=(rand.nextDouble()* jumpRange);
     }
 }



 public void removeSinglePlatform(){

 }

    /**
     * Remove all platforms when the pixel is off the lower b
     */
 public void removeAllPlatform(){
     platformCollection.removeAll();
 }


}
