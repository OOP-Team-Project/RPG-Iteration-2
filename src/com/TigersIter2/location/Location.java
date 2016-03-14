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

        int dx = Math.abs(x - l.x);
        int dy = Math.abs(getCubeY() - l.getCubeY());
        int dz = Math.abs(getCubeZ() - l.getCubeZ());
        return Math.max(dx, Math.max(dy, dz));
    }

    public boolean equals(Object o) {
        if (o instanceof Location) {
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }
        return false;
    }

    private int getCubeY() {
        return (int)(Math.ceil(x / -2)) + y;
    }
    private int getCubeZ() {
        return -(x + getCubeY());
    }

    public int hashCode() {
        return x + y;
    }

    public int getDistance(int i, int j) {
        return new Location(i,j,-i-j).getDistance(this);
    }
}
