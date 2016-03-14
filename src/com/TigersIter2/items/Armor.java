package com.TigersIter2.items;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public class Armor extends TakeableItem {
    private int armorSlot; //represents the armor slot for the armor

    public Armor(String n, int increaseDefense, int armorSlot){
        this.armorSlot = armorSlot;
        setName(n);
        setEquippable(true);
        this.sm = new StatsModifier();
        sm.setArmor(sm.getArmor() + increaseDefense); //increases the defense of the player
        itemType = StaticVar.armorItemType;
        equippable = true;
    }

    public int getArmorSlot() { return armorSlot; }
}
