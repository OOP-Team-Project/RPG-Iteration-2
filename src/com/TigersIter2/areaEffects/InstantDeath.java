package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;

/**
 * Created by Nicole on 3/7/16.
 */


public class InstantDeath extends AreaEffect{

    private boolean used = false;

    public InstantDeath(){
        areaEffectType = 1;
    }

    public void affectEntity(Entity entity){
        //  decreases current health by the amount of health entity currently has
        if(!used) {
            entity.getStats().decreaseCurrentLife(entity.getStats().getCurrentLife());
            used = true;
            display = false;
            System.out.println("Entity lost a life!");
            if (entity.getClass().equals(Avatar.class)){
                ((Avatar) entity).getStats().decrementLivesLeft();
            }
        }
    }

    public String getEffectName(){
        return "instantDeath";
    }
}
