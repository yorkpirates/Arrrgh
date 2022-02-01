package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.*;
import com.mygdx.game.map.Campus_east;
import com.mygdx.game.map.PhysicsWorld;
import com.mygdx.game.map.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MAIN extends ScreenAdapter {
  public ArrrghGame game;
  SpriteBatch batch;
  Texture img;
  OrthographicCamera camera;
  public UserInputManager control;
  PhysicsWorld box2D;
  // display
  private int displaywidth;
  private int displayheight;
  private Hud hud;
  private Tutorials tut;
  private int exitTime;
  private int collideTime;
  private int tipstart;
  private int tipend;
  private WorldContactListener contact;
  public Enums.COLLEGE playerCollege;
  private boolean seenCollideTut, seenHealthTut, haveBeenClose;
  int x, y;
  public GoalManager goals;
  int direction_x, direction_y;
  int speed = 3;
  Campus_east map;
  public Boat pboat;
  public Port port_lister, port_goodricke, port_langwith, port_constantine;
  Neesa neesa;
  Castle castle_lister, castle_goodrike, castle_langwith, castle_constantine;
  College Goodricke, Lister, Langwith, Constantine;
  AiBoat goodricke1, constantine1, langwith1, lister1;

  /**
   *
   * @param game The current game
   * @param playerCollege The college that the player chose
   */
  public MAIN(ArrrghGame game, Enums.COLLEGE playerCollege) {
    this.game = game;
    batch = new SpriteBatch();
    this.playerCollege = playerCollege;
    img = new Texture("badlogic.jpg");
    displaywidth = Gdx.graphics.getWidth();
    displayheight = Gdx.graphics.getHeight();
    goals = new GoalManager();
    int h = (int) (displayheight / Math.floor(displayheight / 160));
    int w =
            (int) (displaywidth / (displayheight / (displayheight / Math.floor(displayheight / 160))));

    camera = new OrthographicCamera(w, h);
    camera.zoom = .4f;

    control = new UserInputManager(displaywidth, displayheight, camera);
    control = new UserInputManager(displaywidth, displayheight, camera);
    Gdx.input.setInputProcessor(control);
    box2D = new PhysicsWorld();
    map = new Campus_east(box2D);
    // Create Colleges
    createColleges();
    // Player's Boat

    pboat = new Boat(map.pSpawnCoords, box2D, playerCollege);

    // AI Boats
    goodricke1 = new AiBoat(map.aispawn_goodricke1, box2D, Enums.COLLEGE.GOODRICKE);
    constantine1 = new AiBoat(map.aispawn_constantine1, box2D, Enums.COLLEGE.CONSTANTINE);
    langwith1 = new AiBoat(map.aispawn_langwith1, box2D, Enums.COLLEGE.LANGWITH);
    lister1 = new AiBoat(map.aispawn_lister1, box2D, Enums.COLLEGE.LISTER);

    // Port & Castles
    // Teams: 1=Red, 2=Green, 3=Yellow, 4=Pink
    contact = new WorldContactListener(game);
    port_lister = new Port(map.portlisterCoords, box2D, Enums.COLLEGE.LISTER); // port1
    port_goodricke = new Port(map.portgoodrickeCoords, box2D, Enums.COLLEGE.GOODRICKE); // port2
    port_langwith = new Port(map.portlangwithCoords, box2D, Enums.COLLEGE.LANGWITH); // port3
    port_constantine =
            new Port(map.portconstantineCoords, box2D, Enums.COLLEGE.CONSTANTINE); // port4

    castle_lister = new Castle(map.castleListerCoords, box2D, 1);
    castle_goodrike = new Castle(map.castleGoodrickeCoords, box2D, 2);
    castle_langwith = new Castle(map.castleLangwithCoords, box2D, 3);
    castle_constantine = new Castle(map.castleConstantineCoords, box2D, 4);

    neesa = new Neesa(map.neesaCoords, box2D);
    box2D.world.setContactListener(contact);
    // create the hud
    hud = new Hud();
    tut = new Tutorials();
    tipstart = 0;
    collideTime = 0;
    seenHealthTut = false;
    seenCollideTut = false;
    haveBeenClose = false;
  }

  /** On show set the inputs to be processed by userinput manager */
  @Override
  public void show() {

    Gdx.input.setInputProcessor(control);

  }

  /**
   *
   * @param delta render delta needed by libgdx
   *
   *Function is called every fps holds the main body of logic needed to run the game and all checks
   */
  public void render(float delta) {
    super.render(delta);

    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // GAME LOGIC

    // update boat directions
    pboat.update(control);

    goodricke1.update();
    constantine1.update();
    langwith1.update();
    lister1.update();
    // have the camera follow the boat
    camera.position.lerp(pboat.position, .1f);
    camera.update();

    // GAME DRAW
    batch.setProjectionMatrix(camera.combined);
    batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

    batch.begin();
    // Draw all tiles in the chunk / chunk rows
    for (ArrayList<Tile> row : map.tempchunk.tiles) {
      for (Tile tile : row) {
        batch.draw(tile.texture, tile.position.x, tile.position.y, tile.size, tile.size);
      }
    }

    // Player Boat
    pboat.draw(batch);

    // AI Boats
    goodricke1.draw(batch);
    constantine1.draw(batch);
    langwith1.draw(batch);
    lister1.draw(batch);

    // Misc
    port_lister.draw(batch);
    port_langwith.draw(batch);
    port_constantine.draw(batch);
    port_goodricke.draw(batch);

    castle_lister.draw(batch);
    castle_langwith.draw(batch);
    castle_constantine.draw(batch);
    castle_goodrike.draw(batch);

    neesa.draw(batch);

    batch.end();
    // update the physics world
    box2D.tick(camera, control);

    // hud and tutorials
    hud.updateTime();
    hud.stage.draw();
    tut.stage.draw();

    // tutorials - start and end time

    tipend = tipstart + 4;

    if (tipend == hud.getTime()) {
      tut.removeTip();
    }

    if (hud.getTime() == 4) {
      tut.updateTipForTime();
    }

    if (hud.getTime() == 8) {
      tut.updateTipForPoints();
    }

    if (hud.getHealth() < 80 && seenHealthTut) {
      tipstart = hud.getTime();
      tut.updateTipForHealth();
      seenHealthTut = true;
    }

    if (hud.getTime() == 12) {
      tipstart = hud.getTime();
      tut.updateTipForPorts();
    }

    if (contact.firstCollision && !seenCollideTut) {
      tipstart = hud.getTime();
      tut.updateTipForCollisions();
      seenCollideTut = true;
    }

    if (!haveBeenClose && closeness()) {
      tipstart = hud.getTime();
      tut.updateTipForEnemy();
    }

    // win or lose screens

    //if score is met and goal completed end game in win
    if (hud.isScoreWin() && goals.goalsCompleted()) {
      tut.updateTipForWin();
      exitTime = hud.getFinishTime();
      if (exitTime == hud.getTime()) {
        game.setScreen(new EndScreen(game, true));
      }
    }

    //if time is up end game
    if (hud.isTimeUp()) {
      tut.updateTipForTimeUp();
      exitTime = hud.getFinishTime();
      if (exitTime == hud.getTime()) {
        game.setScreen(new EndScreen(game, false));
      }
    }

    //if ship sinks end game
    if (hud.isSunk()) {
      tut.updateTipForSinking();
      pboat.sinkShip();
      exitTime = hud.getFinishTime();
      if (exitTime == hud.getTime()) {
        game.setScreen(new EndScreen(game, false));
      }
    }


    if (!haveBeenClose && closeness()) {
      tipstart = hud.getTime();
      tut.updateTipForEnemy();
    }

    // win or lose screens
    if (goals.goalsCompleted()) {
      hud.visitedAll();
    }

    boolean endWin = hud.isScoreWin() && goals.goalsCompleted();

    if (endWin) {
      tut.updateTipForWin();
      exitTime = hud.getFinishTime();
      if (exitTime == hud.getTime()) {
        game.setScreen(new EndScreen(game, true));
      }
    }

    if (hud.isTimeUp()) {
      tut.updateTipForTimeUp();
      exitTime = hud.getFinishTime();
      if (exitTime == hud.getTime()) {
        game.setScreen(new EndScreen(game, false));
      }
    }

    if (hud.isSunk()) {
      tut.updateTipForSinking();
      pboat.sinkShip();
      exitTime = hud.getFinishTime();
      if (exitTime == hud.getTime()) {
        game.setScreen(new EndScreen(game, false));

      }
    }

    //remove health on collision
    if (contact.isCollided) {
      if (hud.getTime() > collideTime + 1) {
        collideTime = hud.getTime();
        hud.addHealth((int) -(Math.random() * 15));
      }
    }

    //slowly add health if docked
    if (contact.isDocked) {
      if (hud.getTime() > collideTime + 1 && hud.getHealth() < 100) {
        collideTime = hud.getTime();
        hud.addHealth(1);
      }
    }

    //set danger mode
    if (closeness()) {
      haveBeenClose = true;
      hud.setDanger(true);
    } else {
      hud.setDanger(false);
    }
  }

  @Override
  public void dispose() {
    batch.dispose();
    img.dispose();
    hud.dispose();
    tut.dispose();
  }

  @Override
  public void hide() {
    Gdx.input.setInputProcessor(null);
  }

  /** Create the colleges and set their relationships with each other */
  private void createColleges() {
    // Create the colleges
    Goodricke = new College("Goodricke", Enums.COLLEGE.GOODRICKE);
    Lister = new College("Lister", Enums.COLLEGE.LISTER);
    Langwith = new College("Langwith", Enums.COLLEGE.LANGWITH);
    Constantine = new College("Constantine", Enums.COLLEGE.CONSTANTINE);
    // Add their starting relationships
    Goodricke.relationships.put(Lister, Enums.RELATIONSHIP.ENEMY);
    Goodricke.relationships.put(Langwith, Enums.RELATIONSHIP.ENEMY);
    Goodricke.relationships.put(Constantine, Enums.RELATIONSHIP.ENEMY);

    Lister.relationships.put(Goodricke, Enums.RELATIONSHIP.ENEMY);
    Lister.relationships.put(Langwith, Enums.RELATIONSHIP.ENEMY);
    Lister.relationships.put(Constantine, Enums.RELATIONSHIP.ENEMY);

    Langwith.relationships.put(Goodricke, Enums.RELATIONSHIP.ENEMY);
    Langwith.relationships.put(Lister, Enums.RELATIONSHIP.ENEMY);
    Langwith.relationships.put(Constantine, Enums.RELATIONSHIP.ENEMY);

    Constantine.relationships.put(Goodricke, Enums.RELATIONSHIP.ENEMY);
    Constantine.relationships.put(Lister, Enums.RELATIONSHIP.ENEMY);
    Constantine.relationships.put(Langwith, Enums.RELATIONSHIP.ENEMY);
  }

  /** Return true if player boat is within 25 squares of an enemy boat
   * @return close (boolean)
   **/
  private boolean closeness() {
    HashMap<College, Enums.RELATIONSHIP> bob = null;
    if (playerCollege == Enums.COLLEGE.CONSTANTINE) {
      bob = Constantine.relationships;
    } else if (playerCollege == Enums.COLLEGE.LISTER) {
      bob = Lister.relationships;
    } else if (playerCollege == Enums.COLLEGE.LANGWITH) {
      bob = Langwith.relationships;
    } else if (playerCollege == Enums.COLLEGE.GOODRICKE) {
      bob = Goodricke.relationships;
    }
    float x = pboat.getXPosition();
    float y = pboat.getYPosition();
    boolean close = false;
    for (Map.Entry<College, Enums.RELATIONSHIP> entry : bob.entrySet()) {
      College key = entry.getKey();
      Enums.RELATIONSHIP value = entry.getValue();
      float aiX = 0;
      float aiY = 0;
      double distance;
      if (value == Enums.RELATIONSHIP.ENEMY) {
        if (key.enumField == Enums.COLLEGE.CONSTANTINE) {
          aiX = constantine1.getXPosition();
          aiY = constantine1.getYPosition();
        } else if (key.enumField == Enums.COLLEGE.LANGWITH) {
          aiX = langwith1.getXPosition();
          aiY = langwith1.getYPosition();
        } else if (key.enumField == Enums.COLLEGE.GOODRICKE) {
          aiX = goodricke1.getXPosition();
          aiY = goodricke1.getYPosition();
        } else if (key.enumField == Enums.COLLEGE.LISTER) {
          aiX = lister1.getXPosition();
          aiY = lister1.getYPosition();
        }
        float minusX = aiX - x;
        float minusY = aiY - y;
        distance = Math.sqrt(minusX * minusX + minusY * minusY);
        if (distance < 25) {
          close = true;
        }
      }
    }
    return close;
  }



}
