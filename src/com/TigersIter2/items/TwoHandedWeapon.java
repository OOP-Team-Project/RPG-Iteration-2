package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Breanna on 3/7/16.
 */
public class TwoHandedWeapon extends Weapon{
    /*TwoHanded weapons effect the movement of the player */
    private int decreaseMovement = 0;

    //constructor to create TwoHandedWeapon
    public TwoHandedWeapon(String n, int damage, int decreaseMovement){
        this.damageModifier = damage;
        this.decreaseMovement = decreaseMovement;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        /* adds to the attack rating and decreases the movement */
        sm.setAttackRating(sm.getAttackRating()+damageModifier);
        sm.setMovement(sm.getMovement()-decreaseMovement);
    }
}
