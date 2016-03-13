package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.Stats;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * Brawling adds damage when smasher isn't holding any weapons.
 * ^^Still need a way to implement it (need a handle to Equipment and be able to
 * check what type of weapon is held)
 *
 * "fighting with firsts and feet (note: this includes brass-knuckles, spiked gloves, &c.
 * should you wish to have such items in your game). [Note: unarmed combat is low
 * damage per blow, high speed attack]"
 */
public class Brawling extends ActiveSkill {

    /**
     * base improvements while skill is actuve
     */
    private final int ATTACK_PER_LEVEL = 3;

    /**
     * derived stats of the skill
     */
    private int damage;
    private double probability;
    private Stats playerStats;

    public Brawling(Stats playerStats) {
        super();
        this.playerStats = playerStats;
        this.damage = ATTACK_PER_LEVEL;
        this.probability = .3;
    }

    /**
     * update function to update the stats of the skill
     */
    @Override
    protected void update() {
        probability = .3 + .1 * skillLevel;
        damage = skillLevel * ATTACK_PER_LEVEL;
    }

    /**
     * returns damage to be dealt to the enemy.
     * returns 0 if failed to attack
     */
    //TODO: still need to check if holding no weapon..
    public int getDamage() {
        if ( skillLevel > 0 && Math.random() < probability ) {
            return damage + playerStats.getOffensiveRating();
        } else return 0;
    }

    public String toString() {
        return "Brawling";
    }
}
