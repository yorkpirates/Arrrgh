package com.mygdx.game;

public class Enums {
  public enum TILETYPE {
    WATER,
    LAND
  }

  public enum ENTITYTYPE {
    BOAT,
    PORT,
    NEESA,
    CASTLE
  }

  public enum BOATTYPE {
    SLOOP,
    SCHOONERS,
    BRIGANTINES,
    GALLEONS,
    SHIP_OF_THE_LINE
  }

  public enum COLLEGE {
    // Goodricke = Green (1)
    // Constantine = Purple (2)
    // Langwith = Yellow (3)
    // Lister = Red (4)
    GOODRICKE,
    CONSTANTINE,
    LANGWITH,
    LISTER
  }

  public enum RELATIONSHIP {
    FRIEND,
    ENEMY,
    NEUTRAL
  }
}
