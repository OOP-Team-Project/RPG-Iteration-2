package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Josh on 3/12/2016.
 */
public class BoonHardiness extends Boon{

    public BoonHardiness(PlayerStats playerstats) {
        super(playerstats);
        boonSelect = 1;
    }

    public String toString() {
        return "BoonHardiness";
    }
}
