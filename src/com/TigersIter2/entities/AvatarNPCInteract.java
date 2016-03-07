package com.TigersIter2.entities;

/**
 * Created by Josh on 3/6/2016.
 */
public class AvatarNPCInteract {

    private Avatar avatar;
    private NPC npc;

    public AvatarNPCInteract(Avatar a, NPC n){
        avatar = a;
        npc = n;
    }

    public void talk(String s){
        if(npc.willTalk()) {
            npc.talk(s);
        }
        else{
            attackAvatar(npc.attack());
        }
    }

    private void attackAvatar(int attackStrength){
        //int damageTaken = some value;
        avatar.takeDamage(attackStrength);
    }
}
