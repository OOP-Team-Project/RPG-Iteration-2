package com.TigersIter2.entities;

import com.TigersIter2.location.Location;


public class Avatar extends Entity{

    private Location location;
    //private Inventory inventory;
    //private Equipment equipment;
    //private Stats stats;
    private Occupation occupation;


    public Avatar(){
        //changed this to actually instantiate location. Not sure what Z is for atm. <-- Z is for hextile stuff in the future (SL)
        location = new Location(100,100,0);
    }

    public void setLocation(Location l) {
        location = l;
    }


    //testing purpose variables:

    @Override
    public void update(int xMovement, int yMovement) {

        //System.out.println(dx + " located: " + location.getX() + " , " + dy + " located at: " + location.getY());
        location.incrementX(xMovement);
        location.incrementY(yMovement);
        System.out.println(xMovement + ", " + yMovement);
        //location.incrementX(dx);
        //location.incrementY(dy);
        //if( location.getX() >= 1280 - 50 || location.getX() <= 0 )
            //dx *= -1;
        //if( location.getY() >= 720 - 75 || location.getY() <= 0 )
            //dy *= -1;
    }

    public Location getLocation() {
        return location;
    }



    //    public Location getLocation(){
//        return location;
//    }
}
