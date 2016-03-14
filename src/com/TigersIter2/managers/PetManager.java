package com.TigersIter2.managers;

import java.util.*;

import com.TigersIter2.entities.Pet;
import com.TigersIter2.entities.Avatar;
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
        int index = 0;
        System.out.println("Pet's location: " + LocationConverter.PixelLocationToHex(pet.getLocation()).toString());
        while (iter.hasNext()) {
            Item item = iter.next();
            System.out.println("Item's location: " + LocationConverter.PixelLocationToHex(item.getLocation()).toString());
            if (LocationConverter.PixelLocationToHex(item.getLocation()).getX() == LocationConverter.PixelLocationToHex(pet.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(item.getLocation()).getY() == LocationConverter.PixelLocationToHex(pet.getLocation()).getY()) {
                if (item instanceof TakeableItem) {
                    iter.remove();
                    //itemManager.removeItem(index);

                    item.setDisplay(false);
                    System.out.println("Your pet stole an item :(");
                }
            }
            index++;
        }
    }


    public void updatePetPos(int contX, int contY, long elapsed){

            Location nextLocation = new Location(0, 0, 0);
            nextLocation.setX(pet.getLocation().getX());
            nextLocation.setY(pet.getLocation().getY());
            nextLocation.incrementX(Math.round(contX * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
            nextLocation.incrementY(Math.round(contY * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
            // if next is passable, continue in same direction
            int terrain = map.getTerrainType(LocationConverter.PixelLocationToHex(nextLocation));

            int avatarX = avatar.getLocation().getX();
            int avatarY = avatar.getLocation().getY();
            float angle = getAngleBetween(avatar.getLocation(), pet.getLocation());

            double distance = Math.sqrt(Math.pow(avatarX-nextLocation.getX(), 2) + Math.pow(avatarY-nextLocation.getY(), 2));
            //System.out.println(distance);
          /*  if(terrain == 1) {
                convertDegreesToCoord(pet.getDirection());
                pet.update(xMov, yMov, elapsed);
            }
            else{
                if(distance < 500) {
                    convertDegreesToCoord((int)angle);
                }
                else {
                    convertDegreesToCoord(randomDirection());
                }
                pet.changeDirection(xMov, yMov);

            }*/
            if(terrain == 1) {
                pet.update(contX, contY, elapsed);
            }
            else {
                convertDegreesToCoord(randomDirection());
                nextLocation.incrementX(Math.round(xMov * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));
                nextLocation.incrementY(Math.round(yMov * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));
                terrain = map.getTerrainType(LocationConverter.PixelLocationToHex(nextLocation));
                if(terrain == 1) {
                    pet.update(xMov, yMov, elapsed);
                }
            }



        }

    public void convertDegreesToCoord(int direction){

        switch(direction){
            case 45: xMov = 1;
                yMov = -1;
                break;
            case 90: xMov = 0;
                yMov = -1;
                break;
            case 135: xMov = -1;
                yMov = -1;
                break;
            case 225: xMov = -1;
                yMov = 1;
                break;
            case 270: xMov = 0;
                yMov = 1;
                break;
            case 315: xMov = 1;
                yMov = 1;
                break;
        }
    }

        public int randomDirection(){
            int[] directionArray = new int[]{45, 90, 135, 225, 270, 315};
            int rnd = new Random().nextInt(directionArray.length);
            // cannot have same direction as previous bc that direction will be impassable
            while(pet.getDirection() == rnd){
                rnd = new Random().nextInt(directionArray.length);
            }
            return directionArray[rnd];
        }

        public float getAngleBetween(Location a, Location b) {

            float angle = (float)Math.toDegrees(Math.atan2((double)a.getX()-b.getX(), (double)a.getY()-b.getY()));
            if (angle < 0) {
                angle += 360;
            }
            int[] directionArray = new int[]{45, 90, 135, 225, 270, 315};
            int smallestDistance = 1000;
            int directionAngle = 45;
            for(int i = 0; i < directionArray.length; i++) {
                float distance = Math.abs(angle - directionArray[i]);
                if(distance < smallestDistance) {
                    smallestDistance = (int)distance;
                    directionAngle = directionArray[i];
                }
            }
            return directionAngle;
        }

}
