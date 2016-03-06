package com.TigersIter2.entities;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.location.Location;

/**
 * Created by Josh on 2/27/2016.
 */
public class Vehicle extends Entity {
    private String name;
    private Location location;
    private Location pixelLocation;
    private int direction;
    private int movementBonus;
    private boolean canPassWater;
    private boolean canPassMountain;
    private boolean currentlyMoving;


    public Vehicle(String name, int movement, boolean water, boolean mountain){
        this.name = name;
        this.location = new Location(20 * StaticVar.terrainImageWidth,20 * StaticVar.terrainImageHeight,0);
        this.pixelLocation = new Location(StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth, StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight, 0);
        this.movementBonus = movement;
        this.canPassWater = water;
        this.canPassMountain = mountain;
        this.direction = 270;
        this.currentlyMoving = false;
    }

    public boolean getCanPassWater(){
        return canPassWater;
    }

    public boolean getCanPassMountain(){
        return canPassMountain;
    }

    private void changeDirection(int x, int y){
        if(x == 0){
            if(y == 1)
                direction = 270;
            else if(y == -1)
                direction = 90;
        }
        else if(x == 1){
            if(y == 1)
                direction = 315;
            else if(y == -1)
                direction = 45;
        }
        else{
            if(y == 1)
                direction = 225;
            else if(y == -1)
                direction = 135;
        }
    }

    public int getDirection(){
        return direction;
    }

    public boolean isCurrentlyMoving() {
        return currentlyMoving;
    }

    public Location getPixelLocation() {
        return pixelLocation;
    }

    public void setPixelLocation(Location pixelLocation) {
        this.pixelLocation = pixelLocation;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void update(int xMovement, int yMovement, int zMovement) {
        if(xMovement == 0 && yMovement == 0){
            currentlyMoving = false;
        }
        else {
            currentlyMoving = true;
            location.incrementX(xMovement * 5);   //Made it 3 times faster because IT WAS SO SLOOOOOW (Miles)
            location.incrementY(yMovement * 5);
            changeDirection(xMovement, yMovement);
        }
    }
}
