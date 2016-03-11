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


    private boolean used = false;

    public void affectEntity(Entity entity){
        // only an Avatar wil levelUp
        if (entity.getClass().equals(Avatar.class) && !used) {
            ((Avatar) entity).getStats().incrementLevel();
            used = true;
            display = false;
        }
    }
    public String getEffectName(){
        return "levelUp";
    }


}
