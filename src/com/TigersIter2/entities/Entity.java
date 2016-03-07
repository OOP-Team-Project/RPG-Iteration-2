package com.TigersIter2.entities;

import java.util.Observable;


public abstract class Entity extends Observable { //does Entity need to extend observable??(SL)


    //private Location location;

    //updates entity stuff
    public abstract void update(int x, int y, int z);

    //public abstract int getDirection();
}
