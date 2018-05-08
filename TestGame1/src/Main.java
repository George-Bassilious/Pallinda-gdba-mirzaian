
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


    boolean r= false;

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
        if(input.isKeyDown(Input.KEY_R)){
            if(!r) r=true;
            else r=false;
        }

        img.rotate(turn);
        turn=0;



    }


    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {


      //  double x= (player.getxCoord()-x1);
       // double y= (player.getyCoord()-y1);
       // if(r)
       // System.out.println("x: "+ x+ " y: "+y);


    // s=new Rectangle(player.getxCoord(),player.getyCoord(),10,100);

      //  Shape shape = s.transform(Transform.createRotateTransform((float) Math.toRadians(player.getAngle()),s.getCenterX(),s.getCenterY()));

        //g.draw(shape);

        for(int x=0;x<550;x+=50){
            g.drawLine(x,0,x,500);
        }
        for(int y=0;y<500;y+=50){
            g.drawLine(0,y,500,y);
        }
        g.drawLine(600,100,650+Math.round(Math.cos(player.getAngle())),150+Math.round(Math.sin(player.getAngle())));

        img.draw(player.xCoord, player.yCoord, 1);

        //g.drawLine(150,100,150,500);

      //g.drawLine(200,100,200,500);



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