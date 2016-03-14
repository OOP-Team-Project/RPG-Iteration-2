package com.TigersIter2.managers;

import java.util.*;

import com.TigersIter2.assets.FileReader;
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
        itemList = new ArrayList<Item>();
        //avatar.getInventory().getItems();
        loadItems();
    }

    private void loadItems(){
        System.out.println("ItemManager loading in....");
        String[] itemString = FileReader.fileToString(StaticVar.itemManagerFile).split("\\s+"); //splits up file on any white space
        if (itemString[0].equals("inventory:")) {
            System.out.println("Inventory loading in...");
        }
        int tracker = 1; //just read in at 0
        while (!itemString[tracker].equals("null")) {

            System.out.println("Tracker: " + tracker + ", String: " + itemString[tracker]);
            System.out.println("Tracker: " + (tracker + 1) + ", String: " + itemString[tracker + 1]);
            System.out.println("Tracker: " + (tracker + 2) + ", String: " + itemString[tracker + 2]);
            switch (FileReader.stringToInt(itemString[tracker])) {
                case StaticVar.armorItemType:
                    TakeableItem armor = new Armor(itemString[tracker + 1], FileReader.stringToInt(itemString[tracker + 2]), FileReader.stringToInt(itemString[tracker + 3]));
                    //avatar.getInventory().addItem(new Armor(itemString[tracker + 1], FileReader.stringToInt(itemString[tracker + 2]), FileReader.stringToInt(avatarString[tracker + 3])));
                    tracker += 3;
                    this.addItem(armor);
                    avatar.getInventory().addItem(armor);
                    System.out.println("ArmorType loaded in...");
                    break;
                case StaticVar.weaponItemType:
                    tracker++;
                    if (itemString[tracker].equals("OneHanded")){
                        TakeableItem weapon = new OneHandedWeaponItem(itemString[tracker+1]);
                        this.addItem(weapon);
                        avatar.getInventory().addItem(weapon);
                        tracker+=1;
                        System.out.println("OneHandedWeapon loaded in...");
                    }
                    else if (itemString[tracker].equals("TwoHanded")){
                        TakeableItem weapon = new TwoHandedWeaponItem(itemString[tracker+1], FileReader.stringToInt(itemString[tracker+2]));
                        this.addItem(weapon);
                        avatar.getInventory().addItem(weapon);
                        tracker+=2;
                        System.out.println("TwoHandedWeapon loaded in...");
                    }
                    else if (itemString[tracker].equals("Ranged")){
                        TakeableItem weapon = new RangedWeaponItem(itemString[tracker+1], FileReader.stringToInt(itemString[tracker+2]), FileReader.stringToInt(itemString[tracker+3]), FileReader.stringToInt(itemString[tracker+4]));
                        this.addItem(weapon);
                        avatar.getInventory().addItem(weapon);
                        System.out.println("RangedWeapon loaded in...");
                        tracker+=4;
                    }
                    else if (itemString[tracker].equals("Brawling")){
                        TakeableItem weapon = new BrawlingWeaponItem(itemString[tracker+1]);
                        this.addItem(weapon);
                        avatar.getInventory().addItem(weapon);
                        tracker+=1;
                        System.out.println("BrawlingWeapon loaded in...");
                    }
                    //System.out.println("WeaponType loaded in...");

                    break;
                case StaticVar.keyItemType:
                    TakeableItem key = new Key(itemString[tracker + 1], FileReader.stringToInt(itemString[tracker + 2]));
                    //avatar.getInventory().addItem(new Key(itemString[tracker + 1], FileReader.stringToInt(itemString[tracker + 2])));
                    tracker += 2;
                    this.addItem(key);
                    avatar.getInventory().addItem(key);
                    System.out.println("keyItemType loaded in...");
                    break;
                case StaticVar.potionItemType:
                    TakeableItem potion = new Potion(itemString[tracker + 1], FileReader.stringToInt(itemString[tracker + 2]));
                    //avatar.getInventory().addItem(new Potion(itemString[tracker + 1], FileReader.stringToInt(item[tracker + 2])));
                    tracker += 2;
                    this.addItem(potion);
                    avatar.getInventory().addItem(potion);
                    System.out.println("potionItemType loaded in...");
                    break;

            }
            tracker++;

        }
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
