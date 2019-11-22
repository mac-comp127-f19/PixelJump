package PixelJump;

import comp127graphics.CanvasWindow;

public class PixelJump {
    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGHT = 600;
    CanvasWindow canvas = new CanvasWindow("",CANVAS_WIDTH,CANVAS_HEIGHT);
    Pixel pixel;

    PlatformManager platformManager;
    public PixelJump(){
        pixel = new Pixel();
        platformManager=new PlatformManager(canvas,pixel);
        pixel.addToCanvas(canvas);
        pixelJumpRun();
        platformManager.generatePlatforms();
   }
   public void pixelJumpRun(){
        canvas.animate(()-> {
            pixel.pixelContinuousJump(.5);
            canvas.pause(20);
        }
        );
        canvas.onMouseMove(event -> pixel.pixelMove());
   }
    public static void main(String args[]){
       new PixelJump();

    }
}
