package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ArrrghGame;
import com.mygdx.game.Enums;

public class TitleScreen extends ScreenAdapter {
  ArrrghGame game;
  private Stage stage;
  private Texture playBtn;
  private Texture background;
  private Texture titleTexture;
  private Image title;

  public TitleScreen(ArrrghGame game) {
    this.game = game;
    background = new Texture("bg-1.png");
    playBtn = new Texture("placeholder.png");
    stage = new Stage(new ScreenViewport());
    Gdx.input.setInputProcessor(stage);
    titleTexture = new Texture(Gdx.files.internal("title_screen.png"));
    title = new Image(titleTexture);
  }

    /**
     * Called when the screen is displayed
     */
  @Override
  public void show() {
    Table table = new Table();
    table.setFillParent((true));

    stage.addActor(table);
    Skin skin = new Skin(Gdx.files.internal("skin/metal-ui.json"));
    table.background(new TextureRegionDrawable(new TextureRegion(new Texture("bg-1.png"))));
    // btns
    TextButton playbtn = new TextButton("Play", skin);
    TextButton exitbtn = new TextButton("Exit", skin);

    // making table
    table.add(title);
    table.row().pad(50, 100, 10, 100);
    table.add(playbtn).fillX().uniformX();
    table.row().pad(10, 100, 50, 100);
    table.add(exitbtn).fillX().uniformX();

    // making the listeners w
    exitbtn.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            Gdx.app.exit();
          }
        });

    playbtn.addListener(
        new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(new CollegeSelector(game));
          }
        });
  }

    /**
     *
     * @param delta reuqired variable
     * Called
     */
  public void render(float delta) {

    Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // game.batch.begin();

    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    stage.draw();
    // game.batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    // game.batch.draw(playBtn,(Gdx.graphics.getWidth()/2)-(playBtn.getWidth()/2),(Gdx.graphics.getHeight()/2)-playBtn.getHeight()/2);
    // game.batch.end();
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
