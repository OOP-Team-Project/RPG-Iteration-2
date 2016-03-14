package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;

/**
 * Created by MyMac on 3/7/16.
 */

public class HealDamage extends AreaEffect{

    public HealDamage(){
        areaEffectType = 0;
    }

    public void affectEntity(Entity entity){
        // the longer entity stands on tile, the higher the health goes until full
        entity.getStats().increaseCurrentLife(1);
        System.out.println("Entity healed damage!");
    }

    public String getEffectName(){
            return "healDamage";
    }


}
