/*
 * This code is for exercise 1 im part 5
 *
 * This code implement a new Grid class, used to
 * work more effective than BoundGrid.
 *
 * @author xiezhw3
 */

import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private SparseGridNode[] parseArray;
    private int rowNum;
    private int colNum;
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
        }
        this.rowNum = rows;
        this.colNum = cols;
        this.parseArray = new SparseGridNode[this.rowNum];
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
        SparseGridNode node;
        ArrayList<Location> occupiedLocation = new ArrayList<Location>();
        for (int i = 0; i != getNumRows(); i++)
        {
            node = parseArray[i];
            while (node != null) {
                occupiedLocation.add(new Location(i, node.getColumn()));
                node = node.getNext();
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
        SparseGridNode node = parseArray[loc.getRow()];
        while (node  != null) {
           if (node.getColumn() == loc.getCol()) {
               return (E) node.getOccupant();
           }
           node = node.getNext();        
        }
        return null;       
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
        E oldOccupant = remove(loc);
        SparseGridNode node = parseArray[loc.getRow()];
        if (node == null) {
            parseArray[loc.getRow()] = new SparseGridNode(obj, loc.getCol(), null);
        } else {
            while (node.getNext()  != null) {
                node = node.getNext();
            }
            node.setNext(new SparseGridNode(obj, loc.getCol(), null));
        }
        return oldOccupant; 
    }

    /**
     * Remove the actor from the location
     * @param loc
     *         The location to remove actor
     * @return The actor in the location before remove.
     */
    public E remove(Location loc)
    {
        if (!isValid(loc)) {
                    throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
        SparseGridNode node = parseArray[loc.getRow()];
        Object occupied = null;
        if (node != null && node.getColumn() == loc.getCol()) {
            occupied = node.getOccupant();
            parseArray[loc.getRow()] =  node.getNext();
        }
        else {
            SparseGridNode nodepre = node;
             while (node  != null) {
                if (node.getColumn() == loc.getCol()) {
                    occupied = node.getOccupant();
                    nodepre.setNext(node.getNext());
                    break;
                }
                nodepre = node;
                node = node.getNext();        
             }
        }
        return (E) occupied;    
    }    
}


