package com.TigersIter2.entities;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.location.Location;
import com.TigersIter2.stats.NPCStats;

import java.util.Iterator;
import java.util.List;


public abstract class NPC extends Entity{

    private Location location;  //This is the location used by MODELS to determine where the avatar is
    private Inventory inventory;
    private Equipment equipment;
    private NPCStats stats;

    private int direction;
    private boolean canPassWater;
    private boolean canPassMountain;
    private int timeUntilAttack = 0;

    private boolean currentlyMoving = false;

    protected List<String> responses;
    protected boolean willTrade;
    protected boolean willTalk;
    protected boolean willAttack;
    protected boolean isVillager;
    private boolean canAttack = true;
    private boolean onTileWithAvatar = false;
    private boolean isAttacking = false;


    public NPC(){
        location = new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight);
        direction = 270;
        canPassMountain = false; //if anything this should be under skills (Sam)
        canPassWater = false;
        inventory = new Inventory();
        equipment = new Equipment();
        stats = new NPCStats();
        stats.setLife(100);
        stats.setCurrentLife(100);
        stats.setAttackTime(StaticVar.fps);
    }

    //What is this supposed to do? -Sam
    public void setLocation(Location l) {
        location = l;
    }

    public Inventory getInventory(){
        return inventory;
    }

    //Should be named updatePosition -Sam
    @Override
    public void update(int xMovement, int yMovement, long elapsed) {
        if(xMovement == 0 && yMovement == 0){
            currentlyMoving = false;
        }
        else{
            location.incrementX(Math.round(xMovement * elapsed * StaticVar.entitySpeed * stats.getMovement()));
            location.incrementY(Math.round(yMovement * elapsed * StaticVar.entitySpeed * stats.getMovement()));

            if(yMovement > 0)
                yMovement = 1;
            else if(yMovement < 0)
                yMovement = -1;

            xMovement /= 26;

            changeDirection(xMovement, yMovement);
            currentlyMoving = true;
        }
    }

    public void equipItemAtIndex(int i){
        if(inventory.getItemAtIndex(i).isEquippable())
            equipment.addItem(inventory.removeItemAtIndex(i));
        else
            System.out.println("That item isn't equippable!");
    }

    public void dropItems(){
        Iterator<TakeableItem> iter = inventory.getItems().iterator();
        while (iter.hasNext()) {
            TakeableItem item = iter.next();
            int xLoc = location.getX();
            int yLoc = location.getY()+100;
            item.setLocation(new Location(xLoc, yLoc));
            item.setDisplay(true);
            iter.remove();
        }

        iter = equipment.getItems().iterator();
        while (iter.hasNext()) {
            TakeableItem item = iter.next();
            int xLoc = location.getX();
            int yLoc = location.getY()+100;
            item.setLocation(new Location(xLoc, yLoc));
            item.setDisplay(true);
            iter.remove();
        }
    }

    public Equipment getEquipment(){
        return equipment;
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

    public void changeDirection(int x, int y){
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

    public void setCurrentlyMoving(boolean b){
        currentlyMoving = b;
    }

    public boolean getIsAttacking(){
        return isAttacking;
    }

    public void setIsAttacking(boolean b){
        isAttacking = b;
    }

    public boolean willTalk(){
        return willTalk;
    }

    public boolean willTrade(){
        return willTrade;
    }

    public boolean willAttack(){ return willAttack;}

    //TODO: null pointer here
    public String getResponse(int i){
        return responses.get(i);
    }

    public NPCStats getStats(){
        return stats;
    }

    public boolean isAlive(){
        if(stats.getCurrentLife() <= 0)
            return false;
        else
            return true;
    }

    public boolean isVillager(){
        return isVillager;
    }

    public boolean getCanAttack(){
        return canAttack;
    }

    public void setCanAttack(boolean b){
        canAttack = b;
    }

    public void setOnTileWithAvatar(boolean b){
        onTileWithAvatar = b;
    }

    public boolean getOnTileWithAvatar(){
        return onTileWithAvatar;
    }

    public void setWillAttack(boolean b){
        willAttack = b;
    }

    public int getTimeUntilAttack(){
        return timeUntilAttack;
    }

    public void decrementTimeUntilAttack(){
        if(timeUntilAttack > 0)
            --timeUntilAttack;
    }

    public void resetTimeUntilAttack(){
        timeUntilAttack = stats.getAttackTime();
    }
}
