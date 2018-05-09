
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Main extends BasicGame
{
    int x1=100;
    int y1=100;

    Tank player= new Tank(x1,y1,4,0);
    Image img ;
    float turn=0;


    public Main(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        //  InputProvider provider = new InputProvider(gc.getInput());
        //  provider.addListener(this);
        //  provider.bindCommand(new KeyControl(Input.KEY_SPACE), move);
        img = new Image("/images/GreenTank2.jpg");

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_LEFT)){
            turn-=1*0.4f;
            img.rotate(turn);
            turn=0;

            player.setAngle(img.getRotation());

        }

        if(input.isKeyDown(Input.KEY_RIGHT)){
            turn+=1*0.4f;
            img.rotate(turn);
            turn = 0;
            player.setAngle(img.getRotation());
            System.out.println(player.getAngle());
            System.out.println("x:" + player.getxCoord());
            System.out.println("y:" + player.getyCoord());


        }
        if(input.isKeyDown(Input.KEY_UP)){

            player.setAngle(img.getRotation());
            player.move(-1,player.getAngle());


        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            player.move(1,img.getRotation());
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        for(int x=0;x<550;x+=50){
            g.drawLine(x,0,x,500);
        }
        for(int y=0;y<500;y+=50){
            g.drawLine(0,y,500,y);
        }
        g.drawLine(600,100,100+Math.round(Math.cos(player.getAngle())),100+Math.round(Math.sin(player.getAngle())));


        img.draw(player.xCoord, player.yCoord, 1);
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
            //  appgc.setShowFPS(false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}