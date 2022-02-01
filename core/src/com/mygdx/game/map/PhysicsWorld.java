package com.mygdx.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.UserInputManager;

public class PhysicsWorld {
    public World world;
    private Box2DDebugRenderer debugRenderer;
    public PhysicsWorld(){
        world = new World(new Vector2(.0f,.0f),true);
        debugRenderer = new Box2DDebugRenderer();
    }

    /**
     *
     * @param camera takes the current camera
     * @param control takes the control module
     * Simulates a tick in the physics world
     */
    public void tick(OrthographicCamera camera, UserInputManager control){
        // Pass in control to check if debug is true
        if (control.debug) debugRenderer.render(world, camera.combined);
        // step the world forward in time
        world.step(Gdx.app.getGraphics().getDeltaTime(), 6, 2);
        world.clearForces();
    }
}
