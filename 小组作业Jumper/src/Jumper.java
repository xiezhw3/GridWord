/*
 * This code is for Group activity in
 * part3
 *
 * This code implements a Jumper who
 * move two steps in each move.
 *
 * @author: xiezhw3
 * */

import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;


/**
 * A <code>Jumper</code> for group activity exercise.
 * */
public class Jumper extends Bug {

    private Location getTwoStepsNextLoc(Location loc) {
        Location locNextTmp = loc.getAdjacentLocation(getDirection());
        Location next = null;
        if (locNextTmp != null) {
            next = locNextTmp.getAdjacentLocation(getDirection());
        }
        return next;
    }
    /**
     * Moves the bug tow steps forward.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location next = getTwoStepsNextLoc(getLocation());
        if (gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }

        /**
          * Moves if it can move, turns otherwise.
          */
    public void act() {
        if (canMove()) {
            move();
        } else {
            turn();
        }
    }

    /**
     * Tests whether this Jumper can move forward into a location that is empty or
     * contains a flower.
     * @return true if this bug can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }

        Location loc = getLocation();
        Location locNextTmp = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(locNextTmp)) {
            if (gr.get(locNextTmp) instanceof Jumper) {
                return false;
            }
            Location next = getTwoStepsNextLoc(getLocation());

            if (!gr.isValid(next)) {
                return false;
            }

            Actor neighbor = gr.get(next);
            return (neighbor == null) || (neighbor instanceof Flower);
        }
        return false;
    }
}

