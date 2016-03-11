package com.TigersIter2.skills;

import com.TigersIter2.assets.sprites.MainMenuSprite;
import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.Stats;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "self descriptive [Note: two-handed weapons combat is high
 * damage per blow, low speed attack]"
 */
public class TwoHandedWeapon extends ActiveSkill {

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

    public TwoHandedWeapon(Stats playerStats) {
        super();
        this.playerStats = playerStats;
        this.damage = 0;
        this.probability = 0.0;
    }

    /**
     * executed after each level up
     */
    @Override
    protected void update() {
        this.probability = .3 + .1 * skillLevel;
        this.damage = skillLevel * ATTACK_PER_LEVEL;
    }

    /**
     * returns the damage to be dealt to an enemy npc
     */
    //TODO: still need to check if holding 2h weapon..
    public int getDamage() {
        if ( skillLevel > 0 && Math.random() < probability ) {
            return damage + playerStats.getOffensiveRating();
        } else return 0;
    }

    public String toString() {
        return "TwoHandedWeaponItem";
    }
}
