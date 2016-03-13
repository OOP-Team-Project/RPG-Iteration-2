package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.MonsterSprite;
import com.TigersIter2.assets.sprites.VillagerSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.NPC;
import com.TigersIter2.entities.Villager;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;
import sun.awt.image.PixelConverter;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NPCView extends View implements ActionListener{

    Avatar aHandle;
    NPC vHandle;
    int animationDelay = 100;
    int currentAnimationFrame = 0;
    Timer animationTimer;
    Location pixelLocation;
    int mapXLength, mapYLength = 0;

    public NPCView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public NPCView(NPC v, Avatar a, TerrainMap map) {
        aHandle = a;
        vHandle = v;
        mapXLength = map.getMapWidth();
        mapYLength = map.getMapHeight();


        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        animationTimer = new Timer(animationDelay, this);
        animationTimer.start();

        pixelLocation = vHandle.getLocation();

    }

    public void actionPerformed(ActionEvent e) {
        currentAnimationFrame = (currentAnimationFrame + 1) % 2;
    }

    private void drawVillager(Graphics2D g2d){
        if (vHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VillagerSprite.villagerUpRight1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VillagerSprite.villagerUpRight2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VillagerSprite.villagerUp1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VillagerSprite.villagerUp2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VillagerSprite.villagerUpLeft1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VillagerSprite.villagerUpLeft2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VillagerSprite.villagerDownLeft1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VillagerSprite.villagerDownLeft2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VillagerSprite.villagerDown1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VillagerSprite.villagerDown2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VillagerSprite.villagerDownRight1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VillagerSprite.villagerDownRight2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
    }

    private void drawMonster(Graphics2D g2d){
        if (vHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(MonsterSprite.monsterUpRight1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(MonsterSprite.monsterUpRight2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(MonsterSprite.monsterUp1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(MonsterSprite.monsterUp2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(MonsterSprite.monsterUpLeft1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(MonsterSprite.monsterUpLeft2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(MonsterSprite.monsterDownLeft1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(MonsterSprite.monsterDownLeft2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(MonsterSprite.monsterDown1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(MonsterSprite.monsterDown2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(MonsterSprite.monsterDownRight1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(MonsterSprite.monsterDownRight2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
    }

    public void paintComponent(Graphics g){

        switch (View.currentMapMode) {
            case PLAYER_FOLLOW_MODE:

                if((float) (aHandle.getLocation().getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                    //tileViews.get(i).get(j).setCurrentXLocation(i);
                    vHandle.setPixelLocation(new Location(Math.round(vHandle.getLocation().getX()*.75f - 80), vHandle.getPixelLocation().getY(), 0));
                    //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
                    //aHandle.setPixelLocation(new Location(Math.round(aHandle.getLocation().getX()*.75f - 80), aHandle.getPixelLocation().getY(), 0));

                }
                else if((float) (aHandle.getLocation().getX())/StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
                    //tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                    vHandle.setPixelLocation(new Location(Math.round((vHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), vHandle.getPixelLocation().getY(), 0));
                    //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                    //aHandle.setPixelLocation(new Location(Math.round((aHandle.getLocation().getX() - ((tileViews.size() - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), aHandle.getPixelLocation().getY(), 0));
                }
                else  {
                    vHandle.setPixelLocation(new Location(Math.round((vHandle.getLocation().getX() - aHandle.getLocation().getX() + StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth)*.75f) - 80, vHandle.getPixelLocation().getY(), 0));

                }

                //Y Stuff Below
                if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                    vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), Math.round(vHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                }
                else if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
                    //tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                    vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), Math.round((vHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                    //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                    //aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round((aHandle.getLocation().getY() - ((tileViews.get(0).size() - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                }
                else {
                    vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), vHandle.getLocation().getY() - aHandle.getLocation().getY() + Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight) - StaticVar.terrainImageHeight - 20, 0));
                }
                break;

            case SCROLL_MODE:
                if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                    //tileViews.get(i).get(j).setCurrentXLocation(i);
                    vHandle.setPixelLocation(new Location(Math.round(vHandle.getLocation().getX()*.75f - 80), vHandle.getPixelLocation().getY(), 0));
                    //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
                    //aHandle.setPixelLocation(new Location(Math.round(aHandle.getLocation().getX()*.75f - 80), aHandle.getPixelLocation().getY(), 0));

                }
                else if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
                    //tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                    vHandle.setPixelLocation(new Location(Math.round((vHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), vHandle.getPixelLocation().getY(), 0));
                    //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                    //aHandle.setPixelLocation(new Location(Math.round((aHandle.getLocation().getX() - ((tileViews.size() - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), aHandle.getPixelLocation().getY(), 0));
                }
                else  {
                    vHandle.setPixelLocation(new Location(Math.round((vHandle.getLocation().getX() - View.cameraLocation.getX() + StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth)*.75f) - 80, vHandle.getPixelLocation().getY(), 0));

                }

                //Y Stuff Below
                if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                    vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), Math.round(vHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                }
                else if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
                    //tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                    vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), Math.round((vHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                    //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                    //aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round((aHandle.getLocation().getY() - ((tileViews.get(0).size() - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                }
                else {
                    vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), vHandle.getLocation().getY() - View.cameraLocation.getY() + Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight) - StaticVar.terrainImageHeight - 20, 0));
                }

            default:
                break;
        }

        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Location l = LocationConverter.PixelLocationToHex(vHandle.getLocation());

        if (vHandle.isAlive() && aHandle.canSeeHex(l)) {
            if (vHandle.isVillager()) {
                drawVillager(g2d);
            } else {
                drawMonster(g2d);
            }
        }
        g2d.dispose();
    }

}
