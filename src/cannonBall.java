import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class cannonBall extends Circle {


    private double xCoord;
    private double yCoord;


    public cannonBall(float centerPointX, float centerPointY, float radius) {
        super(centerPointX, centerPointY, radius);
        xCoord=centerPointX;
        yCoord=centerPointY;
    }


    public void move(double angle) {

        double xChange = Math.cos(Math.toRadians(angle));
        double yChange = Math.sin(Math.toRadians(angle));

        xCoord += xChange * 0.4f;
        yCoord += yChange * 0.4f;
        // System.out.println("pangag");


    }

    public double getxCoord(){
        return xCoord;
    }
    public double getyCoord(){
        return yCoord;
    }



}
