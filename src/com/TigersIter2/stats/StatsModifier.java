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
    private int attack;
    private int strength;
    private int agility;
    private int intellect;
    private int mana;
    private int barter;
    private int lightRadius;
    private int lives;
    private int attackTime;
    private int influenceRadius;

    /**
     * The stats modifier class initializes stat boosts to 0
     */
    public StatsModifier() {
        hardiness = 0;
        movement = 0;
        life = 0;
        armor = 0;
        attack = 0;
        strength = 0;
        agility = 0;
        intellect = 0;
        mana = 0;
        barter = 0;
        lightRadius = 0;
        lives = 0;
        attackTime = 0;
        influenceRadius = 0;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
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

    public int getLightRadius() {
        return lightRadius;
    }

    public void setLightRadius(int lightRadius) {
        this.lightRadius = lightRadius;
    }

    public int getLives(){
        return lives;
    }

    public void setLives(int l){
        lives = l;
    }

    public int getAttackTime(){
        return attackTime;
    }

    public void setAttackTime(int attackTime){
        this.attackTime = attackTime;
    }

    public int getInfluenceRadius(){
        return influenceRadius;
    }

    public void setInfluenceRadius(int influenceRadius){
        this.influenceRadius = influenceRadius;
    }
}
