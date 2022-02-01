package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;

public class College {
  public String name;
  public HashMap<College, Enums.RELATIONSHIP> relationships;
  public ArrayList entities;
  public Enums.COLLEGE enumField;

  /**
   * @param name the name that you want to give the college
   * @param field the enum that represents the college
   */
  public College(String name, Enums.COLLEGE field) {

    this.name = name;
    relationships = new HashMap<College, Enums.RELATIONSHIP>();
    enumField = field;
  }
}
