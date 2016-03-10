package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.Stats;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "the higher this skill, the better a Smasher will be when fighting
 * with a one-handed weapon. [Note: one-handed weapons combat is medium
 * damage per blow, medium speed attack]"
 */
public class OneHandedWeapon extends ActiveSkill {

    /**
     * level bonus
     */
    private final int ATTACK_PER_LEVEL = 5;

    /**
     * derived skill stats
     */
    private int damage;
    private double probability;

    private Stats playerStats;

    public OneHandedWeapon(Stats playerStats) {
        super();
        this.playerStats = playerStats;
        this.damage = 0;
        this.probability = 0.0;
    }


    @Override
    protected void update() {
        this.probability = .3 + .1 * skillLevel;
        this.damage = skillLevel * ATTACK_PER_LEVEL;
    }

    /**
     * returns the damage to be dealt to the enemy npc
     */
    //TODO: still need to check if holding 1h weapon..
    public int getDamage() {
        if ( skillLevel > 0 && Math.random() < probability ) {
            return damage + playerStats.getOffensiveRating();
        } else return 0;
    }

    public String toString() {
        return "OneHandedWeapon";
    }
}
