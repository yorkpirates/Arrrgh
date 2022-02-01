package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.map.Box2DHelper;
import com.mygdx.game.map.PhysicsWorld;

public class Port extends Entity{
    int direction_x,direction_y;
    String name;
    Fixture fixture;
    Enums.COLLEGE allegiance;
    public int health;

    /**
     * @param pos the position of the port entity on the map
     * @param box2d the hitbox of the port entity to collide with boats
     * @param team the college/team of the port to identify the sprite texture and team
     */
    public Port(Vector3 pos, PhysicsWorld box2d, Enums.COLLEGE team){
        type = Enums.ENTITYTYPE.PORT;
        allegiance = team;
        width = 18;
        height = 8;
        name="Test port";
        health = 500;
        this.position.x = pos.x;
        this.position.y = pos.y;
        
        this.createBody(box2d.world, width, height, pos, BodyDef.BodyType.StaticBody);

        // Teams: 1=Red, 2=Green, 3=Yellow, 4=Pink
        if (team== Enums.COLLEGE.LISTER){
            texture = new Texture("dock/dock_red.png");
        }
        else if (team== Enums.COLLEGE.GOODRICKE){
            texture = new Texture("dock/dock_green.png");
        }
        else if (team== Enums.COLLEGE.LANGWITH){
            texture = new Texture("dock/dock_yellow.png");
        }
        else{
            texture = new Texture("dock/dock_pink.png");
        }
        body.setUserData(this);
    }
}
