package com.TigersIter2.skills;

import com.TigersIter2.stats.NPCStatsModifier;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */

/**
 * "magic that focuses of influencing another's behavior (e.g, make an NPC fall asleep,
 * be move favorably predisposed to the character, &c.); note: failed enchantment
 * attempts will make the target hostile."
 */
public class Enchantment extends ActiveSkill {
    /**
     * level bonuses
     */
    private final int HARDINESS_PER_LEVEL = 4;
    private final int ATTACK_PER_LEVEL = 4;
    private final int AGILITY_PER_LEVEL = 1;

    /**
     * NPC stat modifier to be added to the enemy npc's
     */
    private NPCStatsModifier nsm;

    public Enchantment() {
        this.nsm = new NPCStatsModifier();
    }

    /**
     * executes each level
     */
    @Override
    protected void update() {
        nsm.setHardiness( -1 * skillLevel * HARDINESS_PER_LEVEL );
        nsm.setAttack( -1 * skillLevel * ATTACK_PER_LEVEL );
        nsm.setAgility( -1 * skillLevel * AGILITY_PER_LEVEL );
    }

    /**
     * returns stat modifier to be applied to the enemy npc's
     */
    public NPCStatsModifier getStatModifier() {
        return nsm;
    }
    public String toString() {
        return "Enchantment";
    }
}
