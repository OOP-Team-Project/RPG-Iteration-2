package com.TigersIter2.entities;


import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.location.Location;
/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public class Pet extends Entity {
    private String name;
    private Avatar avatar; //needs a handle to avatar for location
    private Location location;
    private Location pixelLocation;
    private int direction;

    public Pet(String n, Avatar avatar){
        name = n;
        this.avatar = avatar;
        //sets the location of the Pet to be next to the avatar
        int avatarXLocation = avatar.getLocation().getX();
        int avatarYLocation = avatar.getLocation().getY();
        this.location = new Location(avatarXLocation, avatarYLocation, 0);
        int avatarXPixelLoc = avatar.getPixelLocation().getX();
        int avatarYPixelLoc = avatar.getPixelLocation().getY();
        this.pixelLocation = new Location(avatarXPixelLoc+100, avatarYPixelLoc-100, 0);
    }

    @Override
    public void update(int xMovement, int yMovement, long elapsed) {
        //checks to see if avatar is currently moving
        if(avatar.isCurrentlyMoving()) {
            //made the Pet the same speed as the avatar
            location.incrementX(Math.round(xMovement * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));
            location.incrementY(Math.round(yMovement * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));
            changeDirection(xMovement, yMovement);
        }
    }

    public int getDirection(){
        return direction;
    }

    public Location getPixelLocation() { return pixelLocation; }

    public void changeDirection(int x, int y) {
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
}
