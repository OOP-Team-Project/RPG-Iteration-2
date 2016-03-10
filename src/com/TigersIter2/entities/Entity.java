package com.TigersIter2.entities;

import java.util.Observable;


public abstract class Entity extends Observable { //does Entity need to extend observable??(SL)


    //private Location location;
    protected int attackTime;

    //updates entity stuff
    public abstract void update(int x, int y, long elapsed);

    public int getAttackTime(){
        return attackTime;
    }

    public void setAttackTime(int a){
        attackTime = a;
    }

    //public abstract int getDirection();
}
