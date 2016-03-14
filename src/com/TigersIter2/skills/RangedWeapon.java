package com.TigersIter2.skills;

import com.TigersIter2.stats.Stats;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public class RangedWeapon extends ActiveSkill {

    private final int ATTACK_PER_LEVEL = 5;

    private int damage;
    private double probability;
    private Stats playerStats;

    public RangedWeapon(Stats ps){
        super();
        this.playerStats = ps;
        this.damage = ATTACK_PER_LEVEL;
        this.probability = .3;
    }

    @Override
    protected void update() {
        this.probability = .3 + .1 * skillLevel;
        this.damage = skillLevel * ATTACK_PER_LEVEL;
    }

    public int getDamage() {
        if ( skillLevel > 0 && Math.random() < probability ) {
            return damage + playerStats.getOffensiveRating();
        } else return 0;
    }

    public String toString() {
        return "RangedWeapon";
    }
}
