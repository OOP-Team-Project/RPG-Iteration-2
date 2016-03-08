package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.PlayerStats;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public class PickPocket extends ActiveSkill {
    private double probability;

    public PickPocket() {
        super();
        probability = 0.0;
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
}
