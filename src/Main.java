

import java.util.ArrayList;

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


public class Main extends BasicGame {
    float x1 = 62;
    float y1 = 362;


    Tank player = new Tank(100, 100, 1, 10, true);
    Tank player2 = new Tank(400, 400, 2, 10, true);
    Image img;
    Image img2;
    Image cannon;

    boolean move = false;

    Sound sound;
    double temp = 0;

    private int shotNumber = 0;
    private int shotNumber2=0;

    private TiledMap map;
    Shape polygon;
    float[] vertices;

    public Main(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        cannon = new Image("images/canonball.jpg");
        sound = new Sound("sound/sound.ogg");

        map = new TiledMap("/Map2/map2.tmx");
        img = new Image("/images/tank1.png");
        img2 = new Image("/images/tank2.jpg");

        vertices = new float[]{72, 64, 147, 64, 147, 122, 72, 122};
        polygon = new Polygon(vertices);
        map = new TiledMap("/Map2/map2.tmx");
        img = new Image("/images/tank1.png");

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        
        Input input = gc.getInput();
      
        if (input.isKeyDown(Input.KEY_LEFT)) {

            turn(-1,player);
        }

        if (input.isKeyDown(Input.KEY_RIGHT)) {
            turn(1,player);

        }
        if (input.isKeyDown(Input.KEY_D)) {

            turn(1,player2);

        }

        if (input.isKeyDown(Input.KEY_A)) {


            turn(-1,player2);
        }


        if (input.isKeyDown(Input.KEY_W)) {
            move(-1, player2);

        }
        if (input.isKeyDown(Input.KEY_UP)) {
            move(-1, player);
        }

        if(input.isKeyDown(Input.KEY_UP)){
                player.move(-1, img.getRotation());
        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
                player.move(1, img.getRotation());
        }

        if (input.isKeyDown(Input.KEY_DOWN)) {
            move(1, player);
        }
        if (input.isKeyDown(Input.KEY_S)) {
            move(1, player2);
        }

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            shoot(player,shotNumber);
        }
        if (input.isKeyDown(Input.KEY_Q)) {
            shoot(player2,shotNumber2);
        }


        if (input.isKeyPressed(Input.KEY_C)) {

            move = true;

        }
        if (input.isKeyPressed(Input.KEY_V)) {

            move = true;

        }


        //tank lista med for loops

        if (!player.getAmmoList().isEmpty())
            for (cannonBall b : player.getAmmoList()) {


                if (b.getMove()) {
                    b.move(b.getAngle());
                    b.setVisible(true);

//temporary
                    if (b.getyCoord() > 800 || b.getxCoord() > 800 || b.getxCoord() < 0 || b.getyCoord() < 0) {
                        // System.out.println("kk"+b.getId());
                        b.setMove(false);

                    }
                }

            }
        if (!player2.getAmmoList().isEmpty())
            for (cannonBall b : player2.getAmmoList()) {


                if (b.getMove()) {
                    b.move(b.getAngle());
                    b.setVisible(true);

//temporary
                    if (b.getyCoord() > 800 || b.getxCoord() > 800 || b.getxCoord() < 0 || b.getyCoord() < 0) {
                        // System.out.println("kk"+b.getId());
                        b.setMove(false);
                    }


                }

            }
    }


    public void turn(int direction, Tank T) {

        float turn=0;
        turn +=direction*3.4f;
        if(T.equals(player)){
            img.rotate(turn);

            player.setAngle(img.getRotation());
            polygon = polygon.transform(Transform.createRotateTransform((float) Math.toRadians(turn),polygon.getCenterX(),polygon.getCenterY()));
            turn = 0;
        }
        if(T.equals(player2)){
            img2.rotate(turn);
            player2.setAngle(img2.getRotation());


        }

    }


   public void shoot(Tank T,int shotNumber){

       if (T.getAmmosize() != 0 && !T.getAmmo(shotNumber).getMove()) {


           sound.play(1,100);
           temp= T.getAngle();

           x1 = T.getxCoord() + (img.getWidth() / 2);
           y1 = T.getyCoord() + (img.getHeight() / 2);
           if(T.equals(player2)){
               x1= T.getxCoord() + (img2.getWidth() / 2);
               y1 = T.getyCoord() + (img2.getHeight() / 2);
           }


           if(T.getAmmosize()!=0) {

               T.getAmmo(shotNumber).setxCoord(x1);
               T.getAmmo(shotNumber).setyCoord(y1);
               T.getAmmo(shotNumber).setAngle(temp);
               T.getAmmo(shotNumber).setMove(true);

               shotNumber++;
               T.shoot();
           }

       }
    }

    public void move(int direction,Tank T){

        if(T.equals(player)) {
            player.move(direction, img.getRotation());

            double xChange=  Math.cos(Math.toRadians(img.getRotation()))*1*0.1f;
            double yChange=  Math.sin(Math.toRadians(img.getRotation()))*1*0.1f;


            polygon.setCenterX(polygon.getCenterX() + (float) xChange);
            polygon.setCenterY(polygon.getCenterY() + (float) yChange);


        }


        if(T.equals(player2))player2.move(direction,img2.getRotation());
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

        if(player.getAmmosize()!=0) {
           for (int x=0;x<player.getAmmosize();x++) {
            //   System.out.println(player.getAmmosize());
               cannonBall b=player.getAmmo(x);
               cannon.draw(b.getxCoord(),b.getyCoord(),0.1f);
              // g.draw(b);
           }
        }

        if(player2.getAmmosize()!=0) {
            for (int x = 0; x < player2.getAmmosize(); x++) {

                cannonBall b = player2.getAmmo(x);
                cannon.draw(b.getxCoord(), b.getyCoord(), 0.1f);
                  //  g.draw(b);
            }
        }




        sidePanel(g);


        g.draw(polygon);




        img.draw(player.getxCoord(), player.getyCoord(), 1);
        img2.draw(player2.getxCoord(),player2.getyCoord(),1);

        if (!player.isState()){
            gameover(player2,g);
        }
        else if(!player2.isState()){
            gameover(player,g);
        }

        if(move) gameover(player,g);


    }

    public void sidePanel(Graphics g) {

       g.drawString("Player one!",820,50);
       g.drawString("Player two!",820,730);
       g.drawString("shots:"+player.getAmmosize(),820,80);
       g.drawString("shots:"+player2.getAmmosize(),820,750);

    }


    public void gameover(Tank winner,Graphics g){

        if(winner.equals(player)) g.drawString("Congratulations \n player 1 !",820,400);
        if(winner.equals(player2)) g.drawString("Congratulations player 2 !",820,400);


    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(1000, 800, false);
            //  appgc.setShowFPS(false);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(60);
            appgc.start();

        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}