package gamehelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import gameworld.GameObject;

/**
 * Created by Hazel on 29/2/2016.
 */
public class AssetLoader {
    public static Texture texture, speedUp, player;
    public static TextureRegion bg, grass;

    public static Texture up, down, left, right;
    public static Texture touchBackground, touchKnob;

    public static TextureRegion skullUp, skullDown, bar;
    public static ArrayList<Texture> textures = new ArrayList<Texture>();

    public static void load() {
        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        bg = new TextureRegion(texture, 0,0,136, 43);
        //arguments: don't flip x, but flip y (since libGDX uses a Y Down coord system)
        bg.flip(false, true);

        player = new Texture(Gdx.files.internal("data/Character/Character1.png"));
        textures.add(player);

        speedUp = new Texture(Gdx.files.internal("data/speedup.png"));
        textures.add(speedUp);

        up = new Texture(Gdx.files.internal("data/Buttons/up.png"));
        down = new Texture(Gdx.files.internal("data/Buttons/down.png"));
        left = new Texture(Gdx.files.internal("data/Buttons/left.png"));
        right = new Texture(Gdx.files.internal("data/Buttons/right.png"));

        touchBackground = new Texture(Gdx.files.internal("data/Buttons/touchbackground.png"));
        touchKnob = new Texture(Gdx.files.internal("data/Buttons/knob.png"));



    }

    public static void dispose() {
        //dispose of texture when finished
        texture.dispose();

    }


}
