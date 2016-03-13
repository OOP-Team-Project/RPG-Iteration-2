package com.TigersIter2.entities;


import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;

/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public class Pet extends Entity {
    private String name;
    private Avatar avatar; //needs a handle to avatar for location
    private Location location;
    private Location pixelLocation;
    private int direction;

    public Pet(String n, Avatar avatar){
        name = n;
        this.avatar = avatar;
        direction = 270;
        //sets the location of the Pet to be next to the avatar
        int avatarXLocation = avatar.getLocation().getX();
        int avatarYLocation = avatar.getLocation().getY();
        this.location = new Location(avatarXLocation+50, avatarYLocation+130, 0);
        this.pixelLocation = new Location(Math.round(StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth*.75f - 80), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)), 0);

    }

    @Override
    public void update(int xMovement, int yMovement, long elapsed) {
        //made the Pet the same speed as the avatar
        location.incrementX(Math.round(xMovement * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));
        location.incrementY(Math.round(yMovement * elapsed * StaticVar.entitySpeed * avatar.getStats().getMovement()));

    }

    public int getDirection(){
        return direction;
    }

    public Location getPixelLocation() { return pixelLocation; }

    public void setPixelLocation(Location pixelLocation) { this.pixelLocation = pixelLocation; }

    public Location getLocation() { return location; }


    public void changeDirection(int x, int y) {
        if(x == 0){
            if(y > 0)
                direction = 270;
            else if(y < 0)
                direction = 90;
        }
        else if(x > 0){
            if(y > 0)
                direction = 315;
            else if(y < 0)
                direction = 45;
        }
        else{
            if(y > 0)
                direction = 225;
            else if(y < 0)
                direction = 135;
        }
    }

    public boolean canPassTerrain(int xMov, int yMov, long elapsed, TerrainMap map){
        Location nextLocation = new Location(0, 0, 0);
        nextLocation.setX(this.getLocation().getX());
        nextLocation.setY(this.getLocation().getY());
        nextLocation.incrementX(Math.round(xMov * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        nextLocation.incrementY(Math.round(yMov * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        int terrainType = map.getTerrainType(LocationConverter.PixelLocationToHex(nextLocation));
        if(terrainType == 1)
            return true;
        else if(terrainType == 2 && avatar.getCanPassWater())
            return true;
        else if(terrainType == 3 && avatar.getCanPassMountain())
            return true;
        else
            return false;
    }
}
