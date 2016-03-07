package com.TigersIter2.entities;

import com.TigersIter2.items.Item;
import com.TigersIter2.items.Potion;
import com.TigersIter2.items.TakeableItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/6/2016.
 */
public class Villager extends NPC {



    public Villager(List<String> phrase, boolean talk, boolean trade){
        phrases = phrase;
        willTrade = trade;
        willTalk = talk;
    }

    public String talk(String s){
        return "TEST";
    }

}
