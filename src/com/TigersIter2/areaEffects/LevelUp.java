package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;

/**
 * Created by MyMac on 3/7/16.
 */
public class LevelUp extends AreaEffect{


    private boolean used = false;

    public LevelUp(){
        areaEffectType = 2;
    }

    public void affectEntity(Entity entity){
        // only an Avatar wil levelUp
        if ((entity instanceof Avatar) && !used) {
            ((Avatar) entity).getStats().incrementLevel();
            used = true;
            display = false;
        }
    }
    public String getEffectName(){
        return "levelUp";
    }


}
