package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.map.PhysicsWorld;

public class Castle extends Entity{
    String name;

    /**
     * @param pos the position for the castle
     * @param box2d the hitbox for each castle
     * @param team the team/college each castle is assigned to
     */
    public Castle(Vector3 pos, PhysicsWorld box2d, int team){
        type = Enums.ENTITYTYPE.CASTLE;
        width = 18;
        height = 15;
        name="Castle";
        this.position.x = pos.x;
        this.position.y = pos.y;

        this.createBody(box2d.world, width, height, pos, BodyDef.BodyType.StaticBody);

        // Teams: 1=Red, 2=Green, 3=Yellow, 4=Pink
        if (team==1){
            texture = new Texture("castle/red_castle.png");
        }
        else if (team==2){
            texture = new Texture("castle/green_castle.png");
        }
        else if (team==3){
            texture = new Texture("castle/yellow_castle.png");
        }
        else{
            texture = new Texture("castle/pink_castle.png");
        }
        body.setUserData(this);
    }
}
