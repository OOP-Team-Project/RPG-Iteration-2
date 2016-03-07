package com.TigersIter2.managers;

import com.TigersIter2.areaEffects.AreaEffect;
import com.TigersIter2.areaEffects.InstantDeath;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */
public class AreaEffectManager {

    private Entity entity;
    private AreaEffect areaEffect;
    private StatsModifier statsMod;
    private InstantDeath instantDeath;


    public void getStatsModifier(){

        switch(getAreaEffect()) {

            case "instantDeath": statsMod = instantDeath.affectEntity();

                // add other cases for other area effects
        }

    }

    public String getAreaEffect(){
        return areaEffect.getEffectName();
    }


}
