package com.TigersIter2.location;

//simple location class, has three dimensions for hex tile system
public class Location {

    private int x,y,z;

    public Location(int xHex, int yHex, int zHex){
        x = xHex;
        y = yHex;
        z = zHex;
    }

    public Location(Location location){
        x = location.getX();
        y = location.getY();
        z = location.getZ();
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

    public String toString() { return "(" + x + ", " + y + ")"; }

    public int getDistance(Location l) {
        int xDistance = Math.abs(l.getX() - x);
        int yDistance = Math.abs(l.getY() - y);
        return Math.max(xDistance, yDistance);
    }

    public boolean equals(Object o) {
        if (o instanceof Location) {
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }
        return false;
    }

    public int hashCode() {
        return x + y;
    }

    public int getDistance(int i, int j) {
        return new Location(i,j,-i-j).getDistance(this);
    }
}
