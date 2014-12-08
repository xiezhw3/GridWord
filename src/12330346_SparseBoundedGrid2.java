/* 
 * This code is for exercise 2 in part 5
 *
 * This code is an implements of UnBoundedGrid class
 * with HashMap to Store occupied location
 *
 * @author xiezhw3
 */

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import java.util.*;

/**
 * An <code>SparseBoundedGrid2</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int rowNum;
    private int colNum;

    /**
    * Constructs an empty unbounded grid.
    */
    public SparseBoundedGrid2(int rows, int cols)
    {
        occupantMap = new HashMap<Location, E>();
        this.rowNum = rows;
        this.colNum = cols;
    }

    /**
     * Get the row of the grid.
     * @return The row of the grid
     */
    public int getNumRows()
    {
        return this.rowNum;
    }

    /**
     * Get the column of the grid.
     * @return The column of the grid
     */
    public int getNumCols()
    {
        return this.colNum;
    }

    /**
     * Judge whether the location is >= (0, 0)
     * @param the location
     * @return whether the location is valid.
     */
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    /**
     * Get the list of the occupied location in the grid
     * @return the list of the occupied location
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()) {
            a.add(loc);
        }
        return a;
    }

    /**
     * Find the actor in the location
     * @param loc
     *         The location to find
     * @return The actor in the location
     */
    public E get(Location loc)
    {
        if (loc == null) {
            return null;
        }
        return occupantMap.get(loc);
    }

    /**
     * Put the actor to the location
     * @param loc
     *         The location to put
     * @return The actor in the location before put.
     */
    public E put(Location loc, E obj)
    {
        if (loc == null) {
            return null;
        }
        if (obj == null) {
            return null;
        }
        return occupantMap.put(loc, obj);
    }

    /**
     * Remove the actor from the location
     * @param loc
     *         The location to remove actor
     * @return The actor in the location before remove.
     */
    public E remove(Location loc)
    {
        if (loc == null) {
            return null;
        }
        return occupantMap.remove(loc);
    }
}

