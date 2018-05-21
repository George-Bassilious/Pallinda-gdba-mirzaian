
import java.lang.Math;

public class Tank {

    public int[] upleft = {0,0};
    public int[] upright = {90,0};
    public int[] downleft = {0,60};
    public int[] downright = {90,60};

    public float xCoord;
    public float yCoord;
    public int velocity;
    public double angle;


    public Tank(int xCoord,int yCoord, int velocity,double angle ){
        this.xCoord=xCoord;
        this.yCoord=yCoord;
        this.velocity=velocity;
        this.angle=angle;
    }

    public int getSpeed(){
        return velocity;
    }

    public float getxCoord(){
        return xCoord;
    }

    public double getAngle(){
        return angle;
    }

    public float getyCoord(){
        return yCoord;
    }

    public void setxCoord(int x){
        xCoord=x;
    }
    public void setAngle(double angle1){
        angle=angle1;

    }

    public void setyCoord(int y){
        yCoord=y;
    }

    public void rotate(double change, double direction){

    }
    public void move(int direction,double angle){

        double xChange= Math.cos(Math.toRadians(angle))*direction;
        double yChange= Math.sin(Math.toRadians(angle))*direction;
        this.angle = angle;
        xCoord+= xChange*0.1f;
        yCoord+= yChange*0.1f;

    }
}
