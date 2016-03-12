package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Josh on 3/12/2016.
 */
public class EnchantingBlast extends Enchantment{

    public EnchantingBlast(PlayerStats playerstats) {
        super(playerstats);
        influenceRadiusType = "angular";
    }

    public String toString() {
        return "EnchantingShot";
    }
}
