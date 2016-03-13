package com.TigersIter2.managers;

import com.TigersIter2.entities.*;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.skills.*;
import com.TigersIter2.stats.NPCStatsModifier;
import com.TigersIter2.views.FooterView;
import com.TigersIter2.views.MessageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class AvatarNPCInteract implements ActionListener{

    private Avatar avatar;
    private NPC npcOnTile;
    private List<NPC> npcList;
    private List<Vehicle> vehicleList;
    private List<String> questions;
    private List<String> originalOptions;
    private FooterView footerView;
    private boolean talking, skillMain, skillSecondary, usingItem, pressContinue, trading, notFromInteraction;
    private Inventory playerSelectedInventory;
    private Inventory npcSelectedInventory;
    private boolean playerCanAttack = true;
    private Timer playerAttackTimer;
    private List<Timer> npcAttackTimers;
    private int whichSkillSelect;

    public AvatarNPCInteract(Avatar a, FooterView fv){
        avatar = a;
        npcList = new ArrayList<NPC>();
        vehicleList = new ArrayList<Vehicle>();
        npcOnTile = null;
        footerView = fv;
        talking = false;
        skillMain = false;
        skillSecondary = false;
        usingItem = false;
        trading = false;
        pressContinue = false;
        notFromInteraction = false;
        questions = new ArrayList<String>();
        originalOptions = new ArrayList<String>();
        playerSelectedInventory = new Inventory();
        npcSelectedInventory = new Inventory();
        playerAttackTimer = new Timer(avatar.getAttackTime(), this);
        npcAttackTimers = new ArrayList<Timer>();
        for(NPC n : npcList){
            npcAttackTimers.add(new Timer(n.getAttackTime(), this));
        }

        playerAttackTimer.start();
        for(Timer t : npcAttackTimers){
            t.start();
        }
        fillQuestions();
        fillOriginalOptions();
    }

    private void fillQuestions(){
        questions.add("Who are you?");
        questions.add("What are you doing here?");
        questions.add("Do you like Tigers?");
        questions.add("What is a Dave?");
        questions.add("Would you like to trade?");
    }

    private void fillOriginalOptions(){
        originalOptions.add("Talk");
        originalOptions.add("Attack");
        originalOptions.add("Use Skill");
        originalOptions.add("Use Item");
    }

    public void actionPerformed(ActionEvent e) {
        if(!playerCanAttack) {
            playerCanAttack = true;
        }

        for(NPC n : npcList){
            if(!n.getCanAttack()) {
                n.setCanAttack(true);
            }
        }

    }

    private boolean enemyInRange(NPC n){
        //somehow determine if npc is in range based off of direction and attack range and such
        int xDist = Math.abs(LocationConverter.PixelLocationToHex(n.getLocation()).getX() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getX());
        int yDist = Math.abs(LocationConverter.PixelLocationToHex(n.getLocation()).getY() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getY());

        if(xDist <= avatar.getInfluenceRadius() && yDist <= avatar.getInfluenceRadius())
            return true;
        else
            return false;
    }

    private boolean playerInRange(NPC n){
        //somehow determine if npc is in range based off of direction and attack range and such
        int xDist = Math.abs(LocationConverter.PixelLocationToHex(n.getLocation()).getX() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getX());
        int yDist = Math.abs(LocationConverter.PixelLocationToHex(n.getLocation()).getY() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getY());
        if(xDist <= n.getInfluenceRadius() && yDist <= n.getInfluenceRadius())
            return true;
        else
            return false;
    }

    private boolean inLinearRange(NPC npc) {
        boolean ret = false;
        int dir = avatar.getDirection();
        int xDist = LocationConverter.PixelLocationToHex(npc.getLocation()).getX() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getX();
        int yDist = LocationConverter.PixelLocationToHex(npc.getLocation()).getY() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getY();
        int xTile = LocationConverter.PixelLocationToHex(npc.getLocation()).getX();

        if (xDist == 0) {
            if (yDist < 0 && dir == 90)
                ret = true;
            else if (yDist == 0)
                ret = true;
            else if (yDist > 0 && dir == 270)
                ret = true;
        }

        if (xTile % 2 == 0) {    //Even
            if (xDist < 0) {
                if (yDist < 0 && dir == 135 && Math.abs(xDist / 2) == Math.abs(yDist))
                    ret = true;
                else if(yDist == 0 && dir == 135 && Math.abs(xDist) == Math.abs(yDist)+1)
                    ret = true;
                else if (yDist > 0 && dir == 225 && (Math.abs(xDist)+1) / 2 == Math.abs(yDist))
                    ret = true;
            }
            else if (xDist > 0) {
                if (yDist < 0 && dir == 45 && xDist / 2 == Math.abs(yDist))
                    ret = true;
                else if(yDist == 0 && dir == 45 && xDist == Math.abs(yDist)+1)
                    ret = true;
                else if (yDist > 0 && dir == 315 && (xDist+1) / 2 == Math.abs(yDist))
                    ret = true;
            }
        }
        else{
            if (xDist < 0) {
                if (yDist < 0 && dir == 135 && (Math.abs(xDist)+1) / 2 == Math.abs(yDist))
                    ret = true;
                else if(yDist == 0 && dir == 225 && Math.abs(xDist) == Math.abs(yDist)+1)
                    ret = true;
                else if (yDist > 0 && dir == 225 && Math.abs(xDist / 2) == Math.abs(yDist))
                    ret = true;
            }
            else if (xDist > 0) {
                if (yDist < 0 && dir == 45 && (xDist+1) / 2 == Math.abs(yDist))
                    ret = true;
                else if(yDist == 0 && dir == 315 && xDist == Math.abs(yDist)+1)
                    ret = true;
                else if (yDist > 0 && dir == 315 && xDist / 2 == Math.abs(yDist))
                    ret = true;
            }
        }
        return ret;
    }

    private boolean inAngularRange(NPC npc){
        boolean ret = false;
        int dir = avatar.getDirection();
        int xDist = LocationConverter.PixelLocationToHex(npc.getLocation()).getX() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getX();
        int yDist = LocationConverter.PixelLocationToHex(npc.getLocation()).getY() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getY();
        int xTile = LocationConverter.PixelLocationToHex(npc.getLocation()).getX();
        int range = avatar.getStats().getInfluenceRadius();

        if(dir == 45){
            if(xDist == 0)
                ret = (yDist == 0);
            else if(yDist > 0)
                ret = false;
            else if(xDist < 0)
                ret = false;
            else if(yDist == 0 && xDist < 1)
                ret = false;
            else if(Math.abs(yDist/xDist) <= 2 && xTile % 2 == 1 && Math.abs(xDist) <= range) {
                if(-yDist <= range)
                    ret = true;
            }
            else if(Math.abs(yDist/xDist) <= 1 && xTile % 2 == 0 && -yDist <= range && xDist <= range){
                ret = true;
            }
        }
        else if(dir == 90){
            if(yDist == 0)
                ret = (xDist == 0);
            else if(yDist > 0)
                ret = false;
            else if(xDist == 0)
                ret = (-yDist <= range);
            else if(Math.abs(xDist/yDist) < 1 && xTile % 2 == 1 && Math.abs(xDist) < range) {
                if(-yDist <= range)
                    ret = true;
            }
            else if(Math.abs(xDist/yDist) <= 1 && xTile % 2 == 0 && yDist <= range && xDist < range){
                if(Math.abs(yDist) == Math.abs(xDist) && Math.abs(yDist) > 1)
                    ret = false;
                else
                    ret = true;
            }
        }

        else if(dir == 135){
            if(xDist == 0)
                ret = (yDist == 0);
            else if(yDist > 0)
                ret = false;
            else if(xDist > 0)
                ret = false;
            else if(yDist == 0 && -xDist >= 1 && xTile % 2 == 1)
                ret = false;
            else if(Math.abs(yDist/xDist) <= 2 && xTile % 2 == 1 && -yDist <= range ) {
                if(-xDist <= range)
                    ret = true;
                else if(-xDist <= range+1 && -yDist <= 1)
                    ret = true;
            }
            else if(Math.abs(yDist/xDist) <= 1 && xTile % 2 == 0 && -yDist <= range){
                if(-xDist <= range+1 && -yDist <= 1)
                    ret = true;
            }
        }

        else if(dir == 270){
            if(yDist == 0)
                ret = (xDist == 0);
            else if(yDist < 0)
                ret = false;
            else if(xDist == 0)
                ret = (yDist <= range);
            else if(Math.abs(xDist/yDist) < 1 && xTile % 2 == 0 && Math.abs(xDist) < range) {
                if(yDist <= range)
                    ret = true;
                else if(yDist <= range+1 & Math.abs(xDist) < range-1)
                    ret = true;
            }
            else if(Math.abs(xDist/yDist) <= 1 && xTile % 2 == 1 && yDist <= range && xDist < range){
                if(Math.abs(yDist) == Math.abs(xDist) && Math.abs(yDist) > 1)
                    ret = false;
                else
                    ret = true;
            }
        }

        else if(dir == 225){
            if(xDist == 0)
                ret = (yDist == 0);
            else if(yDist < 0)
                ret = false;
            else if(xDist > 0)
                ret = false;
            else if(Math.abs(yDist/xDist) <= 2 && xTile % 2 == 1 && yDist <= range ) {
                if(-xDist <= range)
                    ret = true;
                else if(-xDist <= range+1 && yDist <= 1)
                    ret = true;
            }
            else if(Math.abs(yDist/xDist) <= 2 && xTile % 2 == 0 && yDist <= range){
                if(yDist == 0)
                    ret = (-xDist > 1);
                else if(-xDist <= range+1 && yDist <= 1)
                    ret = true;
                else if (-xDist <= range)
                    ret = true;
            }
        }
        if(dir == 315){
            if(xDist == 0)
                ret = (yDist == 0);
            else if(yDist < 0)
                ret = false;
            else if(xDist < 0)
                ret = false;
            else if(yDist == 0 && xDist <= 1)
                ret = false;
            else if(Math.abs(yDist/xDist) <= 1 && xTile % 2 == 1) {
                if(Math.abs(xDist) <= range+1 && yDist <= 1)
                    ret = true;
                else if(Math.abs(xDist) <= range && yDist <= range)
                    ret = true;
            }
            else if(Math.abs(yDist/xDist) <= 2 && xTile % 2 == 0 && yDist <= range){
                if(xDist <= range+1 && yDist <= 1)
                    ret = true;
                else if(xDist <= range)
                        ret= true;
            }
        }

        return ret;
    }

    private int inRadialRange(NPC npc){
        int ret = avatar.getStats().getInfluenceRadius()+1;
        int xDist = LocationConverter.PixelLocationToHex(npc.getLocation()).getX() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getX();
        int yDist = LocationConverter.PixelLocationToHex(npc.getLocation()).getY() - LocationConverter.PixelLocationToHex(avatar.getLocation()).getY();
        int xTile = LocationConverter.PixelLocationToHex(npc.getLocation()).getX();
        int range = avatar.getStats().getInfluenceRadius();

        if(yDist > 0 && xTile % 2 == 1)
            yDist += (Math.abs(xDist)+1)/2;
        else if(yDist > 0 && xTile % 2 == 0)
            yDist += Math.abs(xDist)/2;
        else if(yDist < 0 && xTile % 2 == 1)
            yDist -= (Math.abs(xDist))/2;
        else if(yDist < 0 && xTile % 2 == 0)
            yDist -= (Math.abs(xDist)+1)/2;

        xDist = Math.abs(xDist);
        yDist = Math.abs(yDist);
        if(xDist <= range && yDist <= range){
            if(xDist >= yDist)
                ret = xDist;
            else
                ret = yDist;
        }
        return ret;
    }

    private int withinInfluenceRadius(String type, NPC npc){
        if(enemyInRange(npc)) {
            if (type.equals("linear") && inLinearRange(npc))
                return 0;
            else if (type.equals("angular") && inAngularRange(npc))
                return 0;
            else if (type.equals("radial"))
                return inRadialRange(npc);
            else
                return -1;
        }
        else
            return -1;
    }

    private int getObservationError(){
        int observed = 0;
        int error;
        double accuracy = ((Observation)avatar.getSkills().getSkill("Observation")).getAccuracy();
        Random rand = new Random();
        error = rand.nextInt(10 - (int)(accuracy*10));
        if(Math.random() < 0.5)
            error = -error;
        return error;
    }

    public void attack(){
        int observed = 0;
        int error = getObservationError();
        if(playerCanAttack) {
            playerCanAttack = false;
            //Check your position/direction/range against the NPC's in the list
            Iterator<NPC> iter = npcList.iterator();
            while (iter.hasNext()) {
                NPC npc = iter.next();
                if(npc.isAlive()) {
                    if (enemyInRange(npc)) {
                        String weaponType = avatar.getWeaponType();
                        if (weaponType.equals("none")) {
                            if(avatar.getOccupation().toString().equals("Sneak") && ((Creep)avatar.getSkills().getSkill("Creep")).isActive()
                                    && inRadialRange(npc) == 0) {
                                npc.getStats().decreaseCurrentLife(15);
                                System.out.println(npc.getStats().getCurrentLife() + "/" + npc.getStats().getLife());
                                if (npc.isAlive()) {
                                    retaliate(npc);
                                } else {
                                    killNPC(npc);
                                    resetOptions();
                                }
                                MessageView.addMessage("Backstabbed!", npc.getPixelLocation().getX(), npc.getPixelLocation().getY()-20);
                            }
                            else
                                System.out.println("Not holding a weapon, can't attack");
                        } else if (weaponType.equals("RangedWeapon")) {
                            // Ranged attack
                            if (inLinearRange(npc)) {
                                observed = attackEnemy(npc);
                                observed += error;
                                MessageView.addMessage("-"+Integer.toString(observed), npc.getPixelLocation().getX(), npc.getPixelLocation().getY());
                            }
                        } else {
                            // Melee attack
                            observed = attackEnemy(npc);
                            observed += error;
                            MessageView.addMessage("-"+Integer.toString(observed), npc.getPixelLocation().getX(), npc.getPixelLocation().getY());
                        }
                    }
                }
            }
            MessageView.drawMessage();
        }
    }

    private void useBoon(String spellName){
        ((Boon)avatar.getSkills().getSkill(spellName)).activate();
    }

    private void useEnchantment(String spellName){
        if(playerCanAttack) {
            playerCanAttack = false;
            //Check your position/direction/range against the NPC's in the list
            Iterator<NPC> iter = npcList.iterator();
            while (iter.hasNext()) {
                NPC npc = iter.next();
                if(npc.isAlive()) {
                    if(withinInfluenceRadius(avatar.getSkills().getSkill(spellName).getInfluenceRadiusType(), npc) > -1){
                    Enchantment spell = (Enchantment)avatar.getSkills().getSkill(spellName);
                    NPCStatsModifier nsm = spell.getStatModifier();
                    if(nsm.isEmpty())
                        npc.setWillAttack(true);
                    else
                        npc.getStats().addStatModifier(nsm);
                }

                }
            }
        }
    }

    private void useBane(String spellName){
        int error = getObservationError();
        if(playerCanAttack) {
            playerCanAttack = false;
            //Check your position/direction/range against the NPC's in the list
            Iterator<NPC> iter = npcList.iterator();
            while (iter.hasNext()) {
                NPC npc = iter.next();
                if(npc.isAlive()) {
                    int radialRing;
                    if((radialRing = (withinInfluenceRadius(avatar.getSkills().getSkill(spellName).getInfluenceRadiusType(), npc))) > -1) {
                        int damage = avatar.getSkills().getSkill(spellName).getDamage() - npc.getStats().getDefensiveRating() + npc.getStats().getArmorRating();
                        damage = damage - (radialRing * (damage/(avatar.getInfluenceRadius()+1)));     //Accounts for lessening damage the farther from center you go
                        Random rand = new Random();
                        if (damage > 0) {
                            damage = rand.nextInt(damage);
                            MessageView.addMessage("-"+Integer.toString(damage + error), npc.getPixelLocation().getX(), npc.getPixelLocation().getY());
                            npc.getStats().decreaseCurrentLife(damage);
                            System.out.println("Dealt " + damage + " damage");
                        } else {
                            System.out.println("MISS");
                        }
                        System.out.println(npc.getStats().getCurrentLife() + "/" + npc.getStats().getLife());

                        if (npc.isAlive()) {
                            retaliate(npc);
                        } else {
                            killNPC(npc);
                            resetOptions();
                        }
                    }

                }
            }
            MessageView.drawMessage();
        }
    }

    private int attackEnemy(NPC npc){
        String weaponType = avatar.getWeaponType();
        Random rand = new Random();
        int damage = avatar.getSkills().getSkill(weaponType).getDamage() - npc.getStats().getDefensiveRating() + npc.getStats().getArmorRating();
        if (damage > 0) {
            damage = rand.nextInt(damage);
            npc.getStats().decreaseCurrentLife(damage);
            System.out.println("Dealt " + damage + " damage");
        } else {
            System.out.println("MISS");
        }
        System.out.println(npc.getStats().getCurrentLife() + "/" + npc.getStats().getLife());

        if (npc.isAlive()) {
            retaliate(npc);
        } else {
            killNPC(npc);
            resetOptions();
        }
        return damage;
    }

    private void retaliate(NPC npc){
        if(npc.getCanAttack()) {
            if(playerInRange(npc)) {
                footerView.setDisplay(false);
                footerView.setTradingView(false);
                avatar.setTrading(trading);
                npc.setWillAttack(true);
                npc.setCanAttack(false);
                Random rand = new Random();
                int attackAttempts = npc.getStats().getOffensiveRating() / 2 + 1;
                int randNumMax = avatar.getStats().getDefensiveRating() + avatar.getStats().getArmor() + 1;
                int numToHit = rand.nextInt(randNumMax);
                boolean hit = false;
                while (!hit && attackAttempts > 1) {
                    --attackAttempts;
                    if (rand.nextInt(attackAttempts) == numToHit)
                        hit = true;
                }

                if (hit) {
                    //TESTING
                    int damage = rand.nextInt(npc.getStats().getOffensiveRating() + 1);
                    System.out.println("You lost " + damage + " health!");
                    //END TESTING

                    avatar.getStats().decreaseCurrentLife(damage);
                    if (avatar.getStats().getCurrentLife() <= 0)
                        System.out.println("You died!!");

                } else
                    System.out.println("NPC missed!");
            }
        }
    }

    private void killNPC(NPC npc){
        System.out.println("You killed the NPC!");

        avatar.getStats().addExperience(npc.getStats().getLife());
        npc.dropItems();
        avatar.setOnTileWithNPC(false);
        npc.setOnTileWithAvatar(false);
        resetOptions();
    }

    public void addVehicle(Vehicle v){
        vehicleList.add(v);
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void mountVehicle(){
        for(Vehicle v : vehicleList) {
            if (LocationConverter.PixelLocationToHex(v.getLocation()).getX() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(v.getLocation()).getY() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getY()) {
                if (avatar.getVehicle() == null) {
                    v.setLocation(avatar.getLocation());
                    avatar.mountOrUnmountVehicle(v);
                    return;
                }
                else{
                    avatar.mountOrUnmountVehicle(avatar.getVehicle());
                    return;
                }
            }
        }
    }

    public void chooseOption(int selected){
        //Do something in a menu with the number you selected
        //This is to be used when interacting with an NPC on the same tile
        if(trading){
            if (selected == 100) {
                footerView.setType(0);
                footerView.setMenuOptions(originalOptions);
                resetOptions();
            }
        }
        else if(notFromInteraction && (skillMain || skillSecondary))
            chooseSkills(selected);
        else {
            if (avatar.getOnTileWithNPC()) {
                if (talking) {
                    haveConversation(selected);
                } else if (skillMain || skillSecondary) {
                    chooseSkills(selected);
                } else if (usingItem) {
                    chooseItem(selected);
                } else if (selected == 1) {
                    if (npcOnTile.willTalk()) {
                        haveConversation(0);
                    }
                    else if(npcOnTile.willTrade()){
                        //If NPC won't talk, but will trade, default to trading screen
                        haveConversation(5);
                    }
                    else if(npcOnTile.willAttack()){
                        //If NPC won't talk/trade, but will attack, NPC attacks the player
                        retaliate(npcOnTile);
                    }
                } else if (selected == 2) {
                    attack();
                } else if (selected == 3) {
                    drawSkillMenu();
                } else if (selected == 4) {
                    useItem();
                } else if (selected == 100) {
                    footerView.setType(0);
                    footerView.setMenuOptions(originalOptions);
                } else {
                    //Do something
                }
            }
        }
        footerView.setTradingView(trading);
    }

    private void haveConversation(int selected){
        talking = true;
        if(pressContinue) {
            if(selected == 100) {
                selected = 0;
                pressContinue = false;
            }
            else
                return;
        }

        List<String> response = new ArrayList<String>();
        footerView.setType(1);

        if(selected == 1)
            response.add(npcOnTile.getResponse(0));
        else if(selected == 2)
            response.add(npcOnTile.getResponse(1));
        else if(selected == 3)
            response.add(npcOnTile.getResponse(2));
        else if(selected == 4)
            response.add(npcOnTile.getResponse(3));
        else if(selected == 5) {
            response.add(npcOnTile.getResponse(4));
            if(npcOnTile.willTrade()) {
                trading = true;
                avatar.setTrading(trading);
                footerView.setDiscount(avatar.getStats().getBarter());
                footerView.setPlayerInventory(avatar.getInventory());
                footerView.setNpcInventory(npcOnTile.getInventory());
                //START TRADING
            }
        }
        else if(selected == 100){
            footerView.setType(0);
            footerView.setMenuOptions(originalOptions);
            talking = false;
            return;
        }
        else{
            footerView.setType(0);
            footerView.setMenuOptions(questions);
            return;
        }
        footerView.setMenuOptions(response);
        pressContinue = true;
    }

    public void addMonster(){
        NPC m = new Monster();

        //TESTING
        m.getLocation().setX(avatar.getLocation().getX()+50);
        m.getLocation().setY(avatar.getLocation().getY()+130);
        m.getStats().setHardiness(20);
        m.getStats().setArmor(3);
        m.getStats().setStrength(13);
        m.getStats().setAttack(20);
        m.setAttackTime(1000);
        //END TESTING

        npcList.add(m);
        Timer t = new Timer(m.getAttackTime(), this);
        npcAttackTimers.add(t);
        t.start();
    }

    public void addVillager(List<String> p, boolean talk, boolean trade, boolean attack){
        NPC v = new Villager(p, talk, trade, attack);
        //v.getInventory().addItem(new OneHandedWeaponItem("Sword",5));
        npcList.add(v);
        v.getStats().setCurrentLife(30);
        v.getLocation().setX(avatar.getLocation().getX()-50);
        v.getLocation().setY(avatar.getLocation().getY());
    }

    public List<NPC> getNpcList(){
        return npcList;
    }

    public void checkTile(){
        for(NPC n : npcList){
            if(n.isAlive()) {
                if (LocationConverter.PixelLocationToHex(n.getLocation()).getX() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getX() &&
                        LocationConverter.PixelLocationToHex(n.getLocation()).getY() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getY()) {
                    if (!avatar.getOnTileWithNPC()) {
                        resetOptions();
                        avatar.setOnTileWithNPC(true);
                        notFromInteraction = false;
                        npcOnTile = n;
                        npcOnTile.setOnTileWithAvatar(true);
                        if (npcOnTile.willTalk() || npcOnTile.willTrade()) {
                            footerView.setDisplay(true);
                            footerView.setType(0);
                            footerView.setMenuOptions(originalOptions);
                        }
                    }

                } else if (avatar.getOnTileWithNPC()) {
                    if (n.getOnTileWithAvatar()) {
                        n.setOnTileWithAvatar(false);
                        resetOptions();
                    }
                }

                if (n.willAttack()) {
                    //NPC attacks player
                    if(!(avatar.getOccupation().toString().equals("Sneak") && ((Creep)avatar.getSkills().getSkill("Creep")).isActive()))
                        retaliate(n);
                }
            }
        }
    }

    public void navigateTradeMenu(int input){
        if(input != -1)
            footerView.setTooExpensive(false);
        if(input == 0){
            footerView.decrementHighlighted();
        }
        else if(input == 1){
            footerView.incrementHighlighted();
        }
        else if(input == 2){
            footerView.decrementWhoseSide();
        }
        else if(input == 3){
            footerView.incrementWhoseSide();
        }
        else if(input == 4){
            int index = footerView.selectItem();
            if(footerView.getWhoseSide() == 0){
                if(playerSelectedInventory.getItems().contains(avatar.getInventory().getItemAtIndex(index)))
                    playerSelectedInventory.getItems().remove(avatar.getInventory().getItemAtIndex(index));
                else
                    playerSelectedInventory.addItem(avatar.getInventory().getItemAtIndex(index));
            }
            else if(footerView.getWhoseSide() == 1){
                if(footerView.getPlayerValue() >= footerView.getNpcValue()) {
                    System.out.println(avatar.getStats().getBarter());
                    for (TakeableItem item : playerSelectedInventory.getItems()) {
                        npcOnTile.getInventory().addItem(item);
                        avatar.getInventory().getItems().remove(item);
                    }
                    for (TakeableItem item : npcSelectedInventory.getItems()) {
                        avatar.getInventory().addItem(item);
                        npcOnTile.getInventory().getItems().remove(item);
                    }
                    clearSelectedInventories();
                    footerView.resetTrade();
                }
                else{
                    footerView.setTooExpensive(true);
                }
            }
            else if(footerView.getWhoseSide() == 2){
                if(npcSelectedInventory.getItems().contains(npcOnTile.getInventory().getItemAtIndex(index)))
                    npcSelectedInventory.getItems().remove(npcOnTile.getInventory().getItemAtIndex(index));
                else
                    npcSelectedInventory.addItem(npcOnTile.getInventory().getItemAtIndex(index));
            }
        }
        else if(input == 5){
            trading = false;
            avatar.setTrading(trading);
            clearSelectedInventories();
            footerView.resetTrade();
            resetOptions();
        }
    }

    public void startSkillsNotFromInteraction(){
        if(!notFromInteraction) {
            footerView.setDisplay(true);
            notFromInteraction = true;
            drawSkillMenu();
        }
        else {
            notFromInteraction = false;
            resetOptions();
        }
    }

    private void drawSkillMenu(){
        footerView.setType(0);
        List<String> skillList = new ArrayList<String>();
        skillList.add("Bind wounds");
        skillList.add("Observe");
        for(String skill : avatar.getSkills().getMainSkillList())
            skillList.add(skill);
        footerView.setMenuOptions(skillList);
        skillMain = true;
    }

    private void chooseSkills(int selected){
        if(skillMain){
            if(selected == 1){
                if(((BindWounds)avatar.getSkills().getSkill("BindWounds")).activate())
                    MessageView.addMessage("Wounds bound");
            }
            else if(selected == 2){
                if(avatar.getOnTileWithNPC()) {
                    int current = npcOnTile.getStats().getCurrentLife() + getObservationError();
                    int total = npcOnTile.getStats().getLife() + getObservationError();
                    MessageView.addMessage(Integer.toString(current) + "/" + Integer.toString(total), npcOnTile.getPixelLocation().getX(), npcOnTile.getPixelLocation().getY());
                }
            }
            else if(selected == 3){
                if(avatar.getOccupation().toString().equals("Summoner")){
                    footerView.setType(0);
                    List<String> skillList = new ArrayList<String>();
                    skillList.add("Fire Shot");
                    skillList.add("Fire Blast");
                    skillList.add("Fire Storm");
                    footerView.setMenuOptions(skillList);
                    skillSecondary = true;
                    skillMain = false;
                    whichSkillSelect = 0;
                }
                else if(avatar.getOccupation().toString().equals("Sneak")){
                    if(avatar.getOnTileWithNPC()){
                        pickpocket();
                    }
                    else
                        System.out.println("Nobody on tile to pickpocket");
                }
            }
            else if(selected == 4){
                if(avatar.getOccupation().toString().equals("Summoner")){
                    footerView.setType(0);
                    List<String> skillList = new ArrayList<String>();
                    skillList.add("Boon Hardiness");
                    skillList.add("Boon Health");
                    skillList.add("Boon Intellect");
                    footerView.setMenuOptions(skillList);
                    skillSecondary = true;
                    skillMain = false;
                    whichSkillSelect = 1;
                }
                else if(avatar.getOccupation().toString().equals("Sneak")){
                    if(((Creep)avatar.getSkills().getSkill("Creep")).activate())
                        MessageView.addMessage("Creeping...");
                }
            }
            else if(selected == 5){
                if(avatar.getOccupation().toString().equals("Summoner")){
                    footerView.setType(0);
                    List<String> skillList = new ArrayList<String>();
                    skillList.add("Enchanting Shot");
                    skillList.add("Enchanting Blast");
                    skillList.add("Enchanting Storm");
                    footerView.setMenuOptions(skillList);
                    skillSecondary = true;
                    skillMain = false;
                    whichSkillSelect = 2;
                }
            }
            else if (selected == 100 && !notFromInteraction) {
                resetOptions();
            }
            MessageView.drawMessage();
        }
        else{
            if(selected == 100){
                skillSecondary = false;
                skillMain = true;
                drawSkillMenu();
            }
            if(whichSkillSelect == 0){
                if(selected == 1)
                    useBane("FireShot");
                else if(selected == 2)
                    useBane("FireBlast");
                else if(selected == 3)
                    useBane("FireStorm");
            }
            else if(whichSkillSelect == 1){
                if(selected == 1)
                    useBoon("BoonHardiness");
                else if(selected == 2)
                    useBoon("BoonHealth");
                else if(selected == 3)
                    useBoon("BoonIntellect");
            }
            else if(whichSkillSelect == 2){
                if(selected == 1)
                    useEnchantment("EnchantingShot");
                else if(selected == 2)
                    useEnchantment("EnchantingBlast");
                else if(selected == 3)
                    useEnchantment("EnchantingStorm");
            }
        }
    }

    public void pickpocket(){
        int inventorySize = npcOnTile.getInventory().getItems().size();
        if(inventorySize > 0) {
            double probability = ((PickPocket) avatar.getSkills().getSkill("PickPocket")).getProbability();
            Random rand = new Random();
            int itemSlot = rand.nextInt(inventorySize);
            if (Math.random() < probability) {
                TakeableItem item = npcOnTile.getInventory().removeItemAtIndex(itemSlot);
                avatar.getInventory().addItem(item);
                System.out.println("Stole " + item.toString());
                MessageView.addMessage("Stole " + item.toString());
            }
            else {
                System.out.println("Pickpocket unsuccessful");
                MessageView.addMessage("Pickpocket failed");
            }
        }
        else {
            System.out.println("NPC has nothing to steal");
            MessageView.addMessage("Nothing to steal");
        }
        MessageView.drawMessage();

    }

    private void useItem(){
        List<String> usableItems = new ArrayList<String>();
        for(TakeableItem item : avatar.getInventory().getItems()){
            if(item.isUsable()){
                usableItems.add(item.toString());
            }
        }
        if(usableItems.size() > 0) {
            footerView.setType(0);
            footerView.setMenuOptions(usableItems);
            usingItem = true;
        }
        else{
            resetOptions();
        }
    }

    private void chooseItem(int selected){
        if(selected == 100) {
            resetOptions();
            return;
        }
        int count = 1;
        Iterator<TakeableItem> iter = avatar.getInventory().getItems().iterator();
        while(iter.hasNext()){
            TakeableItem item = iter.next();
            if(item.isUsable()){
                if(selected == count){
                    npcOnTile.getStats().increaseCurrentLife(item.getStatsModifier().getLife());        //only works for health potions
                    MessageView.addMessage("Used " + item.toString());
                    iter.remove();
                    useItem();
                    return;
                }
                else
                    ++count;
            }
        }
        MessageView.drawMessage();
    }

    private void clearSelectedInventories(){
        npcSelectedInventory.clearInventory();
        playerSelectedInventory.clearInventory();
    }

    private void resetOptions(){
        avatar.setOnTileWithNPC(false);
        npcOnTile = null;
        footerView.setDisplay(false);
        talking = false;
        skillMain = false;
        usingItem = false;
        trading = false;
        pressContinue = false;
        footerView.setTradingView(trading);
        avatar.setTrading(trading);
    }
}
