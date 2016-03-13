package com.TigersIter2.items;

/**
 * Created by Josh on 3/13/2016.
 */
public class TwoHandedSword extends TwoHandedWeaponItem {

    public TwoHandedSword(String n, int decMove){
        super(n, decMove);
        sm.setAttackTime(1000);
    }
}
