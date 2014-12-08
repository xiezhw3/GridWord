/* 
 * This code is for exercise in part 4.
 * 
 * @author xiezhw3
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import java.awt.Color;

import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;
    /**
    * Randomly selects a neighbor and changes this critter's color to be the
    * same as that neighbor's. If there are no neighbors, no action is taken.
    * 
    * @param actors
    *        A list of actor taht need to paocess.
    */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
        {
            darken();
            return; 
        }
        int r = (int) (Math.random() * n);
        Actor other = actors.get(r);
        setColor(other.getColor());
    }
    /**
     * Causes the color of this ChameleonCritter to darken.
     */
    private void darken()
    {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }
    
    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        Location preLoc = getLocation();
        setDirection(preLoc.getDirectionToward(loc));
        super.makeMove(loc);
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(getGrid(), preLoc);
    }
}
