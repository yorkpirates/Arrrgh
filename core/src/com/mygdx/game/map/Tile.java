package com.mygdx.game.map;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Entity;
import com.mygdx.game.Enums;

/**
 *
 */
public class Tile extends Entity {
    public int size;
    public int row;
    public int col;
    public String code;
    public Texture texture;
    public Enums.TILETYPE type;

    /**
     *
     * @param x coordinate for the tile
     * @param y coordinate for the tile
     * @param size the size of the tile
     * @param type the type of the tile
     * @param texture the tiles texture
     */
    public Tile(float x, float y, int size, Enums.TILETYPE type, Texture texture){
        super();
        position.x = x*size;
        position.y = y*size;
        this.size = size;
        this.texture = texture;
        this.col = (int) x;
        this.row = (int) y;
        this.type = type;
        this.code = "";
    }
    public boolean is_land() {
        return type == Enums.TILETYPE.LAND;
    }

    public boolean is_water() {
        return type == Enums.TILETYPE.WATER;
    }
    public boolean is_passable(){
        return is_water();
    }
    public boolean is_not_passable(){
        return !is_water();
    }
}
