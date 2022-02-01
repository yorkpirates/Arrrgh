package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.mygdx.game.map.PhysicsWorld;

public class Neesa extends Entity{
    String name;

    /**
     * @param pos the position of the shop on the map
     * @param box2d the hitbox of the shop
     */
    public Neesa(Vector3 pos, PhysicsWorld box2d){
        type = Enums.ENTITYTYPE.NEESA;
        width = 24;
        height = 16;
        name="Neesa";
        this.position.x = pos.x;
        this.position.y = pos.y;
        this.createBody(box2d.world, width, height, pos, BodyDef.BodyType.StaticBody);
        texture = new Texture("Neesa.png");
        body.setUserData(this);
    }
}
