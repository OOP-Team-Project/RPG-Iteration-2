package com.TigersIter2.skills;

import com.TigersIter2.stats.Stats;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */

/**
 * A general skill that heals the player.
 * " heals some damage—the amount depends on the skill level."
 */
public class BindWounds extends ActiveSkill {

    /**
     * skill stats modifiers for skill level
     */
    private final int BASE_HEAL = 10;
    private final int LEVEL_MULTIPLIER = 10;

    /**
     * derived stats for the skill
     */
    private int derivedHealed;
    private double probability;

    /**
     * handle to the player stat in order to heal
     */
    private Stats playerStats;


    public BindWounds(Stats playerStats){
        super();
        this.derivedHealed = BASE_HEAL;
        this.probability = 0.0;
        this.playerStats = playerStats;
    }

    /**
     * executed on each level up to update the stats of the skill
     */
    @Override
    protected void update() {
        this.probability = .3 + .1 * skillLevel;
        this.derivedHealed = BASE_HEAL + (skillLevel - 1) * LEVEL_MULTIPLIER;
    }

    /**
     * heals the player if successful
     * returns false if failed to heal
     */
    public boolean activate() {
        if ( skillLevel > 0 && Math.random() < probability ) {
            playerStats.increaseCurrentLife(derivedHealed);
            return true;
        } else return false;
    }

    public String toString() {
        return "BindWounds";
    }

}
