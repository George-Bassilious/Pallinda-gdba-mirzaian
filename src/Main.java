
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Main extends BasicGame
{
   float x1=100;
    float y1=100;

    Tank player= new Tank(100,100,4,0);
    Image img ;
    float turn=0;
    Shape currentS;
    boolean move=false;

    float xChange=0;
    float yChange=0;


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
        currentS= new Circle(x1, y1, 10);

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_LEFT)) {
            turn -= 1 * 0.4f;
            img.rotate(turn);
            turn = 0;

            player.setAngle(img.getRotation());

        }

        if (input.isKeyDown(Input.KEY_RIGHT)) {
            turn += 1 * 0.4f;
            img.rotate(turn);
            turn = 0;
            player.setAngle(img.getRotation());


        }
        if (input.isKeyDown(Input.KEY_UP)) {

            player.setAngle(img.getRotation());
            player.move(-1, player.getAngle());


        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            player.move(1, img.getRotation());

        }
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            // player.getBall().move(player.getAngle());
            if (player.getAmmosize() != 0&&!move) {

                player.shoot();

                xChange = (float) Math.cos(Math.toRadians(player.getAngle()));
                yChange = (float) Math.sin(Math.toRadians(player.getAngle()));

                move=true;
                System.out.println("pang");
            }

        }
        if (input.isKeyPressed(Input.KEY_C)) {
            move=false;
            x1 = ( player.getxCoord() + img.getWidth() / 2) +(xChange*100);
            y1 = (player.getyCoord() + img.getHeight() / 2)+(yChange*100);
            currentS = new cannonBall(x1, y1, 10);
            player.addBall(new cannonBall(600, 100 + 10 * player.getAmmosize(), 5));
        }


        if (move){

        x1 -= xChange * 0.4f;
        y1 -= yChange * 0.4f;
        currentS.setY(y1);
        currentS.setX(x1);
        if(y1 > 450 || x1 > 600 || x1 <0|| y1<0){
            move=false;
            //if(player.getAmmosize()!=0){
                x1 = player.getxCoord() + img.getWidth() / 2;
                y1 = player.getyCoord() + img.getHeight() / 2;
                currentS = new cannonBall(x1, y1, 10);
           // }
        }

    }else {
            x1 = player.getxCoord() + img.getWidth() / 2;
            y1 = player.getyCoord() + img.getHeight() / 2;
            currentS = new cannonBall(x1, y1, 10);
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


        img.draw(player.getxCoord(), player.getyCoord(), 1);
        if(player.getAmmosize()!=0)
      //  g.draw(player.getBall());


            if(player.getAmmosize()!=0) {
              //  currentS =
             //   Shape t= currentS.transform(Transform.createRotateTransform(1, x1, y1));

                g.draw(currentS);

            }
//       g.draw(s);

        if(player.getAmmo()!=null) {
            for (cannonBall b : player.getAmmo()) {
               // b.move(player.getAngle());
                g.draw(b);

            }
        }

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