/* 
 * This code is for exercise in part 4

 * @author xiezhw3
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class RockHound extends Critter
{
    /**
    * Remove any rocks in that list from the grid 
    */
    public void processActors(ArrayList<Actor> actors)
    {
      for (Actor actor : actors) {
          if (actor instanceof Rock) {
              actor.removeSelfFromGrid();
          }
      }
    }
}

