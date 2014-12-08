/*
 * This code is for exercise 3 im part 5
 *
 * This code implements a UnBound Grid class that
 * used an two-dimensional array to store the 
 * location.
 *
 * @author xiezhw3
 */

import java.util.ArrayList;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] locations;
    private int length;
    private static final int ARRAYINITSIZE = 16;
       
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public UnboundedGrid2()
    {
        this.length = ARRAYINITSIZE;
        this.locations = new Object[length][length];
    }
    
    /**
     * Get the row of the grid.
     * @return The row of the grid
     */
    public int getNumRows()
    {
        return -1;
    }

    /**
     * Get the column of the grid.
     * @return The column of the grid
     */
    public int getNumCols()
    {
        return -1;
    }

    /**
     * Judge whether the location is >= (0, 0)
     * @param the location
     * @return whether the location is valid.
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0; 
    }
    
    /**
     * Get the list of the occupied location in the grid
     * @return the list of the occupied location
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> occupiedLocation = new ArrayList<Location>();
        for (int i = 0; i != length; i++) {
            for (int j = 0; j != length; j++) {
                Location loc = new Location(i, j);
                if (get(loc) != null) {
                    occupiedLocation.add(loc);
                }
            }
        }
        return occupiedLocation;
    }
    
    /**
     * Find the actor in the location
     * @param loc
     *         The location to find
     * @return The actor in the location
     */
    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                                            + " is not valid");
        }
        if (length <= loc.getRow() || length <= loc.getCol()) {
            return null;
        }
        return (E) locations[loc.getRow()][loc.getCol()];       
    }
    
    /**
     * Put the actor to the location
     * @param loc
     *         The location to put
     * @return The actor in the location before put.
     */
    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                                        + " is not valid");
        }
        if (obj == null) {
            return null;
        }

        // Add the object to the grid.
         E oldOccupant = get(loc);
        if (length <= loc.getRow() || length <= loc.getCol())
        {
            int preLength = length;
            Object[][] preLocations = locations;
            length = adjustmentLength(loc, length);
            locations = new Object[length][length];
            for (int i = 0; i != preLength; i++) {
                for (int j = 0; j != preLength; j++) {
                    locations[i][j] = preLocations[i][j];
                }
            }
        }
        
        locations[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * Adjust the length of the grid
     */
    private int adjustmentLength(Location loc, int length) {
        while (length <= loc.getRow() || length <= loc.getCol()) {
                length *= 2;    
        }
        return length;
    }

    /**
     * Remove the actor from the location
     * @param loc
     *         The location to remove actor
     * @return The actor in the location before remove.
     */
    public E remove(Location loc)
    {
        E occupied = get(loc);
        if (length > loc.getRow() && length > loc.getCol()) {
            locations[loc.getRow()][loc.getCol()]  = null;
        }

         return occupied;    
    }
}
