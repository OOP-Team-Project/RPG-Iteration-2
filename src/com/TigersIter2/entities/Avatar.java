package com.TigersIter2.entities;

import com.TigersIter2.location.Location;


public class Avatar extends Entity{

    private Location location;
    //private Inventory inventory;
    //private Equipment equipment;
    //private Stats stats;
    private Occupation occupation;

    private int direction;


    public Avatar(){
        //changed this to actually instantiate location. Not sure what Z is for atm. <-- Z is for hextile stuff in the future (SL)
        location = new Location(100,100,0);
        direction = 270;
    }

    public void setLocation(Location l) {
        location = l;
    }

    @Override
    public void update(int xMovement, int yMovement) {
        location.incrementX(xMovement);
        location.incrementY(yMovement);
        changeDirection(xMovement, yMovement);
        //System.out.println(direction);
        //System.out.println(xMovement + ", " + yMovement);
    }

    public int getDirection(){
        return direction;
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
}
