package com.TigersIter2.entities;

import com.TigersIter2.stats.NPCStats;
import com.TigersIter2.stats.PlayerStats;
import com.TigersIter2.stats.Stats;

import java.util.Observable;


public abstract class Entity extends Observable { //does Entity need to extend observable??(SL)


    //private Location location;

    //updates entity stuff
    public abstract void update(int x, int y, long elapsed);

    public Stats getStats(){   // to be overridden in subclasses Avatar and NPC
        PlayerStats stats = new PlayerStats();
        return stats;
    }
    public void setStats(Stats stats){}

    //public abstract int getDirection();
}
