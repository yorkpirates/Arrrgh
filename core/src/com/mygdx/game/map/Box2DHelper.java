package com.mygdx.game.map;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class Box2DHelper {
    /**
     * Old class still in use by some un-updated entities
     * The updated better function is in entities
     * @param world the world in which the body will go
     * @param width the body's width
     * @param height the body's height
     * @param pos the body's position
     * @param type the type of body
     * @return Returns A body
     */
    public static Body createBody(World world, float width, float height, Vector3 pos, BodyDef.BodyType type) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x + width/2, pos.y +height/2);
        bodyDef.angle = 0;
        bodyDef.fixedRotation = false;
        bodyDef.type = type;
        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(width / 2, height / 2);

        fixtureDef.shape = boxShape;
        fixtureDef.restitution = 0.4f;

        body.createFixture(fixtureDef);

        boxShape.dispose();

        return body;
    }




}