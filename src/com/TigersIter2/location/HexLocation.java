package com.TigersIter2.location;

//simple location class, has three dimensions for hex tile system
public class HexLocation {

    private int q,r;

    public HexLocation(int x, int y){
        q = x;
        r = y;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int x) {
        q = x;
    }

    public void incrementQ(int dx) { q += dx; }

    public int getR() { return r; }

    public void setR(int y) {
        r = y;
    }

    public void incrementR(int dy) { r += dy; }

}
