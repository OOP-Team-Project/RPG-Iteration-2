package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */

public class TakeDamage extends AreaEffect{

    public void affectEntity(Entity entity){
        stats = entity.getStats();
        stats.decreaseCurrentLife(stats.getCurrentLife()/3);  // decrements health by 1/3
        entity.setStats(stats);
    }

    public String getEffectName(){
        return "takeDamage";
    }
}
