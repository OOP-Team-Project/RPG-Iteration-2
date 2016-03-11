package com.TigersIter2.entities;

/**
 * Created by Josh on 2/27/2016.
 */
public class Smasher extends Occupation {

    public Smasher() {
        strength = 10;
        agility = 5;
        intellect = 5;
        hardiness = 10;
        movement = 5;
        life = 75;
        mana = 25;
        attackTime = 750;

        strengthIncrement = 5;
        agilityIncrement = 1;
        intellectIncrement = 1;
        hardinessIncrement = 5;
        movementIncrement = 1;
        lifeIncrement = 10;
        manaIncrement = 0;
    }

    public String toString(){
        return "Smasher";
    }
}
