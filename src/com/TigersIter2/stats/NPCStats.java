package com.TigersIter2.stats;

/**
 * Created by Magic_Buddha on 3/4/2016.
 */
public class NPCStats extends Stats {

    @Override
    public int getArmorRating() {
        return hardiness;
    }


    @Override
    public int getOffensiveRating() {
        return attack;
    }

    @Override
    public int getDefensiveRating() {
        return agility;
    }
}
