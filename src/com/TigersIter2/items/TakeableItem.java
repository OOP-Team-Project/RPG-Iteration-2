package com.TigersIter2.items;

import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public abstract class TakeableItem extends Item {

    private boolean equippable;
    private String name;
    private int priceValue; //this is the value of the item which is used in bartering
    StatsModifier sm;

    public String toString(){
        return name;
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
}
