
import org.newdawn.slick.geom.Circle;

import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;

/**
 *
 */
public class Tank {

    private float xCoord;
    private float yCoord;
    private int velocity;
    private double angle;

    private ArrayList<cannonBall> ammo= new ArrayList<cannonBall>();
    private int ammoSize=0;


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
    public void addBall(cannonBall c){

        ammo.add(c);
        ammoSize++;


    }
    public void shoot(){

        if(ammoSize!=0) {
            ammoSize--;
            //check if empty null checks
            // ammo.get(0).move(angle);
            ammo.remove(ammoSize);
        }
        else System.out.println("error");

    }


    public void setyCoord(int y){
        yCoord=y;
    }

   public cannonBall getBall(){

        //null check
        if(ammoSize!=0)
        return ammo.get(ammoSize-1);

        else
            return null;

   }
    public void move(int direction,double angle){

        double xChange= Math.cos(Math.toRadians(angle))*direction;
        double yChange= Math.sin(Math.toRadians(angle))*direction;

        xCoord+= xChange*0.3f;
        yCoord+= yChange*0.3f;



    }

    public int getAmmosize() {
        return ammoSize;
    }

    public ArrayList<cannonBall> getAmmo() {
        return ammo;
    }

    public void setAmmoSize(int ammoSize) {
        this.ammoSize = ammoSize;
    }
}
