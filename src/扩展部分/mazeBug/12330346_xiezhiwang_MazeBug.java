//package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
    private boolean isEnd = false;
    private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private Integer stepCount = 0;
    //final message has been shown
    private boolean hasShown = false;
    private ArrayList<Location> nexts;
    private ArrayList<Location> topfirst;
    private static final int STARTSTEP = 0;
    private static final int NORTHINDEX = 0;
    private static final int EASTINDEX = 1;
    private static final int SOUTHINDEX = 2;
    private static final int WESTINDEX = 3;
    private static final int DIRNUM = 4;
    private static final int COLORMAX = 255;
    private static final int  COLORMIN = 0;
    
    private int[] direStepNum;

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     */
    public MazeBug() {
        setColor(Color.GREEN);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        if (stepCount == 0) {
            ArrayList<Location> loc = new ArrayList<Location>();
            loc.add(getLocation());
            crossLocation.push(loc);
            direStepNum = new int[DIRNUM];
            for (int i = 0; i != DIRNUM; i++) {
                direStepNum[i] = STARTSTEP;
            }
        }
        boolean willMove = canMove();
        if (isEnd) {
        //to show step count when reach the goal        
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            move();
            //increase step count when move 
            stepCount++;
        } else {
            backStep();
            stepCount++;
        }
    }
    
    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return null;
        ArrayList<Location> valid = new ArrayList<Location>();
        if (crossLocation.size() > 0) {
            topfirst = crossLocation.pop();
            
            for (int i = 0; i != DIRNUM; i++) {
                Location locTmp = loc.getAdjacentLocation(getDirection() + Location.RIGHT * i);
                if (gr.isValid(locTmp)) {
                    Actor neighbor = gr.get(locTmp);
                    if (gr.isValid(locTmp)) {
                        if (neighbor == null && !topfirst.contains(locTmp)) {
                            valid.add(locTmp);
                        }
                        if (neighbor instanceof Rock && neighbor.getColor().equals(new Color(COLORMAX, COLORMIN, COLORIN))) {
                            setDirection(getDirection() + Location.RIGHT * i);
                            isEnd = true;
                        }
                    }
                }
            }
            crossLocation.push(topfirst);
        }
        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        if (!gr.isValid(loc)) {
            return false;
        }
        nexts = getValid(loc);
        if (nexts.size() != 0) {
            return true;
        }
        return false;
    }
    
    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (nexts.size() > 0) {
            Location next = getMostPropertyDir(nexts);
            if (gr.isValid(next)) {
                setDirection(getLocation().getDirectionToward(next));
                moveTo(next);
            } else
                removeSelfFromGrid();
            
            Flower flower = new Flower(getColor());
            flower.putSelfInGrid(gr, loc);
            
            topfirst = crossLocation.pop();
            topfirst.add(next);
            crossLocation.push(topfirst);
            
            ArrayList<Location> newList = new ArrayList<Location>();
            newList.add(next);
            crossLocation.push(newList);
        }
    }
    
    /**
     * Back one step when the there is no valid location
     * to move.
     */
    public void backStep() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        
        if (crossLocation.size() > 0) {
            crossLocation.pop();
            if (crossLocation.size() > 0) {
                Location loc = getLocation();
                ArrayList<Location> pres = crossLocation.peek();
                Location pre = pres.get(0);
                if (!gr.isValid(pre)) {
                    removeSelfFromGrid();
                    return;
                }
                setDirection(loc.getDirectionToward(pre));
                moveTo(pre);
                Flower flower = new Flower(getColor());
                flower.putSelfInGrid(gr, loc);
                direStepNum[getAngelIndex(getAngel(pre, loc))]--;
                direStepNum[getAngelIndex(getAngel(loc, pre))]++;
            }
        }
    }
    
    /**
     * Back one step when the there is no valid location
     * to move.
     * 
     * @param nexts
     *            the list of location that bug can move to
     * @return A location which has the most property to move
     */
    private Location getMostPropertyDir(ArrayList<Location> nexts) {
        int dirNum = 0;
        int dir = 0;
        int dirindex = 0;
        Location loc = getLocation();
        Location next = null;
        if (nexts.size() > 0) {
            // Randomly select a direction location for initialization
            next = nexts.get((new Random()).nextInt(nexts.size()));
            dirindex = getAngelIndex(getAngel(loc, next));
            
            for (int i = 0; i != nexts.size(); i++) {
                dir = getAngel(loc, nexts.get(i));
                if (dir == Location.NORTH) {
                    if (dirNum < direStepNum[NORTHINDEX]) {
                        dirNum = direStepNum[NORTHINDEX];
                        dirindex = NORTHINDEX;
                        next = nexts.get(i);
                    }
                } else if (dir == Location.EAST) {
                    if (dirNum < direStepNum[EASTINDEX]) {
                        dirNum = direStepNum[EASTINDEX];
                        dirindex = EASTINDEX;
                        next = nexts.get(i);
                    }
                } else if (dir == Location.SOUTH) {
                    if (dirNum < direStepNum[SOUTHINDEX]) {
                        dirNum = direStepNum[SOUTHINDEX];
                        dirindex = SOUTHINDEX;
                        next = nexts.get(i);
                    }
                } else if (dir == Location.WEST) {
                    if (dirNum < direStepNum[WESTINDEX]) {
                        dirNum = direStepNum[WESTINDEX];
                        dirindex = WESTINDEX;
                        next = nexts.get(i);
                    }
                }
            }
            direStepNum[dirindex]++;
        }
        return next;
    }
    
    /**
     * Get the angel from the location immediately to the next
     * 
     * @param sorLoc
     *            the immediately location
     * @param sorLoc
     *            the next location
     *            
     * @return The angel from immediately location to next
     */
    private int getAngel(Location sorLoc, Location nextLoc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return 0;    
        }
        if (!gr.isValid(sorLoc) || !gr.isValid(nextLoc)) {
            return 0;
        }
        int sory = sorLoc.getRow();
        int sorx = sorLoc.getCol();
        int nexty = nextLoc.getRow();
        int nextx = nextLoc.getCol();
        if (sorx < nextx) {
            return Location.EAST;
        }
        if (sory < nexty) {
            return Location.SOUTH;
        }
        if (sorx > nextx) {
            return Location.WEST;
        }
        if (sory > nexty) {
            return Location.NORTH;
        }
        return 0;
    }
    
    /**
     * Get the angel from the location immediately to the next
     * 
     * @param degerr
     *            the angel
     *            
     * @return The index of the angel in angel list.
     */
    private int getAngelIndex(int degerr) {
        if (degerr == Location.NORTH) {
            return NORTHINDEX;
        } else if (degerr == Location.EAST) {
            return EASTINDEX;
        } else if (degerr == Location.SOUTH) {
            return SOUTHINDEX;
        } else if (degerr == Location.WEST) {
            return WESTINDEX;
        }
        return 0;
    }
}

