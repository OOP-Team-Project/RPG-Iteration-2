package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public abstract class TakeableItem extends Item {

    protected boolean equippable = false;
    protected boolean usable = false;
    private String name;
    protected String weaponType;
    private int priceValue = 10; //this is the value of the item which is used in bartering
    StatsModifier sm;

    public String toString(){
        return name;
    }

    public String getWeaponType(){
        return weaponType;
    }

    public void setName(String n){
        name = n;
    }

    public boolean isEquippable(){
        return equippable;
    }

    public void setEquippable(boolean b){
        equippable = b;
    }

    public void setPrice(int priceValue) { this.priceValue = priceValue; }

    public int getPrice() { return priceValue; }

    public StatsModifier getStatsModifier() { return sm; }

    public boolean isUsable(){
        return usable;
    }
}
