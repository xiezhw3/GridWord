/*
 * Copright(c) 2014 xiezhw3(xiezhw3@gmail.com)
 *
 * This code is the answer of the exercise 2
 * in part2.
 *
 * @author: xiezhw3
 * */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * A <code>DancingBugRunner</code> of exercise 2 in part2.
 * */
public final class JumperRunner {
    private JumperRunner(){}
 
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(0, 0), alice);
        world.add(new Location(0, 2), new Rock());
   
        world.show();
    }
}

