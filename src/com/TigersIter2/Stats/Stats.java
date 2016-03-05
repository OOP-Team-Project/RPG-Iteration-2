package com.TigersIter2.Stats;

/**
 * Created by Magic_Buddha on 3/4/2016.
 */
public class Stats {

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
    protected int attackRating;
    protected int strength;
    protected int agility;
    protected int intellect;


    public Stats() {
        hardiness = 0;
        movement = 0;
        life = 0;
        currentLife = 0;
        armor = 0;
        attackRating = 0;
        strength = 0;
        agility = 0;
        intellect = 0;
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

    public int getLife() {
        return life + 10 * hardiness;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public int getArmor() {
        return armor;
    }

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
        if (currentLife + life > this.life) {
            currentLife = getLife();
        }
        else {
            currentLife += life;
        }
    }

    public void decreaseCurrentHealth(int life) {
        if (currentLife - life < 0) {
            currentLife = 0;
        } else {
            currentLife -= life;
        }
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
