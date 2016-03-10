package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */

public class TakeDamage extends AreaEffect{

    public void affectEntity(Entity entity){
        entity.getStats().decreaseCurrentLife(5);
        System.out.println("Avatar took damage!");
    }

    public String getEffectName(){
        return "takeDamage";
    }
}
