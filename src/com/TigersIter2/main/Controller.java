package com.TigersIter2.main;

import com.TigersIter2.views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private final int TRADE_MENU_UP = 0;
    private final int TRADE_MENU_DOWN = 1;
    private final int TRADE_MENU_LEFT = 2;
    private final int TRADE_MENU_RIGHT = 3;
    private final int TRADE_MENU_SELECT = 4;
    private final int TRADE_MENU_EXIT = 5;

    private JComponent component;
    private int xMovement, yMovement;
    private int cameraXMovement, cameraYMovement;
    private boolean movingUp, movingDown, movingUpLeft, movingUpRight, movingDownLeft, movingDownRight;
    private int optionSelected;
    private int tradeMenuInput;
    private List<String> controlNames;
    private List<Integer> controlCodes;

    //temporary? --all temporary stuff added by Sam for MainMenu
    private int keyPressed;


    public Controller(JComponent component){
        this.component = component;
        xMovement = 0;
        yMovement = 0;
        movingUp = false;
        movingDown = false;
        movingDownLeft = false;
        movingDownRight = false;
        optionSelected = -1;
        tradeMenuInput = -1;
        controlNames = new ArrayList<String>();
        controlCodes = new ArrayList<Integer>();
        setDefaultControlCodes();
    }

    private void setDefaultControlCodes(){
        //Movement
        controlCodes.add(KeyEvent.VK_NUMPAD7);
        controlCodes.add(KeyEvent.VK_NUMPAD8);
        controlCodes.add(KeyEvent.VK_NUMPAD9);
        controlCodes.add(KeyEvent.VK_NUMPAD1);
        controlCodes.add(KeyEvent.VK_NUMPAD2);
        controlCodes.add(KeyEvent.VK_NUMPAD3);

        //Camera Movement
        controlCodes.add(KeyEvent.VK_U);
        controlCodes.add(KeyEvent.VK_I);
        controlCodes.add(KeyEvent.VK_O);
        controlCodes.add(KeyEvent.VK_J);
        controlCodes.add(KeyEvent.VK_K);
        controlCodes.add(KeyEvent.VK_L);

        //Actions
        controlCodes.add(KeyEvent.VK_C);
        controlCodes.add(KeyEvent.VK_Z);
        controlCodes.add(KeyEvent.VK_X);
        controlCodes.add(KeyEvent.VK_BACK_SPACE);
        controlCodes.add(KeyEvent.VK_ENTER);
        controlCodes.add(KeyEvent.VK_V);
    }

    public List<Integer> getControlCodes(){
        return controlCodes;
    }

    public JComponent getComponent(){
        return component;
    }

    public int getXMovement(){
        if(yMovement == 0 || xMovement == 0)
            return 0;
        else if(xMovement < 0)
            return -1;
        else
            return 1;
    }

    public int getyMovement(){
        if(yMovement < 0)
            return -1;
        else if(yMovement == 0)
            return 0;
        else
            return 1;
    }

    public int getCameraXMovement(){
        return cameraXMovement;
    }

    public int getCameraYMovement(){
        return cameraYMovement;
    }

    public int getOptionSelected(){
        return optionSelected;
    }

    public void resetOptionSelected(){
        optionSelected = -1;
    }

    public int getKeyPressed() {return keyPressed; }

    public void setKeyPressed(int i){
        keyPressed = i;
    }

    public int getTradeMenuInput(){
        int ret = tradeMenuInput;
        tradeMenuInput = -1;
        return ret;
    }

    public List<String> getControlNames(){
        return controlNames;
    }

    public void setBindings(){
        InputMap inMap = component.getInputMap();
        inMap.clear();

        // Each key we want to use is put into the InputMap
        // false means key pressed, true means key released
        // 0 means no modifiers

        // Movement
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(0), 0, false), "UP_LEFT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(0), 0, true), "UP_LEFT_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(1), 0, false), "UP_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(1), 0, true), "UP_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(2), 0, false), "UP_RIGHT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(2), 0, true), "UP_RIGHT_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(3), 0, false), "DOWN_LEFT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(3), 0, true), "DOWN_LEFT_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(4), 0, false), "DOWN_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(4), 0, true), "DOWN_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(5), 0, false), "DOWN_RIGHT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(5), 0, true), "DOWN_RIGHT_STOP");

        //For scrolling I guess (Miles)
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(6), 0, false), "UP_SCROLL_LEFT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(6), 0, true), "UP_SCROLL_LEFT_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(7), 0, false), "UP_SCROLL_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(7), 0, true), "UP_SCROLL_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(8), 0, false), "UP_SCROLL_RIGHT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(8), 0, true), "UP_SCROLL_RIGHT_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(9), 0, false), "DOWN_SCROLL_LEFT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(9), 0, true), "DOWN_SCROLL_LEFT_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(10), 0, false), "DOWN_SCROLL_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(10), 0, true), "DOWN_SCROLL_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(11), 0, false), "DOWN_SCROLL_RIGHT_GO");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(11), 0, true), "DOWN_SCROLL_RIGHT_STOP");

        //Actions
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(12), 0, true), "ATTACK_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(13), 0, true), "VEHICLE_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(14), 0, true), "STATUS_VIEW_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(15), 0, true), "MENU_BACK");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(16),0,false),"MENU_SELECT");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(16),0,true),"MENU_SELECT_STOP");
        inMap.put(KeyStroke.getKeyStroke(controlCodes.get(17), 0, true), "USE_SKILL_STOP");

        //Controls that can't be changed
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "OPTIONS_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, true), "OPTION1_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, true), "OPTION2_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, true), "OPTION3_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, true), "OPTION4_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, true), "OPTION5_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, true), "CONTROLLER_MENU");

        //For those who don't have a numpad, temporary controls...
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "UP_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "UP_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "DOWN_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "DOWN_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "UP_LEFT_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, true), "UP_LEFT_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "UP_RIGHT_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, true), "UP_RIGHT_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "DOWN_LEFT_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "DOWN_LEFT_STOP");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "DOWN_RIGHT_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "DOWN_RIGHT_STOP");


        //temporary??
        //inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false),"ENTER_GO");
        //inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,true),"ENTER_STOP");

        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0,false),"SPACE_GO");
        inMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE,0,true),"SPACE_STOP");

        // Each entry in the InputMap is then inserted in the ActionMap
        // Actions defined below
        ActionMap aMap = component.getActionMap();
        aMap.clear();
        aMap.put("UP_GO", UP_GO);
        aMap.put("UP_STOP", UP_STOP);
        aMap.put("DOWN_GO", DOWN_GO);
        aMap.put("DOWN_STOP", DOWN_STOP);
        aMap.put("UP_LEFT_GO", UP_LEFT_GO);
        aMap.put("UP_LEFT_STOP", UP_LEFT_STOP);
        aMap.put("UP_RIGHT_GO", UP_RIGHT_GO);
        aMap.put("UP_RIGHT_STOP", UP_RIGHT_STOP);
        aMap.put("DOWN_LEFT_GO", DOWN_LEFT_GO);
        aMap.put("DOWN_LEFT_STOP", DOWN_LEFT_STOP);
        aMap.put("DOWN_RIGHT_GO", DOWN_RIGHT_GO);
        aMap.put("DOWN_RIGHT_STOP", DOWN_RIGHT_STOP);
        aMap.put("OPTIONS_GO", OPTIONS_GO);
        aMap.put("OPTION1_STOP", OPTION1_STOP);
        aMap.put("OPTION2_STOP", OPTION2_STOP);
        aMap.put("OPTION3_STOP", OPTION3_STOP);
        aMap.put("OPTION4_STOP", OPTION4_STOP);
        aMap.put("OPTION5_STOP", OPTION5_STOP);
        aMap.put("MENU_BACK", MENU_BACK);
        aMap.put("VEHICLE_STOP", VEHICLE_STOP);
        aMap.put("STATUS_VIEW_STOP", STATUS_VIEW_STOP);
        aMap.put("ATTACK_STOP", ATTACK_STOP);
        aMap.put("CONTROLLER_MENU", CONTROLLER_MENU);
        aMap.put("USE_SKILL_STOP", USE_SKILL_STOP);



        //For scrolling (Miles)
        aMap.put("UP_SCROLL_GO", UP_SCROLL_GO);
        aMap.put("UP_SCROLL_STOP", UP_SCROLL_STOP);
        aMap.put("DOWN_SCROLL_GO", DOWN_SCROLL_GO);
        aMap.put("DOWN_SCROLL_STOP", DOWN_SCROLL_STOP);
        aMap.put("UP_SCROLL_LEFT_GO", UP_SCROLL_LEFT_GO);
        aMap.put("UP_SCROLL_LEFT_STOP", UP_SCROLL_LEFT_STOP);
        aMap.put("UP_SCROLL_RIGHT_GO", UP_SCROLL_RIGHT_GO);
        aMap.put("UP_SCROLL_RIGHT_STOP", UP_SCROLL_RIGHT_STOP);
        aMap.put("DOWN_SCROLL_LEFT_GO", DOWN_SCROLL_LEFT_GO);
        aMap.put("DOWN_SCROLL_LEFT_STOP", DOWN_SCROLL_LEFT_STOP);
        aMap.put("DOWN_SCROLL_RIGHT_GO", DOWN_SCROLL_RIGHT_GO);
        aMap.put("DOWN_SCROLL_RIGHT_STOP", DOWN_SCROLL_RIGHT_STOP);

        //temporary??
        aMap.put("MENU_SELECT", ENTER_KEY_GO);
        aMap.put("MENU_SELECT_STOP",ENTER_KEY_STOP);

        aMap.put("SPACE_GO", SPACE_KEY_GO);
        aMap.put("SPACE_STOP", SPACE_KEY_STOP);


        controlNames.clear();

        //Movement
        for(int i = 0; i < controlCodes.size(); ++i)
            controlNames.add(KeyEvent.getKeyText(controlCodes.get(i)));
    }

    public void changeControl(int index, int keyCode){
        controlCodes.remove(index);
        controlCodes.add(index,keyCode);

        setBindings();
    }

    public void menuBindings(){
        ActionMap aMap = component.getActionMap();
        aMap.put("ENTER_GO", ENTER_KEY_GO);
        aMap.put("ENTER_STOP", ENTER_KEY_STOP);
    }

    public void tradeBindings(){
        ActionMap aMap = component.getActionMap();
        if(aMap.get("UP_GO") != MENU_UP) {
            aMap.clear();
            aMap.put("UP_GO", MENU_UP);
            aMap.put("DOWN_GO", MENU_DOWN);
            aMap.put("UP_LEFT_GO", MENU_LEFT);
            aMap.put("DOWN_LEFT_GO", MENU_LEFT);
            aMap.put("UP_RIGHT_GO", MENU_RIGHT);
            aMap.put("DOWN_RIGHT_GO", MENU_RIGHT);
            aMap.put("MENU_SELECT", MENU_SELECT);
            aMap.put("MENU_BACK", MENU_EXIT);
        }
    }

    public void revertTradeBindings(){
        if(component.getActionMap().get("UP_GO") == MENU_UP) {
            component.getActionMap().clear();
            component.getInputMap().clear();
            setBindings();
        }
    }

    public void setStatusViewControls(boolean statusView){
        ActionMap aMap = component.getActionMap();
        aMap.clear();
        if(statusView){
            aMap.put("UP_GO", MENU_UP);
            aMap.put("DOWN_GO", MENU_DOWN);
            aMap.put("UP_LEFT_GO", MENU_LEFT);
            aMap.put("DOWN_LEFT_GO", MENU_LEFT);
            aMap.put("UP_RIGHT_GO", MENU_RIGHT);
            aMap.put("DOWN_RIGHT_GO", MENU_RIGHT);
            aMap.put("MENU_SELECT", MENU_SELECT);
            aMap.put("MENU_BACK", MENU_EXIT);
            aMap.put("STATUS_VIEW_STOP", STATUS_VIEW_STOP);
        }
        else{
            component.getInputMap().clear();
            setBindings();
        }
    }

    public void setControlViewControls(boolean statusView){
        ActionMap aMap = component.getActionMap();
        aMap.clear();
        if(statusView){
            aMap.put("UP_GO", MENU_UP);
            aMap.put("DOWN_GO", MENU_DOWN);
            aMap.put("UP_LEFT_GO", MENU_LEFT);
            aMap.put("DOWN_LEFT_GO", MENU_LEFT);
            aMap.put("UP_RIGHT_GO", MENU_RIGHT);
            aMap.put("DOWN_RIGHT_GO", MENU_RIGHT);
            aMap.put("MENU_SELECT", MENU_SELECT);
            aMap.put("MENU_BACK", MENU_EXIT);
            aMap.put("CONTROLLER_MENU", CONTROLLER_MENU);
        }
        else{
            component.getInputMap().clear();
            setBindings();
        }
    }

    Action MENU_UP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tradeMenuInput = TRADE_MENU_UP;
        }
    };

    Action MENU_DOWN = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tradeMenuInput = TRADE_MENU_DOWN;
        }
    };

    Action MENU_LEFT = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tradeMenuInput = TRADE_MENU_LEFT;
        }
    };

    Action MENU_RIGHT = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tradeMenuInput = TRADE_MENU_RIGHT;
        }
    };

    Action MENU_SELECT = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tradeMenuInput = TRADE_MENU_SELECT;
        }
    };

    Action MENU_EXIT = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tradeMenuInput = TRADE_MENU_EXIT;
        }
    };

    Action UP_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingUp) {
                --yMovement;
                movingUp = true;
                View.setCurrentMapMode(View.mapMode.PLAYER_FOLLOW_MODE);
            }
        }
    };

    Action UP_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingUp) {
                ++yMovement;
                movingUp = false;
            }
        }
    };

    Action DOWN_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingDown) {
                ++yMovement;
                movingDown = true;
                View.setCurrentMapMode(View.mapMode.PLAYER_FOLLOW_MODE);
            }
        }
    };

    Action DOWN_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingDown) {
                --yMovement;
                movingDown = false;
            }
        }
    };

    Action UP_LEFT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingUpLeft) {
                --xMovement;
                --yMovement;
                movingUpLeft = true;
                //temporary??
                keyPressed = KeyEvent.VK_LEFT;
                View.setCurrentMapMode(View.mapMode.PLAYER_FOLLOW_MODE);
            }
        }
    };

    Action UP_LEFT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingUpLeft) {
                ++xMovement;
                ++yMovement;
                movingUpLeft = false;
               keyPressed = 0;
            }
        }
    };

    Action UP_RIGHT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingUpRight) {
                ++xMovement;
                --yMovement;
                movingUpRight = true;
                keyPressed = KeyEvent.VK_RIGHT;
                View.setCurrentMapMode(View.mapMode.PLAYER_FOLLOW_MODE);
            }
        }
    };

    Action UP_RIGHT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingUpRight) {
                --xMovement;
                ++yMovement;
                movingUpRight = false;
                keyPressed = 0;
            }
        }
    };

    Action DOWN_LEFT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingDownLeft) {
                --xMovement;
                ++yMovement;
                movingDownLeft = true;
                //temporary??
                keyPressed = KeyEvent.VK_LEFT;
                View.setCurrentMapMode(View.mapMode.PLAYER_FOLLOW_MODE);
            }
        }
    };

    Action DOWN_LEFT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingDownLeft) {
                ++xMovement;
                --yMovement;
                movingDownLeft = false;
                keyPressed = 0;
            }
        }
    };

    Action DOWN_RIGHT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingDownRight) {
                ++xMovement;
                ++yMovement;
                movingDownRight = true;
                keyPressed = KeyEvent.VK_RIGHT;
                View.setCurrentMapMode(View.mapMode.PLAYER_FOLLOW_MODE);
            }
        }
    };

    Action DOWN_RIGHT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingDownRight) {
                --xMovement;
                --yMovement;
                movingDownRight = false;
                keyPressed = 0;
            }
        }
    };

    //----For Scrolling (Miles)
    Action UP_SCROLL_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingUp) {
                --cameraYMovement;
                movingUp = true;
                View.setCurrentMapMode(View.mapMode.SCROLL_MODE);
            }
        }
    };

    Action UP_SCROLL_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingUp) {
                ++cameraYMovement;
                movingUp = false;
            }
        }
    };

    Action DOWN_SCROLL_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingDown) {
                ++cameraYMovement;
                movingDown = true;
                View.setCurrentMapMode(View.mapMode.SCROLL_MODE);
            }
        }
    };

    Action DOWN_SCROLL_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingDown) {
                --cameraYMovement;
                movingDown = false;
            }
        }
    };

    Action UP_SCROLL_LEFT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingUpLeft) {
                --cameraXMovement;
                --cameraYMovement;
                movingUpLeft = true;
                //temporary??
                keyPressed = KeyEvent.VK_LEFT;
                View.setCurrentMapMode(View.mapMode.SCROLL_MODE);
            }
        }
    };

    Action UP_SCROLL_LEFT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingUpLeft) {
                ++cameraXMovement;
                ++cameraYMovement;
                movingUpLeft = false;
                keyPressed = 0;
            }
        }
    };

    Action UP_SCROLL_RIGHT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingUpRight) {
                ++cameraXMovement;
                --cameraYMovement;
                movingUpRight = true;
                keyPressed = KeyEvent.VK_RIGHT;
                View.setCurrentMapMode(View.mapMode.SCROLL_MODE);
            }
        }
    };

    Action UP_SCROLL_RIGHT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingUpRight) {
                --cameraXMovement;
                ++cameraYMovement;
                movingUpRight = false;
                keyPressed = 0;
            }
        }
    };

    Action DOWN_SCROLL_LEFT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingDownLeft) {
                --cameraXMovement;
                ++cameraYMovement;
                movingDownLeft = true;
                //temporary??
                keyPressed = KeyEvent.VK_LEFT;
                View.setCurrentMapMode(View.mapMode.SCROLL_MODE);
            }
        }
    };

    Action DOWN_SCROLL_LEFT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingDownLeft) {
                ++cameraXMovement;
                --cameraYMovement;
                movingDownLeft = false;
                keyPressed = 0;
            }
        }
    };

    Action DOWN_SCROLL_RIGHT_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!movingDownRight) {
                ++cameraXMovement;
                ++cameraYMovement;
                movingDownRight = true;
                keyPressed = KeyEvent.VK_RIGHT;
                View.setCurrentMapMode(View.mapMode.SCROLL_MODE);
            }
        }
    };

    Action DOWN_SCROLL_RIGHT_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movingDownRight) {
                --cameraXMovement;
                --cameraYMovement;
                movingDownRight = false;
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

    Action SPACE_KEY_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            keyPressed = KeyEvent.VK_SPACE;
        }
    };

    Action SPACE_KEY_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            keyPressed = 0;
        }
    };

    Action OPTIONS_GO = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = -1;
        }
    };

    Action OPTION1_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 1;
        }
    };

    Action OPTION2_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 2;
        }
    };

    Action OPTION3_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 3;
        }
    };

    Action OPTION4_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 4;
        }
    };

    Action OPTION5_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 5;
        }
    };

    Action MENU_BACK = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 100;
        }
    };

    Action VEHICLE_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 6;
        }
    };

    Action STATUS_VIEW_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 7;
        }
    };

    Action ATTACK_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 8;
        }
    };

    Action CONTROLLER_MENU = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 9;
        }
    };

    Action USE_SKILL_STOP = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            optionSelected = 10;
        }
    };
}
