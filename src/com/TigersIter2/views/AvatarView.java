package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.location.Location;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.skills.Creep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AvatarView extends View implements ActionListener{

    Avatar aHandle;
    Location pixelLocation;
    int animationDelay = 100;
    int currentAnimationFrame = 0;
    Timer animationTimer;
    int mapXLength, mapYLength = 0;

    public AvatarView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public AvatarView(Avatar a, TerrainMap map) {
        aHandle = a;
        mapXLength = map.getMapWidth();
        mapYLength = map.getMapHeight();
        pixelLocation = new Location(Math.round(StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth*.75f - 80), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)));
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
        if (aHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SmasherSprite.smasherUpRight1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(SmasherSprite.smasherUpRight2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SmasherSprite.smasherUp1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(SmasherSprite.smasherUp2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SmasherSprite.smasherUpLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(SmasherSprite.smasherUpLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SmasherSprite.smasherDownLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(SmasherSprite.smasherDownLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SmasherSprite.smasherDown1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(SmasherSprite.smasherDown2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(SmasherSprite.smasherDownRight1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(SmasherSprite.smasherDownRight2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
    }

    private void drawSneak(Graphics2D g2d){
        if(!((Creep)aHandle.getSkills().getSkill("Creep")).isActive()) {
            if (aHandle.getDirection() == 45) {
                if (currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneakUpRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneakUpRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (aHandle.getDirection() == 90) {
                if (currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneakUp1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneakUp2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (aHandle.getDirection() == 135) {
                if (currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneakUpLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneakUpLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (aHandle.getDirection() == 225) {
                if (currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneakDownLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneakDownLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (aHandle.getDirection() == 270) {
                if (currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneakDown1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneakDown2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (aHandle.getDirection() == 315) {
                if (currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneakDownRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneakDownRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
        }
        else{
            if (aHandle.getDirection() == 45) {
                if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneak2UpRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneak2UpRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
            else if (aHandle.getDirection() == 90) {
                if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneak2Up1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneak2Up2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
            else if (aHandle.getDirection() == 135) {
                if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneak2UpLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneak2UpLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
            else if (aHandle.getDirection() == 225) {
                if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneak2DownLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneak2DownLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
            else if (aHandle.getDirection() == 270) {
                if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneak2Down1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneak2Down2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
            else if (aHandle.getDirection() == 315) {
                if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                    g2d.drawImage(SneakSprite.sneak2DownRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(SneakSprite.sneak2DownRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
        }
    }

    private void drawSummoner(Graphics2D g2d){
        if (aHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(WizardSprite.wizardUpRight1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(WizardSprite.wizardUpRight2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(WizardSprite.wizardUp1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(WizardSprite.wizardUp2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(WizardSprite.wizardUpLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(WizardSprite.wizardUpLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(WizardSprite.wizardDownLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(WizardSprite.wizardDownLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(WizardSprite.wizardDown1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(WizardSprite.wizardDown2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (aHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(WizardSprite.wizardDownRight1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(WizardSprite.wizardDownRight2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
    }

    public void paintComponent(Graphics g){

        if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
            //tileViews.get(i).get(j).setCurrentXLocation(i);
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
            setPixelLocation(new Location(Math.round(aHandle.getLocation().getX()*.75f - 80), pixelLocation.getY()));

        }
        else if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
            //tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
            setPixelLocation(new Location(Math.round((aHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), pixelLocation.getY()));
        }
        else {
            //tileViews.get(i).get(j).setCurrentXLocation(i - (float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
            setPixelLocation(new Location(Math.round((StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth - View.cameraLocation.getX() + aHandle.getLocation().getX())*.75f - 80), pixelLocation.getY()));
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - (float) (aHandle.getLocation().getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
        }


        //Y Stuff Below
        if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
            //tileViews.get(i).get(j).setCurrentYLocation(j);
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j);
            setPixelLocation(new Location(pixelLocation.getX(), Math.round(aHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f))));
        }
        else if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
            //tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
            setPixelLocation(new Location(pixelLocation.getX(), Math.round((aHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f))));
        }
        else {
            //tileViews.get(i).get(j).setCurrentYLocation(j - (float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
            setPixelLocation(new Location(pixelLocation.getX(), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)) - View.cameraLocation.getY() + aHandle.getLocation().getY()));
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - (float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
        }

        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        //System.out.println("Inside AvatarView");
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        //g2d.fillOval(pixelLocation.getX(), pixelLocation.getY(), 50, 50);
        if(aHandle.getOccupation().toString() == "Smasher")
            drawSmasher(g2d);
        else if(aHandle.getOccupation().toString() == "Sneak")
            drawSneak(g2d);
        else
            drawSummoner(g2d);

        //System.out.println("X Location: " + pixelLocation.getX());
        //System.out.println("Y Location: " + pixelLocation.getY());
        g2d.dispose();

    }

    public void setPixelLocation(Location pixelLocation) {
        this.pixelLocation = pixelLocation;
    }
}
