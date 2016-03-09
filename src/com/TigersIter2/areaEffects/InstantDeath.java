package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */


public class InstantDeath extends AreaEffect{

    public void affectEntity(Entity entity){
        stats = entity.getStats();
        stats.decrementLivesLeft();  //  to decrement 1 life
        entity.setStats(stats);
    }

    public String getEffectName(){
        return "instantDeath";
    }
}
