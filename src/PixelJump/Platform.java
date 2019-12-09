package PixelJump;
import comp127graphics.CanvasWindow;
import comp127graphics.Rectangle;
public class Platform extends Rectangle {

    public Platform(double positionX, double positionY, double platformWidth, double platformHeight){
        super(positionX, positionY, platformWidth, platformHeight);
    }
    public double getTopYPosition(){
        return getY();
    }
    public double getLeftX(){
        return getX();
    }
    public double getRightX(){
        return getX() + getWidth();
    }
}
