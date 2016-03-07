package com.TigersIter2.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/6/2016.
 */
public class AvatarNPCInteract {

    private Avatar avatar;
    private List<NPC> npcList;

    public AvatarNPCInteract(Avatar a){
        avatar = a;
        npcList = new ArrayList<NPC>();
    }

    public void attack(){
        //Check your position/direction/range against the NPC's in the list
    }

    public void chooseOption(int selected){
        //Do something in a menu with the number you selected
        //This is to be used when interacting with an NPC on the same tile
    }

    public void addMonster(){
        npcList.add(new Monster());
    }

    public void addVillager(List<String> p, boolean talk, boolean trade){
        npcList.add(new Villager(p, talk, trade));
    }

    private void attackAvatar(int attackStrength){
        //int damageTaken = some value;
        avatar.takeDamage(attackStrength);
    }
}
