package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.PlayerStats;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "ability to stealthily move from place to place; movement is
 * slowed while creeping. A successfully creeping character can
 * surprise and back-stab an unwary opponent for extra damage."
 */
public class Creep extends ActiveSkill {
    private double probability;
    private PlayerStats playerStats;

    public Creep( PlayerStats playerStats ) {
        super();
        this.probability = 0.0;
        this.playerStats = playerStats;
    }


    @Override
    protected void update() {
        probability = .5 + .1 * skillLevel;
    }

    public boolean activate(NPC target) {
        if ( skillLevel > 0 ) {
            if ( Math.random() < probability ) {
                //get random item from npc
                return true;
            } else {
                //target.setHostile()
                return false;
            }

        } else return false;
    }

    public String toString() {
        return "Creep";
    }
}
