package com.TigersIter2.entities;

/**
 * Created by Josh on 3/6/2016.
 */
public class Monster extends NPC{

    public Monster(){
        willTalk = false;
        willTrade = false;
        willAttack = true;
        isVillager = false;
    }

    public int attack(){
        // Probably base the return value off of the offensive rating of the NPC stats
        System.out.println("Attacked by a monster!");
        return 1;
    }
}
