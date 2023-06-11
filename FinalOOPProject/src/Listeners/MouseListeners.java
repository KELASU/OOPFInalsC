package Listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Game_Main.MainGUI;
import Game_Main.Current_Condition;

public class MouseListeners implements MouseListener, MouseMotionListener {

    private MainGUI game;

    public MouseListeners(MainGUI game) {
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (Current_Condition.gameState) {
            case MENU:
                game.getMenu().mouseDragged(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getInPlay().mouseDragged(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseDragged(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mouseDragged(e.getX(), e.getY());
                break;
            default:
                break;
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Current_Condition.gameState) {
            case MENU:
                game.getMenu().mouseMoved(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getInPlay().mouseMoved(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseMoved(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mouseMoved(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mouseMoved(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            switch (Current_Condition.gameState) {
                case MENU:
                    game.getMenu().mouseClicked(e.getX(), e.getY());
                    break;
                case PLAYING:
                    game.getInPlay().mouseClicked(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSettings().mouseClicked(e.getX(), e.getY());
                    break;
                case EDIT:
                    game.getEditor().mouseClicked(e.getX(), e.getY());
                    break;
                case GAME_OVER:
                    game.getGameOver().mouseClicked(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (Current_Condition.gameState) {
            case MENU:
                game.getMenu().mousePressed(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getInPlay().mousePressed(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mousePressed(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mousePressed(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mousePressed(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Current_Condition.gameState) {
            case MENU:
                game.getMenu().mouseReleased(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getInPlay().mouseReleased(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseReleased(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mouseReleased(e.getX(), e.getY());
                break;
            case GAME_OVER:
                game.getGameOver().mouseReleased(e.getX(), e.getY());
                break;

            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // We wont use this

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // We wont use this
    }

}