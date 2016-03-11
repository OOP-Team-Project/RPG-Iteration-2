package com.TigersIter2.entities;

/**
 * Created by Josh on 2/27/2016.
 */
public class Sneak extends Occupation {

    public Sneak() {
        strength = 5;
        agility = 10;
        intellect = 5;
        hardiness = 5;
        movement = 8;
        life = 50;
        mana = 50;
        attackTime = 750;
        influenceRadius = 2;

        strengthIncrement = 1;
        agilityIncrement = 5;
        intellectIncrement = 1;
        hardinessIncrement = 2;
        movementIncrement = 2;
        lifeIncrement = 7;
        manaIncrement = 5;
    }

    public String toString(){
        return "Sneak";
    }
}
