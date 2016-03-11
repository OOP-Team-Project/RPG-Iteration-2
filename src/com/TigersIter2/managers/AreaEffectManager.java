package com.TigersIter2.managers;

import com.TigersIter2.areaEffects.*;
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
    private InstantDeath instantDeath;
    private TakeDamage takeDamage;
    private HealDamage healDamage;
    private LevelUp levelUp;


    //public AreaEffectManager(Entity entity, AreaEffect effectType, Location l){
    public AreaEffectManager(Entity entity){
        entityOnTile = entity;
        effects = new ArrayList<AreaEffect>();
        //areaEffect = effectType;
        //areaEffect.setLocation(l);
        //instantDeath = new InstantDeath();
        //takeDamage = new TakeDamage();
        //healDamage = new HealDamage();
        //levelUp = new LevelUp();
    }

    public void addEffect(AreaEffect ae){
        effects.add(ae);
    }

    public void removeEffect(AreaEffect ae){
        effects.remove(ae);
    }

    public void checkTile(){
        Iterator<AreaEffect> iter = effects.iterator();
        while(iter.hasNext()){
            AreaEffect effect = iter.next();
            if(LocationConverter.PixelLocationToHex(entityOnTile.getLocation()).getX() == LocationConverter.PixelLocationToHex(effect.getLocation()).getX() &&
                    LocationConverter.PixelLocationToHex(entityOnTile.getLocation()).getY() == LocationConverter.PixelLocationToHex(effect.getLocation()).getY())
            {
                effect.affectEntity(entityOnTile);
            }

        }
    }

    public List<AreaEffect> getAreaEffects(){
        return effects;
    }

}
