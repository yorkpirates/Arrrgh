package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.mygdx.game.map.Box2DHelper;
import com.mygdx.game.map.PhysicsWorld;
import static com.mygdx.game.Enums.COLLEGE.GOODRICKE;
import static com.mygdx.game.Enums.COLLEGE.CONSTANTINE;
import static com.mygdx.game.Enums.COLLEGE.LANGWITH;
import static com.mygdx.game.Enums.COLLEGE.LISTER;


/** Class that creates an AI boat */
public class AiBoat extends Entity{
    int direction_x,direction_y;
    private char lastD;
    int colour = 0;

    public AiBoat(Vector3 pos, PhysicsWorld box2d, Enums.COLLEGE college){
        type = Enums.ENTITYTYPE.BOAT;
        width = 8;
        height = 12;
        if (college == GOODRICKE){
            colour = 1;
        }
        else if (college == CONSTANTINE){
            colour = 2;
        }
        else if (college == LANGWITH){
            colour = 3;
        }
        else if (college == LISTER){
            colour = 4;
        }
        this.position.x = pos.x;
        this.position.y = pos.y;
        //texture = new Texture("red_boat/stationary_up.png");
        speed = 10;
        this.createBody(box2d.world, width-5, height, pos, BodyDef.BodyType.DynamicBody); // height /2
        EdgeShape sight = new EdgeShape();
        direction_x=(int) Math.floor(Math.random()*3)+-1;
        direction_y=(int) Math.floor(Math.random()*3)+-1;
    }

    /** updates position of boats*/
    public void update() {
        int randX = (int) Math.floor(Math.random()*3)+-1;
        int randY = (int) Math.floor(Math.random()*3)+-1;
        if (Math.floor(Math.random()*(1000+1)+0)>975){

            if (Math.floor(Math.random()*(100+1)+0)>50){
                direction_y=randY;
                direction_x = 0;
            }
            else{
                direction_x=randX;
                direction_y = 0;
            }
        }
        if (direction_x != 0){
            if (direction_x ==1){ // Right
                width = 12;
                height = 8;
                if (colour == 1) { // Goodricke / Green
                    texture = new Texture("green_boat/right.png");
                }
                else if (colour == 2) { // Constantine / Purple
                    texture = new Texture("purple_boat/right.png");
                }
                else if (colour == 3) { // Langwith / Yellow
                    texture = new Texture("yellow_boat/right.png");
                }
                else if (colour == 4) { // Lister / Red
                    texture = new Texture("red_boat/right.png");
                }
            }
            else{ // Left
                width = 12;
                height = 8;
                if (colour == 1) { // Goodricke / Green
                    texture = new Texture("green_boat/left.png");
                }
                else if (colour == 2) { // Constantine / Purple
                    texture = new Texture("purple_boat/left.png");
                }
                else if (colour == 3) { // Langwith / Yellow
                    texture = new Texture("yellow_boat/left.png");
                }
                else if (colour == 4) { // Lister / Red
                    texture = new Texture("red_boat/left.png");
                }
            }
        }else{
            if (direction_y ==1){ // Up
                width = 8;
                height = 12;
                if (colour == 1) { // Goodricke / Green
                    texture = new Texture("green_boat/up.png");
                }
                else if (colour == 2) { // Constantine / Purple
                    texture = new Texture("purple_boat/up.png");
                }
                else if (colour == 3) { // Langwith / Yellow
                    texture = new Texture("yellow_boat/up.png");
                }
                else if (colour == 4) { // Lister / Red
                    texture = new Texture("red_boat/up.png");
                }
            }
            else{ // Down
                width = 8;
                height = 12;
                if (colour == 1) { // Goodricke / Green
                    texture = new Texture("green_boat/down.png");
                }
                else if (colour == 2) { // Constantine / Purple
                    texture = new Texture("purple_boat/down.png");
                }
                else if (colour == 3) { // Langwith / Yellow
                    texture = new Texture("yellow_boat/down.png");
                }
                else if (colour == 4) { // Lister / Red
                    texture = new Texture("red_boat/down.png");
                }
            }
        }

        body.setLinearVelocity(direction_x * speed, direction_y * speed);
        position.x = body.getPosition().x - width/2;
        position.y = body.getPosition().y - height/4;

    }

    /** Reverses a boat direction if has collided */
    public void collision(){
        //Have collided immediately reverse the direction of travel.
        direction_x= direction_x *-1;
        direction_y=direction_y *-1;
    }

    /** Returns X position of boat */
    public float getXPosition() {
        return this.position.x;
    }

    /** Returns Y position of boat */
    public float getYPosition() {
        return this.position.y;
    }

}

