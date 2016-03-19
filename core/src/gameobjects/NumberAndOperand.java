package gameobjects;

import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.util.Random;

import gameworld.GameObject;

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
    //TODO: SIYUAN INSERT CORRESPONDING TEXT IMAGE
    private File image;


    //attributes for number_and_operand
    public NumberAndOperand(){
        Random random = new Random();
        value= random.nextInt(9);
        operand_chooser= random.nextInt(50);
        if (operand_chooser<40){
            operation= "+";
        }
        else{
            operation= "*";
        }
        //overwrite value cos only need mul2 and mul3
        if (operation.equals("*")){
            choose_2_or_3= random.nextInt(30);
            if (choose_2_or_3<=15){
                value=2;
            }
            else{
                value=3;
            }
        }
        number_n_operand= operation + String.valueOf(value);
    }

    public void update_player_situation(Player player){
        player.setCurrentValue(value, operation);
    };

    public String getName(){
        return number_n_operand;
    }


}