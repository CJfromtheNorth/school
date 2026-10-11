package com.zetcode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoardCollisionTest extends BoardTestSupport {
    // Black-box equivalence partitions: safe position and four outside regions.
    // Boundary values: -1, 0, 299, 300 for a 300 by 300 board.
    @ParameterizedTest(name = "head at ({0},{1}) -> playing={2}")
    @CsvSource({"0,0,true", "299,299,true", "-1,50,false", "300,50,false", "50,-1,false", "50,300,false", "0,299,true", "299,0,true"})
    void boundaryAndEquivalenceCases(int x, int y, boolean expected) throws Exception {
        head(x, y);
        call("checkCollision");
        assertEquals(expected, playing());
        if (!expected) assertFalse(timer().isRunning());
    }

    // White-box path: z > 4, same x and y causes self-collision.
    @Test
    void bodyCollisionAtSegmentFiveEndsGame() throws Exception {
        set("dots", 5);
        head(50, 50);
        xs()[5] = 50;
        ys()[5] = 50;
        call("checkCollision");
        assertFalse(playing());
    }

    @Test
    void bodyOverlapAtSegmentFourDoesNotEndGame() throws Exception {
        set("dots", 5);
        head(50, 50);
        xs()[4] = 50;
        ys()[4] = 50;
        xs()[5] = 90;
        ys()[5] = 90;
        call("checkCollision");
        assertTrue(playing());
    }

    @Test
    void matchingXButDifferentYIsNotCollision() throws Exception {
        set("dots", 5);
        head(50, 50);
        xs()[5] = 50;
        ys()[5] = 60;
        call("checkCollision");
        assertTrue(playing());
    }
}
