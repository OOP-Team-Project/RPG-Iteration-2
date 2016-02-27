package com.TigersIter2.entities;

import java.util.Observable;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public abstract class Entity extends Observable {


    //private Location location;

    //updates entity stuff
    public abstract void update(int x, int y);


}
