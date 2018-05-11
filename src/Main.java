
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

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

   // cannonBall r= new cannonBall(100,100,10);
    float xChange=0;
    float yChange=0;
    Sound sound;
    double temp=0;

    ArrayList<cannonBall> shotBalls= new ArrayList<>();
    private int shotNumber=0;


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
        for(int x=0;x<550;x+=50){
            g.drawLine(x,0,x,500);
        }
        for(int y=0;y<500;y+=50){
            g.drawLine(0,y,500,y);
        }


        img.draw(player.getxCoord(), player.getyCoord(), 1);



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


    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(640, 480, false);
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