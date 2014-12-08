/* 
 * AP(r) Computer Science GridWorld Case Exercise 
 * Copyright(c) 2014 xiezhw3 (xiezhw3@gmail.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author xiezhw3
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>KingCrab</code> causes  each actor that it process to move
 * one location futher away or remove it if can't move<br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter 
{
    /**
     * move each actor futher away from itself or remove it
         */
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor actor : actors) {
            if (actor instanceof KingCrab) {
                continue;
            }
            Location loc = getDirectionLocation(getLocation(), actor.getLocation());
            if (loc != null) {
                actor.moveTo(loc);
            }   
            else {
                actor.removeSelfFromGrid();  
            }                      
        }
    }

    /**
     * */
    public Location getDirectionLocation(Location locCritter, Location locActor) {
        int dir = locCritter.getDirectionToward(locActor);
        Location locNext = locActor.getAdjacentLocation(dir);
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        if (!gr.isValid(locNext)) {
            return null;
        }
        Actor actor = gr.get(locNext);
        if (actor == null || actor instanceof Flower) {
            return locNext;
        }
        return null;
    }
}
