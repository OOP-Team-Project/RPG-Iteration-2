package com.TigersIter2.skills;

import com.TigersIter2.stats.NPCStatsModifier;
import com.TigersIter2.stats.PlayerStats;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "magic that focuses of influencing another's behavior (e.g, make an NPC fall asleep,
 * be move favorably predisposed to the character, &c.); note: failed enchantment
 * attempts will make the target hostile."
 */
public class Enchantment extends Skill {
    /**
     * level bonuses
     */
    private final int MANA_COST = 5;
    private final int HARDINESS_PER_LEVEL = 4;
    private final int ATTACK_PER_LEVEL = 4;
    private final int AGILITY_PER_LEVEL = 1;
    private final int MANA_COST_LEVEL_MULTIPLIER = 3;


    private int manaCost;
    private double probability;
    private PlayerStats playerStats;

    /**
     * NPC stat modifier to be added to the enemy npc's
     */
    private NPCStatsModifier nsm;

    public Enchantment(PlayerStats ps) {
        super();
        playerStats = ps;
        this.nsm = new NPCStatsModifier();
        this.manaCost = MANA_COST;
        this.manaCost = MANA_COST;
        this.probability = 0.3;
        this.influenceRadiusType = "none";
    }

    /**
     * executes each level
     */
    @Override
    protected void update() {
        nsm.setHardiness( -1 * skillLevel * HARDINESS_PER_LEVEL );
        nsm.setAttack( -1 * skillLevel * ATTACK_PER_LEVEL );
        nsm.setAgility( -1 * skillLevel * AGILITY_PER_LEVEL );
        this.probability = .3 + .1 * skillLevel;
        this.manaCost = MANA_COST + skillLevel * MANA_COST_LEVEL_MULTIPLIER;
    }

    /**
     * returns stat modifier to be applied to the enemy npc's
     */
    public NPCStatsModifier getStatModifier() {
        if(playerStats.getCurrentMana() >= manaCost)
            playerStats.decreaseCurrentMana(manaCost);
        if ( skillLevel > 0 && Math.random() < probability) {
            System.out.println("Enchantment success!");
            return nsm;
        }
        else{
            System.out.println("Enchantment failure");
            return (new NPCStatsModifier());
        }
    }
    public String toString() {
        return "Enchantment";
    }

    public String getInfluenceRadiusType(){
        System.out.println(influenceRadiusType);
        return influenceRadiusType;
    }
}
