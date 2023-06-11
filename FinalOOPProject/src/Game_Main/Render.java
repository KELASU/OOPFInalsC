package Game_Main;

import com.sun.tools.javac.Main;

import java.awt.Graphics;

public class Render {

    private MainGUI game;

    public Render(MainGUI game) {
        this.game = game;
    }

    public void render(Graphics g) {
        switch (Current_Condition.gameState) {
            case MENU:
                game.getMenu().render(g);
                break;
            case PLAYING:
                game.getInPlay().render(g);
                break;
            case SETTINGS:
                game.getSettings().render(g);
                break;
            case EDIT:
                game.getEditor().render(g);
                break;
            case GAME_OVER:
                game.getGameOver().render(g);
                break;
            default:
                break;

        }

    }

}
