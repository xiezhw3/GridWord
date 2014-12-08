/* 
 * This code is for exercise in part 4
 * 
 * @author xiezhw3
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.awt.Color;

import java.util.ArrayList;

/**
 * A <code>BlusterCritter</code> find the number in the two steps nebours and
 * set color <br />
 */
public class BlusterCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;
    private static final int RGB_MAX = 255;
    private static final int LOCDIS1 = -2;
    private static final int LOCDIS2 = -1;
    private static final int LOCDIS3 = 0;
    private static final int LOCDIS4 = 1;
    private static final int LOCDIS5 = 2;
    private static final int COLOR = 1;
    private int c;
    public BlusterCritter(int c) 
    {
        super();
        this.c = c;
    }
    /**
    * Get the two steps neighbors
    *
    * @return A list of actors two steps 
    * near to the bluster critter.
    */
    public ArrayList<Actor> getActor() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Location loc = getLocation();
        int row = loc.getRow();
        int col = loc.getCol();
        Grid grid = getGrid();
        int[] locDis = {LOCDIS1, LOCDIS2, LOCDIS3, LOCDIS4, LOCDIS5};
        for (int rowdis : locDis) {
           for (int coldis : locDis) {
                Location loctmp = new Location(row + rowdis, col + coldis);
                if (grid.isValid(loctmp)) {
                    Actor act = getGrid().get(loctmp);
                    if (act != this && act != null) {
                        actors.add(act);
                    }
                }
            }
        }
        return actors;
    }

    /**
    * Get the number of critter in two steps neighbors and change the
    * color of itself by the number of critter.
    *
    * @param actors
    *        A list of actors need to process.
    */
    public void processActors(ArrayList<Actor> actors)
    {
        int numOfCitter = 0;
        for (Actor actor : actors) {
            if (actor instanceof Critter) {
                numOfCitter++;
            }
        }
        if (numOfCitter < c) {
            bright();
        }
        else {
                darken();
        }
    }
    /**
     * Darken critter's color
     */
    private void darken()
    {
        Color color = getColor();
        int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }
    /**
     * Lighten the Critter's color.
     */
    private void bright() 
    {        
        Color color = getColor();
        int red = (int) (color.getRed() * (COLOR + DARKENING_FACTOR));
        int green = (int) (color.getGreen() * (COLOR + DARKENING_FACTOR));
        int blue = (int) (color.getBlue() * (COLOR + DARKENING_FACTOR));
        if (red > RGB_MAX) {
            red = RGB_MAX;
        }
        if (green > RGB_MAX) {
            green = RGB_MAX;
        }    
        if (blue > RGB_MAX) {
            blue = RGB_MAX;
        }
        setColor(new Color(red, green, blue));
    }
}
