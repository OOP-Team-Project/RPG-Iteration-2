package com.TigersIter2.managers;

import java.util.*;

import com.TigersIter2.entities.Pet;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.NPC;
import com.TigersIter2.items.Item;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.location.Location;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;

/**
 * Created by Breanna on 3/12/16.
 * Handles the pet interactions that cause troubles for the Avatar
 */
public class PetManager {
    private Pet pet;
    private Avatar avatar;
    private ItemManager itemManager;
    private List<Item> itemList;
    private TerrainMap map;
    private int xMov;
    private int yMov;
    private int direction;
    private boolean isFollowing = false;

    public PetManager(Pet pet, ItemManager itemManager, Avatar avatar, TerrainMap map) {
        this.pet = pet;
        direction = pet.getDirection();
        this.convertDegreesToCoord(direction);
        this.itemManager = itemManager;
        this.avatar = avatar;
        this.map = map;
    }

    public void stealItem() {
        itemList = itemManager.getItemList();
        Iterator<Item> iter = itemList.iterator();
        while (iter.hasNext()) {
            Item item = iter.next();
            if (item.getLocation() != null) {
                if (LocationConverter.PixelLocationToHex(item.getLocation()).getX() == LocationConverter.PixelLocationToHex(pet.getLocation()).getX() &&
                        LocationConverter.PixelLocationToHex(item.getLocation()).getY() == LocationConverter.PixelLocationToHex(pet.getLocation()).getY()) {
                    if (item instanceof TakeableItem) {
                        iter.remove();
                        item.setDisplay(false);
                        System.out.println("Your pet stole an item :(");
                    }
                }

            }
        }
    }

    public void startFight(List<NPC> npcs) {
        Iterator<NPC> iter = npcs.iterator();
        while(iter.hasNext()) {
            NPC npc = iter.next();
            if (LocationConverter.PixelLocationToHex(npc.getLocation()).getX() == LocationConverter.PixelLocationToHex(pet.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(npc.getLocation()).getY() == LocationConverter.PixelLocationToHex(pet.getLocation()).getY()) {
                npc.setWillAttack(true);
            }
        }
    }


    public void updatePetPos(int contX, int contY, long elapsed){

        Location nextLocation = new Location(0, 0);
        nextLocation.setX(pet.getLocation().getX());
        nextLocation.setY(pet.getLocation().getY());
        int avatarX = avatar.getLocation().getX();
        int avatarY = avatar.getLocation().getY();
        Location nextAvatarLocation = new Location(avatarX, avatarY);
        double distance = Math.sqrt(Math.pow(avatarX-nextLocation.getX(), 2) + Math.pow(avatarY-nextLocation.getY(), 2));
        distance = Math.floor(distance);
        nextLocation.incrementX(Math.round(contX * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        nextLocation.incrementY(Math.round(contY * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        // if next is passable, continue in same direction
        int terrain = map.getTerrainType(LocationConverter.PixelLocationToHex(nextLocation));

        if(map.getTerrainType(LocationConverter.PixelLocationToHex(nextAvatarLocation)) != 1)
            return;


        if(terrain == 1 && distance <= 120 && !isFollowing) {
                isFollowing = true;
                System.out.println("Following!");
        }
        else if(distance > 120 && distance < 250 && isFollowing){
            if(terrain == 1) {
                pet.update(contX, contY, elapsed);
                System.out.println("Move with avatar");
                System.out.println(distance);
            }
        }
        else if(terrain == 1 && isFollowing){
            //do nothing
        }
        else {
            if(terrain == 1)
                pet.update(xMov, yMov, elapsed);
            else
                randomMovement(nextLocation, elapsed);
            isFollowing = false;
        }
    }

    private void randomMovement(Location nextLoc, long elapsed){
        int newDir = (pet.getDirection() + 180) % 360;
        int val1 = 0;
        int val2 = 0;
        int x1 = LocationConverter.PixelLocationToHex(pet.getLocation()).getX();
        int x2 = LocationConverter.PixelLocationToHex(nextLoc).getX();


        //Helps make sure it doesn't select a new direction also towards impassable tile
        if ((newDir == 270 && x1 > x2) || (newDir == 90 && x1 < x2)) {
            val1 = -45;
            val2 = -90;
        } else if ((newDir == 270 && x1 < x2) || (newDir == 90 && x1 > x2)) {
            val1 = 45;
            val2 = 90;
        } else if ((newDir == 135 && x1 == x2) || (newDir == 315 && x1 == x2)) {
            val1 = -45;
            val2 = -45;
        } else if ((newDir == 225 && x1 == x2) || (newDir == 45 && x1 == x2)) {
            val1 = 45;
            val2 = 45;
        } else if (x1 == x2 && (newDir == 90 || newDir == 270)) {
            val1 = 45;
            val2 = -45;
        } else if ((newDir == 45 && x1 < x2) || (newDir == 225 && x1 > x2)) {
            val1 = 45;
            val2 = 45;
        } else if ((newDir == 315 && x1 < x2) || (newDir == 135 && x1 > x2)) {
            val1 = -45;
            val2 = -45;
        }

        double rand = Math.random();
        if (rand < 0.33)
            newDir = (newDir + val1) % 360;
        else if (rand < 0.67)
            newDir = (newDir + val2) % 360;


        if (newDir % 180 == 0)     //just make sure we don't try to move horizontal
            newDir = (newDir + 45) % 360;

        convertDegreesToCoord(newDir);
        pet.update(xMov, yMov, elapsed);
    }

    public void convertDegreesToCoord(int direction){

        switch(direction){
            case 45: xMov = 26;
                yMov = -15;
                break;
            case 90: xMov = 0;
                yMov = -15;
                break;
            case 135: xMov = -26;
                yMov = -15;
                break;
            case 225: xMov = -26;
                yMov = 15;
                break;
            case 270: xMov = 0;
                yMov = 15;
                break;
            case 315: xMov = 26;
                yMov = 15;
                break;
        }
    }

}
