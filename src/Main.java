
import java.util.ArrayList;
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
    
    
  //  float x1=100;
  //  float y1=100;


    Tank player= new Tank(100,100,4,0);
    Image img ;
    Image cannon;
    float turn=0;

    boolean move=false;

    Sound sound;
    double temp=0;

    ArrayList<cannonBall> shotBalls= new ArrayList<>();
    private int shotNumber=0;

    private TiledMap map;



    public Main(String gamename)
    {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {


       
        cannon= new Image("images/canonball.jpg");
        sound = new Sound("sound/sound.ogg");

        map = new TiledMap("/Map2/map2.tmx");
        img = new Image("/images/tank1.png");

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_LEFT)) {
            turn -= 1 * 0.4f;
            img.rotate(turn);

            player.setAngle(img.getRotation());
        }

        if (input.isKeyDown(Input.KEY_RIGHT)) {
            turn += 1 * 0.4f;
            img.rotate(turn);
            turn = 0;

            player.setAngle(img.getRotation());


        }


        if(input.isKeyDown(Input.KEY_UP)){
            player.move(-1, img.getRotation());

        }

        if (input.isKeyDown(Input.KEY_DOWN)) {
            player.move(1, img.getRotation());

        }
        if (input.isKeyPressed(Input.KEY_SPACE)) {

            if (player.getAmmosize() != 0 && !move) {


                sound.play(1,100);
                temp= player.getAngle();

                x1 = player.getxCoord() + img.getWidth() / 2;
                y1 = player.getyCoord() + img.getHeight() / 2;

                shotBalls.add(player.getBall());


                shotBalls.get(shotNumber).setxCoord(x1);
                shotBalls.get(shotNumber).setyCoord(y1);
                shotBalls.get(shotNumber).setAngle(temp);
                shotBalls.get(shotNumber).setMove(true);

                shotNumber++;
                player.shoot();

            }

        }
        if (input.isKeyPressed(Input.KEY_C)) {

            player.addBall(new cannonBall(600, 100+20*player.getAmmosize(), 10, player.getAmmosize()));

        }

        if(input.isKeyPressed(Input.KEY_A)) {
            shotBalls.clear();
            shotNumber=0;
            player.getAmmo().clear();
            player.setAmmoSize(0);
        }


        if (!shotBalls.isEmpty())
        for (cannonBall b: shotBalls) {


            if(b.getMove()) {
                b.move(b.getAngle());
                b.setVisible(true);

//temporary
                if (b.getyCoord() > 450 || b.getxCoord() > 600 || b.getxCoord() < 0 || b.getyCoord() < 0) {
                   // System.out.println("kk"+b.getId());
                    b.setMove(false);


                }
            }

        }

       

    }



    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {

if(player.getAmmosize()!=0) {
           for (cannonBall b : player.getAmmo()) {

               g.draw(b);
           }
        }

        if (!shotBalls.isEmpty())
        for(cannonBall b : shotBalls){

            cannon.draw(b.getxCoord(),b.getyCoord(),0.1f);
            if(!b.isVisible()) shotBalls.remove(b);
        }

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
            appgc.setVSync(true);
            appgc.start();

        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}