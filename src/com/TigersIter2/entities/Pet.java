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

    public Pet(String n, Avatar avatar){
        name = n;
        this.avatar = avatar;
        //sets the location of the Pet to be next to the avatar
        int avatarXLocation = avatar.getLocation().getX();
        int avatarYLocation = avatar.getLocation().getY();
        this.location = new Location(avatarXLocation+100, avatarYLocation-100, 0);
    }

    @Override
    public void update(int xMovement, int yMovement, long elapsed) {
        location.incrementX(Math.round(xMovement * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));   //Made it invariant of framerate
        location.incrementY(Math.round(yMovement * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));

    }

    public int getDirection(){
        return 0;   //until direction is implemented
    }
}
