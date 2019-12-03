package PixelJump;
import comp127graphics.Rectangle;
public class Platform extends Rectangle {
    private double positionX;
    private double positionY;
    private double platformWidth;
    public Platform(double positionX, double positionY, double platformWidth, double platformHeight){
        super(positionX,positionY,platformWidth,platformHeight);
        this.positionX=positionX;
        this.positionY=positionY;
        this.platformWidth=platformWidth;
    }
    public double getTopYPosition(){
        return positionY;
    }
    public double getLeftX(){
        return positionX;
    }
    public double getRightX(){
        return positionX + platformWidth;
    }
}
