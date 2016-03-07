package com.TigersIter2.entities;

import com.TigersIter2.location.LocationConverter;

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
        NPC m = new Monster();
        npcList.add(m);
    }

    public void addVillager(List<String> p, boolean talk, boolean trade){
        npcList.add(new Villager(p, talk, trade));
    }

    public void checkTile(){
        for(NPC n : npcList){
            System.out.println(LocationConverter.PixelLocationToHex(n.getLocation()).getX() + ", " + LocationConverter.PixelLocationToHex(avatar.getLocation()).getX());
            if(LocationConverter.PixelLocationToHex(n.getLocation()).getX() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getX() &&
               LocationConverter.PixelLocationToHex(n.getLocation()).getY() == LocationConverter.PixelLocationToHex(avatar.getLocation()).getY())
            {
                // Needs to pop up with some options
                if(!avatar.getOnTileWithNPC()) {
                    avatar.setOnTileWithNPC(true);
                }

            }
            else{
                System.out.println("Not on same tile");
                avatar.setOnTileWithNPC(false);
            }
        }
    }

    private void attackAvatar(int attackStrength){
        //int damageTaken = some value;
        avatar.takeDamage(attackStrength);
    }
}
