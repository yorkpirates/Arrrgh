
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Tutorials implements Disposable {

    public Stage stage;
    private Viewport viewport;
    SpriteBatch batch;
    private Label tutorialLabel;
    private String tip;

    /**
     * Tutorials class to display tutorials on the screen
     * Creates a overlay for the screen which is controlled by this class
     */
    public Tutorials () {
        batch = new SpriteBatch();
        viewport = new FitViewport(300, 300, new OrthographicCamera());
        stage = new Stage(viewport);

        tip = "Hey there Sailor! Press the arrow keys to navigate the choppy waters of York!";

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        BitmapFont font = new BitmapFont();

        tutorialLabel = new Label(tip, new Label.LabelStyle(font, Color.WHITE));
        font.getData().setScale(0.5f);

        table.add(tutorialLabel).expandX().padTop(90);

        stage.addActor(table);
    }

    /**
     * Remove tip from the screen
     */
    public void removeTip() {
        tip = "";
        tutorialLabel.setText(tip);
    }
    /*
    Change what the tip says
     */
    public void updateTipForPoints() {
        tip = "The more dangerous your actions the more points you'll get... but be careful, Sailor!";
        tutorialLabel.setText(tip);
    }

    public void updateTipForPorts() {
        tip = "Mmmm a port... Make contact and press 'e' - I wonder what treasures lie inside?";
        tutorialLabel.setText(tip);
    }

    public void updateTipForEnemy() {
        tip = "Cutlasses! That ship doesn't look too friendly...";
        tutorialLabel.setText(tip);
    }

    public void updateTipForTime() {
        tip = "Shiver Me Timbers! The clock's ticking, Sailor...";
        tutorialLabel.setText(tip);
    }

    public void updateTipForHealth() {
        tip = "You're pretty healthy right now Sailor, but don't go sinking our ship!";
        tutorialLabel.setText(tip);
    }

    public void updateTipForCollisions() {
        tip = "Monkeys! You've run aground. I'd avoid colliding into cliffs if you like smooth sailing!";
        tutorialLabel.setText(tip);
    }

    public void updateTipForTimeUp() {
        tip = "Monkeys! You're out of time...";
        tutorialLabel.setText(tip);
    }

    public void updateTipForSinking() {
        tip = "You sunk our ship. You're clearly not ready for the high seas, Sailor!";
        tutorialLabel.setText(tip);
    }

    public void updateTipForWin() {
        tip = "Skull and Cross Bones! You've gone and won. Well done sailor...";
        tutorialLabel.setText(tip);
    }

    @Override
    public void dispose() { stage.dispose(); }

}



