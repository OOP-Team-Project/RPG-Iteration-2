package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */


public class InstantDeath extends AreaEffect{

    public void affectEntity(Entity entity){
        //  decreases current health by the amount of health entity currently has
        entity.getStats().decreaseCurrentLife(entity.getStats().getCurrentLife());
        System.out.println("Entity lost a life!");
    }

    public String getEffectName(){
        return "instantDeath";
    }
}
