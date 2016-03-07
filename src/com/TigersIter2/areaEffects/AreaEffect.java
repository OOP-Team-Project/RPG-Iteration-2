package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.location.Location;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by MyMac on 3/7/16.
 */
public abstract class AreaEffect {

    private Location location;
    private StatsModifier statsMod;

    public void setLocation(Location l){ location = l; }

    public Location getLocation() {
        return location;
    }

    public abstract void affectEntity(Entity entity);

    public abstract String getEffectName();


}
