package PixelJump;
import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;
import comp127graphics.GraphicsObject;

import java.util.Random;

public class PlatformManager {
private CanvasWindow canvas;
private GraphicsGroup platformCollection;
private double platformWidth;
private double platformHeight;
private static final int Jumprange=100;
private Random rand;
 public PlatformManager(CanvasWindow canvas){
     this.canvas=canvas;
     this.platformWidth=(canvas.getWidth()*0.1);
     this.platformHeight=(canvas.getHeight()*0.1);
     rand=new Random();
 }

 public void generatePlatforms(){
     platformCollection=new GraphicsGroup();

 }



 public void removeSinglePlatform(){

 }
 public void removeAllPlatform(){
     platformCollection.removeAll();
 }


}
