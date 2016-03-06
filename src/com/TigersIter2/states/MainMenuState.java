package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.MainMenuSprite;
import com.TigersIter2.main.Controller;

import java.awt.*;


//JButtons fuck with threads!
public class MainMenuState extends State {

    private int testCounter = 0;

    private String name;
    //pr


    public MainMenuState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {
        System.out.println("IntroState initialized");
        name = "MainMenuState";

        MainMenuSprite.init();

    }

    @Override
    public void update() {
        testCounter ++;
        if (testCounter == 100){
            stateManager.setState(StateManager.GAME);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());
        g2d.drawImage(MainMenuSprite.mainMenuImage,195,100,null);
        g2d.drawImage(MainMenuSprite.newGameButton,270,450,null);
        g2d.drawImage(MainMenuSprite.loadGameButton,685,450,null);
        g2d.drawImage(MainMenuSprite.mainMenuTextImage,390,600,null);
        //g2d.drawString("MainMenu paintComponent. Components: " + this.getComponentCount(), 100, 500);
        g2d.dispose();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public String returnName() {
        return name;
    }

    //Overriding component stuff, may be unnecessary
    @Override
    public Dimension getPreferredSize() { //good practice, don't know if necessary or not
        return new Dimension(StaticVar.gameWidth, StaticVar.gameHeight);
    }


}
