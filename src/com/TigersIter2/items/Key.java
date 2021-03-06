package com.TigersIter2.items;

import com.TigersIter2.assets.StaticVar;

/**
 * Created by Josh on 2/27/2016.
 */
public class Key extends TakeableItem {
    private int itemCode = 0; //value matches key to corresponding interactive item

    public Key(String n, int itemCode){
        setName(n);
        setEquippable(false);
        this.itemCode = itemCode;
        itemType = StaticVar.keyItemType;
    }

    public int getItemCode() { return itemCode; }

}
