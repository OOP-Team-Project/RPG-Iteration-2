package com.TigersIter2.managers;

import com.TigersIter2.entities.*;
import com.TigersIter2.items.OneHandedWeapon;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.items.RangedWeapon;
import com.TigersIter2.items.Weapon;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.views.FooterView;

import javax.swing.*;
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
    private boolean talking, fighting, usingSkill, usingItem, pressContinue, trading;
    private Inventory playerSelectedInventory;
    private Inventory npcSelectedInventory;
    private boolean playerCanAttack = true;
    private Timer playerAttackTimer;
    private List<Timer> npcAttackTimers;

    public AvatarNPCInteract(Avatar a, FooterView fv){
        avatar = a;
        npcList = new ArrayList<NPC>();
        vehicleList = new ArrayList<Vehicle>();
        npcOnTile = null;
        footerView = fv;
        talking = false;
        fighting = false;
        usingSkill = false;
        usingItem = false;
        trading = false;
        pressContinue = false;
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
            if(!n.getCanAttack())
                n.setCanAttack(true);
        }

    }

    private boolean inRange(NPC n){
        //somehow determine if npc is in range based off of direction and attack range and such


        //in the meantime, just use this (since ranged attacks are not implemented yet
        if(n == npcOnTile)
            return true;
        else
            return false;
    }

    public void attack(){
        //NEED SOME SORT OF TIMING FOR THIS METHOD
        if(playerCanAttack) {
            playerCanAttack = false;
            Random rand = new Random();
            int attackAttempts = avatar.getStats().getOffensiveRating() / 2 + 1;
            int randNumMax;
            //Check your position/direction/range against the NPC's in the list
            Iterator<NPC> iter = npcList.iterator();
            while (iter.hasNext()) {
                NPC npc = iter.next();
                if (inRange(npc)) {
                    //Take stats into account
                    randNumMax = npc.getStats().getDefensiveRating() + npc.getStats().getArmor() + 1;
                    int numToHit = rand.nextInt(randNumMax);
                    boolean hit = false;
                    while (!hit && attackAttempts > 0) {
                        --attackAttempts;
                        if (rand.nextInt(randNumMax) == numToHit)
                            hit = true;
                    }

                    if (hit) {

                        //Take skills into account
                        //int damage = rand.nextInt(getActiveSkill().getSkillLevel());
                        //Will be slightly different than this based on which type of weapon is being used


                        //TESTING
                        int damage = rand.nextInt(20);
                        //END TESTING

                        npc.getStats().decreaseCurrentLife(damage);
                        System.out.println("Dealt " + damage + " damage");
                        System.out.println(npc.getStats().getCurrentLife() + "/" + npc.getStats().getLife());
                        if (npc.isAlive()) {
                            //NEED SOME WAY OF TIMING THIS
                            retaliate(npc);
                        } else {
                            System.out.println("You killed the NPC!");
                            npc.dropItems();
                            iter.remove();
                        }

                    } else
                        System.out.println("MISS!");
                }
            }
        }
    }

    private void retaliate(NPC npc){
        if(npc.getCanAttack()) {
            npc.setCanAttack(false);
            Random rand = new Random();
            int attackAttempts = npc.getStats().getOffensiveRating() / 2 + 1;
            int randNumMax = avatar.getStats().getDefensiveRating() + avatar.getStats().getArmor();
            int numToHit = rand.nextInt(randNumMax);
            boolean hit = false;
            while (!hit && attackAttempts > 0) {
                --attackAttempts;
                if (rand.nextInt(randNumMax) == numToHit)
                    hit = true;
            }

            if (hit) {

                //Take skills into account
                //int damage = rand.nextInt(getActiveSkill().getSkillLevel());
                //Will be slightly different than this based on which type of weapon is being used


                //TESTING
                int damage = rand.nextInt(20);
                System.out.println("You lost " + damage + " health!");
                //END TESTING

                avatar.getStats().decreaseCurrentLife(damage);
                if (avatar.getStats().getCurrentLife() <= 0)
                    System.out.println("You died!!");

            }
        }
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
        else {
            if (avatar.getOnTileWithNPC()) {
                if (talking) {
                    haveConversation(selected);
                } else if (fighting) {
                    attack();
                } else if (usingSkill) {

                } else if (usingItem) {

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
                    //Player attacks NPC
                    attack();
                } else if (selected == 3) {
                    //Use Skill
                } else if (selected == 4) {
                    //Use Item
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
        m.getStats().setAttack(12);
        //END TESTING

        npcList.add(m);
    }

    public void addVillager(List<String> p, boolean talk, boolean trade, boolean attack){
        NPC v = new Villager(p, talk, trade, attack);
        //v.getInventory().addItem(new OneHandedWeapon("Sword",5));
        npcList.add(v);
        v.getLocation().setX(avatar.getLocation().getX());
        v.getLocation().setY(avatar.getLocation().getY());
    }

    public List<NPC> getNpcList(){
        return npcList;
    }

    public void checkTile(){
        for(NPC n : npcList){
            if(LocationConverter.PixelLocationToHex(n.getLocation()).getX() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getX() &&
               LocationConverter.PixelLocationToHex(n.getLocation()).getY() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getY())
            {
                if(!avatar.getOnTileWithNPC()) {
                    avatar.setOnTileWithNPC(true);
                    npcOnTile = n;
                    npcOnTile.setOnTileWithAvatar(true);
                    if(npcOnTile.willTalk() || npcOnTile.willTrade()) {
                        footerView.setDisplay(true);
                        footerView.setType(0);
                        footerView.setMenuOptions(originalOptions);
                    }
                    else if(npcOnTile.willAttack()){
                        //NPC attacks player
                        retaliate(npcOnTile);
                    }
                }
            }
            else if(avatar.getOnTileWithNPC()){
                 if(n.getOnTileWithAvatar()){
                     n.setOnTileWithAvatar(false);
                     resetOptions();
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

    private void clearSelectedInventories(){
        npcSelectedInventory.clearInventory();
        playerSelectedInventory.clearInventory();
    }

    private void resetOptions(){
        avatar.setOnTileWithNPC(false);
        npcOnTile = null;
        footerView.setDisplay(false);
        talking = false;
        fighting = false;
        usingSkill = false;
        usingItem = false;
        trading = false;
        pressContinue = false;
        footerView.setTradingView(trading);
        avatar.setTrading(trading);
    }
}
