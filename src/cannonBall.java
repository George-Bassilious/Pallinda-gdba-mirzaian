import org.newdawn.slick.geom.Circle;


public class cannonBall extends Circle {


    private float xCoord;
    private float yCoord;
    private boolean move;


    public cannonBall(float centerPointX, float centerPointY, float radius) {
        super(centerPointX, centerPointY, radius);
        xCoord=centerPointX;
        yCoord=centerPointY;
    }


    public void move(double angle) {
        System.out.println("f");
        double xChange = Math.cos(Math.toRadians(angle));
        double yChange = Math.sin(Math.toRadians(angle));

        xCoord -= xChange * 0.4f;
        yCoord -= yChange * 0.4f;
        // System.out.println("pangag");


    }
    public boolean getMove(){
        return move;
    }

    public void setMove(boolean change){
        move=change;

    }

    public float getxCoord(){
        return xCoord;
    }
    public float getyCoord(){
        return yCoord;
    }

    public void setxCoord(float xCoord1) {
        xCoord=xCoord1;
    }
    public void setyCoord(float yCoord1) {
        yCoord=yCoord1;
    }




}
