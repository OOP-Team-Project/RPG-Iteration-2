package com.TigersIter2.items;

/**
 * Created by Josh on 2/27/2016.
 */
public class Interactive extends Item {
    private boolean interactedWith = false; //whether the item has been interacted with yet
    private int itemCode = 0; //value matches item with corresponding key

    public Interactive(int itemCode) {
        this.itemCode = itemCode;
    }

    public boolean getInteractedWith() { return interactedWith; }

    public int getItemCode() { return itemCode; }

    public void setInteractedWith(boolean interactedWith) { this.interactedWith = interactedWith; }
}
