package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.Stats;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public class Bargain extends Skill {

    private PlayerStats playerStats;
    private final int BARTER_PER_LEVEL = 5;

    public Bargain( PlayerStats playerStats ) {
        this.playerStats = playerStats;
    }

    @Override
    public void setSkill( int level ) {
        skillLevel = level;
        updateWhenSet();
    }


    @Override
    protected void update() {
        playerStats.incrementBarter(BARTER_PER_LEVEL);
    }

    private void updateWhenSet() {
        playerStats.incrementBarter(skillLevel * BARTER_PER_LEVEL);
    }
}
