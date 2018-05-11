
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.tiled.TiledMap;

public class Main extends BasicGame
{
    int x1=32;
    int y1=32;

    Tank player= new Tank(x1,y1,4,0);
    Image img ;
    float turn=0;
    private TiledMap map;


    public Main(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        map = new TiledMap("/Map2/map2.tmx");
        img = new Image("/images/tank1.png");
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_LEFT)){
            turn-=1*0.4f;
            img.rotate(turn);
            turn=0;
        }

        if(input.isKeyDown(Input.KEY_RIGHT)){
            turn+=1*0.4f;
            img.rotate(turn);
            turn = 0;
        }

        if(input.isKeyDown(Input.KEY_UP)){
            player.setAngle(img.getRotation());
            player.move(-1,player.getAngle());
        }

        if(input.isKeyDown(Input.KEY_DOWN)){
            player.setAngle(img.getRotation());
            player.move(1,player.getAngle());
           // player.move(1, img.getRotation());
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        map.render(0,0);
        img.draw(player.xCoord, player.yCoord, 0.8f);
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