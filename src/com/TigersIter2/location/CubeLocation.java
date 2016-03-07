package com.TigersIter2.location;

//simple location class, has three dimensions for hex tile system
public class CubeLocation {

    private int x,y,z;

    public CubeLocation(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void incX(int dx) { this.x += dx; }

    public int getY() { return y; }

    public void incY(int dy) { this.y += dy; }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void incZ(int dz) { this.z += dz; }
}
