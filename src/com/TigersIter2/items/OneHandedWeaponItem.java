package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Breanna on 3/7/16.
 */
public class OneHandedWeaponItem extends Weapon {

    //constructor to create OneHandedWeaponItem
    public OneHandedWeaponItem(String n){
        this.damageModifier = 3;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        sm.setAttack(sm.getAttack()+damageModifier);
        weaponType = "OneHandedWeapon";
    }
}
