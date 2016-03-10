package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.entities.NPC;
import com.TigersIter2.location.Location;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by MyMac on 3/7/16.
 */

public class HealDamage extends AreaEffect{

    public void affectEntity(Entity entity, Location location){
        entity.getStats().setCurrentLife(entity.getStats().getLife());  // Sets life ot max depending on Avatar's occupation
        System.out.println("Entity healed damage!");
    }

    public String getEffectName(){
            return "healDamage";
    }


}
//TODO add locations to all area affects