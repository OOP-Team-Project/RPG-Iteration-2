package com.TigersIter2.skills;

import com.TigersIter2.entities.Occupation;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.Stats;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Magic_Buddha on 3/3/2016.
 */
public class SkillTree {

    /**
     * Map of skills. Skillname String is the key, and the skill itself is the value.
     */
    private Map<String,Skill> skills;
    private PlayerStats playerStats;

    /**
     * makes a skill tree depending on the entity type by first building
     * general skills and then occupation specific
     */
    public SkillTree(PlayerStats playerStats) {
        String o = playerStats.getOccupation().toString();
        this.playerStats = playerStats;

        skills = new HashMap<>();

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
        skills.put("BindWounds", new BindWounds(playerStats));
        skills.put("Bargain", new Bargain(playerStats));
        skills.put("Observation", new Observation());
    }

    /**
     * builds smasher skills
     */
    private void buildSmasherSkills() {
        skills.put("OneHandedWeapon" ,new OneHandedWeapon(playerStats));
        skills.put("TwoHandedWeapon", new TwoHandedWeapon(playerStats));
        skills.put("Brawling", new Brawling(playerStats));
    }

    /**
     * builds summoner skills
     */
    private void buildSummonerSkills() {
        skills.put("Enchantment", new Enchantment());
        skills.put("Boon", new Boon(playerStats));
        skills.put("Bane", new Bane(playerStats));
        skills.put("Staff", new Staff(playerStats));
    }

    /**
     * builds sneak skills
     */
    private void buildSneakSkills() {
        skills.put("PickPocket", new PickPocket());
        skills.put("DetectRemoveTrap", new DetectRemoveTrap());
        skills.put("Creep", new Creep(playerStats));
        skills.put("RangedWeapon", new RangedWeapon());
    }

    /**
     * raises a skill level of passive skill.
     */
    public boolean raiseSkill( String s ) {
        if ( skills.containsKey( s ) && playerStats.getAbilityPoints() > 0 ) {
            boolean success = skills.get( s ).raiseSkill();
            if ( success ) decrementAbilityPoints();
            return success;
        }
        else return false;
    }

    private void decrementAbilityPoints() {
        playerStats.decrementAbilityPoint();
    }

    /**
     * If skill exists in the map, return the skill level, otherwise returns -1
     */
    public int getSkillLevel( String s ) {
        if ( skills.containsKey( s ) ) {
            return skills.get( s ).getSkillLevel();
        }
        else return -1;
    }


//    /**
//     * adds ability points to be used to raise the skills
//     */
//    public void addAbilityPoints( int ap ) {
//        abilityPoints += ap;
//    }
//
//    public void incrementAbilityPoints() { abilityPoints++; }

    /**
     * returns the available ap's
     */
    public int getAbilityPoints() {
        return playerStats.getAbilityPoints();
    }

//    public void setAbilityPoints( int ap ) { abilityPoints = ap; }

    public Map<String,Skill> getSkills() {
        return skills;
    }

    public Set<String> getSkillsString() { return skills.keySet(); }

    public String toString() {
        return playerStats.getOccupation().toString();
    }

    public Skill getSkill(String skillName) { return skills.get(skillName); }
}
