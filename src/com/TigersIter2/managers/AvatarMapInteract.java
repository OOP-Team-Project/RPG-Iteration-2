package com.TigersIter2.managers;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.main.Controller;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.maps.terrains.TerrainType;

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

    public void updateAvatarPos(long elapsed, int xMov, int yMov){

        Location nextLocation = new Location(avatar.getLocation());
        nextLocation.incrementX(xMov);
        nextLocation.incrementY(yMov);
        //if next location is not on a different tile, update avatar position
        if (map.checkPassable(LocationConverter.PixelLocationToHex(nextLocation))) avatar.update(xMov,yMov, elapsed);
    }

}
