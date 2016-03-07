package com.TigersIter2.entities;

/**
 * Created by Josh on 3/6/2016.
 */
public class Monster extends NPC{

    public Monster(){
        willTalk = false;
        willTrade = false;
    }

    public String talk(String s){
        return "TEST";
    }
}
