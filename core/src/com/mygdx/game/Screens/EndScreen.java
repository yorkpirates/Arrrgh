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

public class EndScreen extends ScreenAdapter {
    ArrrghGame game;
    private Stage stage;
    private Texture playBtn;
    private Texture background;
    private Texture titleTexture;
    private Image title;
    private boolean playerWin;

    /**
     *
     * @param game the current game
     * @param win whether the player won the game or not
     */
    public EndScreen(ArrrghGame game,Boolean win){
        this.game = game;
        playerWin = win;
        background = new Texture("bg-1.png");
        playBtn = new Texture("placeholder.png");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        //titleTexture = new Texture(Gdx.files.internal("Arrrgh Logo Colour.png"));
        //title = new Image(titleTexture);
        if (playerWin){
            titleTexture = new Texture(Gdx.files.internal("winning_screen.png"));
            title = new Image(titleTexture);
            //table.add(new Label("You Won!!",skin));
        }else{
            titleTexture = new Texture(Gdx.files.internal("losing_screen.png"));
            title = new Image(titleTexture);
            //table.add(new Label("You Lost!!",skin));
        }
    }

    /**
     * called when the screen is shown
     */
    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent((true));

        stage.addActor(table);
        Skin skin =new Skin(Gdx.files.internal("skin/metal-ui.json"));

        //btns
        TextButton playbtn = new TextButton("Return To menu",skin);
        TextButton exitbtn = new TextButton("Exit",skin);
        //making table
        table.add(title);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("bg-1.png"))));

        table.row().pad(10, 0, 10, 0);

        /// it goes here


        table.row().pad(10, 0, 10, 0);
        table.add(playbtn).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exitbtn).fillX().uniformX();

        //making the listeners
        playbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new TitleScreen(game));
            }
        });
        exitbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }

    /**
     *
     * @param delta
     */
    public void render(float delta) {

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //game.batch.begin();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        //game.batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //game.batch.draw(playBtn,(Gdx.graphics.getWidth()/2)-(playBtn.getWidth()/2),(Gdx.graphics.getHeight()/2)-playBtn.getHeight()/2);
        //game.batch.end();
    }
    @Override
    public void hide(){


    }
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