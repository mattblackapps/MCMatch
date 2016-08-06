package com.mygdx.mcmatch.handlers;

/**
 * Created by matthew on 8/6/16.
 */
public class Touch {
    public int x;
    public int y;
    public final int id;

    public enum TouchState {
        BEGAN, MOVING, ENDED
    }
    public TouchState state;

    public Touch(int x, int y, int id, TouchState state) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.state = state;
    }
}
