package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

public class Entity {
    public Vector3 position;
    public Texture texture;
    public float width;
    public float height;
    public Enums.ENTITYTYPE type;
    public float speed;
    public Body body;
    public Fixture fixture;
    public Entity(){
        position = new Vector3();
    }

    /**
     * draws entity
     * @param batch the batch in which this entity should be drawn
     */
    public void draw(SpriteBatch batch){
        batch.draw(texture, position.x,position.y,width,height);
    }

    /**
     * @param world
     * @param width the width of the entity
     * @param height the height of the entity
     * @param pos the position of the entity on the map
     * @param type the type of the entity that brings along different attributes
     */
    public void createBody(World world, float width, float height, Vector3 pos, BodyDef.BodyType type) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos.x + width/2, pos.y +height/2);
        bodyDef.angle = 0;
        bodyDef.fixedRotation = true;
        bodyDef.type = type;
        this.body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(width / 2, height / 2);

        fixtureDef.shape = boxShape;
        fixtureDef.restitution = 0.4f;

        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(this);
        boxShape.dispose();
    }
}
