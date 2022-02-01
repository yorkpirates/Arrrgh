package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ArrrghGame;
import com.mygdx.game.Enums;

public class CollegeSelector extends ScreenAdapter {
    ArrrghGame game;
    private Stage stage;
    private Texture playBtn;
    private Texture background;
    private Texture titleTexture;
    private Image title;
    private Label label;

    /**
     *
     * @param game the game currently in progress
     */
    public CollegeSelector(ArrrghGame game){
        this.game = game;
        background = new Texture("bg-1.png");
        playBtn = new Texture("placeholder.png");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        titleTexture = new Texture(Gdx.files.internal("piratequest.png"));
        title = new Image(titleTexture);

    }
    /**
     * called when the screen is shown
     */
    @Override
    public void show(){
        Table table = new Table();
        table.setFillParent((true));

        stage.addActor(table);
        Skin skin =new Skin(Gdx.files.internal("skin/metal-ui.json"));

        label = new Label("So what do you say, Sailor? Pick a college to swear allegiance to!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //btns
        TextButton playbtn = new TextButton("Play",skin);
        //selection box
        final SelectBox<String> selectBox=new SelectBox<String>(skin);
        selectBox.setItems("Select College","Goodricke","Langwith","Constantine","Anne Lister");

        //making table
        table.add(title);
        table.background(new TextureRegionDrawable(new TextureRegion(new Texture("bg-1.png"))));
        table.row().pad(10, 0, 10, 0);
        table.add(label).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(selectBox).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(playbtn).fillX().uniformX();

        //making the listeners
        playbtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                /*chosenCollege is initialised to a value here
                to prevent error.
                 */
                Enums.COLLEGE chosenCollege = Enums.COLLEGE.GOODRICKE;
                switch (selectBox.getSelected()){
                    case "Select College":
                        return;
                    case "Goodricke":
                        chosenCollege = Enums.COLLEGE.GOODRICKE;
                        break;
                    case "Langwith":
                        chosenCollege = Enums.COLLEGE.LANGWITH;
                        break;
                    case "Constantine":
                        chosenCollege = Enums.COLLEGE.CONSTANTINE;
                        break;
                    case "Anne Lister":
                        chosenCollege = Enums.COLLEGE.LISTER;
                        break;
                }
                //Pass through Chosen College
                game.currentgame = new MAIN(game,chosenCollege);
                game.setScreen(game.currentgame);

            }
        });

    }

    public void render(float delta) {

        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

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
