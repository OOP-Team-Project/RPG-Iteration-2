package com.TigersIter2.skills;

import com.TigersIter2.stats.Stats;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */
public class BindWounds extends GeneralSkill {

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

    @Override
    public boolean raiseSkill() {
        if ( skillLevel < maxLevel ) {
            skillLevel++;
            update();
            return true;
        } else return false;
    }

    @Override
    public void setSkill( int level ) {
        skillLevel = level;
        update();
    }


    /**
     * update function to update the stats of the skill
     */
    private void update() {
        probability = .2 * skillLevel;
        derivedHealed = BASE_HEAL + (skillLevel - 1) * LEVEL_MULTIPLIER;
    }

    @Override
    public boolean activate() {
        if ( skillLevel > 0 && getNewProbability() < probability ) {
            playerStats.increaseCurrentLife(derivedHealed);
            return true;
        } else return false;
    }

    private double getNewProbability() {
        return Math.random();
    }
}
