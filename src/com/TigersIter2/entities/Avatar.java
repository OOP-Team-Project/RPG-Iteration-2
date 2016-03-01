package com.TigersIter2.entities;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.location.Location;


public class Avatar extends Entity{

    private Location location;  //This is the location used by MODELS to determine where the avatar is
    private Location pixelLocation; //This is the location used by VIEWS to determine where the avatar is (Miles)
    private Inventory inventory;
    private Equipment equipment;
    private Occupation occupation;
    private Pet pet;
    private Vehicle vehicle;
    //private Stats stats;

    private int direction;
    private boolean canPassWater;
    private boolean canPassMountain;

    private boolean currentlyMoving = false;


    public Avatar(){
        //changed this to actually instantiate location. Not sure what Z is for atm. <-- Z is for hextile stuff in the future (SL)
        location = new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight,0);
        pixelLocation = new Location(StaticVar.gameWidth/2 - 100, StaticVar.gameHeight/2 - 100, 0);
        direction = 270;
        canPassMountain = false;
        canPassWater = false;
        inventory = new Inventory();
        equipment = new Equipment();
    }

    public void setLocation(Location l) {
        location = l;
    }

    @Override
    public void update(int xMovement, int yMovement) {
        if(xMovement == 0 && yMovement == 0){
            currentlyMoving = false;
        }
        else{
            location.incrementX(xMovement * 3);   //Made it 3 times faster because IT WAS SO SLOOOOOW (Miles)
            location.incrementY(yMovement * 3);
            changeDirection(xMovement, yMovement);
            currentlyMoving = true;
        }
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

    public void setVehicle(Vehicle v){
        vehicle = v;
        canPassWater = v.getCanPassWater();
        canPassMountain = v.getCanPassMountain();
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public void setPet(Pet p){
        pet = p;
    }

    public Pet getPet(){
        return pet;
    }

    public int getDirection(){
        return direction;
    }

    public boolean getCanPassWater(){
        return canPassWater;
    }

    public boolean getCanPassMountain(){
        return canPassMountain;
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

    public boolean isCurrentlyMoving() {
        return currentlyMoving;
    }

    public Location getPixelLocation() {
        return pixelLocation;
    }

    public void setPixelLocation(Location pixelLocation) {
        this.pixelLocation = pixelLocation;
    }
}
