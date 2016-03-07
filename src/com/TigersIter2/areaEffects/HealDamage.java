package com.TigersIter2.areaEffects;

import com.TigersIter2.stats.Stats;
import com.TigersIter2.stats.StatsModifier;

/**
 * Created by MyMac on 3/7/16.
 */
public class HealDamage extends AreaEffect{


    public Stats affectEntity(){
        stats.setLife(10);  // Random because idk the default Life value;
                            // the defaults should be added in StaticVar eventually
            return stats;
        }
    public String getEffectName(){
            return "healDamage";
    }


}
