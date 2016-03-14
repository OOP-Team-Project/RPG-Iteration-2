package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * This skill alters the players barter stat
 * "an avatar with a high bargain skill will get better prices when buying/selling items."
 * only passive skill at the moment
 */
public class Bargain extends Skill {

    /**
     * handle to player stats to edit it's barter stat
     */
    private PlayerStats playerStats;

    //level modifier
    private final int BARTER_PER_LEVEL = 5;

    public Bargain( PlayerStats playerStats ) {
        //super();
        this.playerStats = playerStats;
    }

    /**
     * ability to set the skill to a certain level during loading
     */
    @Override
    public void setSkill( int level ) {
        skillLevel = level;
        updateWhenSet();
    }

    /**
     * executed on each level up
     */
    @Override
    protected void update() {
        playerStats.incrementBarter(BARTER_PER_LEVEL);
    }

    private void updateWhenSet() {
        playerStats.incrementBarter(skillLevel * BARTER_PER_LEVEL);
    }

    public String toString() {
        return "Bargain";
    }
}
