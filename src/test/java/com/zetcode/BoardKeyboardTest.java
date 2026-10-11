package com.zetcode;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.junit.jupiter.api.Test;

class BoardKeyboardTest extends BoardTestSupport {
    private void press(int code) {
        KeyEvent event = new KeyEvent(board, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, code, KeyEvent.CHAR_UNDEFINED);
        for (KeyListener listener : board.getKeyListeners()) listener.keyPressed(event);
    }

    @Test
    void cannotReverseDirectlyFromRightToLeft() throws Exception {
        press(KeyEvent.VK_LEFT);
        assertTrue((boolean) get("rightDirection"));
        assertFalse((boolean) get("leftDirection"));
    }

    @Test
    void canTurnUpThenLeft() throws Exception {
        press(KeyEvent.VK_UP);
        assertTrue((boolean) get("upDirection"));
        press(KeyEvent.VK_LEFT);
        assertTrue((boolean) get("leftDirection"));
        assertFalse((boolean) get("upDirection"));
    }

    @Test
    void cannotReverseFromUpToDown() throws Exception {
        press(KeyEvent.VK_UP);
        press(KeyEvent.VK_DOWN);
        assertTrue((boolean) get("upDirection"));
        assertFalse((boolean) get("downDirection"));
    }

    @Test
    void canTurnDownThenRight() throws Exception {
        press(KeyEvent.VK_DOWN);
        assertTrue((boolean) get("downDirection"));
        press(KeyEvent.VK_RIGHT);
        assertTrue((boolean) get("rightDirection"));
        assertFalse((boolean) get("downDirection"));
    }
}
