
import java.lang.Math;

public class Tank {

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
        angle=Math.abs(angle1+360)%360;
        //  if(angle >=360){

        // }

        //       || angle <=360){
        //angle=angle%360;
        //}

    }

    public void setyCoord(int y){
        yCoord=y;
    }

    public void rotate(double change, double direction){

    }
    public void move(int direction,double angle){


        double x = angle;
        double xChange=Math.abs(Math.cos(x))*direction;
        double yChange= Math.abs(Math.sin(x))*direction;
        //  System.out.println(getAngle());
        if(x >= 0 && x < 90){
            //  System.out.println(getAngle()+" case 1 cos:"+Math.cos(getAngle())+" sin: "+Math.sin(getAngle()));
            xCoord-= xChange*0.4f;
            yCoord-= yChange*0.4f;
        }
        else if (x >= 90 && x < 180) {
            xCoord += xChange*0.4f;
            yCoord -= yChange*0.4f;
        }
        else if( x >= 180 && x < 270) {
            xCoord += xChange*0.4f;
            yCoord += yChange*0.4f;
        }
        else if(x >= 270 && x < 360) {
            xCoord-= xChange*0.4f;
            yCoord+= yChange*0.4f;
        }


    }

}
