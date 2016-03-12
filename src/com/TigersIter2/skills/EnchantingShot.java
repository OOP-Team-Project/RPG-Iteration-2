package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Josh on 3/12/2016.
 */
public class EnchantingShot extends Enchantment{

    public EnchantingShot(PlayerStats playerstats) {
        super(playerstats);
        influenceRadiusType = "linear";
    }

    public String toString() {
        return "EnchantingShot";
    }
}
