package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */
public class SkillTree {

    private int abilityPoints;
    //skill tree is made up of passive, general, and occupation skills
    private Set<Skill> skills;

    /**
     * makes a skill tree depending on the entity type by first building
     * general skills and then occupation specific
     */
    public SkillTree(String occupation) {

        abilityPoints = 0;

        skills = new HashSet<Skill>();

        buildGeneralSkills();
        if ( occupation.equalsIgnoreCase("Smasher") )
            buildSmasherSkills();
        else if ( occupation.equalsIgnoreCase("Summoner") )
            buildSummonerSkills();
        else if ( occupation.equalsIgnoreCase("Sneak") )
            buildSneakSkills();
        else
            System.out.println( "error" ); //will throw exception instead or something - rokas
    }

    /**
     * builds general skills
     */
    private void buildGeneralSkills() {
        skills.add(new Bargain(new PlayerStats()));
        skills.add(new BindWounds(new PlayerStats()));
        skills.add(new Observation());
    }

    /**
     * builds smasher skills
     */
    private void buildSmasherSkills() {

    }

    /**
     * builds summoner skills
     */
    private void buildSummonerSkills() {

    }

    /**
     * builds sneak skills
     */
    private void buildSneakSkills() {

    }

    /**
     * raises a skill level of passive skill.
     */
    public boolean raiseSkill( Skill s ) {
        if ( skills.contains( s ) && abilityPoints > 0 ) {
            abilityPoints--;
            return s.raiseSkill();
        }
        else return false;
    }


    /**
     * adds ability points to be used to raise the skills
     */
    public void addAbilityPoints( int ap ) {
        abilityPoints += ap;
    }

    public void incrementAbilityPoints() { abilityPoints++; }

    /**
     * returns the available ap's
     */
    public int getAbilityPoints() {
        return abilityPoints;
    }

    public void setAbilityPoints( int ap ) { abilityPoints = ap; }
}
