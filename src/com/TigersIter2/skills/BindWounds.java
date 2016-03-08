package com.TigersIter2.skills;

import com.TigersIter2.stats.Stats;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */
public class BindWounds extends ActiveSkill {

    private final int BASE_HEAL = 10;
    private final int LEVEL_MULTIPLIER = 10;
    private int derivedHealed;
    private double probability;
    private Stats playerStats;

    /**
     * Constructor, will need some sort of handle to stats
     */
    public BindWounds(Stats playerStats){
        super();
        derivedHealed = BASE_HEAL;
        probability = 0.0;
    }

    /**
     * update function to update the stats of the skill
     */
    @Override
    protected void update() {
        probability = .2 * skillLevel;
        derivedHealed = BASE_HEAL + (skillLevel - 1) * LEVEL_MULTIPLIER;
    }

    private boolean activate() {
        if ( skillLevel > 0 && Math.random() < probability ) {
            playerStats.increaseCurrentLife(derivedHealed);
            return true;
        } else return false;
    }

    /**
     * gets called when observable object notifies this object
     * */
    @Override
    public void update(Observable observable, Object obj) {
        activate();
    }
}
