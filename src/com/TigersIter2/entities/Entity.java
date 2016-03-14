package com.TigersIter2.entities;

import com.TigersIter2.location.Location;
import com.TigersIter2.stats.NPCStats;
import com.TigersIter2.stats.Stats;

import java.util.Observable;


public abstract class Entity extends Observable { //does Entity need to extend observable??(SL)


    //private Location location;

    //updates entity stuff
    public abstract void update(int x, int y, long elapsed);

    public Stats getStats(){   // to be overridden in subclasses Avatar and NPC
        Stats stats = new NPCStats();
        return stats;
    }
    public void setStats(Stats stats) {}

    public Location getLocation(){
        Location l = new Location(0,0);
        return l;
    }

    public int getAttackTime(){
        return getStats().getAttackTime();
    }

    public void setAttackTime(int at){
        getStats().setAttackTime(at);
    }

    public int getInfluenceRadius(){
        return getStats().getInfluenceRadius();
    }

    //public abstract int getDirection();
}
