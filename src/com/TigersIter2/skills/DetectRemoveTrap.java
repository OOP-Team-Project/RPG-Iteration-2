package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.items.Item;

import java.util.Observable;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public class DetectRemoveTrap extends ActiveSkill {
    private double probability;

    public DetectRemoveTrap() {
        super();
        probability = 0.0;
    }


    @Override
    protected void update() {
        probability = .5 + .1 * skillLevel;
    }

    public boolean activate(Item trap) {
        if ( skillLevel > 0 ) {
            if ( Math.random() < probability ) {
                //remove trap
                return true;
            } else {
                //activate trap
                return false;
            }
        } else return false;
    }
}
