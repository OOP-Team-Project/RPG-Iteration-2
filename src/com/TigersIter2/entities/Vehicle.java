package com.TigersIter2.entities;

/**
 * Created by Josh on 2/27/2016.
 */
public class Vehicle extends Entity {
    private String name;
    private int movementBonus;
    private boolean canPassWater;
    private boolean canPassMountain;

    public Vehicle(String name, int movement, boolean water, boolean mountain){
        this.name = name;
        this.movementBonus = movement;
        this.canPassWater = water;
        this.canPassMountain = mountain;
    }

    public boolean getCanPassWater(){
        return canPassWater;
    }

    public boolean getCanPassMountain(){
        return canPassMountain;
    }


    @Override
    public void update(int xMovement, int yMovement, long elapsed) {
        System.out.println(xMovement + ", " + yMovement);
    }
}
