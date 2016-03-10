package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Breanna on 3/7/16.
 */
public class OneHandedWeapon extends Weapon {

    //constructor to create OneHandedWeapon
    public OneHandedWeapon(String n, int damage){
        this.damageModifier = damage;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        sm.setAttack(sm.getAttack()+damageModifier);
    }
}
