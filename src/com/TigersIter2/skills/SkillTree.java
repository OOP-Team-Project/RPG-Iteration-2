package com.TigersIter2.skills;

import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.Stats;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */
public class SkillTree {

    private int abilityPoints;

    //skill tree is made up of passive, general, and occupation skills
    private Set<Skill> skills;
    private PlayerStats playerStats;

    /**
     * makes a skill tree depending on the entity type by first building
     * general skills and then occupation specific
     */
    public SkillTree(PlayerStats playerStats) {
        String o = playerStats.getOccupation().toString();
        this.playerStats = playerStats;
        abilityPoints = 0;

        skills = new HashSet<Skill>();

        buildGeneralSkills();
        if (o.equalsIgnoreCase("Smasher") )
            buildSmasherSkills();
        else if ( o.equalsIgnoreCase("Summoner") )
            buildSummonerSkills();
        else if ( o.equalsIgnoreCase("Sneak") )
            buildSneakSkills();
        else
            System.out.println( "error" ); //will throw exception instead or something - rokas
    }

    /**
     * builds general skills
     */
    private void buildGeneralSkills() {
        skills.add(new Bargain(playerStats));
        skills.add(new BindWounds(playerStats));
        skills.add(new Observation());
    }

    /**
     * builds smasher skills
     */
    private void buildSmasherSkills() {
        skills.add(new OneHandedWeapon(playerStats));
        skills.add(new TwoHandedWeapon(playerStats));
        skills.add(new Brawling(playerStats));
    }

    /**
     * builds summoner skills
     */
    private void buildSummonerSkills() {
        skills.add(new Enchantment());
        skills.add(new Boon(playerStats));
        skills.add(new Bane(playerStats));
        skills.add(new Staff(playerStats));
    }

    /**
     * builds sneak skills
     */
    private void buildSneakSkills() {
        skills.add(new PickPocket());
        skills.add(new DetectRemoveTrap());
        skills.add(new Creep());
        skills.add(new RangedWeapon());
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

    public Set<Skill> getSkills() {
        return skills;
    }
}
