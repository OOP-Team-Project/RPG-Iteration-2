package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.SkillsSprite;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.skills.SkillTree;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;

/**
 * Created by Magic_Buddha on 3/8/2016.
 */
public class SkillManagementView extends JComponent {

    private final int STATS_OFFSET = 50;
    private final int SKILL_IMAGE_WIDTH = 150;
    private final int SKILL_IMAGE_HEIGHT = 150;

    private boolean display;
    private int highlighted;
    private int maxHighlighted;

    private SkillTree skillTree;
    private String[] skillNames;
    private String occupation;

    public SkillManagementView(SkillTree sk) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        skillTree = sk;
        display = false;
        highlighted = 0;
        occupation = skillTree.toString();
        if ( occupation.equalsIgnoreCase("smasher") ) {
            maxHighlighted = 6;

        } else if ( occupation.equalsIgnoreCase("summoner") ) {
            maxHighlighted = 7;

        } else if ( occupation.equalsIgnoreCase("sneak") ) {
            maxHighlighted = 7;

        }
    }

    public void toggle(){
        display = !display;

    }

    public boolean getDisplay() {
        return display;
    }

    public void handleInput(int input){
        if(input == 2){
            decrementHighlighted();
            System.out.println("CH: " + highlighted);
        }
        else if(input == 3){
            incrementHighlighted();
            System.out.println("CH: " + highlighted);

        }
        else if(input == 4 && skillTree.getAbilityPoints() > 0) {
            String occupation = skillTree.toString();
            if (highlighted == 0) {
                skillTree.raiseSkill("BindWounds");
            } else if (highlighted == 1) {
                skillTree.raiseSkill("Bargain");
            } else if (highlighted == 2) {
                skillTree.raiseSkill("Observation");
            } else if (highlighted == 3) {
                if ( occupation.equalsIgnoreCase("smasher") ) {
                    skillTree.raiseSkill("oneHandedWeapon");
                } else if ( occupation.equalsIgnoreCase("summoner") ) {
                    skillTree.raiseSkill("EnchantingBlast");
                    skillTree.incrementAbilityPoints();
                    skillTree.raiseSkill("EnchantingShot");
                    skillTree.incrementAbilityPoints();
                    skillTree.raiseSkill("EnchantingStorm");
                } else if ( occupation.equalsIgnoreCase("sneak") ) {
                    skillTree.raiseSkill("PickPocket");
                }
            } else if (highlighted == 4) {
                if ( occupation.equalsIgnoreCase("smasher") ) {
                    skillTree.raiseSkill("TwoHandedWeapon");
                } else if ( occupation.equalsIgnoreCase("summoner") ) {
                    skillTree.raiseSkill("BoonHardiness");
                    skillTree.incrementAbilityPoints();
                    skillTree.raiseSkill("BoonHealth");
                    skillTree.incrementAbilityPoints();
                    skillTree.raiseSkill("BoonIntellect");
                } else if ( occupation.equalsIgnoreCase("sneak") ) {
                    skillTree.raiseSkill("DetectRemoveTrap");
                }
            } else if (highlighted == 5) {
                if ( occupation.equalsIgnoreCase("smasher") ) {
                    skillTree.raiseSkill("Brawling");
                } else if ( occupation.equalsIgnoreCase("summoner") ) {
                    skillTree.raiseSkill("FireBlast");
                    skillTree.incrementAbilityPoints();
                    skillTree.raiseSkill("FireShot");
                    skillTree.incrementAbilityPoints();
                    skillTree.raiseSkill("FireStorm");
                } else if ( occupation.equalsIgnoreCase("sneak") ) {
                    skillTree.raiseSkill("Creep");
                }
            } else if (highlighted == 6) {
                if ( occupation.equalsIgnoreCase("summoner") ) {
                    skillTree.raiseSkill("Staff");
                } else if ( occupation.equalsIgnoreCase("sneak") ) {
                    skillTree.raiseSkill("RangedWeapon");
                }
            }
        }
    }

    private void decrementHighlighted() {
        if (highlighted > 0) {
            highlighted--;
        }
    }

    private void incrementHighlighted() {
        if (highlighted < maxHighlighted - 1) {
            highlighted++;
        }
    }


    @Override
    public void paintComponent(Graphics g) {
         if (display) {
             Graphics2D g2d = (Graphics2D) g.create();

             g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                     RenderingHints.VALUE_ANTIALIAS_ON);

             g2d.setColor(Color.GRAY);
             g2d.fillRect(0, STATS_OFFSET, this.getWidth(), this.getHeight() - STATS_OFFSET);

             g2d.setColor(Color.BLACK);
             g2d.setStroke(new BasicStroke(5));

             g2d.setColor(Color.BLACK);
             g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
             g2d.drawString("Size: " + getWidth() + "x" + getHeight(), 100, 100);
             g2d.drawString("Ability Points: " + skillTree.getAbilityPoints(), (getWidth() / 3) * 2 + 135, 175);
             if (occupation.equalsIgnoreCase("Smasher")) {
                 paintSmasherSkills(g2d,(getWidth() / 3) * 2, getHeight() - STATS_OFFSET);
                 Image img = SmasherSprite.smasherDown1.getScaledInstance(400,400,BufferedImage.TYPE_INT_ARGB);
                 g2d.drawImage(img, (getWidth() / 3) * 2 , 175, null);
             } else if (occupation.equalsIgnoreCase("Summoner")) {
                 paintSummonerSkills(g2d,(getWidth() / 3) * 2, getHeight() - STATS_OFFSET);
                 Image img = WizardSprite.wizardDown1.getScaledInstance(400,400,BufferedImage.TYPE_INT_ARGB);
                 g2d.drawImage(img, (getWidth() / 3) * 2 , 200, null);
             } else if (occupation.equalsIgnoreCase("Sneak")) {
                 paintSneakSkills(g2d,(getWidth() / 3) * 2, getHeight() - STATS_OFFSET);
                 Image img = SneakSprite.sneakDown1.getScaledInstance(400,400,BufferedImage.TYPE_INT_ARGB);
                 g2d.drawImage(img, (getWidth() / 3) * 2 , 175, null);
             }
             g2d.dispose();
         }
    }

    private void paintSmasherSkills(Graphics2D g2d, int width, int height) {
        int marginBetweenSkills = (width - 4 * SKILL_IMAGE_WIDTH) / 5;
        int topMargin = (height - 2 * SKILL_IMAGE_HEIGHT) / 3;
        int sideMarginForThreeSkills = (width - 3 * SKILL_IMAGE_HEIGHT - 2 * marginBetweenSkills) / 2;
        if (highlighted == 0) {
            g2d.drawImage(SkillsSprite.bindWoundsSelected, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("BindWounds").getSkillLevel(),
                    sideMarginForThreeSkills + 7, topMargin + 41);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.oneHandedWeapon,sideMarginForThreeSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.twoHandedWeapon, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.brawling, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 1) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargainSelected, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Bargain").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH + 7, topMargin + 41);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.oneHandedWeapon,sideMarginForThreeSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.twoHandedWeapon, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.brawling, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 2) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observationSelected, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Observation").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2 + 7, topMargin + 41);

            g2d.drawImage(SkillsSprite.oneHandedWeapon,sideMarginForThreeSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.twoHandedWeapon, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.brawling, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 3) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.oneHandedWeaponSelected,sideMarginForThreeSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("OneHandedWeapon").getSkillLevel(),
                    sideMarginForThreeSkills + 7, topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.twoHandedWeapon, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.brawling, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 4) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.oneHandedWeapon,sideMarginForThreeSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.twoHandedWeaponSelected, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("TwoHandedWeapon").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH + 7, topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.brawling, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.oneHandedWeapon,sideMarginForThreeSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.twoHandedWeapon, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.brawlingSelected, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Brawling").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2 + 7, topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
        }

    }

    private void paintSummonerSkills(Graphics2D g2d, int width, int height) {
        int marginBetweenSkills = (width - 4 * SKILL_IMAGE_WIDTH) / 5;
        int topMargin = (height - 2 * SKILL_IMAGE_HEIGHT) / 3;
        int sideMarginForThreeSkills = (width - 3 * SKILL_IMAGE_HEIGHT - 2 * marginBetweenSkills) / 2;

        if (highlighted == 0) {
            g2d.drawImage(SkillsSprite.bindWoundsSelected, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("BindWounds").getSkillLevel(),
                    sideMarginForThreeSkills + 7, topMargin + 41);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.enchantment, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.boon, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bane, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.staff, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 1) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargainSelected, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Bargain").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH + 7, topMargin + 41);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.enchantment, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.boon, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bane, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.staff, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 2) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observationSelected, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Observation").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2 + 7, topMargin + 41);

            g2d.drawImage(SkillsSprite.enchantment, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.boon, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bane, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.staff, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 3) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.enchantmentSelected, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("EnchantingBlast").getSkillLevel(),
                    marginBetweenSkills + 7, topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.boon, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bane, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.staff, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 4) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.enchantment, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.boonSelected, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("BoonHardiness").getSkillLevel(),
                    marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH + 7,  topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.bane, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.staff, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 5) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.enchantment, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.boon, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.baneSelected, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("BoonHardiness").getSkillLevel(),
                    marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2 + 7,  topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.staff, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 6) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.enchantment, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.boon, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bane, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.staffSelected, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Staff").getSkillLevel(),
                    marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3 + 7,  topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
        }
    }

    private void paintSneakSkills(Graphics2D g2d, int width, int height) {
        int marginBetweenSkills = (width - 4 * SKILL_IMAGE_WIDTH) / 5;
        int topMargin = (height - 2 * SKILL_IMAGE_HEIGHT) / 3;
        int sideMarginForThreeSkills = (width - 3 * SKILL_IMAGE_HEIGHT - 2 * marginBetweenSkills) / 2;

        if (highlighted == 0) {
            g2d.drawImage(SkillsSprite.bindWoundsSelected, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("BindWounds").getSkillLevel(),
                    sideMarginForThreeSkills + 7, topMargin + 41);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.pickPocket, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.detectAndRemoveTrap, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.creep, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.rangedWeapon, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 1) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargainSelected, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Bargain").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH + 7, topMargin + 41);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.pickPocket, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.detectAndRemoveTrap, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.creep, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.rangedWeapon, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 2) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observationSelected, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Observation").getSkillLevel(),
                    sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2 + 7, topMargin + 41);

            g2d.drawImage(SkillsSprite.pickPocket, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.detectAndRemoveTrap, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.creep, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.rangedWeapon, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 3) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.pickPocketSelected, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("PickPocket").getSkillLevel(),
                    marginBetweenSkills + 7, topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.detectAndRemoveTrap, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.creep, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.rangedWeapon, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 4) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.pickPocket, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.detectAndRemoveTrapSelected, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("DetectRemoveTrap").getSkillLevel(),
                    marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH + 7,  topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.creep, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.rangedWeapon, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 5) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.pickPocket, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.detectAndRemoveTrap, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.creepSelected, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("Creep").getSkillLevel(),
                    marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2 + 7,  topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
            g2d.drawImage(SkillsSprite.rangedWeapon, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
        } else if (highlighted == 6) {
            g2d.drawImage(SkillsSprite.bindWounds, sideMarginForThreeSkills, topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.bargain, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH,
                    topMargin + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.observation, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2,
                    topMargin + STATS_OFFSET, null);

            g2d.drawImage(SkillsSprite.pickPocket, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.detectAndRemoveTrap, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.creep, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawImage(SkillsSprite.rangedWeaponSelected, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3,
                    topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET, null);
            g2d.drawString("Current level: " + skillTree.getSkill("RangedWeapon").getSkillLevel(),
                    marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3 + 7,  topMargin * 2 + SKILL_IMAGE_HEIGHT + 41);
        }
    }
}
