package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ArrrghGame;

public class PortMenu extends ScreenAdapter {
  ArrrghGame game;
  private Stage stage;
  private Texture background;
  private Texture titleTexture;
  private Image title;
  private boolean playerWin;

  /**
   *
   * @param game the current game in progress
   */
  public PortMenu(ArrrghGame game) {
    this.game = game;

    background = new Texture("bg-1.png");
    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);
    titleTexture = new Texture(Gdx.files.internal("dock/dock_green.png"));
    title = new Image(titleTexture);
  }

  /**
   * called whenever the port menu is displayed
   */
  @Override
  public void show() {
    Gdx.input.setInputProcessor(stage);
    Table table = new Table();
    table.setFillParent((true));

    stage.addActor(table);
    Skin skin = new Skin(Gdx.files.internal("skin/metal-ui.json"));

    // btns
    TextButton shopbtn = new TextButton("Enter The shop", skin);
    TextButton tavernbtn = new TextButton("Enter the Tavern", skin);
    TextButton repairbtn = new TextButton("Repair Ye ship", skin);
    TextButton exitbtn = new TextButton("Exit", skin);

    // making table
    table.add(title);

    table.background(new TextureRegionDrawable(new TextureRegion(new Texture("bg-1.png"))));

    table.row().pad(10, 0, 10, 0);
    table.add(new Label("Welcome Ashore Me harties make ya selves at home !", skin));

    table.row().pad(10, 0, 10, 0);
    table.add(shopbtn).fillX().uniformX();
    table.row().pad(10, 0, 10, 0);
    table.add(repairbtn).fillX().uniformX();
    table.row().pad(10, 0, 10, 0);
    table.add(tavernbtn).fillX().uniformX();
    table.row().pad(10, 0, 10, 0);
    table.add(exitbtn).fillX().uniformX();

    // making the listeners
    //when clicking the exit button return to the game
    exitbtn.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(game.currentgame);
          }
        });
  }

  /**
   * Render function to render the screen
   * @param delta
   */
  public void render(float delta) {

    Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

    stage.draw();
  }

  @Override
  public void hide() {}
  /**
   *
   * @param width new window width
   * @param height new window height
   */
  @Override
  public void resize(int width, int height) {
    // change the stage's viewport when teh screen size is changed
    stage.getViewport().update(width, height, true);
  }
}
