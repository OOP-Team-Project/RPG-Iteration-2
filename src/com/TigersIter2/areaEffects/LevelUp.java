package com.TigersIter2.areaEffects;

import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by MyMac on 3/7/16.
 */
public class LevelUp extends AreaEffect{


    public Stats affectEntity(){
        // this needs to level up, but setLevel is in PlayerStats, not Stats, so i'll come back to this
        stats.setLife(-1);
        return stats;
    }
    public String getEffectName(){
        return "levelUp";
    }


}
