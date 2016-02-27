package com.TigersIter2.assets.items;

/**
 * Created by Josh on 2/27/2016.
 */
public abstract class TakeableItem extends Item {

    private boolean equippable;
    private String name;

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
}
