package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Screens.PortMenu;
import com.mygdx.game.Screens.TitleScreen;

public class WorldContactListener implements ContactListener {
    ArrrghGame game;

    /**
     * @param game the current render of the game
     */
    public WorldContactListener(ArrrghGame game){
        super();
        this.game = game;
    }
    public boolean isCollided = false;
    public boolean isDocked = false;
    public boolean firstCollision = false;

    /**
     * Is called whenever there is a collision in the physics engine
     * @param contact Contains the two entities that are no longer colliding
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fix1 = contact.getFixtureA();
        Fixture fiz2 = contact.getFixtureB();
        if  ((contact.getFixtureA().getUserData() instanceof Port) || (contact.getFixtureB().getUserData() instanceof Port )) {
            Port collidedport;
            /*
            * WARNING
            * Here we are downcasting
            * Make sure that you cast into the CORRECT OBJECT TYPE OR have fun
            *
            * */
            //Checking if its a player
            if(contact.getFixtureA().getUserData() instanceof Boat || contact.getFixtureB().getUserData() instanceof Boat){
                PlayerDocks(contact);
            }

            if(contact.getFixtureA().getUserData() instanceof Port){
                //downcast
                collidedport = (Port) contact.getFixtureA().getUserData();
            }
            else{
                collidedport = (Port) contact.getFixtureB().getUserData();
            }
        } else if ((contact.getFixtureA().getUserData() instanceof Boat) || (contact.getFixtureB().getUserData() instanceof Boat )) {
            isCollided = true;
            firstCollision = true;
        } else {
            if ((contact.getFixtureA().getUserData() instanceof AiBoat) || (contact.getFixtureB().getUserData() instanceof AiBoat )){
                AiCollision(contact);
            }
        }
        //System.out.println("Collision");
    }

    /**
     *
     * @param contact Contains the two entities that are no longer colliding
     * Sets the collision boolean variables to false
     */
    @Override
    public void endContact(Contact contact) {
        isCollided = false;
        isDocked = false;
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    /**
     *
     * @param contact contact object holds the two entities that are colliding
     *       Deals with Ai collision involving any entity
     */
    private void AiCollision(Contact contact){
        Fixture fix1 = contact.getFixtureA();
        Fixture fiz2 = contact.getFixtureB();
        AiBoat collidedBoat;
        if(fix1.getUserData() instanceof AiBoat){
            //downcast
            collidedBoat = (AiBoat) fix1.getUserData();
        }
        else{
            collidedBoat = (AiBoat) fiz2.getUserData();
        }
        collidedBoat.collision();
    }

    /**
     *
     * @param contact contact object holds the two entities that are colliding
     *       Deals with player collision involving a dock
     */
    private void PlayerDocks(Contact contact){

        Boat player =game.currentgame.pboat ;
        //just giving it a default value so the compiler will not moan
        Port dock =  game.currentgame.port_constantine;
        if(contact.getFixtureA().getUserData() instanceof Boat){
            player= (Boat) contact.getFixtureA().getUserData();
            dock = (Port) contact.getFixtureB().getUserData();
        }
        else if (contact.getFixtureB().getUserData() instanceof Boat){
            player= (Boat) contact.getFixtureA().getUserData();
            dock = (Port) contact.getFixtureB().getUserData();
        }
        game.currentgame.goals.test(dock.allegiance);
        // dont allow boats to dock at ports there are not of their allegiance
        if(dock.allegiance != game.currentgame.playerCollege){

            return;
        }
        isDocked = true;

        //stop the boat
        player.body.setLinearVelocity(0, 0);

        //if player wants to interact with the dock stop all movement and switch screen
        if (game.currentgame.control.interact){
            game.currentgame.control.interact = false;
            game.currentgame.control.down = false;
            game.currentgame.control.up = false;
            game.currentgame.control.left = false;
            game.currentgame.control.right = false;

            game.setScreen(new PortMenu(game));
        }

    }


}
