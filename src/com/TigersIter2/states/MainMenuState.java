package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.main.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by slichtenheld on 3/5/2016.
 */
public class MainMenuState extends State implements ActionListener{

    private String name;
    private JButton newGameButton, loadGameButton;

    int nextState = StateManager.MAINMENU;
    public MainMenuState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {
        System.out.println("IntroState initialized");
        name = "MainMenuState";
        newGameButton = new JButton("New Game");
        newGameButton.setMnemonic(KeyEvent.VK_D);
        newGameButton.setActionCommand("newGame");
        newGameButton.setToolTipText("Click to start a new game!");
        newGameButton.addActionListener(this);

        loadGameButton = new JButton("Load Game");
        loadGameButton.setMnemonic(KeyEvent.VK_S);
        loadGameButton.setActionCommand("loadGame");
        loadGameButton.setToolTipText("Click to load a new game!");
        newGameButton.addActionListener(this);

        newGameButton.setBounds(100,100,100,100);
        loadGameButton.setBounds(200,200,100,100);

        add(newGameButton);
        add(loadGameButton);

    }

    @Override
    public void update() {
        if (nextState!= StateManager.MAINMENU){
            stateManager.setState(nextState);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("newGame".equals(e.getActionCommand())){
            stateManager.setState(StateManager.GAME);
            System.out.println("newGame pressed");
        }
        else if ("loadGame".equals(e.getActionCommand())) {
            stateManager.setState(StateManager.INTRO);
            //nextState = StateManager.INTRO; //just for testing purposes for now
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
//        g2d.setColor(Color.GREEN);
//        g2d.fillRect(0,20, this.getWidth(), this.getHeight()-20);
        g2d.setColor(Color.RED);
        g2d.drawString("MainMenu paintComponent. Components: " + this.getComponentCount(), 100, 500);
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
