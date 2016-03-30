package gameworld;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import gameobjects.Item;
import gameobjects.Player;
import gameobjects.Simple_Item_Buffer;



/**
 * Created by Hazel on 28/2/2016.
 */
public class GameWorld {
    private Player player1;

    //TODO: initialize all players and game objects here- SERVER
    public static ArrayList<Player> players = new ArrayList<Player>();

    public static Simple_Item_Buffer simple_item_buffer= new Simple_Item_Buffer();

    public static ArrayList<Item> items = simple_item_buffer.items_currently_appearing;  // items for server to update

    public static int endScore;

    public static boolean win;


    public GameWorld() {
        //initialize player here
        player1 = new Player(200, 200, 100, 100);


        //intialize buffer of items and pickups
        simple_item_buffer= new Simple_Item_Buffer();
        //all the items are initialized inside the buffer already when it is constructed

        endScore = new Random().nextInt(100) + 1;

    }

    //TODO(Extra): consider doing thread version? (complicated)

    //TODO: do all the "threading"- ADD items every few seconds
    public void update(float delta) {
        //all the items are initialized inside the buffer already when it is constructed

        for(Player player: players) {
            if(player.getCurrentValue()==this.endScore) {
                Gdx.app.log("World", "someone has won");
                win = true;
            }
        }

        //todo: use timer to generate new item everytime (random timing) if not more than 10 items
        //TODO: add object if capacity haven't reached- add to copy or the original?
        if(simple_item_buffer.items_currently_appearing.size() < Simple_Item_Buffer.max_items_capacity){
            //make new object every few seconds
            System.out.println("generate new item");
            simple_item_buffer.generate_random_Item();
        }


        player1.update(delta);
        //todo: remove Players and objects accordingly
        //TODO: PLAYER RESPONSES TO COLLISION
        //int i = 0;
        for(Iterator<Item> iterator =simple_item_buffer.items_currently_appearing.iterator(); iterator.hasNext(); ){

            Item item = iterator.next();
            item.decreaseLife(delta);
            if(item.expired()) iterator.remove();
            else if(player1.collides(item)){
                iterator.remove();
                //todo: remove corresponding coords
                simple_item_buffer.existing_item_pos_vec.remove(item.getPosition());
                item.update_player_situation(player1);

            }



//        for(Player each_player: players){
//            each_player.update(delta);
//            //: remove Players and objects accordingly
//            //: PLAYER RESPONSES TO COLLISION
//            //int i = 0;
//            for(Item item: simple_item_buffer.items_currently_appearing){
//                //System.out.println("item"+i++);
//                if(each_player.collides(item)){
//
//
//                    item.destroy();
//                    //todo: remove corresponding coords
//                    simple_item_buffer.existing_item_pos_vec.remove(item.getPosition());
//                    item.update_player_situation(each_player);
//
//
//                }
//            }
            //: check if a player collided into another player
//            for(Player other_player: players){
//                if (!other_player.equals(each_player)){ //you can't knock into yourself
//                    if (each_player.knock_into(other_player)){
//                        if(each_player.getShielded()) {
//                            each_player.update_collision_count();
//                        }
//                        else
//                            each_player.decreaseScoreUponKnock();
//                    }
//                }
//            }
        }




    }


    public Simple_Item_Buffer getSimple_item_buffer(){
        return simple_item_buffer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(){ //TODO: modify to the first player in list.
        return player1;
    }

}
