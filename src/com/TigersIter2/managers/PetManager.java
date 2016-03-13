package com.TigersIter2.managers;

import java.util.*;

import com.TigersIter2.entities.Pet;
import com.TigersIter2.items.Item;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.location.LocationConverter;

/**
 * Created by Breanna on 3/12/16.
 * Handles the pet interactions that cause troubles for the Avatar
 */
public class PetManager {
    private Pet pet;
    private ItemManager itemManager;
    private List<Item> itemList;

    public PetManager(Pet pet, ItemManager itemManager) {
        this.pet = pet;
        this.itemManager = itemManager;
    }

    public void stealItem() {
        itemList = itemManager.getItemList();
        Iterator<Item> iter = itemList.iterator();
        int index = 0;
        while(iter.hasNext()) {
            Item item = iter.next();
            if (LocationConverter.PixelLocationToHex(item.getLocation()).getX() == LocationConverter.PixelLocationToHex(pet.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(item.getLocation()).getY() == LocationConverter.PixelLocationToHex(pet.getLocation()).getY()) {
                if (item instanceof TakeableItem) {
                    iter.remove();
                    itemManager.removeItem(index);

                    item.setDisplay(false);
                    System.out.println("Your pet stole an item :(");
                }
            }
            index++;
        }
    }
}
