package com.TigersIter2.skills;

import com.TigersIter2.entities.NPC;
import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * This skill returns magicdamage to be dealt to an enemy
 * derived from int and skill level. Mana cost increases as the skill levels up.
 * Requires handle to playerStats on intantiation
 */
public class Bane extends Skill {
    /**
     * level modifiers and base stats of the skill
     */
    private final int MANA_COST = 5;
    private final int MAGIC = 30;
    private final int MAGIC_PER_LEVEL = 15;
    private final int MANA_COST_LEVEL_MULTIPLIER = 3;

    /**
     * derived stats of the skill
     */
    private int magicDamage;
    private int manaCost;
    private double probability;

    /**
     * handle to player stats to get avatars OffensiveRating
     */
    private PlayerStats playerStats;

    public Bane(){}

    public Bane(PlayerStats playerStats) {
        super();
        this.playerStats = playerStats;
        this.magicDamage = MAGIC_PER_LEVEL + MAGIC;
        this.manaCost = MANA_COST;
        this.probability = 0.3;
        this.influenceRadiusType = "none";
    }

    /**
     * update method gets called on each level up.
     */
    @Override
    protected void update() {
        this.probability = .3 + .1 * skillLevel;
        this.magicDamage = skillLevel * MAGIC_PER_LEVEL + MAGIC;
        this.manaCost = MANA_COST + skillLevel * MANA_COST_LEVEL_MULTIPLIER;
    }

    /**
     * depending on the current skill statistics,
     * returns the damage to be dealt
     */
    public int getDamage() {
        if(playerStats.getCurrentMana() >= manaCost)
            playerStats.decreaseCurrentMana(manaCost);
        if ( skillLevel > 0 && Math.random() < probability) {
            return magicDamage + playerStats.getOffensiveRating();
        } else return 0;
    }

    public String toString() {
        return "Bane";
    }

    public String getInfluenceRadiusType(){
        return influenceRadiusType;
    }
}
