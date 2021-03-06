package creatures;

import huglife.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestClorus {
    @Test
    public void testReplicate() {
        // TODO
        Clorus p = new Clorus(2);
        Clorus baby = p.replicate();
        assertEquals(p.energy(), baby.energy(), 0.01);
        assertNotEquals(p, baby);

    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; move.
        p = new Clorus(.99);

        HashMap<Direction, Occupant> topEmpty2 = new HashMap<Direction, Occupant>();
        topEmpty2.put(Direction.TOP, new Empty());
        topEmpty2.put(Direction.BOTTOM, new Impassible());
        topEmpty2.put(Direction.LEFT, new Impassible());
        topEmpty2.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty2);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);

        //Plip

        p = new Clorus(.99);

        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, new Plip(0.5));
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        p = new Clorus(.99);

        HashMap<Direction, Occupant> topPlip2 = new HashMap<Direction, Occupant>();
        topPlip2.put(Direction.TOP, new Plip(0.5));
        topPlip2.put(Direction.BOTTOM, new Impassible());
        topPlip2.put(Direction.LEFT, new Impassible());
        topPlip2.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topPlip2);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);
    }
}
