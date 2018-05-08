
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
    Tank player= new Tank(200,200,4,0);
    Image img ;
    float turn=0;

    float angle2;
    // Testa att det inte fuckar

    Rectangle s;
    Transform T= new Transform();


    public Main(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        //  InputProvider provider = new InputProvider(gc.getInput());
        //  provider.addListener(this);
        //  provider.bindCommand(new KeyControl(Input.KEY_SPACE), move);

        img = new Image("/home/georgeb/courses/projinda/TestGame1/images/GreenTank2.jpg");
        s=new Rectangle(player.getxCoord(),player.getyCoord(),10,100);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {


        // player.setAngle(player.angle+=i*0.03f);



        // img.draw(player.xCoord,player.yCoord);
        // img.draw(player.xCoord, player.yCoord, 1);

        // player.move();
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_LEFT)){
            turn-=1;
            player.setAngle(player.angle-=1);

            //  angle2--;

        }

        if(input.isKeyDown(Input.KEY_RIGHT)){
            turn+=1;
            player.setAngle(player.angle+=1);
            // test1=player.getAngle();
            //  angle2++;

        }
        if(input.isKeyDown(Input.KEY_UP)){
            //   System.out.println(img.getRotation());
            //     System.out.println(player.getAngle());
            //  move(1);
            player.move(1,player.getAngle());
            //  test2=player.getAngle();
            //  if(test1==test2) System.out.println("WOrks");
            //else System.out.println("not works");


        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            player.move(-1,player.angle);
        }
        img.rotate(turn);

        turn=0;



    }


    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {




        Shape shape = s.transform(Transform.createRotateTransform((float) Math.toRadians(angle2),s.getCenterX(),s.getCenterY()));

        s.transform(T.createRotateTransform(20));

        g.draw(shape);

        //   g.drawLine(100,100,100,200);

        img.draw(player.xCoord, player.yCoord, 1);

        // g.drawImage(img,player.xCoord,player.yCoord);

        //  g.drawImage(img,100,100);




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