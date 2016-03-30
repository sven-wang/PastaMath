package gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import java.util.Vector;

import gameconstants.GameConstants;
import gameworld.GameObject;
import gameworld.GameRenderer;
import gameworld.GameWorld;

/**
 * Created by WSY on 18/3/16.
 */
public abstract class Item implements GameObject{




    public abstract void update_player_situation(Player player);

    public abstract String getName();

    Vector2 position;

    private int width= (int) (75*GameConstants.SCALE_X);
    private int height= (int) (75*GameConstants.SCALE_Y);

    private float destructionCounter = 10;

    private Rectangle boundingRect;

    float lifeTime;

    public Item(){
        position = new Vector2();
        //this.assign_random_coord(); - to be done in Buffer
        lifeTime = 10 + (new Random()).nextFloat()*10;

    }

    public Item(float x, float y){
        position = new Vector2(x,y);
        setBoundingRect();

    }

    public void decreaseLife(float delta){
        lifeTime -= delta;
    }

    public boolean expired(){
        return (lifeTime<=0);
    }

    public void setBoundingRect(){
        boundingRect = new Rectangle(this.position.x-width/2, this.position.y-height/2, width, height); // DUNNO why x,y have to be shifted by half the dimension
    }


    public void destroy() {
        //boundingRect=null;
        //ORIGINAL
        //GameWorld.objectsCopy.remove(this);
        //NEW
        Gdx.app.log("Debug","Item destroyed.");
        GameWorld.simple_item_buffer.items_currently_appearing.remove(this);
    }

    public void update(float delta) {
        destructionCounter -= (1*delta);
        if(destructionCounter < 0) {
            this.destroy();
        }
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    //todo: add get and set for items
    public void setPosition(float x, float y){
        this.position.x= x;
        this.position.y= y;
    }

    public Vector2 getPosition(){
        return position;
    }


    public Rectangle getCollider() {
        return new Rectangle(this.boundingRect);
    }

    /*
    public void assign_random_coord() {
        //generate a random number (use integer, easier to check for overlap)
        Random randomizer = new Random();
        //nextInt gives 0 to n-1
        float x = randomizer.nextFloat() * (Gdx.graphics.getWidth()- 4) + 2 ;
        float y = randomizer.nextFloat() * (Gdx.graphics.getHeight() - 4) + 2;

        position.x= x;
        position.y= y;
    }
    */



}
