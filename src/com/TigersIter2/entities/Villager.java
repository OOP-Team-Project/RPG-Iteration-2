package com.TigersIter2.entities;

import java.util.List;

/**
 * Created by Josh on 3/6/2016.
 */
public class Villager extends NPC {

    public Villager(List<String> phrase, boolean talk, boolean trade, boolean attack){
        responses = phrase;
        willTrade = trade;
        willTalk = talk;
        willAttack = attack;
        isVillager = true;
    }

}
