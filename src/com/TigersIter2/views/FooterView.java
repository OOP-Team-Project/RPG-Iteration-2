package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Inventory;
import com.TigersIter2.items.TakeableItem;

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
    private int whoseSide;
    private int highlighted;
    private List<TakeableItem> playerSelectedItems;
    private List<TakeableItem> npcSelectedItems;
    private int playerValue = 0;
    private int npcValue = 0;
    private boolean tooExpensive = false;
    private int discount = 0;

    public FooterView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
        display = false;
        menuOptions = new ArrayList<String>();
        type = 0;
        trading = false;
        whoseSide = 0;
        highlighted = 0;
        playerSelectedItems = new ArrayList<TakeableItem>();
        npcSelectedItems = new ArrayList<TakeableItem>();
    }

    public void setDisplay(boolean b){
        display = b;
    }

    public void setDiscount(int i){
        discount = i/4;
    }

    public void setTooExpensive(boolean b){
        tooExpensive = b;
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
        trading = isTrading;
    }

    public void incrementHighlighted(){
        if(whoseSide == 0){
            if(highlighted < playerInventory.getItems().size()-1)
                ++highlighted;
        }
        else{
            if(highlighted < npcInventory.getItems().size()-1)
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

    public int getPlayerValue(){
        return playerValue;
    }

    public int getNpcValue(){
        return npcValue;
    }

    public void resetTrade(){
        whoseSide = 0;
        highlighted = 0;
        playerSelectedItems.clear();
        npcSelectedItems.clear();
        playerValue = 0;
        npcValue = 0;
    }


    public int selectItem(){
        if(whoseSide == 0 && playerSelectedItems.contains(playerInventory.getItemAtIndex(highlighted))) {
            playerSelectedItems.remove(playerInventory.getItemAtIndex(highlighted));
            playerValue -= playerInventory.getItemAtIndex(highlighted).getPrice();
        }
        else if(whoseSide == 0 && !playerSelectedItems.contains(playerInventory.getItemAtIndex(highlighted))) {
            playerSelectedItems.add(playerInventory.getItemAtIndex(highlighted));
            playerValue += playerInventory.getItemAtIndex(highlighted).getPrice();
        }
        else if(whoseSide == 2 && npcSelectedItems.contains(npcInventory.getItemAtIndex(highlighted))) {
            npcSelectedItems.remove(npcInventory.getItemAtIndex(highlighted));
            npcValue -= npcInventory.getItemAtIndex(highlighted).getPrice() - discount;
        }
        else if(whoseSide == 2 && !npcSelectedItems.contains(npcInventory.getItemAtIndex(highlighted))) {
            npcSelectedItems.add(npcInventory.getItemAtIndex(highlighted));
            npcValue += npcInventory.getItemAtIndex(highlighted).getPrice() - discount;
        }
        return highlighted;
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
            g2d.setColor(new Color(0.3f, 0.3f, 0.3f));
            g2d.fillRect(300, 40, StaticVar.gameWidth - 600, 80);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 60));
            g2d.drawString("T R A D I N G", 450, 100);

            g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
            g2d.fillRect(75, 130, StaticVar.gameWidth/2 - 115, StaticVar.gameHeight-200);
            g2d.fillRect(StaticVar.gameWidth/2 + 40, 130, StaticVar.gameWidth/2 - 115, StaticVar.gameHeight-200);

            g2d.setColor(Color.gray);
            if(whoseSide == 1) {
                g2d.setColor(Color.red);
            }
            g2d.fillRect(StaticVar.gameWidth/2 - 80, StaticVar.gameHeight/2 - 10, 160, 50);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Submit Trade", StaticVar.gameWidth/2 - 75, StaticVar.gameHeight/2 + 30);

            g2d.drawString("Value: " + playerValue, 85, StaticVar.gameHeight - 100);
            g2d.drawString("Value: " + npcValue, 50 + StaticVar.gameWidth/2, StaticVar.gameHeight - 100);
            if(tooExpensive){
                g2d.setColor(Color.red);
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
                g2d.drawString("That's too expensive!", StaticVar.gameWidth/3, StaticVar.gameHeight/2 + 100);
            }

            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Your Stuff", 275, 150);
            int playerIter = 0;
            int height = 170;
            for(TakeableItem item : playerInventory.getItems()) {
                g2d.setColor(Color.black);
                if(playerIter == highlighted && whoseSide == 0)
                    g2d.drawString(">", 125, height);
                if(playerSelectedItems.contains(playerInventory.getItemAtIndex(playerIter)))
                    g2d.setColor(Color.red);
                g2d.drawString(item.toString(), 150, height);
                height += 20;
                ++playerIter;
            }


            g2d.setColor(Color.black);
            g2d.drawString("Their Stuff", 250 + StaticVar.gameWidth/2, 150);
            int npcIter = 0;
            height = 170;
            for(TakeableItem item : npcInventory.getItems()){
                g2d.setColor(Color.black);
                if(npcIter == highlighted && whoseSide == 2)
                    g2d.drawString(">", 125 + StaticVar.gameWidth/2, height);
                if(npcSelectedItems.contains(npcInventory.getItemAtIndex(npcIter)))
                    g2d.setColor(Color.red);
                g2d.drawString(item.toString(), 150 + StaticVar.gameWidth/2, height);
                height += 20;
                ++npcIter;
            }
        }
        g2d.dispose();
    }

}
