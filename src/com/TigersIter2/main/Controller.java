package com.TigersIter2.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Controller {

    private JComponent component;
    private int xMovement, yMovement;
    private boolean movingUp, movingDown, movingLeft, movingRight;

    //temporary? --all temporary stuff added by Sam for MainMenu
    private int keyPressed;


    public Controller(JComponent component){
        this.component = component;
        xMovement = 0;
        yMovement = 0;
        movingUp = false;
        movingDown = false;
        movingLeft = false;
        movingRight = false;
    }

    public int getXMovement(){
        return xMovement;
    }

    public int getyMovement(){
        return yMovement;
    }

    public int getKeyPressed() {return keyPressed; }

    public void setBindings(){
        InputMap inMap = component.getInputMap();

        // Each key we want to use is put into the InputMap
        // false means key pressed, true means key released
        // 0 means no modifiers
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "UP_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "DOWN_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "LEFT_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "LEFT_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "RIGHT_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "RIGHT_STOP");
        //temporary??
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false),"ENTER_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,true),"ENTER_STOP");

        // Each entry in the InputMap is then inserted in the ActionMap
        // Actions defined below
        component.getActionMap().put("UP_GO", UP_ARROW_GO);
        component.getActionMap().put("UP_STOP", UP_ARROW_STOP);
        component.getActionMap().put("DOWN_GO", DOWN_ARROW_GO);
        component.getActionMap().put("DOWN_STOP", DOWN_ARROW_STOP);
        component.getActionMap().put("LEFT_GO", LEFT_ARROW_GO);
        component.getActionMap().put("LEFT_STOP", LEFT_ARROW_STOP);
        component.getActionMap().put("RIGHT_GO", RIGHT_ARROW_GO);
        component.getActionMap().put("RIGHT_STOP", RIGHT_ARROW_STOP);

        //temporary??
        component.getActionMap().put("ENTER_GO", ENTER_KEY_GO);
        component.getActionMap().put("ENTER_STOP", ENTER_KEY_STOP);

        System.out.println("Bindings have been set");
    }

    Action UP_ARROW_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingUp) {
                --yMovement;
                movingUp = true;
            }
        }
    };

    Action UP_ARROW_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingUp) {
                ++yMovement;
                movingUp = false;
            }
        }
    };

    Action DOWN_ARROW_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingDown) {
                ++yMovement;
                movingDown = true;
            }
        }
    };

    Action DOWN_ARROW_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingDown) {
                --yMovement;
                movingDown = false;
            }
        }
    };

    Action LEFT_ARROW_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingLeft) {
                --xMovement;
                movingLeft = true;
                //temporary??
                keyPressed = KeyEvent.VK_LEFT;
            }
        }
    };

    Action LEFT_ARROW_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingLeft) {
                ++xMovement;
                movingLeft = false;
               keyPressed = 0;
            }
        }
    };

    Action RIGHT_ARROW_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingRight) {
                ++xMovement;
                movingRight = true;
                keyPressed = KeyEvent.VK_RIGHT;
            }
        }
    };

    Action RIGHT_ARROW_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingRight) {
                --xMovement;
                movingRight = false;
                keyPressed = 0;
            }
        }
    };

    Action ENTER_KEY_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            keyPressed = KeyEvent.VK_ENTER;
        }
    };

    Action ENTER_KEY_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            keyPressed = 0;
        }
    };
}
