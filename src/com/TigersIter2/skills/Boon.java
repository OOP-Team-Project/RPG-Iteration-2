package com.TigersIter2.skills;

import com.TigersIter2.entities.Summoner;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.StatsModifier;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public class Boon extends ActiveSkill {

    /**
     * base improvements while skill is actuve
     */
    private final int BASE_HEAL = 20;
    private final int BASE_INT = 10;
    private final int BASE_MANA = 10;
    private final int LIGHT_RADIUS = 5;
    private final int BASE_HARDINESS = 2;
    private final long LENGTH = 30000;
    private final int MANA_COST = 5;

    /**
     * level bonuses
     */
    private final int HEAL_LEVEL_MULTIPLIER = 15;
    private final int INT_LEVEL_MULTIPLIER = 10;
    private final int MANA_LEVEL_MULTIPLIER = 10;
    private final int HARDINESS_LEVEL_MULTIPLIER = 1;
    private final int MANA_COST_LEVEL_MULTIPLIER = 1;
    private final long LENGTH_LEVEL_MULTIPLIER = 50000;

    /**
     * derived stats of the skill
     */
    private int manaCost;
    private int derivedHealed;
    private double probability;
    private PlayerStats playerStats;

    private StatsModifier sm;

    /**
     * util time used to make the skill active for a period of time
     */
    private Timer timer;
    private long length;

    private boolean active;

    /**
     * Constructor, will need some sort of handle to stats
     */
    public Boon(PlayerStats playerStats){
        super();
        sm = new StatsModifier();
        derivedHealed = BASE_HEAL;
        probability = 0.0;
        manaCost = MANA_COST;
        timer = new Timer();
        active = false;
        this.playerStats = playerStats;
    }

    /**
     * update function to update the stats of the skill
     */
    @Override
    protected void update() {
        probability = .4 + .1 * skillLevel;
        derivedHealed = BASE_HEAL + (skillLevel - 1) * HEAL_LEVEL_MULTIPLIER;
        manaCost = MANA_COST + skillLevel * MANA_COST_LEVEL_MULTIPLIER;
        length = LENGTH + skillLevel + LENGTH_LEVEL_MULTIPLIER;
        sm.setIntellect(BASE_INT + INT_LEVEL_MULTIPLIER * skillLevel);
        sm.setMana((BASE_MANA + MANA_LEVEL_MULTIPLIER * skillLevel));
        sm.setLightRadius(LIGHT_RADIUS);
        sm.setHardiness(BASE_HARDINESS + HARDINESS_LEVEL_MULTIPLIER * skillLevel);
    }

    public boolean activate() {
        if ( skillLevel > 0 && Math.random() < probability) {
            playerStats.increaseCurrentMana(derivedHealed);
            playerStats.decreaseCurrentMana(manaCost);
            if (!active) {

                playerStats.addStatModifier(sm);
                timer.schedule(new TimedSkill(), LENGTH + skillLevel * LENGTH_LEVEL_MULTIPLIER);
            }
            return true;
        } else return false;
    }

    private void skillEnded() {
        playerStats.removeStatModifier(sm);
        active = false;
    }

    private class TimedSkill extends TimerTask {

        @Override
        public void run() {
            skillEnded();
        }
    }

    public static void main (String[] args) {
        PlayerStats ps = new PlayerStats(new Summoner());
        ps.setMana(2000);
        Boon b = new Boon(ps);
        System.out.println(ps.toString());
        b.setSkill(5);
        b.activate();
        System.out.println(ps.toString());
    }
}
