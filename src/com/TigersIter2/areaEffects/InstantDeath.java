package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */


public class InstantDeath extends AreaEffect{

    public StatsModifier affectEntity(){
        statsMod.setLife(-1);
        return statsMod;
    }
    public String getEffectName(){
        return "instantDeath";
    };
}
