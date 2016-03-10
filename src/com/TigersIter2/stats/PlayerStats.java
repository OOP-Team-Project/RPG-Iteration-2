package com.TigersIter2.stats;

import com.TigersIter2.entities.Occupation;
import com.TigersIter2.entities.Smasher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic_Buddha on 3/4/2016.
 * aka ROKAS. Please let me know if there are any concerns
 */
public class PlayerStats extends Stats {

    private final int LIFE_PER_HARDINESS = 25;
    private final int LIFE_PER_LEVEL = 10;
    private final int OFFENSIVE_RATING_PER_STRENGTH = 1;
    private final int OFFENSIVE_RATING_PER_LEVEL = 2;
    private final int DEFENSIVE_RATING_PER_AGILITY = 2;
    private final int DEFENSIVE_RATING_PER_LEVEL = 2;
    private final int ARMOR_RATING_PER_HARDINESS = 2;
    private final int MANA_PER_INTELLECT = 5;
    private final int MANA_PER_LEVEL = 5;
    private final double LEVEL_EXPERIENCE_EXPONENT = 1.1;
    private final int FIRST_LEVEL_EXPERIENCE = 100;

    /**
     * stats relevant to player
     */
    protected int livesLeft;
    protected int maxLives;
    protected int experience;
    protected int level;
    protected int mana;
    protected int currentMana;
    protected int barter;
    protected int lightRadius;
    protected int abilityPoints;

    protected List<StatsModifier> mods;


    protected Occupation o;

//    /**
//     * Constructor currently sets all stats to 0
//     */
//    public PlayerStats() {
//        super();
//        livesLeft = 2;
//        maxLives = 10;
//        experience = 0;
//        level = 0;
//        mana = 0;
//        currentMana = 0;
//        barter = 0;
//        lightRadius = 10;
//        mods = new ArrayList<StatsModifier>();
//    }

    /**
     * or be a cool kid and pass me your occupation!
     */
    public PlayerStats(Occupation o) {
        super();
        //Set the Occupation specific stats
        this.o = o;
        this.strength = o.getStrength();
        this.agility = o.getAgility();
        this.intellect = o.getIntellect();
        this.hardiness = o.getHardiness();
        this.movement = o.getMovement();
        this.life = o.getLife();
        this.mana = o.getMana();

        this.lightRadius = 10;

        this.barter = 0;
        this.livesLeft = 2;
        this.maxLives = 10;
        this.experience = 0;
        this.level = 0;

        this.abilityPoints = 0;


        //max the current mana and life
        this.currentMana = getMana();
        this.currentLife = getLife();


        this.mods = new ArrayList<StatsModifier>();
    }

    /*************************************************************************************************
     * Setters and Modifiers
     ************************************************************************************************/

    /**
     * Sets livesLeft. Value passed must be less than maxLives for the character
     */
    public boolean setLivesLeft(int livesLeft) {
        if (maxLives >= livesLeft) {
            this.livesLeft = livesLeft;
            return true;
        } else return false;
    }

    /**
     * prolly more user friendly
     */
    public boolean incrementLivesLeft() {
        if ( livesLeft < maxLives ) {
            livesLeft++;
            return true;
        } else return false;
    }

    public void decrementLivesLeft() {
        livesLeft--;
    }

    public void setMaxLives(int maxLives) {
        this.maxLives = maxLives;
    }

    //TODO: skills must be restored separately, or ill add more to this function.
    /**
     * should only used while loading the game
     */
    public void setExperience(int experience) {
        int levelRequested = getLevelFromTotalExperience(experience);
        this.experience = experience;
        while (level < levelRequested) {
            levelUpLoadingHelper();
        }
    }

