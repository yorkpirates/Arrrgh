package com.mygdx.game;

import java.util.HashMap;

/** Class that keeps track of goals and their completion*/
public class GoalManager {
  public HashMap<Enums.COLLEGE, Boolean> visited;

  public GoalManager() {
    // create the colleges you need to visit
    visited = new HashMap<Enums.COLLEGE, Boolean>();
    visited.put(Enums.COLLEGE.GOODRICKE, false);
    visited.put(Enums.COLLEGE.LANGWITH, false);
    visited.put(Enums.COLLEGE.CONSTANTINE, false);
    visited.put(Enums.COLLEGE.LISTER, false);
  }

  /** @return returns true if all goals are completed */
  public boolean goalsCompleted() {
    Boolean result = true;
    for (HashMap.Entry<Enums.COLLEGE, Boolean> set : visited.entrySet()) {
      if (!set.getValue()) {
        result = false;
      }
    }
    return result;
  }

  /**
   *
   * @param input takes the college and sets it to true updating the hashmap that you have visited
   *              the college
   */
  public void test(Enums.COLLEGE input) {
    if (visited.containsKey(input)) {
      visited.put(input, true);
    }
  }
}
