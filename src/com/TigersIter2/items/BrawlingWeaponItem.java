package com.TigersIter2.items;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.stats.StatsModifier;

public class BrawlingWeaponItem extends Weapon{

    //constructor to create BrawlingWeapon
    public BrawlingWeaponItem(String n){
        this.damageModifier = 1;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        /* adds to the attack rating */
        sm.setAttackTime(-StaticVar.fps/2);
        sm.setAttack(sm.getAttack()+damageModifier);
        weaponType = "Brawling";
    }
}
