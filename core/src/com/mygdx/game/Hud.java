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

/** Class that keeps track of game states and displays them*/
public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    //time
    private Integer maxTime;
    private float timePassed;
    private boolean timeOver;
    private Integer finishTime;
    private static Integer timer;

    //score
    private static Integer winScore;
    private static Integer score;
    private static boolean haveWon;
    private boolean dangermode;
    private String goalOneComplete = "INCOMPLETE";
    private String goalTwoComplete = "INCOMPLETE";
    private boolean visitedAll;

    //health
    private static boolean isSunk;
    private static Integer health;

    //gold
    private static Integer gold;

    //labels
    private Label timeLabel, timeTextLabel, scoreTextLabel, goldTextLabel;
    private static Label scoreLabel, goldLabel, healthLabel, healthTextLabel, goalOneLabel, goalTwoLabel, goalLabel, goalOneCriteria, goalTwoCriteria;


    public Hud () {
        //time
        maxTime = 300; //5 minutes
        timePassed = 0;
        timer = 0;
        finishTime = 5;
        timeOver = false;

        //score
        score = 0;
        winScore = 1000; //this should only be met when spending sufficient time in danger or with good luck, for ass 2 this will be raised so win cna only be easily achieved with combat
        haveWon = false;
        dangermode = false;
        visitedAll = false;

        //health
        health = 100;
        isSunk = false;

        //gold
        gold = 0;

        viewport = new FitViewport(300, 300, new OrthographicCamera());
        stage = new Stage(viewport);

        //variable labels
        timeLabel = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        goldLabel =new Label(String.format("%04d", gold), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel =new Label(String.format("%03d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //text labels
        timeTextLabel = new Label("TIME PASSED", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreTextLabel = new Label("POINTS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        goldTextLabel = new Label("GOLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthTextLabel = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        goalLabel = new Label("GOALS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        goalOneLabel = new Label("GET 1000 POINTS:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        goalTwoLabel = new Label("VISIT ALL COLLEGES:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        goalOneCriteria = new Label(goalOneComplete, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        goalTwoCriteria = new Label(goalTwoComplete, new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        //table
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        table.add(scoreTextLabel).expandX().padTop(10);
        table.add(timeTextLabel).expandX().padTop(10);
        table.add(goldTextLabel).expandX().padTop(10);
        table.add(healthTextLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(timeLabel).expandX();
        table.add(goldLabel).expandX();
        table.add(healthLabel).expandX();

        stage.addActor(table);

        Table goalTable = new Table();
        goalTable.bottom();
        goalTable.left();
        goalTable.setFillParent(true);

        goalTable.add(goalLabel).expandX().padTop(10);
        goalTable.row();
        goalTable.add(goalOneLabel).expandX().padTop(10);
        goalTable.add(goalOneCriteria).expandX().padTop(10);
        goalTable.row();
        goalTable.add(goalTwoLabel).expandX().padTop(10);
        goalTable.add(goalTwoCriteria).expandX().padTop(10);

        stage.addActor(goalTable);

    }

    /** Function to update the timer of the game*/
    public void updateTime(){
        timePassed++;
        if(timePassed == 60){ //every sixty renders = a second
            if (maxTime < timer) {
                if (timeOver == false) {
                    finishTime += timer;
                }
                timeOver = true;
            }
            timer++;
            timePassed = 0;
            if (!timeOver && (!haveWon || !visitedAll) && !isSunk) {
                timeLabel.setText(String.format("%03d", timer));
                if (dangermode == true) {
                    addScore((int) (Math.random() * 11));
                } else {
                    addScore((int) (Math.random() * 6));
                }
            }
        }
    }


    /** Function to add score to the game state
     * @param value (int) the number of points to add
     **/
    public void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
        if(score >= winScore){
           if (haveWon == false || visitedAll == false) {
               finishTime += timer;
           }
            haveWon = true;
            goalOneComplete = "COMPLETE";
            goalOneCriteria.setText(goalOneComplete);
        }
    }

    /** Function to set the mode to or from danger-nearby
     * @param danger (boolean) whether there is danger nearby
     **/
    public void setDanger(boolean danger){
        dangermode = danger;
    }

    /** Function to add an amount of gold to the game state gold
     * @param value (gold) the number of gold to add
     **/
    public static void addGold(int value){
        gold += value;
        goldLabel.setText(String.format("%06d", gold));
    }

    /** Function to add an amount of health to the game state health
     * @param value (int) the number of health to add
     **/
    public void addHealth(int value){
        if (!isSunk) {
            health += value;
        }
        if (health <= 0) {
            if (!isSunk) {
                finishTime += timer;
            }
            isSunk = true;
            health = 0;
            healthLabel.setText(String.format("%03d", health));
        } else if (health > 0) {
            healthLabel.setText(String.format("%03d", health));
        }
    }

    @Override
    /** Dispose the hud*/
    public void dispose() { stage.dispose(); }

    /** Return true if time is over
     * @return timeOver (boolean)
     **/
    public boolean isTimeUp() { return timeOver; }

    /** Return true if score is win score
     * @return haveWon (boolean)
     **/
    public boolean isScoreWin() { return haveWon; }

    /** Return the number of gold owned
     * @return gold (int)
     **/
    public static Integer getGold() { return gold; }

    /** Return the time the game ended at
     * @return finishTime (int)
     **/
    public Integer getFinishTime() { return finishTime; }

    /** Return the current time
     * @return timer (int)
     **/
    public Integer getTime() {return timer; }

    /** Return the current health
     * @return health (int)
     **/
    public static Integer getHealth() {return health; }

    /** Return true if ship has sunk (0 health)
     * @return isSunk (boolean)
     **/
    public boolean isSunk() {return isSunk; }

    /** Set visitedAll boolean to true and update HUD */
    public void visitedAll() {
        visitedAll = true;
        goalTwoComplete = "COMPLETE";
        goalTwoCriteria.setText(goalTwoComplete);
    }

}
