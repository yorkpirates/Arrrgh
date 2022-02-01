package com.mygdx.game;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * User input manager is a class that monitors and keeps track of them
 */
public class UserInputManager extends InputAdapter implements InputProcessor {
  OrthographicCamera camera;

  // DIRECTIONS
  public boolean up;
  public boolean down;
  public boolean left;
  public boolean right;
  public boolean interact;
  public boolean debug;
  // SCREEN
  int screen_width;
  int screen_height;

  public UserInputManager(int screen_width, int screen_height, OrthographicCamera camera) {
    this.camera = camera;
    this.screen_width = screen_width;
    this.screen_height = screen_height;
    interact = false;
  }

    /**
     * @param keycode takes the keycode of the key that has been pressed
     * @return returns false
     */
  @Override
  public boolean keyDown(int keycode) {
    switch (keycode) {
      case Keys.DOWN:
        down = true;
        break;
      case Keys.UP:
        up = true;
        break;
      case Keys.LEFT:
        left = true;
        break;
      case Keys.RIGHT:
        right = true;
        break;
      case Keys.BACKSPACE:
        debug = true;
      case Keys.E:
        interact = true;
    }
    return false;
  }

    /**
     *
     * @param keycode takes the keycode of the key that has been released
     * @return returns false
     */
  @Override
  public boolean keyUp(int keycode) {
    switch (keycode) {
      case Keys.DOWN:
        down = false;
        break;
      case Keys.UP:
        up = false;
        break;
      case Keys.LEFT:
        left = false;
        break;
      case Keys.RIGHT:
        right = false;
        break;
      case Keys.E:
        interact = false;
    }
    return false;
  }

  @Override
  public boolean keyTyped(char character) {
    return false;
  }
}
