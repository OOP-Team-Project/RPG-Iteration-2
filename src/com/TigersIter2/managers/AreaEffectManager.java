package com.TigersIter2.managers;

import com.TigersIter2.areaEffects.*;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by Nicole on 3/7/16.
 */
public class AreaEffectManager {

    private Entity entity;
    private AreaEffect areaEffect;
    private Stats stats;
    private InstantDeath instantDeath;
    private TakeDamage takeDamage;
    private HealDamage healDamage;
    private LevelUp levelUp;

    public void getStatsModifier(){

        switch(getAreaEffect()) {

            case "instantDeath": instantDeath.affectEntity(entity);

            case "takeDamage": takeDamage.affectEntity(entity);

            case "healDamage": healDamage.affectEntity(entity);

            case "levelUp": levelUp.affectEntity(entity);
                // add other cases for other area effects
                // is teleport an area affect???
        }

    }

    public String getAreaEffect(){
        return areaEffect.getEffectName();
    }


}
