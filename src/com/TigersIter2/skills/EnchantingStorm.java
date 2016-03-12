package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Josh on 3/12/2016.
 */
public class EnchantingStorm extends Enchantment{

    public EnchantingStorm(PlayerStats playerstats) {
        super(playerstats);
        influenceRadiusType = "radial";
    }

    public String toString() {
        return "EnchantingShot";
    }
}
