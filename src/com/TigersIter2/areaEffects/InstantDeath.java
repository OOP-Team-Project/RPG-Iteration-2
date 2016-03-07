package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */


public class InstantDeath extends AreaEffect{

    public Stats affectEntity(){
        int life = stats.getLife()-1;  // decrement lives by 1
        stats.setCurrentLife(life);  // this needs to increment health
        return stats;
    }

    public String getEffectName(){
        return "instantDeath";
    }
}
