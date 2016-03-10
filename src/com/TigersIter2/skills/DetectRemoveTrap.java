package com.TigersIter2.skills;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "the higher this skill the more likely the avatar is to notice a
 * trap; once the trap is detected, she may attempt to remove
 * it — the higher her skill the more likely she is to succeed."
 */
public class DetectRemoveTrap extends ActiveSkill {

    /**
     * derived stat of the skill
     */
    private double probability;

    public DetectRemoveTrap() {
        super();
        probability = 0.0;
    }

    /**
     * executed each time the skill levels up
     */
    @Override
    protected void update() {
        probability = .4 + .1 * skillLevel;
    }

    /**
     * returns the probability ot successful detection or removal
     */
    public double getProbability() {
        return probability;
    }

    public String toString() {
        return "DetectRemoveTrap";
    }
}
