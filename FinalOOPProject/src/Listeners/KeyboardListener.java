package Listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Game_Main.Current_Condition.*;

import Game_Main.MainGUI;
import Game_Main.Current_Condition;

public class KeyboardListener implements KeyListener {
    private MainGUI game;

    public KeyboardListener(MainGUI game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Current_Condition.gameState == EDIT)
            game.getEditor().keyPressed(e);
        else if (Current_Condition.gameState == PLAYING)
            game.getInPlay().keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
