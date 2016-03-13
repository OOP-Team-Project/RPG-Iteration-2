package com.TigersIter2.areaEffects;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Entity;
import com.TigersIter2.managers.AreaEffectManager;
import com.TigersIter2.skills.DetectRemoveTrap;
import com.TigersIter2.views.MessageView;

/**
 * Created by MyMac on 3/11/16.
 */


/**
*  Trap remains hidden until it's encountered.
*  Based on DetectRemoveTrap probability, the avatar will either:
*       get caught in trap for 5 seconds (trap is revealed in view) OR
 *      successfully detect and remove trap.
 */

public class Trap extends AreaEffect{

    private Boolean removed;
    private AreaEffectManager aem;

    public Trap(){
        areaEffectType = 5;
        removed = false;
    }

    public void affectEntity(Entity entity){
        // Only an Avatar can use this area effect
        if (entity instanceof Avatar){
            // Gets probability that the Avatar will detect and remove trap based on skill
            Avatar avatar = (Avatar)entity;
            double probability = 0.0;
            if(avatar.getOccupation().toString().equals("Sneak"))  // only sneaks can remove detect and remove trap
                    probability = ((DetectRemoveTrap)avatar.getSkills().getSkill("DetectRemoveTrap")).getProbability();
            if (Math.random() <= probability && !removed) {
                // trap removed when avatar detects it
                display = false;
                removed = true;
                MessageView.addMessage("Removed trap!");
                MessageView.drawMessage();
            }
            // What do we want to happen if you get caught in a trap?
            // Right now it holds the avatar for 5 seconds by putting the thread tow sleep
            else{
                try {
                    display = true;
                    MessageView.addMessage("Trapped!");
                    MessageView.drawMessage();
                    Thread.sleep(3000);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    public Boolean getRemoved(){
        return removed;
    }

    public String getEffectName(){
        return "Trap";
    }
}
