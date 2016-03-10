package com.TigersIter2.skills;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public abstract class Skill {

    protected int skillLevel;
    protected int maxLevel;

    public Skill() {
        skillLevel = 0;
        maxLevel = 20;
    }

    /**
     * raises the level of the skill. if current level
     * is max, returns false
     */
    public boolean raiseSkill() {
        if ( skillLevel < maxLevel ) {
            skillLevel++;
            update();
            return true;
        } else return false;
    }

    public int getSkillLevel() { return skillLevel; }

    /**
     * Shouldn't be used besides loading the game
     */
    public void setSkill( int level ) {
        skillLevel = level;
        update();
    }

    /**
     * method to be overridden to update skill when it levels
     */
    protected  void update(){}

}
