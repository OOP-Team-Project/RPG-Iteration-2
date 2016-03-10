package com.TigersIter2.skills;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public class PickPocket extends ActiveSkill {
    private double probability;

    public PickPocket() {
        super();
        probability = 0.0;
    }


    @Override
    protected void update() {
        probability = .3 + .1 * skillLevel;
    }

    /**
     * returns the probability of success
     */
    public double getProbability() {
        return probability;
    }

    public String toString() {
        return "PickPocket";
    }
}
