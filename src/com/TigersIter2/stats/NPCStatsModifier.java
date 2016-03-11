package com.TigersIter2.stats;

/**
 * Created by Magic_Buddha on 3/10/2016.
 */
public class NPCStatsModifier {

    private int hardiness;
    private int attack;
    private int agility;

    public NPCStatsModifier() {
        this.hardiness = 0;
        this.attack = 0;
        this.agility = 0;
    }

    public int getHardiness() {
        return hardiness;
    }

    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }
}
