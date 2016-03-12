package com.TigersIter2.states;

import com.TigersIter2.assets.FileWriter;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.MainMenuSprite;
import com.TigersIter2.assets.sprites.NewGameMenuSprites;
import com.TigersIter2.main.Controller;
import com.TigersIter2.managers.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by slichtenheld on 3/9/2016.
 */
public class NewGameState extends State {

    int counterBuffer;

    private int avatarSel;

    public NewGameState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {
        System.out.println("NewGameState initialized");
        counterBuffer = 0;
        NewGameMenuSprites.init();
        avatarSel = 2;
    }

    @Override
    public void update(long elapsed) {

        counterBuffer++;
        if (controller.getXMovement()==-1 && avatarSel > 1 && counterBuffer > 3) {
            avatarSel--;
            counterBuffer =0;
        }
        else if (controller.getXMovement()==1 && avatarSel < 3 && counterBuffer > 3) {
            avatarSel++;
            counterBuffer =0;
        }
        else if (controller.getKeyPressed() == KeyEvent.VK_SPACE) stateManager.setState(StateManager.MAINMENU);

        if (controller.getKeyPressed()==KeyEvent.VK_ENTER){
            FileWriter.stringToFile(StaticVar.avatarFile,FileWriter.intToString(avatarSel));
        }
    }

    @Override
    public void handleInput() {}

    @Override
    public String returnName() {
        return "NewGameState";
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(new Color(0.8352941f, 0.78039217f, 0.98039216f));
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());
        g2d.drawImage(NewGameMenuSprites.ChooseAnAvatar,400,100,null);
        g2d.drawImage(MainMenuSprite.largeSmasher,240,250,null);
        g2d.drawImage(MainMenuSprite.largeWizard,550,250,null);
        g2d.drawImage(MainMenuSprite.largeSneak,825,250,null);

        if (avatarSel == 0){
            g2d.drawImage(NewGameMenuSprites.SmasherStringPressed,200,450,null);
            g2d.drawImage(NewGameMenuSprites.SummonerString,500,450,null);
            g2d.drawImage(NewGameMenuSprites.SneakString,825,450,null);
        }
        else if (avatarSel == 1){
            g2d.drawImage(NewGameMenuSprites.SmasherString,200,450,null);
            g2d.drawImage(NewGameMenuSprites.SummonerStringPressed,500,450,null);
            g2d.drawImage(NewGameMenuSprites.SneakString,825,450,null);
        }
        if (avatarSel == 2){
            g2d.drawImage(NewGameMenuSprites.SmasherString,200,450,null);
            g2d.drawImage(NewGameMenuSprites.SummonerString,500,450,null);
            g2d.drawImage(NewGameMenuSprites.SneakStringPressed,825,450,null);
        }

        //g2d.drawString("MainMenu paintComponent. Components: " + this.getComponentCount(), 100, 500);
        g2d.dispose();
    }
}
