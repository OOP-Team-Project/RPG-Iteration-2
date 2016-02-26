package com.iter2.location;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class Location {

    private int x,y,z;

    public Location(int xHex, int yHex, int zHex){
        x = xHex;
        y = yHex;
        z = zHex;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void incrementX(int dx) { this.x += dx; }

    public int getY() { return y; }

    public void incrementY(int dy) { this.y += dy; }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void incrementZ(int dz) { this.z += dz; }
}