    /**
     * adds experience and checks if levelUp happend, if so it executes levelup function
     * for the levels gained
     */
    public void addExperience(int experience) {
        this.experience += experience;
        if (this.experience >= getExperienceRequiredForLevel(this.level + 1)) {
            int timesLeveledUp = getLevelFromTotalExperience(this.experience) - this.level;
            while (timesLeveledUp > 0) {
                levelUp();
                timesLeveledUp--;
            }
        }
    }

    //TODO: IMPLEMENT BELOW
    public void incrementLevel() {
        int currentExp = getExperience();
        int expNeeded = getExperienceRequiredForLevel(level + 1);
        addExperience(expNeeded - currentExp);
    }

    /**
     * should only use for loading
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * should only use for loading
     */
    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public void increaseCurrentMana(int mana) {
        if (currentMana + mana > getMana()) {
            currentMana = getMana();
        } else {
            currentMana += mana;
        }
    }

    public void decreaseCurrentMana(int mana) {
        if (currentMana - mana < 0) {
            currentMana = 0;
        } else {
            currentMana -= mana;
        }
    }

    public void setBarter(int barter) {
        this.barter = barter;
    }

    public void incrementBarter(int barter) { this.barter += barter; }

    public void setOccupation(Occupation o) {
        this.o = o;
    }

    /**
     * adds a stat modifier and immediately affects the stats
     */
    public void addStatModifier(StatsModifier sm) {
        mods.add( sm );
        this.hardiness += sm.getHardiness();
        this.movement += sm.getMovement();
        this.life += sm.getLife();
        this.armor += sm.getArmor();
        this.attack += sm.getAttack();
        this.strength += sm.getStrength();
        this.agility += sm.getAgility();
        this.intellect += sm.getIntellect();
        this.mana += sm.getMana();
        this.barter += sm.getBarter();
        this.lightRadius += sm.getLightRadius();
    }

    /**
     * removes a stat modifier and immediately restores the stats
     */
    public void removeStatModifier(StatsModifier sm) {
        if ( mods.contains( sm ) ) {
            mods.remove( sm );
            this.hardiness -= sm.getHardiness();
            this.movement -= sm.getMovement();
            this.life -= sm.getLife();
            this.armor -= sm.getArmor();
            this.attack -= sm.getAttack();
            this.strength -= sm.getStrength();
            this.agility -= sm.getAgility();
            this.intellect -= sm.getIntellect();
            this.mana -= sm.getMana();
            this.barter -= sm.getBarter();
            this.lightRadius -= sm.getLightRadius();
        }
    }

    public void setLightRadius(int lightRadius) {
        this.lightRadius = lightRadius;
    }


    /*************************************************************************************************
     * Getters
     ************************************************************************************************/
    public int getLivesLeft() {
        return livesLeft;
    }

    public int getMaxLives() {
        return this.livesLeft;
    }

    @Override
    public int getLife() {
        return life + LIFE_PER_HARDINESS * hardiness + LIFE_PER_LEVEL * level;
    }

    public boolean isDead() {
        return currentLife <= 0;
    }

