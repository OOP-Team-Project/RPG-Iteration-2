package com.TigersIter2.items;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Breanna on 3/7/16.
 */
public class TwoHandedWeaponItem extends Weapon{
    /*TwoHanded weapons effect the movement of the player */
    private int decreaseMovement = 0;

    //constructor to create TwoHandedWeaponItem
    public TwoHandedWeaponItem(String n, int decreaseMovement){
        this.decreaseMovement = decreaseMovement;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        /* adds to the attack rating and decreases the movement */
        sm.setAttackTime(StaticVar.fps);
        sm.setAttack(sm.getAttack()+damageModifier);
        sm.setMovement(sm.getMovement()-decreaseMovement);
        weaponType = "TwoHandedWeapon";
    }

    public int getDecreaseMovement() {
        return decreaseMovement;
    }
}
