package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;
/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public class Weapon extends TakeableItem {
    //default values for weapons
    int damageModifier = 10;


    //constructor to create a default weapon
    public Weapon(){
        setName("weapon");
        setEquippable(true); //weapons are equippable
        this.sm = new StatsModifier();
        sm.setAttack(sm.getAttack()+damageModifier); //adds the damage value of the weapon to the attack rating
    }


}
