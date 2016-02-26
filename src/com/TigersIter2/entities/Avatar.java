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


    //testing purpose variables:
    int dx = 4;
    int dy = 4;

    @Override
    public void update() {

        System.out.println(dx + " located: " + location.getX() + " , " + dy + " located at: " + location.getY());
        location.incrementX(dx);
        location.incrementY(dy);
        if( location.getX() >= 1280 - 50 || location.getX() <= 0 )
            dx *= -1;
        if( location.getY() >= 720 - 75 || location.getY() <= 0 )
            dy *= -1;
    }

    public Location getLocation() {
        return location;
    }



    //    public Location getLocation(){
//        return location;
//    }
}
