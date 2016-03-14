package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.location.Location;
import com.TigersIter2.managers.AreaEffectManager;

/**
 * Created by MyMac on 3/11/16.
 */
public class Teleport extends AreaEffect {

    private final Location destination;

    public Teleport(Location destination){
        areaEffectType = 4;
        this.destination = destination;
    }

    public void affectEntity(Entity entity){
        // only an Avatar wil levelUp
        if (entity instanceof Avatar) {
            Avatar avatar = ((Avatar) entity);
            avatar.getLocation().setX(destination.getX());
            avatar.getLocation().setY(destination.getY());
            System.out.println("Avatar teleported!");
        }
    }

    public String getEffectName(){ return "Teleport"; }

}
