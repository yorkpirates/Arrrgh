package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Screens.CollegeSelector;
import com.mygdx.game.Screens.MAIN;
import com.mygdx.game.Screens.PortMenu;
import com.mygdx.game.Screens.TitleScreen;

public class ArrrghGame extends Game {
  SpriteBatch batch;

  BitmapFont font;
  public MAIN currentgame;
  ShapeRenderer shapeRenderer;

  /** creates the game */
  @Override
  public void create() {

    batch = new SpriteBatch();
    font = new BitmapFont();
    // switch to the main title screen
    setScreen(new TitleScreen(this));
    shapeRenderer = new ShapeRenderer();
  }

  /**
   * destroy the game
   */
  @Override
  public void dispose() {
    batch.dispose();

    font.dispose();
  }
}
