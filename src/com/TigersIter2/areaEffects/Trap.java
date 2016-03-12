package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;

/**
 * Created by MyMac on 3/11/16.
 */
public class Trap extends AreaEffect{

    public Trap(){
        areaEffectType = 5;
    }

    public void affectEntity(Entity entity){
        
    }

    public String getEffectName(){
        return "Trap";
    }
}
