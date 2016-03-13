package com.TigersIter2.skills;

import com.TigersIter2.entities.Summoner;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.StatsModifier;
import com.TigersIter2.views.MessageView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * Summoners skill that alters players stats for some time
 * depending on the level of the skill.
 * "magic that heals, temporarily grants (partial) immunities
 * and defensive bonuses, improves stats, and other beneficial things."
 */
public class Boon extends Skill {

    /**
     * base improvements while skill is actuve
     */
    private final int BASE_HEAL = 20;
    private final int BASE_HARDINESS = 2;
    private final long LENGTH = 30000;
    private final int MANA_COST = 5;

    /**
     * level bonuses
     */
    private final int HEAL_LEVEL_MULTIPLIER = 15;
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
    protected int boonSelect;

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
        probability = 0.3;
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
        probability = .3 + .1 * skillLevel;
        length = LENGTH + skillLevel + LENGTH_LEVEL_MULTIPLIER;
        manaCost = MANA_COST + skillLevel * MANA_COST_LEVEL_MULTIPLIER;
    }

    public boolean activate() {
        if(boonSelect == 0){
            sm.setIntellect(20);
        }
        else if(boonSelect == 1){
            sm.setHardiness(BASE_HARDINESS + HARDINESS_LEVEL_MULTIPLIER * skillLevel);
        }
        else if(boonSelect == 2){
            derivedHealed = BASE_HEAL + (skillLevel - 1) * HEAL_LEVEL_MULTIPLIER;
        }
        if(playerStats.getCurrentMana() >= manaCost) {
            playerStats.decreaseCurrentMana(manaCost);
            if (skillLevel > 0 && Math.random() < probability) {
                playerStats.increaseCurrentLife(derivedHealed);
                derivedHealed = 0;
                if (!active) {
                    //adds a statmodifier and starts a timer to remove it
                    playerStats.addStatModifier(sm);
                    timer.schedule(new TimedSkill(), LENGTH + skillLevel * LENGTH_LEVEL_MULTIPLIER);
                }
                System.out.println("Booned your stats");
                MessageView.addMessage("Boon successful");
                MessageView.drawMessage();
                return true;
            }
            else {
                System.out.println("Failed to boon your stats");
                MessageView.addMessage("Boon failed");
                MessageView.drawMessage();
                return false;
            }
        }
        else{
            System.out.println("Failed to boon your stats");
            MessageView.addMessage("Boon failed");
            MessageView.drawMessage();
            return false;
        }
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
        return "Boon";
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
