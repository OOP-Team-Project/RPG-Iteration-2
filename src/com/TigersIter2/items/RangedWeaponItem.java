package com.TigersIter2.items;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Breanna on 3/7/16.
 */
public class RangedWeaponItem extends Weapon {
    private int range = 0;
    private int angle = 0;

    public RangedWeaponItem(String n, int damage, int range, int angle){
        this.damageModifier = damage;
        this.range = range;
        this.angle = angle;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        /* adds to the attack rating and decreases the movement */
        sm.setAttack(sm.getAttack()+damageModifier);
        sm.setAttackTime(StaticVar.fps/2);
        sm.setInfluenceRadius(range);
        weaponType = "RangedWeapon";
    }
}
