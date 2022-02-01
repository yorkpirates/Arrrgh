package com.mygdx.game.map;
import java.util.ArrayList;

public class Chunk {
    int number_rows;
    int number_cols;
    int tile_size;

    public ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();

    /**
     *
     * @param number_rows number of rows of tiles
     * @param number_cols number of columns  of tiles
     * @param tile_size the size of the tile
     */
    public Chunk(int number_rows, int number_cols, int tile_size ){
        tiles = new ArrayList<ArrayList<Tile>>();
        this.number_rows = number_rows;
        this.number_cols = number_cols;
        this.tile_size = tile_size;
    }

    /**
     *
     * @param row the row of the tile you want
     * @param col the column of the tile you want
     * @return returns the tile
     */
    public Tile get_tile(int row, int col){
        //System.out.println("Row: " + row + " Col: " + col);
        ArrayList<Tile> chunk_row;
        if(tiles.size() > row && row >= 0){
            chunk_row = tiles.get(row);

            if(chunk_row != null && chunk_row.size() > col && col >= 0){
                return chunk_row.get(col);
            }
        }
        return null;
    }
}
