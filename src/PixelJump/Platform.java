package PixelJump;
import comp127graphics.Rectangle;
public class Platform extends Rectangle {
    public Platform(double positionX, double positionY, double platformWidth, double platformHeight){
        super(positionX,positionY,platformWidth,platformHeight);

    }
    public double getTopYPosition(){
        return getY();
    }
}
