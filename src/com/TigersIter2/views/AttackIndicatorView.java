package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.AttackSprite;
import com.TigersIter2.assets.sprites.ItemSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Equipment;
import com.TigersIter2.entities.Inventory;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.stats.PlayerStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
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
        xPos.add(x);
        yPos.add(y);
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
