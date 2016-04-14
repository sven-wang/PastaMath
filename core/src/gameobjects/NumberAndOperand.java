package gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MyGdxGame;

import java.io.File;
import java.util.Random;

import gameworld.GameObject;
import screens.GameScreen;

/**
 * Created by valerie_tan on 3/19/2016.
 */
public class NumberAndOperand extends Item { //All these are a form of item
    public int value;
    public String operation;
    public String number_n_operand;

    private static int largest_number=9;
    private static int operand_chooser;
    private static int choose_2_or_3;



    //attributes for number_and_operand
    public NumberAndOperand(String operation, int value, float x, float y){
        this.operation = operation;
        this.value = value;
        number_n_operand= operation + String.valueOf(value);
        position = new Vector2(x,y);
        this.world = GameScreen.world;
        defineItem();





    }
    public NumberAndOperand(){
        Random random = new Random();
        value= random.nextInt(8)+1;
        operand_chooser= random.nextInt(50);
        if (operand_chooser<40){
            operation= "plus";
        }
        else{
            operation= "mul";
        }
        //overwrite value cos only need mul2 and mul3
        if (operation.equals("mul")){
            choose_2_or_3= random.nextInt(30);
            if (choose_2_or_3<=15){
                value=2;
            }
            else{
                value=3;
            }
        }
        number_n_operand= operation + String.valueOf(value);

        this.world = GameScreen.world;
        defineItem();

    }

    public void update_player_situation(Player player){
        player.setCurrentValue(value, operation);
        MyGdxGame.playServices.sendToPlayer("ITEM " + getID() + " " + "RM");
        MyGdxGame.playServices.sendToPlayer("SCORE " + player.getId()+" "+ player.getCurrentValue());
    }



    public String getName(){
        return number_n_operand;
    }


}
