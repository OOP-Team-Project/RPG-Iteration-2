package com.TigersIter2.skills;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Magic_Buddha on 3/5/2016.
 */
public abstract class ActiveSkill extends Skill implements Observer{
    public abstract void update(Observable observable, Object obj);
}
