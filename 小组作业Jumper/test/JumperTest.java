import static org.junit.Assert.*;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

import org.junit.Before;
import org.junit.Test;


public class JumperTest {
    private Jumper alice;
    private ActorWorld world;
    private static final int LOC0 = 0;
    private static final int LOC2 = 2;
    private static final int LOC1 = 1;
    private static final int LOC4 = 4;

    private static final int GRIDHEIGHT = 20;
    private static final int GRIDWIDTH = 19;

    private static final int ACTTIME2 = 2;
    private static final int ACTTIME3 = 3;
    private static final int ACTTIME5 = 5;
    public JumperTest() { }
    
    // Before every test, create a new bounded grid and a new jumper
    @Before
    public void before() {
        world = new ActorWorld(new BoundedGrid<Actor>(GRIDHEIGHT, GRIDWIDTH));
        alice = new Jumper();
        world.add(new Location(LOC0, LOC0), alice);
        world.add(new Location(LOC0, LOC2), new Rock());
    }
    
    @Test
    public void testCanMove1() {
        // When there is a bound of grid in front of jumper,
        // it can't move.
        assertEquals(alice.canMove(), false);
        for (int i = 0; i != ACTTIME2; i++) {
            alice.act();
        }
        
        // When there is a rock tow steps in front of jumper,
        // it can't move.
        assertEquals(alice.canMove(), false);
        alice.act();
        
        // When there is nothing one and tow steps in front of jumper,
        // it can move.
        assertEquals(alice.canMove(), true);
    }
    
    @Test
    public void testCanMove2() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        Jumper jumperT = new Jumper();
        world.add(new Location(LOC1, LOC1), jumperT);
        
        // When there is a another jumper in front of jumper,
        // it can't move.
        assertEquals(alice.canMove(), false);
        
        // When there is a grid bound two steps in front of jumper,
        // it can't move.
        assertEquals(jumperT.canMove(), false);
    }
    
    @Test
    public void testCanMov3() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        Jumper jumperT = new Jumper();
        world.add(new Location(LOC2, LOC2), jumperT);
        
        // When there is a another jumper two steps in front of jumper,
        // it can't move.
        assertEquals(alice.canMove(), false);
    }
    
    @Test
    public void testCanMov4() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        world.add(new Location(LOC1, LOC1), new Rock());
        
        // When there is a rock in front of jumper,
        // it can move.
        assertEquals(alice.canMove(), true);
    }
    
    @Test
    public void testCanMov5() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        
        // When there is a flower two steps in front of jumper,
        // it can move.
        world.add(new Location(LOC2, LOC2), new Flower());
        assertEquals(alice.canMove(), true);
    }
    
    @Test
    public void testCanMov6() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        
        // When there is a flower in front of jumper,
        // it can move.
        world.add(new Location(LOC1, LOC1), new Flower());
        assertEquals(alice.canMove(), true);
    }
    
    @Test
    public void testFlower() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        world.add(new Location(LOC2, LOC2), new Flower());
        alice.act();
        alice.act();
        
        // When the jumper move to the location which has a flower,
        // the flower will be removed.
        assertEquals(alice.getGrid().get(new Location(LOC2, LOC2)), null);
    }
    
    @Test
    public void testOtherActor() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        world.add(new Location(LOC2, LOC2), new Actor());
        
        // When there is an actor which is not a jumper two steps in front of jumper,
        // it can't move.
        assertEquals(alice.canMove(), false);
    }
    
    @Test
    public void testOtherActor2() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        world.add(new Location(LOC1, LOC1), new Actor());
        
        // When there is an actor which is not a jumper in front of jumper,
        // it can move.
        assertEquals(alice.canMove(), true);
    }
    
    @Test
    public void testMove() {
        for (int i = 0; i != ACTTIME5; i++) {
            alice.act();
        }
        
        // After 5 steps, the location of jumper must be (4, 4)
        assertEquals(alice.getLocation().getRow(), LOC4);
        assertEquals(alice.getLocation().getCol(), LOC4);
    }
    
    @Test
    public void testMove2() {
        for (int i = 0; i != ACTTIME3; i++) {
            alice.act();
        }
        world.add(new Location(LOC1, LOC1), new Actor());
        alice.act();
        alice.act();
        // If there is an actor in front of jumper, 
        // the jumper will jumper over the actor.
        assertEquals(alice.getLocation().getRow(), LOC4);
        assertEquals(alice.getLocation().getCol(), LOC4);
    }
}
