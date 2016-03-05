package com.TigersIter2.stats;

/**
 * Created by Magic_Buddha on 3/4/2016.
 * aka ROKAS. Please let me know if there are any concerns
 */
public class StatsModifier {

    private int hardiness;
    private int movement;
    private int life;
    private int armor;
    private int attackRating;
    private int strength;
    private int agility;
    private int intellect;
    private int mana;
    private int barter;

    /**
     * The stats modifier class initializes stat boosts to 0
     */
    public StatsModifier() {
        hardiness = 0;
        movement = 0;
        life = 0;
        armor = 0;
        attackRating = 0;
        strength = 0;
        agility = 0;
        intellect = 0;
        mana = 0;
        barter = 0;
    }


    /**
     * for now you should use getters and setters to set up the stat boosts
     */
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

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armorRating) {
        this.armor = armorRating;
    }

    public int getAttackRating() {
        return attackRating;
    }

    public void setAttackRating(int attackRating) {
        this.attackRating = attackRating;
    }

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

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getBarter() {
        return barter;
    }

    public void setBarter(int barter) {
        this.barter = barter;
    }
}
