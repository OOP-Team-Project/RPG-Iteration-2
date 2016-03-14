package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.SpriteLoader;
import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

/**
 * Created by rokas on 3/12/16.
 */
public class SkillsSprite {
    public static BufferedImage bindWounds, bindWoundsSelected,
                                bargain, bargainSelected,
                                observation, observationSelected,
                                oneHandedWeapon, oneHandedWeaponSelected,
                                twoHandedWeapon, twoHandedWeaponSelected,
                                brawling, brawlingSelected,
                                enchantment, enchantmentSelected,
                                boon, boonSelected,
                                bane, baneSelected,
                                staff, staffSelected,
                                pickPocket, pickPocketSelected,
                                detectAndRemoveTrap, detectAndRemoveTrapSelected,
                                creep, creepSelected,
                                rangedWeapon, rangedWeaponSelected;

    //needs to be called once
    public static void init(){
        SpriteSheet skillSheet1 = new SpriteSheet(SpriteLoader.loadImage("/textures/Skills1.png"));
        SpriteSheet skillSheet2 = new SpriteSheet(SpriteLoader.loadImage("/textures/Skills2.png"));
        SpriteSheet skillSheet3 = new SpriteSheet(SpriteLoader.loadImage("/textures/Skills3.png"));

        bindWoundsSelected = skillSheet1.cropSheet(0,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        bindWounds = skillSheet1.cropSheet(StaticVar.skillImageWidth,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        bargainSelected = skillSheet1.cropSheet(StaticVar.skillImageWidth * 2,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        bargain = skillSheet1.cropSheet(StaticVar.skillImageWidth * 3,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        observationSelected = skillSheet1.cropSheet(StaticVar.skillImageWidth * 4,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        observation = skillSheet1.cropSheet(StaticVar.skillImageWidth * 5,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        detectAndRemoveTrapSelected = skillSheet1.cropSheet(StaticVar.skillImageWidth * 6,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        detectAndRemoveTrap = skillSheet1.cropSheet(StaticVar.skillImageWidth * 7,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        oneHandedWeaponSelected = skillSheet1.cropSheet(StaticVar.skillImageWidth * 8,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        oneHandedWeapon = skillSheet1.cropSheet(StaticVar.skillImageWidth * 9,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        twoHandedWeaponSelected = skillSheet1.cropSheet(StaticVar.skillImageWidth * 10,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        twoHandedWeapon = skillSheet1.cropSheet(StaticVar.skillImageWidth * 11,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);

        brawling = skillSheet2.cropSheet(0,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        brawlingSelected = skillSheet2.cropSheet(StaticVar.skillImageWidth,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        enchantment = skillSheet2.cropSheet(StaticVar.skillImageWidth * 2,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        enchantmentSelected = skillSheet2.cropSheet(StaticVar.skillImageWidth * 3,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        boon = skillSheet2.cropSheet(StaticVar.skillImageWidth * 4,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        boonSelected = skillSheet2.cropSheet(StaticVar.skillImageWidth * 5,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        bane = skillSheet2.cropSheet(StaticVar.skillImageWidth * 6,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        baneSelected = skillSheet2.cropSheet(StaticVar.skillImageWidth * 7,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        staff = skillSheet2.cropSheet(StaticVar.skillImageWidth * 8,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        staffSelected = skillSheet2.cropSheet(StaticVar.skillImageWidth * 9,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);

        pickPocket = skillSheet3.cropSheet(0,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        pickPocketSelected = skillSheet3.cropSheet(StaticVar.skillImageWidth,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        creep = skillSheet3.cropSheet(StaticVar.skillImageWidth * 2,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        creepSelected = skillSheet3.cropSheet(StaticVar.skillImageWidth * 3,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        rangedWeapon = skillSheet3.cropSheet(StaticVar.skillImageWidth * 4,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
        rangedWeaponSelected = skillSheet3.cropSheet(StaticVar.skillImageWidth * 5,0, StaticVar.skillImageWidth, StaticVar.skillImageHeight);
    }
}
