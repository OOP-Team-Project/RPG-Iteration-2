package com.TigersIter2.entities;

import com.TigersIter2.items.TakeableItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 2/27/2016.
 */
public class Equipment {
    private List<TakeableItem> items;

    public Equipment(){
        items = new ArrayList<TakeableItem>();

    }

    public void addItem(TakeableItem item){
        items.add(item);
    }

    public TakeableItem getItemAtIndex(int i){
        TakeableItem item = items.get(i);
        return item;
    }

    public TakeableItem removeItemAtIndex(int i){
        TakeableItem item = getItemAtIndex(i);
        items.remove(item);
        return item;
    }


    public List<TakeableItem> getItems(){
        return items;
    }

    public void printEquipment(){
        for(TakeableItem i : items)
            System.out.println(i);
        if(items.isEmpty())
            System.out.println("Nothing is equipped!");
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }
}
