package com.TigersIter2.views;

import com.TigersIter2.entities.Entity;

/**
 * Created by Miles on 3/13/16.
 */
public class EntityFollowingCamera extends Camera{  //This camera sticks on an entity like glue!

    Entity entityOfInterest;

    public EntityFollowingCamera(Entity e){
        entityOfInterest = e;
    }

    @Override
    public int getObjectOfInterest_X() {
        return entityOfInterest.getLocation().getX();
    }

    @Override
    public int getObjectOfInterest_Y() {
        return entityOfInterest.getLocation().getY();
    }
}
