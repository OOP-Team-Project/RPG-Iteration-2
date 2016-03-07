package com.TigersIter2.entities;

/**
 * Created by Josh on 2/27/2016.
 */
public class Pet extends Entity {
    private String name;

    public Pet(String n){
        name = n;
    }

    @Override
    public void update(int xMovement, int yMovement, long elapsed) {
        System.out.println(xMovement + ", " + yMovement);
    }

    public int getDirection(){
        return 0;   //until direction is implemented
    }
}
