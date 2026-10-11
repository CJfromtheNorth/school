package com.zetcode;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.swing.Timer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

abstract class BoardTestSupport {
    protected Board board;

    @BeforeEach
    void setUp() throws Exception {
        board = new Board();
        timer().stop(); // Avoid background ticks while tests set state.
    }

    @AfterEach
    void tearDown() throws Exception {
        if (board != null) timer().stop();
    }

    protected Object get(String field) throws Exception {
        Field f = Board.class.getDeclaredField(field);
        f.setAccessible(true);
        return f.get(board);
    }

    protected void set(String field, Object value) throws Exception {
        Field f = Board.class.getDeclaredField(field);
        f.setAccessible(true);
        f.set(board, value);
    }

    protected void call(String method) throws Exception {
        Method m = Board.class.getDeclaredMethod(method);
        m.setAccessible(true);
        m.invoke(board);
    }

    protected int[] xs() throws Exception { return (int[]) get("x"); }
    protected int[] ys() throws Exception { return (int[]) get("y"); }
    protected int dots() throws Exception { return (int) get("dots"); }
    protected boolean playing() throws Exception { return (boolean) get("inGame"); }
    protected Timer timer() throws Exception { return (Timer) get("timer"); }

    protected void head(int x, int y) throws Exception {
        xs()[0] = x;
        ys()[0] = y;
    }

    protected void direction(boolean left, boolean right, boolean up, boolean down) throws Exception {
        set("leftDirection", left);
        set("rightDirection", right);
        set("upDirection", up);
        set("downDirection", down);
    }
}
