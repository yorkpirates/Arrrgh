package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.Enums;

import java.util.ArrayList;

public class Campus_east {
    Texture water,land,dock_rl,dock_rr,
            land_tl, land_tc, land_tr,
            land_cl, land_cc, land_cr,
            land_bl, land_bc, land_br;
    public Vector2 centretile;
    public Vector3 middlecoords, pSpawnCoords, neesaCoords,
                   castleListerCoords, castleGoodrickeCoords, castleConstantineCoords, castleLangwithCoords,
                   portlisterCoords, portgoodrickeCoords, portconstantineCoords, portlangwithCoords,
                   aispawn_goodricke1, aispawn_constantine1, aispawn_langwith1, aispawn_lister1;

    String template[][] ={

            // Lister (red) BL             Goodricke (green) TL
            //
            //
            // Constantine (purple) BR     Langwith (yellow) TR


            //1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33   34   35   36   37   38   39   40   41   42
            {"cc","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cr","cc"},// 1
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 2
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 3
            {"tc","ww","ww","bl","cl","cl","cl","tl","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bl","cl","cl","cl","tl","ww","ww","bc"},// 4
            {"tc","ww","ww","bc","cc","cc","cc","tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc","cc","cc","cc","tc","ww","ww","bc"},// 5
            {"tc","ww","ww","bc","cc","cc","cc","tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc","cc","cc","cc","tc","ww","ww","bc"},// 6
            {"tc","ww","ww","bc","cc","cc","cc","tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc","cc","cc","cc","tc","ww","ww","bc"},// 7
            {"tc","ww","ww","br","cr","cr","cr","tr","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","br","cr","cr","cr","tr","ww","ww","bc"},// 8
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 9
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 10
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 11
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 12
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 13
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 14
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 15
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 16
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 17
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 18
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 19
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 20
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bl","cl","tl","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 21
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc","cc","tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 22
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","br","cr","tr","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 23
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 24
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 25
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 26 center
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 27
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 28
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 29
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 30
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 31
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 32
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 33
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 34
            {"tc","ww","ww","bl","cl","cl","cl","tl","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bl","cl","cl","cl","tl","ww","ww","bc"},// 35
            {"tc","ww","ww","bc","cc","cc","cc","tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc","cc","cc","cc","tc","ww","ww","bc"},// 36
            {"tc","ww","ww","bc","cc","cc","cc","tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc","cc","cc","cc","tc","ww","ww","bc"},// 37
            {"tc","ww","ww","bc","cc","cc","cc","tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc","cc","cc","cc","tc","ww","ww","bc"},// 38
            {"tc","ww","ww","br","cr","cr","cr","tr","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","br","cr","cr","cr","tr","ww","ww","bc"},// 39
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 40
            {"tc","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","ww","bc"},// 41
            {"cc","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cl","cc"} // 42 right side of screen
    };
    //        1    2    3    4    5    6    7    8    9    10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33   34   35   36   37   38   39   40   41   42
    public Chunk tempchunk;

    /**
     * @param box2D the hitbox for collidable tiles (with LAND attribute)
     */
    public Campus_east(PhysicsWorld box2D){
        //load textures
        land = new Texture("tiles/CC.png");
        water = new Texture("tiles/W.png");
        land_tl = new Texture("tiles/TL.png");
        land_tc = new Texture("tiles/TC.png");
        land_tr = new Texture("tiles/TR.png");
        land_cl = new Texture("tiles/CL.png");
        land_cc = new Texture("tiles/CC.png");
        land_cr = new Texture("tiles/CR.png");
        land_bl = new Texture("tiles/BL.png");
        land_bc = new Texture("tiles/BC.png");
        land_br = new Texture("tiles/BR.png");
        dock_rr = new Texture("tiles/dock_right_mount_r.png");
        dock_rl = new Texture("tiles/dock_right_mount_L.png");
        tempchunk = new Chunk(12,20, 8); // Chunk(rows, col, tilesize)

        int current_row = 0;
        int counter =0;
        Tile temptile;

        for (int i =0; i<template.length;i++){
            ArrayList<Tile>  rowlist = new ArrayList<Tile>();
            for (int j=0;j<template[0].length;j++){
                if (template[i][j]=="cc"){ // Land tiles
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land);
                }
                else if (template[i][j]=="wc"){ // water collision (edges of map)
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, water);
                }
                else if (template[i][j]=="tl"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_tl);
                }
                else if (template[i][j]=="tc"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_tc);
                }
                else if (template[i][j]=="tr"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_tr);
                }
                else if (template[i][j]=="cl"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_cl);
                }
                else if (template[i][j]=="cc"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_cc);
                }
                else if (template[i][j]=="cr"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_cr);
                }
                else if (template[i][j]=="bl"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_bl);
                }
                else if (template[i][j]=="bc"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_bc);
                }
                else if (template[i][j]=="br"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, land_br);
                }
                else if (template[i][j]=="rr"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.LAND, dock_rr);
                }
                else if (template[i][j]=="rl"){
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.WATER, dock_rl);
                }
                else{
                    temptile = new Tile(i,j, tempchunk.tile_size, Enums.TILETYPE.WATER,water);
                }
                rowlist.add(temptile);

            }
            tempchunk.tiles.add(rowlist);
        }
        centretile = new Vector2(Math.round(template.length/2),Math.round(template[0].length/2));
        middlecoords = tempchunk.get_tile((int)centretile.x, (int)centretile.y).position;

        // Player's boat spawn coords
        pSpawnCoords = tempchunk.get_tile(21,25).position;

        // Port Coords
        portlisterCoords = tempchunk.get_tile(7,5).position;
        portgoodrickeCoords = tempchunk.get_tile(7,36).position;
        portlangwithCoords = tempchunk.get_tile(33,36).position;
        portconstantineCoords = tempchunk.get_tile(33,5).position;

        // Castle base Coords
        castleListerCoords = tempchunk.get_tile(4,5).position;
        castleGoodrickeCoords = tempchunk.get_tile(4,36).position;
        castleLangwithCoords = tempchunk.get_tile(36, 36).position;
        castleConstantineCoords = tempchunk.get_tile(36, 5).position;

        // Shop Coords
        neesaCoords = tempchunk.get_tile(20,21).position;

        // AI Boat Spawn Coords
        aispawn_lister1 = tempchunk.get_tile(1,1).position;
        aispawn_goodricke1 = tempchunk.get_tile(9,36).position;
        aispawn_langwith1 = tempchunk.get_tile(32,35).position;
        aispawn_constantine1 = tempchunk.get_tile(32,6).position;

        addHitBoxes(box2D);
    }

    /**
     *
     * @param box2D the physics world too add the hitboxes too
     *
     * Adds hitboxes to all land tiles
     */
    private void addHitBoxes(PhysicsWorld box2D){
        for(ArrayList<Tile> row : tempchunk.tiles){
            for(Tile tile : row){
                if (tile.type== Enums.TILETYPE.LAND){
                    Box2DHelper.createBody(box2D.world,tempchunk.tile_size,tempchunk.tile_size,tile.position, BodyDef.BodyType.StaticBody);
                    //System.out.println(tile.is_land());
                }
            }
    }}

}
