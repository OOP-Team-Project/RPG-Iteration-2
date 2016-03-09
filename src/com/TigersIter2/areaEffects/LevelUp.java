package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by MyMac on 3/7/16.
 */
public class LevelUp extends AreaEffect{


    public void affectEntity(Entity entity){
        stats = entity.getStats();
        //stats.incrementLevel();
        // TODO   for when Rokas adds public function to increment level
    }
    public String getEffectName(){
        return "levelUp";
    }


}
