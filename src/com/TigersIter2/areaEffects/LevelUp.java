package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;
import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by MyMac on 3/7/16.
 */
public class LevelUp extends AreaEffect{


    public void affectEntity(Entity entity){
        // only an Avatar wil levelUp
        if (entity instanceof Avatar) {
            ((Avatar) entity).getStats().incrementLevel();
            System.out.println("Avatar leveled up!");
        }
    }
    public String getEffectName(){
        return "levelUp";
    }


}
