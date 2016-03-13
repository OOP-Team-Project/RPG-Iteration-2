package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Josh on 3/13/2016.
 */
public class ThrowingKnife extends RangedWeaponItem {

    public ThrowingKnife(String n, int damage, int range, int angle){
        super(n, damage, range, angle);
        sm.setAttackTime(500);
    }
}
