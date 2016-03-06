package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.entities.Avatar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AvatarView extends JComponent implements ActionListener{

    Avatar aHandle;
    int animationDelay = 100;
    int currentAnimationFrame = 0;
    Timer animationTimer;

    public AvatarView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public AvatarView(Avatar a) {
        aHandle = a;
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        animationTimer = new Timer(animationDelay, this);
        animationTimer.start();

    }

    public void actionPerformed(ActionEvent e) {
        currentAnimationFrame = (currentAnimationFrame + 1) % 2;
    }


    //TODO: Fix this code, if we were to add more occupations stuff would get nasty real fast! (Miles)
    //TODO: Perhaps create an animations class, eventually we'll need to

    private void drawSmasher(Graphics2D g2d){
        if (aHandle.getDirection() == 45)
            g2d.drawImage(SmasherSprite.smasherUpRight1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 90)
            g2d.drawImage(SmasherSprite.smasherUp1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 135)
            g2d.drawImage(SmasherSprite.smasherUpLeft1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 225)
            g2d.drawImage(SmasherSprite.smasherDownLeft1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 270)
            g2d.drawImage(SmasherSprite.smasherDown1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 315)
            g2d.drawImage(SmasherSprite.smasherDownRight1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
    }

    private void drawSneak(Graphics2D g2d){
        if (aHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SneakSprite.sneakUpRight1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(SneakSprite.sneakUpRight2, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        }
        else if (aHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SneakSprite.sneakUp1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(SneakSprite.sneakUp2, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        }
        else if (aHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SneakSprite.sneakUpLeft1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(SneakSprite.sneakUpLeft2, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        }
        else if (aHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SneakSprite.sneakDownLeft1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(SneakSprite.sneakDownLeft2, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        }
        else if (aHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SneakSprite.sneakDown1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(SneakSprite.sneakDown2, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        }
        else if (aHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SneakSprite.sneakDownRight1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(SneakSprite.sneakDownRight2, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        }
    }

    private void drawSummoner(Graphics2D g2d){
        if (aHandle.getDirection() == 45)
            g2d.drawImage(WizardSprite.wizardUpRight1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 90)
            g2d.drawImage(WizardSprite.wizardUp1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 135)
            g2d.drawImage(WizardSprite.wizardUpLeft1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 225)
            g2d.drawImage(WizardSprite.wizardDownLeft1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 270)
            g2d.drawImage(WizardSprite.wizardDown1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
        else if (aHandle.getDirection() == 315)
            g2d.drawImage(WizardSprite.wizardDownRight1, aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), null);
    }

    public void paintComponent(Graphics g){
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        //System.out.println("Inside AvatarView");
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        //g2d.fillOval(aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY(), 50, 50);
        if(aHandle.getOccupation().toString() == "Smasher")
            drawSmasher(g2d);
        else if(aHandle.getOccupation().toString() == "Sneak")
            drawSneak(g2d);
        else
            drawSummoner(g2d);
        g2d.drawString(aHandle.getOccupation().toString() + aHandle.getLocation().getX() + " " + aHandle.getLocation().getY(), aHandle.getPixelLocation().getX(), aHandle.getPixelLocation().getY());
        //System.out.println("X Location: " + aHandle.getPixelLocation().getX());
        //System.out.println("Y Location: " + aHandle.getPixelLocation().getY());


        g2d.dispose();
    }

}
