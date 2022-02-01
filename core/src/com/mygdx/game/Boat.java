package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.map.Box2DHelper;
import com.mygdx.game.map.PhysicsWorld;
import org.graalvm.compiler.loop.MathUtil;
import org.w3c.dom.Text;

import static com.mygdx.game.Enums.COLLEGE.*;


public class Boat extends Entity{
    int direction_x,direction_y;
    private char lastD;
    private boolean verticle;
    private boolean sunk = false;
    private String folder;
    private Texture movingUp,movingDown,movingLeft,movingRight,stationaryUp,stationaryDown,stationaryLeft,stationaryRight,dead;

    /**
     * @param pos the position of the boat
     * @param box2d the hitbox for each boat
     * @param college the college/team assigned for each boat to decide its sprite texture & attributes
     */
    public Boat(Vector3 pos, PhysicsWorld box2d, Enums.COLLEGE college){
        type = Enums.ENTITYTYPE.BOAT;
        width = 8;
        height = 12;
        this.position.x = pos.x;
        this.position.y = pos.y;

        speed = 30;

        this.createBody(box2d.world, width-5, height, pos, BodyDef.BodyType.DynamicBody);

        // Textures for first loading into the game
        if (college == LISTER){
            texture = new Texture("red_boat/stationary_up.png");
        }
        else if (college == CONSTANTINE){
            texture = new Texture("purple_boat/stationary_up.png");
        }
        else if (college == GOODRICKE){
            texture = new Texture("green_boat/stationary_up.png");
        }
        else if (college == LANGWITH) {
            texture = new Texture("yellow_boat/stationary_up.png");
        }
        //depending on the college we will select the folder which holds the textures for that college
        switch (college){
            case LISTER:
                folder = "red_boat/";
                break;
            case CONSTANTINE:
                folder = "purple_boat/";
                break;
            case GOODRICKE:
                folder = "green_boat/";
                break;
            case LANGWITH:
                folder = "yellow_boat/";
                break;
        }
        //Load in all the boat textures
        loadTextures();
    }

    /**
     * @param control the input from the user to define the direction of the boat's movement
     */
    public void update(UserInputManager control) {
        direction_x=0;
        direction_y=0;
        Boolean prevVerticle = verticle;
        if (!sunk) {
            if(control.down){
                direction_y = -1;
                width = 8;
                height = 12;
                texture = movingDown;
                lastD='D';

                verticle=true;
            }
            else if(control.up){
                direction_y = 1 ;
                width = 8;
                height = 12;
                texture = movingUp;
                lastD='U';
                verticle=true;
            }
            else if(control.left){
                direction_x = -1;
                width = 12;
                height = 8;
                texture = movingLeft;
                lastD='L';
                verticle=false;
            }
            else if(control.right){
                direction_x = 1;
                width = 12;
                height = 8;
                texture = movingRight;
                lastD='R';
                verticle=false;
            }
            else{ // if no input then must be stationary
                width = 8;
                height = 12;
                switch (lastD){
                    case 'U':
                        width = 8;
                        height = 12;
                        texture = stationaryUp;
                        break;
                    case 'D':
                        width = 8;
                        height = 12;
                        texture = stationaryDown;
                        break;
                    case 'L':
                        width = 12;
                        height = 8;
                        texture = stationaryLeft;
                        break;
                    case 'R':
                        width = 12;
                        height = 8;
                        texture = stationaryRight;
                        break;
                }
            }
        }
        body.setLinearVelocity(direction_x * speed, direction_y * speed);
        position.x = body.getPosition().x - width/2;
        position.y = body.getPosition().y - height/4;

    }

    /**
     * Load in all the textures for the boat
     */
    private void loadTextures(){
        //Folder is the folder they are in red/blue etc..
        movingUp = new Texture(folder+"up.png");
        movingDown = new Texture(folder+"down.png");
        movingLeft = new Texture(folder+"left.png");
        movingRight = new Texture(folder+"right.png");
        stationaryUp = new Texture(folder+"stationary_up.png");
        stationaryDown = new Texture(folder+"stationary_down.png");
        stationaryLeft = new Texture(folder+"stationary_left.png");
        stationaryRight = new Texture(folder+"stationary_right.png");
        dead = new Texture(folder+"dead.png");
    }

    /**
     * Sink the ship and place the sunken texture on it
     */
    public void sinkShip(){
        width = 12;
        height = 8;
        texture = dead;
        sunk = true;
    }

    /**
     *
     * @return the x coord of the boat
     */
    public float getXPosition() {
       return this.position.x;
    }

    /**
     *
     * @return the y coord of the boat
     */
    public float getYPosition() {
        return this.position.y;
    }
}
