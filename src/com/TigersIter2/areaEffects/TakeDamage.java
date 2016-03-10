package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */

public class TakeDamage extends AreaEffect{

    public void affectEntity(Entity entity){
        // the longer entity stands on tile, the lower the health goes until dead
        entity.getStats().decreaseCurrentLife(1);
        System.out.println("Entity took damage!");
    }

    public String getEffectName(){
        return "takeDamage";
    }
}
