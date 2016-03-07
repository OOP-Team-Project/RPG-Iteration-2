package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.location.Location;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */
public abstract class AreaEffect {

    private Location location;
    public StatsModifier statsMod;

    public void setLocation(Location l){ location = l; }

    public Location getLocation() {
        return location;
    }

    public abstract StatsModifier affectEntity();

    public abstract String getEffectName();



}
