/*
 * This code is for exercise 1 im part 5
 *
 * This class implements a linkList of location.
 * The list is to store the occupied location
 * and the index of the location. 
 *
 * @author xiezhw3
 */


/**
 * A <code>SparseGridNode</code> implements a list structure that
 * stores the occupied location and its index.<br />
 */
public class SparseGridNode
{
    private Object occupant;
    private int col;
    private SparseGridNode next;

    public SparseGridNode(Object occupant, int col,
                SparseGridNode next)
    {
        this.occupant = occupant;
        this.col = col;
        this.next = next;
    }

    public Object getOccupant()
    {
        return this.occupant;
    }

    public int getColumn()
    {
        return this.col;
    }

    public SparseGridNode getNext()
    {
        return this.next;
    }

    public void setColumn(int col) {
        this.col = col;
    }

    public void setNext(SparseGridNode next)
    {
        this.next = next;
    }

    public void setOppucied(Object occupant)
    {
        this.occupant = occupant;
    }
}
