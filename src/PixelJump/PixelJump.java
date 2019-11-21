package PixelJump;

import comp127graphics.CanvasWindow;

public class PixelJump {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 800;
    CanvasWindow canvas = new CanvasWindow("",800,800);
    Pixel pixel;

    public PixelJump(){
        pixel = new Pixel();
        pixel.addToCanvas(canvas);
        pixelJumpRun();

   }
   public void pixelJumpRun(){
        canvas.animate(()-> {
            pixel.pixelContinuousJump();
            canvas.pause(2000);
        });
   }
    public static void main(String args[]){
       new PixelJump();

    }
}
