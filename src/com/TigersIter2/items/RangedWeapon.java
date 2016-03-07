package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Breanna on 3/7/16.
 */
public class RangedWeapon extends Weapon {
    int range = 0;
    int angle = 0;
    public RangedWeapon(String n, int damage, int range, int angle){
        this.damageModifier = damage;
        this.range = range;
        this.angle = angle;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        /* adds to the attack rating and decreases the movement */
        sm.setAttackRating(sm.getAttackRating()+damageModifier);
    }
}
