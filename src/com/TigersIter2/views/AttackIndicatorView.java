package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.AttackSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class AttackIndicatorView extends View {

    private static boolean display = false;
    private static boolean newMessage = false;
    private static List<String> typeList = new ArrayList<String>();
    private static List<String> oldTypeList = new ArrayList<String>();
    private static List<Integer> xPos = new ArrayList<Integer>();
    private static List<Integer> yPos = new ArrayList<Integer>();
    private static Timer messageTimer = new Timer(400, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            display = false;
            xPos.clear();
            yPos.clear();
            typeList.clear();
        }
    });

    public AttackIndicatorView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
    }

    public static void addIndicator(String type){
        typeList.add(type);
        xPos.add(-1);
        yPos.add(-1);
    }

    public static void addIndicator(String type, int x, int y){
        typeList.add(type);

        int translatedX, translatedY;

        if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
            translatedX = Math.round(x*.75f - 80);
        }
        else if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {

            translatedX = Math.round((x - ((mapXLength - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80);
        }
        else {
            translatedX = Math.round((StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth - View.cameraLocation.getX() + x*.75f - 80));
        }

        //Y Stuff Below
        if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
            //tileViews.get(i).get(j).setCurrentYLocation(j);
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j);
            translatedY = Math.round(y - Math.round(StaticVar.terrainImageHeight*1.2f));
        }
        else if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
            //tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
            translatedY = Math.round((y - ((mapYLength - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f));
        }
        else {
            //tileViews.get(i).get(j).setCurrentYLocation(j - (float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
            translatedY = Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)) - View.cameraLocation.getY() + y;
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - (float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
        }


        xPos.add(translatedX);
        yPos.add(translatedY);
    }

    public static void drawIndicator(){
        display = true;
        newMessage = true;
        oldTypeList.clear();
        for(String s : typeList)
            oldTypeList.add(s);
    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(display) {
            if(newMessage) {
                messageTimer.restart();
                newMessage = false;
            }
            for(int i = 0; i < oldTypeList.size(); ++i) {
                if (xPos.get(i) == -1 && yPos.get(i) == -1) {
                    FontMetrics fm = g2d.getFontMetrics();
                    Rectangle2D r = fm.getStringBounds(oldTypeList.get(i), g2d);
                    xPos.set(i,(StaticVar.gameWidth - (int) r.getWidth()) / 2);
                    yPos.set(i,(StaticVar.gameHeight - (int) r.getHeight()) / 2 + fm.getAscent() - 100);
                }
                if(oldTypeList.get(i).equals("bane")) {
                    g2d.drawImage(AttackSprite.bane, xPos.get(i), yPos.get(i), null);
                }
                else if(oldTypeList.get(i).equals("enchantment")) {
                    g2d.drawImage(AttackSprite.enchantment, xPos.get(i), yPos.get(i), null);
                }
                else if(oldTypeList.get(i).equals("range")) {
                    g2d.drawImage(AttackSprite.range, xPos.get(i), yPos.get(i), null);
                }
            }
        }


        g2d.dispose();
    }

}
