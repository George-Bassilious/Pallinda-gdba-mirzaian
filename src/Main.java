
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.tiled.TiledMap;

public class Main extends BasicGame
{
    int x1=62;
    int y1=62;

    int[] upleft = {0,0};
    int[] upright = {90,0};
    int[] downleft = {0,60};
    int[] downright = {90,60};

    Tank player= new Tank(x1,y1,4,0);
    Image img ;
    float turn=0;
    private TiledMap map;
    Shape polygon;
    float[] vertices;

    public Main(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        vertices = new float[]{72, 64, 147, 64, 147, 122, 72, 122};
        polygon = new Polygon(vertices);
        map = new TiledMap("/Map2/map2.tmx");
        img = new Image("/images/tank1.png");

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        // behövs egentligen inte
        int collision = map.getLayerIndex("Collision");

        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_LEFT)){
            turn-=1*0.4f;
            img.rotate(turn);
            polygon = polygon.transform(Transform.createRotateTransform((float) Math.toRadians(turn),polygon.getCenterX(),polygon.getCenterY()));
            turn=0;
        }

        if(input.isKeyDown(Input.KEY_RIGHT)){
            turn+=1*0.4f;
            img.rotate(turn);
            polygon = polygon.transform(Transform.createRotateTransform((float) Math.toRadians(turn),polygon.getCenterX(),polygon.getCenterY()));
            turn = 0;
        }

        if(input.isKeyDown(Input.KEY_UP)){
            if (nextMoveValid(-1,collision)) {
                player.move(-1, img.getRotation());

                double xChange=  Math.cos(Math.toRadians(img.getRotation()))*-1*0.1f;
                double yChange=  Math.sin(Math.toRadians(img.getRotation()))*-1*0.1f;

                polygon.setCenterX(polygon.getCenterX() + (float) xChange);
                polygon.setCenterY(polygon.getCenterY() + (float) yChange);

            }
        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            if (nextMoveValid(1,collision)) {
                player.move(1, img.getRotation());

                double xChange=  Math.cos(Math.toRadians(img.getRotation()))*1*0.1f;
                double yChange=  Math.sin(Math.toRadians(img.getRotation()))*1*0.1f;

                polygon.setCenterX(polygon.getCenterX() + (float) xChange);
                polygon.setCenterY(polygon.getCenterY() + (float) yChange);
            }
        }
    }

    private boolean nextMoveValid(int direction, int collision){

        double xChange = Math.cos(Math.toRadians(img.getRotation()))*direction;
        double yChange = Math.sin(Math.toRadians(img.getRotation()))*direction;

        if (map.getTileId((int)Math.floor(polygon.getPoints()[0]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[1]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(polygon.getPoints()[2]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[3]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(polygon.getPoints()[4]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[5]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(polygon.getPoints()[6]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[7]/32 + (int) yChange),collision) != 0){
            return false;
        }

       return true;
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        map.render(0,0);
        img.draw(player.xCoord, player.yCoord, 1);
        g.draw(polygon);
        g.drawString(String.valueOf(player.getxCoord()), 10 , 25);
        g.drawString(String.valueOf(player.getyCoord()), 10 , 45);
        g.drawString(String.valueOf(player.getAngle()),10,65);
        g.drawString(String.valueOf(polygon.getCenterX()),10,85);
        g.drawString(Arrays.toString(polygon.getPoints()),10,105);

    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(800, 800, false);
            //  appgc.setShowFPS(false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}