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
    private boolean hasEntityRiding;
    private Entity entityRidingMe;


    public Vehicle(String name, int movement, boolean water, boolean mountain){
        this.name = name;
        location = new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight,0);
        pixelLocation = location;
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

    public int getMovementBonus(){
        return movementBonus;
    }

    private void changeDirection(int x, int y){
        if(x == 0){
            if(y > 0)
                direction = 270;
            else if(y < 0)
                direction = 90;
        }
        else if(x > 0){
            if(y > 0)
                direction = 315;
            else if(y < 0)
                direction = 45;
        }
        else{
            if(y > 0)
                direction = 225;
            else if(y < 0)
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
    public void update(int xMovement, int yMovement, long elapsed) {
        if(xMovement == 0 && yMovement == 0){
            currentlyMoving = false;
        }
        else {
            currentlyMoving = true;
            location.incrementX(Math.round(xMovement * elapsed * StaticVar.entitySpeed));   //Change this later so that it just sets the position to whatever the entity's is
            location.incrementY(Math.round(yMovement * elapsed * StaticVar.entitySpeed));
            changeDirection(xMovement, yMovement);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
