package com.TigersIter2.entities;

/**
 * Created by Josh on 2/27/2016.
 */
public class Summoner extends Occupation {

    public Summoner() {
        strength = 5;
        agility = 5;
        intellect = 10;
        hardiness = 3;
        movement = 5;
        life = 40;
        mana = 75;
        attackTime = 750;
        influenceRadius = 2;

        strengthIncrement = 1;
        agilityIncrement = 1;
        intellectIncrement = 5;
        hardinessIncrement = 1;
        movementIncrement = 1;
        lifeIncrement = 5;
        manaIncrement = 10;
    }

    public String toString(){
        return "Summoner";
    }
}
