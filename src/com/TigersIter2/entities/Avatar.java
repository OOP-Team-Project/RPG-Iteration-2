package com.TigersIter2.entities;

import com.TigersIter2.assets.items.Armor;
import com.TigersIter2.assets.items.Potion;
import com.TigersIter2.assets.items.TakeableItem;
import com.TigersIter2.location.Location;


public class Avatar extends Entity{

    private Location location;
    private Inventory inventory;
    private Equipment equipment;
    //private Stats stats;
    private Occupation occupation;

    private int direction;


    public Avatar(){
        //changed this to actually instantiate location. Not sure what Z is for atm. <-- Z is for hextile stuff in the future (SL)
        location = new Location(100,100,0);
        direction = 270;
        inventory = new Inventory();
        equipment = new Equipment();
    }

    public void setLocation(Location l) {
        location = l;
    }

    @Override
    public void update(int xMovement, int yMovement) {
        location.incrementX(xMovement);
        location.incrementY(yMovement);
        changeDirection(xMovement, yMovement);
        //System.out.println(direction);
        //System.out.println(xMovement + ", " + yMovement);
    }

    public void equipItemAtIndex(int i){
        if(inventory.getItemAtIndex(i).isEquippable())
            equipment.addItem(inventory.removeItemAtIndex(i));
        else
            System.out.println("That item isn't equippable!");
    }

    public void unequipItemAtIndex(int i){
        if(!equipment.isEmpty())
            inventory.addItem(equipment.removeItemAtIndex(i));
        else
            System.out.println("There is nothing to unequip!");
    }

    public void dropItemAtIndex(int i){
        TakeableItem item = inventory.removeItemAtIndex(i);

        //Do something here to put item on the current tile
    }

    public int getDirection(){
        return direction;
    }

    public Location getLocation() {
        return location;
    }

    public void setOccupation(Occupation o){
        occupation = o;
    }

    public Occupation getOccupation(){
        return occupation;
    }

    private void changeDirection(int x, int y){
        if(x == 0){
            if(y == 1)
                direction = 270;
            else if(y == -1)
                direction = 90;
        }
        else if(x == 1){
            if(y == 1)
                direction = 315;
            else if(y == -1)
                direction = 45;
        }
        else{
            if(y == 1)
                direction = 225;
            else if(y == -1)
                direction = 135;
        }
    }
}
