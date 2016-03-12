package com.TigersIter2.areaEffects;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.entities.NPC;
import com.TigersIter2.location.Location;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */
public abstract class AreaEffect {

    private Location location;  //This is the location used by MODELS to determine where the areaEffect is
    //private Location pixelLocation;
    protected boolean display = true;
    protected int areaEffectType;
    public Entity entity;

    public AreaEffect(){

        // for now, this is same location as Turtle1
        int x = 10 * StaticVar.terrainImageWidth+100;
        int y = 10 * StaticVar.terrainImageHeight-50;
        x = ((x+50)/100)*100;
        y = ((y+50)/100)*100;
        location = new Location(x, y, 0);
    }

    public void setLocation(Location l){
        int x = ((location.getX()+50)/100)*100;
        int y = ((location.getY()+50)/100)*100;
        location = new Location(x, y, 0);
    }

    public Location getLocation() {
        return location;
    }

    public boolean getDisplay(){
        return display;
    }

    public void setDisplay(boolean val){
        display = val;
    }

    public int getAreaEffectType(){
        return areaEffectType;
    }

    // to be overridden by subclasses
    public abstract void affectEntity(Entity entity);

    public abstract String getEffectName();


}
