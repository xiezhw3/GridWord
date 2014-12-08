/* 
 * This code is for exercise in part4.
 * 
 * @author xiezhw3
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> move left or right two steps if can,
 * or move like a CrabCritter <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter {
    /**
     * Find if left or right can move. If not, work like a crab
     * @return a list of locations can move 
     */
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> moveLocs = ifLRCanMove(getDirection(), getLocation());
        if (moveLocs.size() == 0) {
            return super.getMoveLocations();
        }
        return moveLocs;
    }

   /**
    * Find if left or right can move.
    * @return a list of locations can move 
    */
    private ArrayList<Location> ifLRCanMove(int dir, Location loc) {
        ArrayList<Location> moveLocs = new ArrayList<Location>();
        int[] dirs = {Location.LEFT, Location.RIGHT};
        Grid grid = getGrid();
        for (int dirTmp : dirs) {
            Location loc1 = loc.getAdjacentLocation(dir + dirTmp);
            if (grid.isValid(loc1) && grid.get(loc1) == null) {
                Location loc2 = loc1.getAdjacentLocation(dir + dirTmp);
                if (grid.isValid(loc2) && grid.get(loc2) == null) {
                    moveLocs.add(loc2);
                }
            }

        }
        return moveLocs;
    }
}
