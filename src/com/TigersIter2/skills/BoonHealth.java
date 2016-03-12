package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Josh on 3/12/2016.
 */
public class BoonHealth extends Boon{

    public BoonHealth(PlayerStats playerstats) {
        super(playerstats);
        boonSelect = 2;
    }

    public String toString() {
        return "BoonHealth";
    }
}
