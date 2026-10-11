package com.zetcode;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.ActionEvent;
import org.junit.jupiter.api.Test;

class BoardAppleAndPathTest extends BoardTestSupport {
    // CFG paths: both coordinate matches -> eat; either mismatch -> do not eat.
    @Test
    void eatingAppleIncreasesLengthAndRelocatesApple() throws Exception {
        head(50, 50);
        set("apple_x", 50);
        set("apple_y", 50);
        call("checkApple");
        assertEquals(4, dots());
        assertTrue((int) get("apple_x") >= 0 && (int) get("apple_x") <= 280);
        assertTrue((int) get("apple_y") >= 0 && (int) get("apple_y") <= 280);
    }

    @Test
    void differentAppleXDoesNotIncreaseLength() throws Exception {
        set("apple_x", 60);
        set("apple_y", 50);
        call("checkApple");
        assertEquals(3, dots());
    }

    @Test
    void differentAppleYDoesNotIncreaseLength() throws Exception {
        set("apple_x", 50);
        set("apple_y", 60);
        call("checkApple");
        assertEquals(3, dots());
    }

    @Test
    void tickMovesSnakeWhenGameIsActive() throws Exception {
        set("apple_x", 100);
        set("apple_y", 100);
        board.actionPerformed(new ActionEvent(board, ActionEvent.ACTION_PERFORMED, "tick"));
        assertEquals(60, xs()[0]);
    }

    @Test
    void tickDoesNotMoveSnakeWhenGameIsOver() throws Exception {
        set("inGame", false);
        board.actionPerformed(new ActionEvent(board, ActionEvent.ACTION_PERFORMED, "tick"));
        assertEquals(50, xs()[0]);
    }

    @Test
    void appleLocationAlwaysUsesGridWithinSpawnRange() throws Exception {
        for (int i = 0; i < 100; i++) {
            call("locateApple");
            int x = (int) get("apple_x");
            int y = (int) get("apple_y");
            assertTrue(x >= 0 && x <= 280 && x % 10 == 0);
            assertTrue(y >= 0 && y <= 280 && y % 10 == 0);
        }
    }
}
