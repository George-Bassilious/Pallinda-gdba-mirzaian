import org.newdawn.slick.geom.Circle;


public class cannonBall extends Circle {


    private float xCoord;
    private float yCoord;
    private boolean move;
    private int id;
    private double angle;
private boolean visible;

    public cannonBall(float centerPointX, float centerPointY, float radius,int id) {
        super(centerPointX, centerPointY, radius);
        xCoord=centerPointX;
        yCoord=centerPointY;
        this.id=id;
    }


    public void move(double angle) {

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

    public int getId() {
        return id;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
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


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
