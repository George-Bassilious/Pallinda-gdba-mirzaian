
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
    Image cannon;
    float turn=0;
    cannonBall currentS;
    boolean move=false;

    cannonBall r= new cannonBall(100,100,10);
    float xChange=0;
    float yChange=0;
    Sound sound;


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
        cannon= new Image("images/canonball.jpg");
       // currentS= new cannonBall(x1, y1, 10);
        sound = new Sound("sound/sound.ogg");

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
            //movement.play(1,1);


        }
       // if(input.isKeyPressed(Input.KEY_UP)) movement.stop();
        if (input.isKeyDown(Input.KEY_DOWN)) {
            player.move(1, img.getRotation());

        }
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            // player.getBall().move(player.getAngle());
            if (player.getAmmosize() != 0 && !move) {

                player.shoot();
                sound.play(1,100);


               // xChange = (float) Math.cos(Math.toRadians(player.getAngle()));
              //  yChange = (float) Math.sin(Math.toRadians(player.getAngle()));

                player.getBall().setMove(true);
                //move=true;
                System.out.println("pang");
            }

        }
        if (input.isKeyPressed(Input.KEY_C)) {
            //move=false;
            x1 = ( player.getxCoord() + img.getWidth() / 2) +(xChange*100);
            y1 = (player.getyCoord() + img.getHeight() / 2)+(yChange*100);
            currentS = new cannonBall(x1, y1, 10);

            player.addBall(new cannonBall(x1,y1,10));



        //    player.addBall(currentS);

            //player.addBall(new cannonBall(600, 100 + 10 * player.getAmmosize(), 5));
        }

        if(player.getAmmosize()!=0)
        if (player.getBall().getMove()){

        player.getBall().move(player.getAngle());

        cannonBall r= player.getBall();

        if(r.getyCoord() > 450 || r.getxCoord()> 600 || r.getxCoord() <0|| r.getyCoord()<0){
            player.getBall().setMove(false);
            //if(player.getAmmosize()!=0){
            // x1 = player.getxCoord() + img.getWidth() / 2;
            //y1 = player.getyCoord() + img.getHeight() / 2;
                //currentS = new cannonBall(x1, y1, 10);
           // }
        }

    }else {
          //  x1 = player.getxCoord() + img.getWidth() / 2;
          //  y1 = player.getyCoord() + img.getHeight() / 2;
          //  currentS = new cannonBall(x1, y1, 10);
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


          //  if(player.getAmmosize()!=0) {

            //    g.draw(currentS);

            //}
//       g.draw(s);

        if(player.getAmmo()!=null) {
            for (cannonBall b : player.getAmmo()) {
            //    g.draw(currentS);
                cannon.draw(b.getxCoord(),b.getyCoord(),0.1f);


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