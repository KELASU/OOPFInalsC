package Condition_GUI;

import java.awt.Color;
import java.awt.Graphics;

import Game_Main.MainGUI;
import UIparts.Buttons;

import static Game_Main.Current_Condition.*;

public class Settings extends GameScene implements SMethods {

    private Buttons bMenu;

    public Settings(MainGUI game) {
        super(game);
        initButtons();

    }

    private void initButtons() {
        bMenu = new Buttons("Menu", 2, 2, 100, 30);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 640, 640);

        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            SetGameState(MENU);

    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);

    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    private void resetButtons() {
        bMenu.resetBooleans();

    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub

    }

}
