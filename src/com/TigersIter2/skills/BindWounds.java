package com.TigersIter2.skills;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */
public class BindWounds extends GeneralSkill {

    private final int BASE_HEAL = 10;
    private final int LEVEL_MULTIPLIER = 10;
    private int derivedHealed;

    /**
     * Constructor, will need some sort of handle to stats
     */
    public BindWounds(){
        super();
        derivedHealed = BASE_HEAL;
    }

    /**
     * update function to update the stats of the skill
     */
    private void update() {
        derivedHealed = BASE_HEAL + (skillLevel - 1) * LEVEL_MULTIPLIER;
    }
}
