package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Equipment;
import com.TigersIter2.entities.Inventory;
import com.TigersIter2.items.Potion;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.stats.PlayerStats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StatusView extends View implements ActionListener{

    private final int VIEW_X_START = 75;
    private final int VIEW_Y_START = 130;

    int currentAnimationFrame = 0;
    private boolean display;
    private Inventory playerInventory;
    private PlayerStats stats;
    private Equipment equipment;
    private Avatar avatar;
    private boolean inventorySelected;
    private int whoseSide;
    private int highlighted;
    private int totalWidth;
    private int totalHeight;
    private List<TakeableItem> playerSelectedItems;
    private List<TakeableItem> selectedEquipment;
    private boolean notEquippableMessage = false;
    private boolean drankPotionMessage = false;

    public StatusView(Avatar a){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
        display = false;
        whoseSide = 0;
        highlighted = 0;
        avatar = a;
        playerInventory = a.getInventory();
        stats = a.getStats();
        equipment = a.getEquipment();
        playerSelectedItems = new ArrayList<TakeableItem>();
        selectedEquipment = new ArrayList<TakeableItem>();
        totalWidth = StaticVar.gameWidth - 150;
        totalHeight = StaticVar.gameHeight - 200;
        inventorySelected = true;
    }

    public void handleInput(int input){
        if(input != -1) {
            notEquippableMessage = false;
            drankPotionMessage = false;
        }

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
            if(whoseSide == 0){
                if(playerSelectedItems.contains(playerInventory.getItemAtIndex(highlighted)))
                    playerSelectedItems.remove(playerInventory.getItemAtIndex(highlighted));
                else
                    playerSelectedItems.add(playerInventory.getItemAtIndex(highlighted));
            }
            else if(whoseSide == 1){
                if(inventorySelected) {
                    for (TakeableItem item : playerSelectedItems) {
                        if(highlighted == 0) {
                            if(item.isEquippable()) {
                                equipItem(item);
                            }
                            else{
                                if(item.isUsable()){
                                    System.out.println("Is potion");
                                    //avatar.getStats().addStatModifier(item.getStatsModifier());       In future when we have different potions
                                    avatar.getStats().increaseCurrentLife(item.getStatsModifier().getLife());
                                    playerInventory.getItems().remove(item);
                                    drankPotionMessage = true;
                                }
                                else
                                    notEquippableMessage = true;
                            }
                        }
                        else {
                            avatar.dropItem(item);
                        }
                    }
                }
                else{
                    for (TakeableItem item : selectedEquipment) {
                        playerInventory.addItem(item);
                        equipment.getItems().remove(item);
                        avatar.getStats().removeStatModifier(item.getStatsModifier());
                        if(highlighted == 1){
                            avatar.dropItem(item);
                        }
                    }
                }
                resetView();
            }
            else if(whoseSide == 2){
                if(selectedEquipment.contains(equipment.getItemAtIndex(highlighted)))
                    selectedEquipment.remove(equipment.getItemAtIndex(highlighted));
                else
                    selectedEquipment.add(equipment.getItemAtIndex(highlighted));
            }
        }
        else if(input == 5){
            resetView();
        }
    }

    private void equipItem(TakeableItem item){
        //If a weapon is already equipped, replace it
        if(item.getItemType() == 6){
            boolean canEquipWeaponType = false;
            String occupation = avatar.getOccupation().toString();
            String weaponType = item.getWeaponType();
            if(occupation.equals("Sneak")){
                if(weaponType.equals("RangedWeapon"))
                    canEquipWeaponType = true;
            }
            else if(occupation.equals("Smasher")){
                if(weaponType.equals("OneHandedWeapon") || weaponType.equals("TwoHandedWeapon"))
                    canEquipWeaponType = true;
            }

            if(canEquipWeaponType) {
                Iterator<TakeableItem> iter = equipment.getItems().iterator();
                while (iter.hasNext()) {
                    TakeableItem i = iter.next();
                    if(i.getItemType() == 6) {
                        playerInventory.addItem(i);
                        avatar.getStats().removeStatModifier(i.getStatsModifier());
                        iter.remove();
                    }
                }
            }
            else {
                notEquippableMessage = true;
                return;
            }
        }
        equipment.addItem(item);
        playerInventory.getItems().remove(item);
        avatar.getStats().addStatModifier(item.getStatsModifier());
    }

    public void toggle(){
        display = !display;
    }

    public boolean getDisplay(){
        return display;
    }

    public void incrementHighlighted(){
        if(whoseSide == 0){
            if(highlighted < playerInventory.getItems().size()-1)
                ++highlighted;
        }
        else if(whoseSide == 1){
            if(highlighted == 0)
                ++highlighted;
        }
        else if(whoseSide == 2){
            if(highlighted < equipment.getItems().size()-1)
                ++highlighted;
        }
    }

    public void decrementHighlighted(){
        if(highlighted > 0)
            --highlighted;
    }

    public void incrementWhoseSide(){
        if(whoseSide == 0){
            highlighted = 0;
            ++whoseSide;
        }
        else if(whoseSide  == 1 && !equipment.isEmpty()){
            playerSelectedItems.clear();
            highlighted = 0;
            ++whoseSide;
            inventorySelected = false;
        }
    }

    public void decrementWhoseSide(){
        if(whoseSide == 2){
            highlighted = 0;
            --whoseSide;
        }
        else if(whoseSide == 1 && !playerInventory.isEmpty()){
            selectedEquipment.clear();
            highlighted = 0;
            --whoseSide;
            inventorySelected = true;
        }
    }

    public int getWhoseSide(){
        return whoseSide;
    }

    public void resetView(){
        whoseSide = 0;
        inventorySelected = true;
        highlighted = 0;
        playerSelectedItems.clear();
        selectedEquipment.clear();
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

            g2d.setColor(Color.gray);
            if(whoseSide == 1 && highlighted == 0){
                g2d.setColor(Color.red);
            }
            g2d.fillRect(VIEW_X_START+totalWidth/3-60, VIEW_Y_START+totalHeight/4 - 25, 120, 50);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            if(inventorySelected)
                g2d.drawString("Equip", VIEW_X_START+totalWidth/3-30, VIEW_Y_START+totalHeight/4 + 5);
            else
                g2d.drawString("Unequip", VIEW_X_START+totalWidth/3-50, VIEW_Y_START+totalHeight/4 + 5);

            g2d.setColor(Color.gray);
            if(whoseSide == 1 && highlighted == 1){
                g2d.setColor(Color.red);
            }
            g2d.fillRect(VIEW_X_START+totalWidth/3-60, VIEW_Y_START+totalHeight/4 + 75, 120, 50);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Drop", VIEW_X_START+totalWidth/3-30, VIEW_Y_START+totalHeight/4 + 105);

            if(notEquippableMessage) {
                g2d.setColor(Color.red);
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
                g2d.drawString("That item is not equippable!", VIEW_X_START + totalWidth / 3 + 70, VIEW_Y_START + totalHeight / 4);
            }

            if(drankPotionMessage) {
                g2d.setColor(Color.red);
                g2d.setFont(new Font("TimesRoman", Font.BOLD, 50));
                g2d.drawString("Drank potion!", VIEW_X_START + totalWidth / 3 + 70, VIEW_Y_START + totalHeight / 4);
            }



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
            for (TakeableItem item : equipment.getItems()) {
                g2d.setColor(Color.black);
                if (playerIter == highlighted && whoseSide == 2)
                    g2d.drawString(">", VIEW_X_START + totalWidth/2 - 55, height);
                if (selectedEquipment.contains(equipment.getItemAtIndex(playerIter)))
                    g2d.setColor(Color.red);
                g2d.drawString(item.toString(), VIEW_X_START + totalWidth/2 - 30, height);
                height += 20;
                ++playerIter;
            }
        }


        //Draw stats bar
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, StaticVar.gameWidth, 50);
        g2d.setColor(Color.white);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
        g2d.drawString("Health", 45, 22);
        g2d.drawString("Mana", 140, 22);
        g2d.drawString("Experience", 230, 22);
        g2d.drawString("Lives", 365, 22);
        g2d.drawString("Offense", 445, 22);
        g2d.drawString("Defense", 545, 22);
        g2d.drawString("Armor", 655, 22);
        g2d.drawString("Strength", 735, 22);
        g2d.drawString("Agility", 855, 22);
        g2d.drawString("Intellect", 945, 22);
        g2d.drawString("Hardiness", 1065, 22);
        g2d.drawString("Speed", 1185, 22);
        g2d.setStroke(new BasicStroke());
        g2d.drawLine(0, 25, StaticVar.gameWidth, 25);

        //Draw stats
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g2d.drawString(Integer.toString(stats.getCurrentLife()) + "/" + Integer.toString(stats.getLife()), 45, 45);
        g2d.drawString(Integer.toString(stats.getCurrentMana()) + "/" + Integer.toString(stats.getMana()), 140, 45);
        g2d.drawString(Integer.toString(stats.getExperience()) + "/" +
                Integer.toString(stats.getExperienceRequiredForLevel(stats.getLevel()+1)), 230, 45);
        g2d.drawString(Integer.toString(stats.getLivesLeft()), 380, 45);
        g2d.drawString(Integer.toString(stats.getOffensiveRating()), 470, 45);
        g2d.drawString(Integer.toString(stats.getDefensiveRating()), 570, 45);
        g2d.drawString(Integer.toString(stats.getArmorRating()), 670, 45);
        g2d.drawString(Integer.toString(stats.getStrength()), 770, 45);
        g2d.drawString(Integer.toString(stats.getAgility()), 870, 45);
        g2d.drawString(Integer.toString(stats.getIntellect()), 970, 45);
        g2d.drawString(Integer.toString(stats.getHardiness()), 1100, 45);
        g2d.drawString(Integer.toString(stats.getMovement()), 1210, 45);

        g2d.dispose();
    }

}
