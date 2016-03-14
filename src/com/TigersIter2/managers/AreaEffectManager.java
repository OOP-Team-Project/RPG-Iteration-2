package com.TigersIter2.managers;

import com.TigersIter2.areaEffects.*;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nicole on 3/7/16.
 */
public class AreaEffectManager {

    private Entity entityOnTile;
    private AreaEffect areaEffect;
    private List<AreaEffect> effects;


    //public AreaEffectManager(Entity entity, AreaEffect effectType, Location l){
    public AreaEffectManager(Entity entity){
        entityOnTile = entity;
        effects = new ArrayList<AreaEffect>();
    }

    public void addEffect(AreaEffect ae){
        effects.add(ae);
        if (ae instanceof Trap) {
            // keep traps hidden until encountered
            ae.setDisplay(false);
        }
        else ae.setDisplay(true);
    }

    public void removeEffect(AreaEffect ae){
        effects.remove(ae);
    }

    public void checkTile(){
        Iterator<AreaEffect> iter = effects.iterator();
        while(iter.hasNext()){
            AreaEffect effect = iter.next();
            if (LocationConverter.PixelLocationToHex(entityOnTile.getLocation()).getX() == LocationConverter.PixelLocationToHex(effect.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(entityOnTile.getLocation()).getY() == LocationConverter.PixelLocationToHex(effect.getLocation()).getY()) {
                if (effect instanceof Trap) {
                    if (((Trap) effect).getRemoved()) {
                    }
                    else effect.affectEntity(entityOnTile);  // trap only effects avatar if it hasn't been removed
                }
                else{
                    effect.affectEntity(entityOnTile);
                }
            }
            else{
                if(entityOnTile instanceof Avatar) {
                    if(effect instanceof Trap)
                        if(((Trap) effect).isActive()) {
                            ((Avatar) entityOnTile).setIsTrapped(false);
                            ((Trap) effect).setActive(false);
                        }

                }
            }
        }
    }

    public List<AreaEffect> getAreaEffects(){
        return effects;
    }

}
