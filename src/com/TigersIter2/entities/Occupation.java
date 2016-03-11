package com.TigersIter2.entities;

/**
 * Created by Josh on 2/27/2016.
 */


public abstract class Occupation {

    protected int strength;
    protected int agility;
    protected int intellect;
    protected int hardiness;
    protected int movement;
    protected int life;
    protected int mana;
    protected int attackTime;

    protected int strengthIncrement;
    protected int agilityIncrement;
    protected int intellectIncrement;
    protected int hardinessIncrement;
    protected int movementIncrement;
    protected int lifeIncrement;
    protected int manaIncrement;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public int getHardiness() {
        return hardiness;
    }

    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getAttackTime(){
        return attackTime;
    }

    public void setAttackTime(int attackTime){
        this.attackTime = attackTime;
    }


    public int getStrengthIncrement() {
        return strengthIncrement;
    }

    public void setStrengthIncrement(int strengthIncrement) {
        this.strengthIncrement = strengthIncrement;
    }

    public int getAgilityIncrement() {
        return agilityIncrement;
    }

    public void setAgilityIncrement(int agilityIncrement) {
        this.agilityIncrement = agilityIncrement;
    }

    public int getIntellectIncrement() {
        return intellectIncrement;
    }

    public void setIntellectIncrement(int intellectIncrement) {
        this.intellectIncrement = intellectIncrement;
    }

    public int getHardinessIncrement() {
        return hardinessIncrement;
    }

    public void setHardinessIncrement(int hardinessIncrement) {
        this.hardinessIncrement = hardinessIncrement;
    }

    public int getMovementIncrement() {
        return movementIncrement;
    }

    public void setMovementIncrement(int movementIncrement) {
        this.movementIncrement = movementIncrement;
    }

    public int getLifeIncrement() {
        return lifeIncrement;
    }

    public void setLifeIncrement(int lifeIncrement) {
        this.lifeIncrement = lifeIncrement;
    }

    public int getManaIncrement() {
        return manaIncrement;
    }

    public void setManaIncrement(int manaIncrement) {
        this.manaIncrement = manaIncrement;
    }

    public abstract String toString();
}
