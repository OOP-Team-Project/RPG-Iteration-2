package com.TigersIter2.skills;

/**
 * Created by Magic_Buddha on 3/4/2016.
 */
public class Observation extends GeneralSkill {

    private double probability;
    private double accuracy;

    public Observation() {
        probability = 0.0;
        accuracy = 0.0;
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
        accuracy = .15 * skillLevel;
    }

    @Override
    public boolean activate() {
        if ( getNewProbability() < probability ) {
            //TODO:implement revealing enemy stats...also depends on distance. wtf.
            return true;
        } else return false;
    }

    private double getNewProbability() {
        return Math.random();
    }
}
