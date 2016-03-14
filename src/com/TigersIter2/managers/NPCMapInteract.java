package com.TigersIter2.managers;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.NPC;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;

import java.util.Random;

/**
 * Created by Nicole on 3/13/16.
 */
public class NPCMapInteract {

    private NPC npc;
    private TerrainMap map;
    private int direction;
    private int xMov;
    private int yMov;

    public NPCMapInteract(NPC npc, TerrainMap map){
        this.npc = npc;
        this.map = map;
        direction = npc.getDirection();
        // this initiates xMov and yMov
        convertDegreesToCoord(direction);
    }

    public void updateNPCPos(long elapsed){

        // if next is passable, continue in same direction
        int terrain = map.getTerrainType(LocationConverter.PixelLocationToHex(getNextLocation(elapsed)));
        if (terrain == 2 || terrain == 3){
            int newDir = (npc.getDirection() + 180) % 360;
            int val1 = 45;
            int val2 = -45;
            int x1 = LocationConverter.PixelLocationToHex(npc.getLocation()).getX();
            int x2 = LocationConverter.PixelLocationToHex(getNextLocation(elapsed)).getX();

            if((newDir == 270 && x1 > x2) || (newDir == 90 && x1 < x2)){
                val1 = -45;
                val2 = -90;
            }
            else if((newDir == 270 && x1 < x2) || (newDir == 90 && x1 > x2)){
                val1 = 45;
                val2 = 90;
            }
            else if((newDir == 135 && x1 == x2) || (newDir == 315 && x1 == x2)){
                val1 = -45;
                val2 = -45;
            }
            else if((newDir == 225 && x1 == x2) || (newDir == 45 && x1 == x2)){
                val1 = 45;
                val2 = 45;
            }
            else if(x1 == x2 && (newDir == 90 || newDir == 270)){
                val1 = 45;
                val2 = -45;
            }
            else if((newDir == 45 && x1 < x2) || (newDir == 225 && x1 > x2)){
                val1 = 45;
                val2 = 45;
            }
            else if((newDir == 315 && x1 < x2) || (newDir == 135 && x1 > x2)){
                val1 = -45;
                val2 = -45;
            }



            double rand = Math.random();
            if(rand < 0.33)
                newDir = (newDir + val1) % 360;
            else if(rand < 0.67)
                newDir = (newDir + val2) % 360;



            if(newDir % 180 == 0)     //just make sure we don't try to move horizontal
                newDir = (newDir + 45) % 360;

            convertDegreesToCoord(newDir);
            npc.update(xMov, yMov, elapsed);
        }
        else if(terrain == 1){
            npc.update(xMov, yMov, elapsed);
        }

    }

    public int getxMov(NPC npc){
        //direction = npc.getDirection();
        // this initiates xMov and yMov
        convertDegreesToCoord(direction);
        return xMov;
    }

    public int getyMov(NPC npc){
//        direction = npc.getDirection();
        // this initiates xMov and yMov
        convertDegreesToCoord(direction);
        return yMov;
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

    public int randomDirection(NPC npc){
        int[] directionArray = new int[]{45, 90, 135, 225, 270, 315};
        int rnd = new Random().nextInt(directionArray.length);
        // cannot have same direction as previous bc that direction will be impassable
        while(npc.getDirection() == rnd){
            rnd = new Random().nextInt(directionArray.length);
        }
        return directionArray[rnd];

    }

    public NPC getNpc(){
        return npc;
    }

    public Location getNextLocation(long elapsed){
        Location nextLocation = new Location(0, 0, 0);
        nextLocation.setX(npc.getLocation().getX());
        nextLocation.setY(npc.getLocation().getY());

        nextLocation.incrementX(Math.round(xMov * elapsed * StaticVar.entitySpeed*npc.getStats().getMovement()));
        nextLocation.incrementY(Math.round(yMov * elapsed * StaticVar.entitySpeed*npc.getStats().getMovement()));

        return nextLocation;
    }
}
