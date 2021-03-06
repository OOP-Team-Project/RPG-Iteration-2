package com.TigersIter2.items;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public class Potion extends TakeableItem {

    //healing potion
    public Potion(String n, int addHealth) {
        setName(n);       //Defaults for now, because we don't have any other kinds of stat mods
        setEquippable(false);
        this.sm = new StatsModifier();
        sm.setLife(sm.getLife()+addHealth);
        itemType = StaticVar.potionItemType;
        usable = true;
    }
}
