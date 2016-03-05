package com.TigersIter2.skills;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/4/2016.
 */
public class Observation extends ActiveSkill {

    private double probability;
    private double accuracy;

    public Observation() {
        probability = 0.0;
        accuracy = 0.0;
    }

    /**
     * update function to update the stats of the skill
     */
    @Override
    protected void update() {
        probability = .2 * skillLevel;
        accuracy = .15 * skillLevel;
    }


    private boolean activate() {
        if ( Math.random() < probability ) {
            //TODO:implement revealing enemy stats...also depends on distance. wtf.
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
