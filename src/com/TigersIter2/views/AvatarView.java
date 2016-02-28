package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.entities.Avatar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AvatarView extends JComponent{

    Avatar aHandle;

    public AvatarView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public AvatarView(Avatar a) {
        aHandle = a;
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));

    }

    private void drawSmasher(Graphics2D g2d){
        if (aHandle.getDirection() == 45)
            g2d.drawImage(SmasherSprite.smasherUpRight1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 90)
            g2d.drawImage(SmasherSprite.smasherUp1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 135)
            g2d.drawImage(SmasherSprite.smasherUpLeft1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 225)
            g2d.drawImage(SmasherSprite.smasherDownLeft1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 270)
            g2d.drawImage(SmasherSprite.smasherDown1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 315)
            g2d.drawImage(SmasherSprite.smasherDownRight1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
    }

    private void drawSneak(Graphics2D g2d){
        if (aHandle.getDirection() == 45)
            g2d.drawImage(SneakSprite.sneakUpRight1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 90)
            g2d.drawImage(SneakSprite.sneakUp1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 135)
            g2d.drawImage(SneakSprite.sneakUpLeft1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 225)
            g2d.drawImage(SneakSprite.sneakDownLeft1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 270)
            g2d.drawImage(SneakSprite.sneakDown1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 315)
            g2d.drawImage(SneakSprite.sneakDownRight1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
    }

    private void drawSummoner(Graphics2D g2d){
        if (aHandle.getDirection() == 45)
            g2d.drawImage(WizardSprite.wizardUpRight1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 90)
            g2d.drawImage(WizardSprite.wizardUp1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 135)
            g2d.drawImage(WizardSprite.wizardUpLeft1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 225)
            g2d.drawImage(WizardSprite.wizardDownLeft1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 270)
            g2d.drawImage(WizardSprite.wizardDown1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
        else if (aHandle.getDirection() == 315)
            g2d.drawImage(WizardSprite.wizardDownRight1, aHandle.getLocation().getX(), aHandle.getLocation().getY(), null);
    }

    public void paintComponent(Graphics g){
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        //System.out.println("Inside AvatarView");
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        //g2d.fillOval(aHandle.getLocation().getX(), aHandle.getLocation().getY(), 50, 50);
        if(aHandle.getOccupation().toString() == "Smasher")
            drawSmasher(g2d);
        else if(aHandle.getOccupation().toString() == "Sneak")
            drawSneak(g2d);
        else
            drawSummoner(g2d);
        g2d.drawString(aHandle.getOccupation().toString(), aHandle.getLocation().getX(), aHandle.getLocation().getY());
        g2d.dispose();
    }

}
