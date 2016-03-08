package com.TigersIter2.managers;

import java.util.*;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.items.*;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Breanna on 3/7/16.
 *
 * Handles interactions between the Avatar and Items on the map
 */
public class ItemManager {
    private Avatar avatar;
    private PlayerStats playerStats;
    private List<Item> itemList;

    public ItemManager(Avatar avatar) {
        this.avatar = avatar;
        playerStats = avatar.getPlayerStats();
        itemList = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void checkTile() {
        Iterator<Item> iter = itemList.iterator();
        while(iter.hasNext()) {
            Item item = iter.next();
            if(LocationConverter.PixelLocationToHex(item.getLocation()).getX() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(item.getLocation()).getY() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getY()) {
                if (item instanceof TakeableItem) {
                    avatar.getInventory().addItem((TakeableItem) item); //this could be violating TDA... :(
                    // System.out.println("Location of Item: " + item.getLocation().toString());
                }
            }
            iter.remove();
        }
       // System.out.println("Location of avatar: " + avatar.getLocation().toString());
       //System.out.println("THIS IS THE NEW PLAYER STAT FROM ITEM: " + playerStats.getLife());
    }
}
