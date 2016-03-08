package com.TigersIter2.managers;

import com.TigersIter2.entities.*;
import com.TigersIter2.items.Weapon;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.views.FooterView;

import java.util.ArrayList;
import java.util.List;
public class AvatarNPCInteract {

    private Avatar avatar;
    private NPC npcOnTile;
    private List<NPC> npcList;
    private List<Vehicle> vehicleList;
    private List<String> questions;
    private List<String> originalOptions;
    private FooterView footerView;
    private boolean talking, fighting, usingSkill, usingItem, pressContinue, trading;

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

    public void attack(){
        //Check your position/direction/range against the NPC's in the list
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
                    else {
                        //If NPC won't talk, but will trade, default to trading screen
                        haveConversation(5);
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
        npcList.add(m);
    }

    public void addVillager(List<String> p, boolean talk, boolean trade, boolean attack){
        npcList.add(new Villager(p, talk, trade, attack));

        // This will not actually be here, just for testing
        npcList.get(0).getInventory().addItem(new Weapon("Sword"));
    }

    public void checkTile(){
        for(NPC n : npcList){
            if(LocationConverter.PixelLocationToHex(n.getLocation()).getX() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getX() &&
               LocationConverter.PixelLocationToHex(n.getLocation()).getY() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getY())
            {
                // Needs to pop up with some options
                if(!avatar.getOnTileWithNPC()) {
                    avatar.setOnTileWithNPC(true);
                    npcOnTile = n;
                    if(npcOnTile.willTalk() || npcOnTile.willTrade()) {
                        footerView.setDisplay(true);
                        footerView.setType(0);
                        footerView.setMenuOptions(originalOptions);
                    }
                    else if(npcOnTile.willAttack()){
                        //NPC attacks player
                        attack();
                        System.out.println("Getting attacked now");
                    }
                }
            }
            else if(avatar.getOnTileWithNPC()){
                    resetOptions();
            }
        }
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
    }
}
