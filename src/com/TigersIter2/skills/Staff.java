package com.TigersIter2.skills;

import com.TigersIter2.stats.Stats;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "out of mana? As a last resort one can try whacking an adversary with one's staff."
 */
public class Staff extends ActiveSkill {

    /**
     * Level bonus
     */
    private final int ATTACK_PER_LEVEL = 10;

    private final int BASE_ATTACK = 25;

    /**
     * derived stats
     */
    private int damage;
    private double probability;

    /*
    handle to player stats in order to get offensive rating
     */
    private Stats playerStats;

    public Staff(Stats playerStats) {
        super();
        this.playerStats = playerStats;
        this.damage = ATTACK_PER_LEVEL;
        this.probability = .3;
    }

    /**
     * executes on each level up
     */
    @Override
    protected void update() {
        probability = .3 + .1 * skillLevel;
        damage = skillLevel * ATTACK_PER_LEVEL + BASE_ATTACK;
    }

    /**
     * returns the damage that should be dealt to the enemy
     */
    public int getDamage() {
        if ( skillLevel > 0 && Math.random() < probability ) {
            return damage + playerStats.getOffensiveRating();
        } else return 0;
    }

    public String toString() {
        return "Staff";
    }
}
