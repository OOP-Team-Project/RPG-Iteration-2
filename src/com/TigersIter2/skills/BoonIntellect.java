package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Josh on 3/12/2016.
 */
public class BoonIntellect extends Boon{

    public BoonIntellect(PlayerStats playerstats) {
        super(playerstats);
        boonSelect = 0;
    }

    public String toString() {
        return "BoonIntellect";
    }
}
