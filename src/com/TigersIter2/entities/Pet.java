package com.TigersIter2.entities;

/**
 * Created by Josh on 2/27/2016.
 */
public class Pet extends Entity {

    @Override
    public void update(int xMovement, int yMovement) {
        System.out.println(xMovement + ", " + yMovement);
        //location.incrementX(dx);
        //location.incrementY(dy);
        //if( location.getX() >= 1280 - 50 || location.getX() <= 0 )
        //dx *= -1;
        //if( location.getY() >= 720 - 75 || location.getY() <= 0 )
        //dy *= -1;
    }

}
