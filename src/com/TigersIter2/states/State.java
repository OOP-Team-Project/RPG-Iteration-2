package com.TigersIter2.states;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public abstract class State{

    //update of State should call the updates of everything state is responsible for
    public abstract void update();

    //draw of State should draw everything the State is responsible for
    public abstract void draw();

}
