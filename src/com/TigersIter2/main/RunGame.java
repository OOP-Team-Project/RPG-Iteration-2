package com.TigersIter2.main;

import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.managers.StateManager;
import javax.swing.*;

//contains main method, kicks off everything
public class RunGame extends JFrame {

    private StateManager stateManager;

    private boolean running = false;

    private final int FPS = StaticVar.fps;
    private final int TARGET_TIME = 1000 / FPS;

    public static void main(String[] args){
        RunGame runGame = new RunGame();

        //runGame.setLocationRelativeTo(null); //doesn't work here, added to loadGame(SL)
        runGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //process quits when x button is pressed
        runGame.setResizable(true); //Was False but I like to resize things (Miles)
        runGame.runGameLoop();


    }



    //loads the game
    public void loadGame(){

        setSize(StaticVar.gameWidth, StaticVar.gameHeight); //added class for static variables
        setLocationRelativeTo(null); //window appears in center of screen(SL)

        stateManager = new StateManager();
        this.getContentPane().add(stateManager);
        this.setVisible(true);

    }

    //Starts a new thread and runs the game loop in it.
    public void runGameLoop()
    {
        loadGame();
        running = true;
        Thread loop = new Thread()
        {
            public void run()
            {
                gameLoop();
            }
        };
        loop.start();
    }

    //Only run this in another Thread!
    private void gameLoop() {

        System.out.println("gameLoop called, running = " + running);
        long start = 0;
        long elapsed = 0;
        long wait = 0;

        // game loop
        while (running) {

            start = System.nanoTime();

            //updateGame();
            stateManager.update(wait);
            renderGame();

            elapsed = System.nanoTime() - start;

            wait = TARGET_TIME - elapsed / 1000000;
            if (wait < 0) wait = TARGET_TIME;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //renders the contents of current panel
    //first if statement checks if we are executing repaint on
    //the correct thread for swing functions.
    //if no, we dispatch the repaint to the correct thread (EDT)
    private void renderGame() {
        //System.out.println("is in EDT thread? " + SwingUtilities.isEventDispatchThread());
        if (SwingUtilities.isEventDispatchThread()) {
            System.out.println("On the right thread for using swing method repaint");
            this.repaint();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //System.out.println("Had to invoke repaint from the wrong thread onto EDT");
                    repaint();

                }
            });
        }
    }
}
