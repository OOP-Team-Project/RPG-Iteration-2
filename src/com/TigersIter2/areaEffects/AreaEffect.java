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
    
    public Entity entity;

    public AreaEffect(){

        // for now, this is same location as Turtle1
        location = new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight-50,0);

        //  to add with a visual
        //pixelLocation = new Location(Math.round(StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth*.75f - 80), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)), 0);
    }

    public void setLocation(Location l){ location = l; }

    public Location getLocation() {
        return location;
    }

    // to be overridden by subclasses
    public abstract void affectEntity(Entity entity);

    public abstract String getEffectName();



}
