package com.TigersIter2.stats;

/**
 * Created by Magic_Buddha on 3/10/2016.
 */
public class NPCStatsModifier {

    private int hardiness;
    private int attack;
    private int agility;
    private int attackTime;

    public NPCStatsModifier() {
        this.hardiness = 0;
        this.attack = 0;
        this.agility = 0;
        this.attackTime = 0;
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

    public int getAttackTime(){
        return attackTime;
    }

    public void setAttackTime(int attackTime){
        this.attackTime = attackTime;
    }

    public boolean isEmpty(){
        if(hardiness == 0 && attack == 0 && agility == 0 && attackTime == 0)
            return true;
        else
            return false;
    }
}
