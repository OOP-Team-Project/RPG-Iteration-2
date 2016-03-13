package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Josh on 3/12/2016.
 */
public class FireShot extends Bane{

    public FireShot(PlayerStats playerstats) {
        super(playerstats);
        influenceRadiusType = "linear";
    }

    public String toString() {
        return "FireShot";
    }
}
