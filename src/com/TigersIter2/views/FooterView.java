package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Inventory;
import com.TigersIter2.items.TakeableItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FooterView extends View implements ActionListener{

    int currentAnimationFrame = 0;
    private boolean display;
    private int type;
    private List<String> menuOptions;
    private Inventory playerInventory;
    private Inventory npcInventory;
    private boolean trading;

    public FooterView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
        display = false;
        menuOptions = new ArrayList<String>();
        type = 0;
        trading = false;
    }

    public void setDisplay(boolean b){
        display = b;
    }

    //0 denotes a menu, 1 denotes a conversation
    public void setType(int i){
        type = i;
    }

    //MAXIMUM OF 5 OPTIONS
    public void setMenuOptions(List<String> list){
        menuOptions = list;
    }

    public void setTradingView(boolean isTrading){
        if(isTrading) {
            trading = true;
        }
        else{
            trading = false;
        }
    }

    public void setPlayerInventory(Inventory i){
        playerInventory = i;
    }

    public void setNpcInventory(Inventory i){
        npcInventory = i;
    }


    public void actionPerformed(ActionEvent e) {
        currentAnimationFrame = (currentAnimationFrame + 1) % 2;
    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(display && !trading) {
            g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
            g2d.fillRect(200, StaticVar.gameHeight - 200, StaticVar.gameWidth - 400, 200);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            int height = StaticVar.gameHeight - 170;
            int i = 0;
            for(String s : menuOptions) {
                ++i;
                if(type == 0) {
                    g2d.drawString(i + ") " + s, 225, height);
                }
                else if(type == 1) {
                    g2d.drawString(s, 225, height);
                    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 16));
                    g2d.drawString("Press Backspace to continue...", 225, height + 120);
                }
                height += 30;
            }
        }
        else if(display && trading){
            g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
            g2d.fillRect(75, 100, StaticVar.gameWidth/2 - 115, StaticVar.gameHeight-200);
            g2d.fillRect(StaticVar.gameWidth/2 + 40, 100, StaticVar.gameWidth/2 - 115, StaticVar.gameHeight-200);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Your Stuff", 275, 120);
            //Need to display all inventory items
            if(!playerInventory.isEmpty()){
                g2d.drawString(playerInventory.getItemAtIndex(0).toString(), 295, 140);
            }
            g2d.drawString("Their Stuff", 250 + StaticVar.gameWidth/2, 120);
            if(npcInventory != null){
                g2d.drawString(npcInventory.getItemAtIndex(0).toString(), 295, 140);
            }
            //Need to display all the NPC's items
        }
        g2d.dispose();
    }

}
