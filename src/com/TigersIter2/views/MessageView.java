package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
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

public class MessageView extends View implements ActionListener{

    private static boolean display = false;
    private static int time = 0;
    private static String message = "";

    public MessageView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
    }

    public static void drawMessage(String msg, int duration){
        time = duration;
        message = msg;
        display = true;
    }

    public void actionPerformed(ActionEvent e) {
        display = false;
    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(display) {
            if(time > 0) {
                Timer messageTimer = new Timer(time, this);
                messageTimer.start();
                time = 0;
            }
            FontMetrics fm = g2d.getFontMetrics();
            Rectangle2D r = fm.getStringBounds(message, g2d);
            int x = (StaticVar.gameWidth - (int) r.getWidth()) / 3;
            int y = 110;
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString(message, x-2, y+2);
            g2d.setColor(Color.red);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString(message, x, y);
        }


        g2d.dispose();
    }

}
