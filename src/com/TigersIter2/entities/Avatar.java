package com.TigersIter2.entities;

import com.TigersIter2.location.Location;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class Avatar extends Entity{

    private Location location;


    public Avatar(){
        //changed this to actually instantiate location. Not sure what Z is for atm.
        location = new Location(100,100,0);
    }

    public void setLocation(Location l) {
        location = l;
    }


    @Override
    public void update() {
        location.incrementX(1);
        location.incrementY(1);
    }

    public Location getLocation() {
        return location;
    }



    //    public Location getLocation(){
//        return location;
//    }
}