    public boolean gameOver() {
        return 0 > livesLeft;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public int getMana() {
        return mana + MANA_PER_INTELLECT * intellect + MANA_PER_LEVEL * level;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public int getBarter() {
        return barter;
    }

    @Override
    public int getOffensiveRating() {
        return attack + OFFENSIVE_RATING_PER_STRENGTH * strength + OFFENSIVE_RATING_PER_LEVEL * level;
    }

    @Override
    public int getDefensiveRating() {
        return agility * DEFENSIVE_RATING_PER_AGILITY + DEFENSIVE_RATING_PER_LEVEL * level;
    }

    @Override
    public int getArmorRating() {
        return hardiness * ARMOR_RATING_PER_HARDINESS + armor;
    }

    public int getLightRadius() {
        return lightRadius;
    }

    public void increaseLightRadius(int lr) {
        lightRadius += lr;
    }

    public void decreaseLightRadius(int lr) {
        lightRadius -= lr;
    }

    public Occupation getOccupation() {
        return o;
    }

    public int getAbilityPoints() {
        return abilityPoints;
    }


    @Override
    public String toString() {
        String results = "";
        results = results + "Health: " + currentLife + "/" + getLife() +
                            "\nMana: " + currentMana + "/" + getMana() +
                            "\nLives: " + livesLeft + "/" + maxLives +
                            "\nStrength: " + strength +
                            "\nAgility: " + agility +
                            "\nIntellect: " + intellect +
                            "\nHardiness: " + hardiness +
                            "\nMovement: " + movement +
                            "\nBarter: " + barter +
                            "\nExperience: " + experience +
                            "\nLevel: " + level +
                            "\nOffensiveRating: " + getOffensiveRating() +
                            "\nDefensiveRating: " + getDefensiveRating() +
                            "\nArmorRating: " + getArmorRating();
        return results;
    }

    /*************************************************************************************************
     * Private for class use
     ************************************************************************************************/
    private void levelUpLoadingHelper() {
        this.strength += o.getStrengthIncrement();
        this.agility += o.getAgilityIncrement();
        this.intellect += o.getIntellectIncrement();
        this.hardiness += o.getHardinessIncrement();
        this.movement += o.getMovementIncrement();
        this.life += o.getLifeIncrement();
        this.mana += o.getManaIncrement();

        this.level++;
        this.abilityPoints++;
        this.currentMana = getMana();
        this.currentLife = getLife();
    }

    private void levelUp() {
        this.strength += o.getStrengthIncrement();
        this.agility += o.getAgilityIncrement();
        this.intellect += o.getIntellectIncrement();
        this.hardiness += o.getHardinessIncrement();
        this.movement += o.getMovementIncrement();
        this.life += o.getLifeIncrement();
        this.mana += o.getManaIncrement();

        this.level++;
        this.abilityPoints++;
        this.currentMana = getMana();
        this.currentLife = getLife();
    }

    /**
     * Following two functions are from last iteration. was wasting too much time
     * making a new exp curve. will use this for now.
     */
    public int getExperienceRequiredForLevel(int targetLevel) {
        if (targetLevel < 1) {
            throw new RuntimeException("Level must be greater than or equal to 1");
        }
        else if (targetLevel == 1) {
            return FIRST_LEVEL_EXPERIENCE;
        }
        else
            return (int)(FIRST_LEVEL_EXPERIENCE * Math.pow(targetLevel, LEVEL_EXPERIENCE_EXPONENT));
    }

    private int getLevelFromTotalExperience(int totalExperience) {
        return (int)Math.floor(Math.exp(Math.log(totalExperience / FIRST_LEVEL_EXPERIENCE) / LEVEL_EXPERIENCE_EXPONENT));
    }

    //only used in skillTree
    public void decrementAbilityPoint() {
        this.abilityPoints--;
    }

    //***********************************************TESTING***************************************************//
    public static void main(String[] args) {
        Occupation o = new Smasher();
        PlayerStats ps = new PlayerStats(o);
        System.out.println(ps.toString() + '\n');
        ps.addExperience(100);
        System.out.println(ps.toString() + '\n');
        ps.addExperience(100);
        System.out.println(ps.toString() + '\n');
        ps.addExperience(100);
        System.out.println(ps.toString() + '\n');
        ps.addExperience(100);
        System.out.println(ps.toString() + '\n');
        ps.incrementLivesLeft();
        ps.decreaseCurrentLife(100);
        ps.increaseCurrentMana(100);
        StatsModifier sm = new StatsModifier();
        System.out.println(ps.toString() + '\n');
        System.out.println("Now adjust agility by 10");
        sm.setAgility(10);
        ps.addStatModifier(sm);
        System.out.println(ps.toString() + '\n');
        System.out.println("now remove stat modifier");
        ps.removeStatModifier(sm);
        System.out.println(ps.toString() + '\n');

    }
}
