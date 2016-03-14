package com.TigersIter2.skills;

/**
 * Created by Magic_Buddha on 3/4/2016.
 */

/**
 * "an avatar with a high observation skill will get more and more accurate
 * hints about the abilities/stats of NPCs (e.g., how much damage the last
 * blow did, how much life force is left, immunity to fire, &c.). Note: with
 * a low skill, the information "observed" may be very inaccurate! [General principle:
 * at high skill levels, rather than revealing everything about an NPC, just a couple
 * of random—but useful—things would be accurately observed—though even at very high
 * skill levels, there should always be a small chance of making a mistaken observation!]
 * The accuracy of an observation is affected by distance."
 */

public class Observation extends ActiveSkill {

    private double distanceModifier;
    private double accuracy;
    private double[] accuracyDistance = {0.0, 0.0};


    public Observation() {
        this.distanceModifier = .1;
        this.accuracy = 0.3;
        this.accuracyDistance[0] = accuracy;
        this.accuracyDistance[1] = distanceModifier;
        //this.pni = pni;
    }

    /**
     * update function to update the stats of the skill
     */
    @Override
    protected void update() {
        this.accuracy = .3 + .1 * skillLevel;
        this.distanceModifier = .2 - .03 * skillLevel;
        this.accuracyDistance[0] = accuracy;
        this.accuracyDistance[1] = distanceModifier;
    }

    /**
     * following three methods can be used to get accuracy and distance modifier of the observation skill.
     * choose whatever makes you feel better!
     */
    public double[] getAccuracyDistance() {
        return accuracyDistance;
    }

    public double getAccuracy() { return accuracy; }

    public double getDistanceModifier() { return distanceModifier; }

    public String toString() {
        return "Observation";
    }

}
