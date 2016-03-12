package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.PetSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Pet;
import com.TigersIter2.location.Location;
import com.TigersIter2.maps.TerrainMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Breanna on 3/11/16.
 */
public class PetView extends View implements ActionListener {
    Avatar aHandle;
    Pet pHandle;
    int animationDelay = 100;
    int currentAnimationFrame = 0;
    Timer animationTimer;
    Location pixelLocation;
    int mapXLength, mapYLength = 0;

    public PetView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public PetView(Pet p, Avatar a, TerrainMap map) {
        aHandle = a;
        pHandle = p;
        mapXLength = map.getMapWidth();
        mapYLength = map.getMapHeight();


        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        animationTimer = new Timer(animationDelay, this);
        animationTimer.start();

        pixelLocation = pHandle.getLocation();

    }

    public void actionPerformed(ActionEvent e) { currentAnimationFrame = (currentAnimationFrame + 1) % 2; }

    private void drawPet(Graphics2D g2d) {
        if (pHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petUpRight1, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(PetSprite.petUpRight2, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
        }
        else if (pHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petUp1, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(PetSprite.petUp2, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
        }
        else if (pHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petUpLeft1, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(PetSprite.petUpLeft2, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
        }
        else if (pHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petDownLeft1, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(PetSprite.petDownLeft2, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
        }
        else if (pHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petDown1, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(PetSprite.petDown2, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
        }
        else if (pHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petDownRight1, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(PetSprite.petDownRight2, pHandle.getPixelLocation().getX(), pHandle.getPixelLocation().getY(), null);
        }
    }

    public void paintComponent(Graphics g){

        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);

        drawPet(g2d);

        g2d.dispose();

    }
}
