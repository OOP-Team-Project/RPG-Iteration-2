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

public class StatusView extends View implements ActionListener{

    int currentAnimationFrame = 0;
    private boolean display;
    private int type;
    private List<String> menuOptions;
    private Inventory playerInventory;
    private boolean trading;
    private int whoseSide;
    private int highlighted;
    private List<TakeableItem> playerSelectedItems;

    public StatusView(Inventory inv){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
        display = false;
        menuOptions = new ArrayList<String>();
        type = 0;
        trading = false;
        whoseSide = 0;
        highlighted = 0;
        playerInventory = inv;
        playerSelectedItems = new ArrayList<TakeableItem>();
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
        if(whoseSide < 2) {
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

    public void resetTrade(){
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
        if(display) {
            Graphics2D g2d = (Graphics2D) g.create();
            //gotta use that AA
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(0.3f, 0.3f, 0.3f));
            g2d.fillRect(300, 10, StaticVar.gameWidth - 600, 80);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 60));
            g2d.drawString("T R A D I N G", 450, 70);

            g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
            g2d.fillRect(75, 100, StaticVar.gameWidth - 150, StaticVar.gameHeight - 200);

            g2d.setColor(Color.gray);
            if (whoseSide == 1) {
                g2d.setColor(Color.red);
            }
            g2d.fillRect(StaticVar.gameWidth / 2 - 80, StaticVar.gameHeight / 2 - 30, 160, 50);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Submit Trade", StaticVar.gameWidth / 2 - 65, StaticVar.gameHeight / 2);


            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Your Stuff", 275, 120);
            int playerIter = 0;
            int height = 140;
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

            g2d.dispose();
        }
    }

}
