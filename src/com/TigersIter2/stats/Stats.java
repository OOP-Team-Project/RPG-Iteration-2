package com.TigersIter2.stats;

/**
 * Created by Magic_Buddha on 3/4/2016.
 * aka ROKAS. Please let me know if there are any concerns
 */
public abstract class Stats {

    /**
     * stats that are used by both, player and npc's
     * MOST LIKELY will be abstract
     */
    protected int hardiness;
    protected int movement;
    protected int life;
    protected int currentLife;
    //armor and attack rating are used in deriving offensive rating and defensive rating
    //do not base anything off these values, instead use getters of off/deff rating
    protected int armor;
    protected int attack;
    protected int strength;
    protected int agility;
    protected int intellect;
    protected int attackTime;


    public Stats() {
        hardiness = 0;
        movement = 0;
        life = 0;
        currentLife = 0;
        armor = 0;
        attack = 0;
        strength = 0;
        agility = 0;
        intellect = 0;
        attackTime = 0;
    }

    /**
     * getters
     */
    public int getHardiness() {
        return hardiness;
    }

    public int getMovement() {
        return movement;
    }

    /**
     * needs to be overridden to account for it being derived
     */
    public int getLife() {
        return this.life;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public int getAttackTime(){
        return attackTime;
    }

    /**
     * returns the armor value, not armorRating. armorRating is derivedStat
     */
    public int getArmor() {
        return armor;
    }

    /**
     * returns the attack value. Use OffensiveRating for damaging entities
     */
    public int getAttack() { return attack; }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntellect() {
        return intellect;
    }

    /**
     * must be overridden
     */
    public abstract int getArmorRating();

    public abstract int getOffensiveRating();

    public abstract int getDefensiveRating();


    /**
     * setters
     */
    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = currentLife;
    }

    public void increaseCurrentLife(int life) {
        if (currentLife + life > getLife()) {
            currentLife = getLife();
        }
        else {
            currentLife += life;
        }
    }

    public void decreaseCurrentLife(int life) {
        if (currentLife - life < 0) {
            currentLife = 0;
        } else {
            currentLife -= life;
        }
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setAttack(int attack) { this.attack = attack; }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAttackTime(int attackTime){
        this.attackTime = attackTime;
    }

}
