package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public class Staff extends ActiveSkill {
    private final int ATTACK_PER_LEVEL = 10;
    private final int BASE_ATTACK = 25;
    private int damage;
    private double probability;
    private PlayerStats playerStats;

    public Staff(PlayerStats playerStats) {
        super();
        this.playerStats = playerStats;
        damage = 0;
        probability = 0.0;
    }


    @Override
    protected void update() {
        probability = .5 + .1 * skillLevel;
        damage = skillLevel * ATTACK_PER_LEVEL + BASE_ATTACK;
    }

    public boolean activate(NPC target) {
        if ( skillLevel > 0 && Math.random() < probability ) {
//            target.attack(damage + playerStats.getOffensiveRating());
            return true;
        } else return false;
    }

    public String toString() {
        return "Staff";
    }
}
