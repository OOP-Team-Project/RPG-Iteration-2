package com.TigersIter2.items;

import com.TigersIter2.location.Location;

/**
 * Created by Josh on 2/27/2016.
 * Edited Breanna
 */
public class Item {
    private Location location;
    protected int itemType;       //to differentiate between keys, potions, obstacles, weapons, armor, interactive, and oneShot
    private boolean display = false;

    public Item() {
        location = new Location(0,0,0);
    }

    public void setLocation(Location l){
       /*int x = ((location.getX()+50)/100)*100;
       int y = ((location.getY()+50)/100)*100;
       this.location = new Location(x, y, 0);*/ //What was this stuff? Not sure, so commented it out. Sorry! (Miles)
        location.setX(l.getX());
        location.setY(l.getY());
    }

    public Location getLocation() { return location; }

    public int getItemType(){
        return itemType;
    }

    public boolean getDisplay(){
        return display;
    }

    public void setDisplay(boolean b){
        display = b;
    }
}
