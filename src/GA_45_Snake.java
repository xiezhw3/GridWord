/**
 * This code is for Group Activity in part4
 *
 * This code implements a Snake class. The snake 
 * will run with a random direction and the other
 * critter that meet this snake will die after 
 * random steps and it will darken during this time.
 *
 * @author xiezhw3
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import java.util.Random;

import java.util.ArrayList;

/**
 * A <code>Snake</code> is a snake with poison <br />
 */
public class Snake extends Critter
{
    /**
     * Get the actor ahead, left ahead and right ahead.
     *
     * @retrun the list of Actor ahead, left ahead and 
     * right ahead of the snake.
     * */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
        {Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT};
    
        for (Location loc : getLocationsInDirections(dirs)) {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
            }
        }
        return actors;
    }

   /**
    * Set the Actor ahead, left ahead and right ahead
    * of the snake that not a snak to die.
    * */
    public void processActors(ArrayList<Actor> actors)
    {
        if (actors.size() == 0) {
            return;
        }
        for (Actor a : actors) {
            if (a != null && !(a instanceof Snake) && !(a instanceof Flower) &&
                    !(a instanceof Rock)) {
                a.setPoisoningAndSteps();
            }
        }
    }

    /**
     * Randomly select an adjance empty location
     * And move to.
     * */
    public void act() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        ArrayList<Actor> actors = getActors();
        processActors(actors);

        Location loc = getLocation();
        ArrayList<Location> locnexts = getLocationCanMovaTo(loc);
        if (locnexts.size() > 0) {
            int locIndex = (new Random()).nextInt(locnexts.size());
            Location locNext = locnexts.get(locIndex);
            if (!gr.isValid(loc)) {
                return;
            }
            int dir = loc.getDirectionToward(locNext);
            setDirection(getDirection() + dir);
            moveTo(locNext);
        }
    }

    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
            ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();

        for (int d : directions)
        {
                Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
                if (gr.isValid(neighborLoc)) {
                    locs.add(neighborLoc);
                }
        }
        return locs;
    }

    /**
     * Get the location snake can move to.
     *
     * @param loc - the location of snake.
     *
     * @return validNexts - the location snack move to.
     */
    private ArrayList<Location> getLocationCanMovaTo(Location loc) {
        ArrayList<Location> validNexts = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        if (!gr.isValid(loc)) {
            return null;
        }

        ArrayList<Location> locnexts = gr.getValidAdjacentLocations(loc);
        for (Location locT : locnexts) {
            if (gr.get(locT) == null || gr.get(locT) instanceof Flower) {
                validNexts.add(locT);
            }
        }
        return validNexts;
    }
    
        
}

