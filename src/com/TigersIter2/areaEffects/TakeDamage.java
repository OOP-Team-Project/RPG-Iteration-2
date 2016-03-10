package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */

public class TakeDamage extends AreaEffect{

    public void affectEntity(Entity entity){
        // TODO remove the test print statements here

        stats = entity.getStats();
        System.out.println(stats.getCurrentLife());
        stats.decreaseCurrentLife(50);  // decrements health by 50
        entity.setStats(stats);
        System.out.println(stats.getCurrentLife());
        System.out.println("Avatar took damage");
    }

    public String getEffectName(){
        return "takeDamage";
    }
}
