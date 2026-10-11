package com.zetcode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardMovementTest extends BoardTestSupport {
    // DU chain: direction flag -> updated head coordinate; old head -> body segment.
    @ParameterizedTest
    @CsvSource({"true,false,false,false,40,50", "false,true,false,false,60,50", "false,false,true,false,50,40", "false,false,false,true,50,60", "false,false,false,false,50,50"})
    void movementPaths(boolean left, boolean right, boolean up, boolean down, int x, int y) throws Exception {
        head(50, 50);
        direction(left, right, up, down);
        call("move");
        assertAll(() -> assertEquals(x, xs()[0]), () -> assertEquals(y, ys()[0]));
        assertEquals(50, xs()[1]);
        assertEquals(50, ys()[1]);
    }

    @Test
    void oldBodyCoordinatesPropagateToNextSegment() throws Exception {
        head(50, 50);
        xs()[1] = 40;
        ys()[1] = 50;
        direction(false, true, false, false);
        call("move");
        assertEquals(50, xs()[1]);
        assertEquals(40, xs()[2]);
    }

    @Test
    void initialGameStateIsThreeSegmentsAtExpectedCoordinates() throws Exception {
        assertEquals(3, dots());
        assertArrayEquals(new int[] {50, 40, 30}, new int[] {xs()[0], xs()[1], xs()[2]});
        assertArrayEquals(new int[] {50, 50, 50}, new int[] {ys()[0], ys()[1], ys()[2]});
        assertTrue(playing());
    }
}
