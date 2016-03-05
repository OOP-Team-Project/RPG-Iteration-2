package com.TigersIter2.skills;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */
public abstract class GeneralSkill {
    protected int skillLevel;
    protected int maxLevel;

    public GeneralSkill() {
        skillLevel = 0;
        maxLevel = 5;
    }

    /**
     * raises the level of the skill. if current level
     * is max, returns false
     */
    public boolean raiseSkill() {
        if ( skillLevel < maxLevel ) {
            skillLevel++;
//            update();
            return true;
        } else return false;
    }

    /**
     * Shouldn't be used besides loading the game
     */
    public void setSkill( int level ) {
        skillLevel = level;
    }

    public abstract boolean activate();
}
