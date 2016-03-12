package com.TigersIter2.items;
import com.TigersIter2.stats.StatsModifier;
/**
 * Created by Josh on 2/27/2016.
 * Edited by Breanna
 */
public class OneShot extends Item {
    StatsModifier sm;
    //default one-shot item
    public OneShot() {
        this.sm = new StatsModifier();
        sm.setLives(sm.getLives() + 1); //adds a life to the players lives left
        itemType = 4;
    }

    public StatsModifier getStatsModifier() { return sm; }
}
