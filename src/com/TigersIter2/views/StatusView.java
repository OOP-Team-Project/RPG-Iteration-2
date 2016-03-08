package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Equipment;
import com.TigersIter2.entities.Inventory;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.stats.PlayerStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StatusView extends View implements ActionListener{

    private final int VIEW_X_START = 75;
    private final int VIEW_Y_START = 130;

    int currentAnimationFrame = 0;
    private boolean display;
    private int type;
    private List<String> menuOptions;
    private Inventory playerInventory;
    private PlayerStats stats;
    private Equipment equipment;
    private boolean trading;
    private int whoseSide;
    private int highlighted;
    private int totalWidth;
    private int totalHeight;
    private List<TakeableItem> playerSelectedItems;

    public StatusView(Inventory inv, PlayerStats ps, Equipment equip){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
        display = false;
        menuOptions = new ArrayList<String>();
        type = 0;
        trading = false;
        whoseSide = 0;
        highlighted = 0;
        playerInventory = inv;
        stats = ps;
        equipment = equip;
        playerSelectedItems = new ArrayList<TakeableItem>();
        totalWidth = StaticVar.gameWidth - 150;
        totalHeight = StaticVar.gameHeight - 200;
    }

    public void handleInput(int input){
        if(input == 0){
            decrementHighlighted();
        }
        else if(input == 1){
            incrementHighlighted();
        }
        else if(input == 2){
            decrementWhoseSide();
        }
        else if(input == 3){
            incrementWhoseSide();
        }
        else if(input == 4){
            //select
        }
        else if(input == 5){
            resetView();
        }
    }

    public void toggle(){
        display = !display;
    }

    public boolean getDisplay(){
        return display;
    }

    //0 denotes a menu, 1 denotes a conversation
    public void setType(int i){
        type = i;
    }

    //MAXIMUM OF 5 OPTIONS
    public void setMenuOptions(List<String> list){
        menuOptions = list;
    }

    public void incrementHighlighted(){
        if(whoseSide == 0){
            if(highlighted < playerInventory.getItems().size()-1)
                ++highlighted;
        }
    }

    public void decrementHighlighted(){
        if(highlighted > 0)
            --highlighted;
    }

    public void incrementWhoseSide(){
        if(whoseSide < 1) {
            ++whoseSide;
            highlighted = 0;
        }
    }

    public void decrementWhoseSide(){
        if(whoseSide > 0) {
            --whoseSide;
            highlighted = 0;
        }
    }

    public int getWhoseSide(){
        return whoseSide;
    }

    public void resetView(){
        whoseSide = 0;
        highlighted = 0;
        playerSelectedItems.clear();
    }


    public int selectItem(){
        if(whoseSide == 0 && playerSelectedItems.contains(playerInventory.getItemAtIndex(highlighted)))
            playerSelectedItems.remove(playerInventory.getItemAtIndex(highlighted));
        else if(whoseSide == 0 && !playerSelectedItems.contains(playerInventory.getItemAtIndex(highlighted)))
            playerSelectedItems.add(playerInventory.getItemAtIndex(highlighted));
        return highlighted;
    }

    public void setPlayerInventory(Inventory i){
        playerInventory = i;
    }


    public void actionPerformed(ActionEvent e) {
        currentAnimationFrame = (currentAnimationFrame + 1) % 2;
    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        //Draw stats bar
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, StaticVar.gameWidth, 50);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
        g2d.drawString("      Health    Mana    Experience    Lives    Offense    Defense    Armor    Strength    " +
                "Agility    Intellect    Hardiness    Speed", 10, 22);
        g2d.drawLine(0, 25, StaticVar.gameWidth, 25);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g2d.drawString(Integer.toString(stats.getCurrentLife()) + "/" + Integer.toString(stats.getLife()), 45, 45);


        if(display) {
            g2d.setColor(new Color(0.3f, 0.3f, 0.3f));
            g2d.fillRect(300, 40, StaticVar.gameWidth - 600, 80);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 60));
            g2d.drawString("S T A T U S", 460, 100);

            g2d.fillRect(VIEW_X_START - 2, VIEW_Y_START - 2, totalWidth + 4, totalHeight + 4);
            g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
            g2d.fillRect(VIEW_X_START, VIEW_Y_START, totalWidth, totalHeight);

            g2d.setStroke(new BasicStroke(4));
            g2d.setColor(Color.black);
            g2d.drawLine(VIEW_X_START+totalWidth/3, VIEW_Y_START, VIEW_X_START+totalWidth/3, VIEW_Y_START+totalHeight);
            g2d.drawLine(VIEW_X_START+totalWidth/3, VIEW_Y_START+totalHeight/2,
                    VIEW_X_START+totalWidth, VIEW_Y_START+totalHeight/2);



            //Draw the inventory
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString("Inventory", VIEW_X_START+80, VIEW_Y_START+35);
            int playerIter = 0;
            int height = 200;
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            for (TakeableItem item : playerInventory.getItems()) {
                g2d.setColor(Color.black);
                if (playerIter == highlighted && whoseSide == 0)
                    g2d.drawString(">", 125, height);
                if (playerSelectedItems.contains(playerInventory.getItemAtIndex(playerIter)))
                    g2d.setColor(Color.red);
                g2d.drawString(item.toString(), 150, height);
                height += 20;
                ++playerIter;
            }

            //Draw Equipment
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString("Equipment", VIEW_X_START+80+totalWidth/2, VIEW_Y_START+35);
            playerIter = 0;
            height = 200;
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));

            //Draw Skills
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString("Skills", VIEW_X_START+140+totalWidth/2, VIEW_Y_START+35+totalHeight/2);
            playerIter = 0;
            height = 200;
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));

            g2d.dispose();
        }
    }

}
