package com.TigersIter2.managers;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;

/**
 * Created by slichtenheld on 3/7/2016.
 */

//I took a leaf out of Josh's book, when thinking about it I think this better describes whats going on since
    //avatar's position needs to be updated here
public class AvatarMapInteract {

    //needs a handle to the map (or MapManager in the future if there are multiple levels/dungeons etc)
        //MapManager will also be necessary for things like boats and rockclimbing gear etc
    private Avatar avatar;
    private TerrainMap map;
    //needs a handle to controller because it needs to check where the avatar will go to see if it is allowable
    //private Controller controller;

    public AvatarMapInteract(Avatar avatar, TerrainMap map){
        //pass handles
        this.avatar = avatar;
        this.map = map;
        //this.controller = controller;
    }

    public boolean updateAvatarPos(long elapsed, int xMov, int yMov){

        //Location nextLocation = new Location(avatar.getLocation());
        Location nextLocation = new Location(0, 0);
        nextLocation.setX(avatar.getLocation().getX());
        nextLocation.setY(avatar.getLocation().getY());

        //TONS of things are considered when incrementing location, not just xMov and yMov
        //Copied the next lines pretty much straight out of Avatar
        //Works now
        //In checkPassable(), can't just check grass
        //Vehicles can give avatars ability to cross over other terrains
        //Needs to be more robust
        nextLocation.incrementX(Math.round(xMov * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        nextLocation.incrementY(Math.round(yMov * elapsed * StaticVar.entitySpeed*avatar.getStats().getMovement()));
        //if next location is not on a different tile, update avatar position
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
