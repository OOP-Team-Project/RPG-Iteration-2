package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.StatsModifier;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "ability to stealthily move from place to place; movement is
 * slowed while creeping. A successfully creeping character can
 * surprise and back-stab an unwary opponent for extra damage."
 */
public class Creep extends ActiveSkill {
    private final long LENGTH = 8000;
    private final long LENGTH_LEVEL_MULTIPLIER = 5000;
    private final int SPEED_LOSS = -2;

    private double probability;
    private PlayerStats playerStats;
    private Timer timer;
    private StatsModifier sm;
    private boolean active;

    public Creep( PlayerStats playerStats ) {
        super();
        this.probability = 0.3;
        this.playerStats = playerStats;
        timer = new Timer();
        sm = new StatsModifier();
    }


    @Override
    protected void update() {
        probability = .3 + .1 * skillLevel;
    }

    public boolean activate() {
        if ( skillLevel > 0 && Math.random() < probability) {
            if(!active){
                sm.setMovement(SPEED_LOSS);
                playerStats.addStatModifier(sm);
                timer.schedule(new TimedSkill(), LENGTH + skillLevel * LENGTH_LEVEL_MULTIPLIER);
                System.out.println("Started creeping");
                active = true;
                return true;
            }
        }

        return false;
    }

    public boolean isActive(){
        return active;
    }

    /**
     * executed when the skill timer has finished
     */
    private void skillEnded() {
        playerStats.removeStatModifier(sm);
        active = false;
    }

    /**
     * essentially, when a timer runs out, it executes run function which reverts this skills boosts
     */
    private class TimedSkill extends TimerTask {

        @Override
        public void run() {
            skillEnded();
        }
    }

    public String toString() {
        return "Creep";
    }
}
