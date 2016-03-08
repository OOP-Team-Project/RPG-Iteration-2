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
        for(Item item : itemList) {
            if(LocationConverter.PixelLocationToHex(item.getLocation()).getX() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(item.getLocation()).getY() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getY())
            {
                if(item instanceof Potion) {
                    StatsModifier sm = ((Potion) item).getStatsModifier();
                    playerStats.addStatModifier(sm);
                }
            }
        }
        System.out.println("THIS IS THE NEW PLAYER STAT FROM ITEM: " + playerStats.getLife());
    }
}
