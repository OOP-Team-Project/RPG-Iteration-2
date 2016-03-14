package com.TigersIter2.managers;

import java.util.*;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.items.*;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.entities.Inventory;

/**
 * Created by Breanna on 3/7/16.
 *
 * Handles interactions between the Avatar and Items on the map
 */
public class ItemManager {
    private Avatar avatar;
    private Inventory avatarInventory;
    private PlayerStats playerStats;
    private List<Item> itemList;

    public ItemManager(Avatar avatar) {
        this.avatar = avatar;
        avatarInventory = avatar.getInventory();
        playerStats = avatar.getPlayerStats();
        itemList = new ArrayList<Item>(avatar.getInventory().getItems());
    }

    public void addItem(Item item) {
        itemList.add(item);
        item.setDisplay(true);
    }

    public List<Item> getItemList(){
        return itemList;
    }

    /*checks the tile player is moving to to see if there is an item on it
     *Handles the interaction based off the item type, returns false if item is
     *an obstacle*/
    public boolean checkTile(long elapsed, int xMov, int yMov) {
        Iterator<Item> iter = itemList.iterator();
        Location nextLocation = new Location(0, 0, 0);
        nextLocation.setX(avatar.getLocation().getX());
        nextLocation.setY(avatar.getLocation().getY());
        nextLocation.incrementX(Math.round(xMov * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        nextLocation.incrementY(Math.round(yMov * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        while(iter.hasNext()) {
            Item item = iter.next();
            if(item.getDisplay()) {
                if (LocationConverter.PixelLocationToHex(item.getLocation()).getX() == LocationConverter.PixelLocationToHex(nextLocation).getX() &&
                        LocationConverter.PixelLocationToHex(item.getLocation()).getY() == LocationConverter.PixelLocationToHex(nextLocation).getY()) {

                    if (item instanceof TakeableItem) {
                        avatarInventory.addItem((TakeableItem) item);
                        item.setDisplay(false);
                        return true;
                    } else if (item instanceof OneShot) {
                        playerStats.addStatModifier(((OneShot) item).getStatsModifier());
                        item.setDisplay(false);
                        return true;
                    } else if (item instanceof Interactive) {
                        List<TakeableItem> avatarInventoryItems = avatarInventory.getItems();
                    /* Iterates through the player's inventory to see if it holds the corresponding Key */
                        for (TakeableItem i : avatarInventoryItems) {
                            if (i instanceof Key) {
                                if (!((Interactive) item).getInteractedWith()) { //keys match!
                                    if (((Key) i).getItemCode() == ((Interactive) item).getItemCode()) {
                                        ((Interactive) item).setInteractedWith(true);
                                        return true;
                                    }
                                }
                            }
                        }
                        if(!((Interactive) item).getInteractedWith())
                            return false;
                    } else if (item instanceof Obstacle) { //item is an obstacle
                        System.out.println("obstacle encountered");
                        return false;
                    }
                }
            }

        }

        return true;
    }
}
