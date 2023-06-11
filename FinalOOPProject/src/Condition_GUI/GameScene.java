package Condition_GUI;

import java.awt.image.BufferedImage;

import Game_Main.MainGUI;
import Game_Main.MainGUI;

public class GameScene {

    protected MainGUI game;
    protected int animationIndex;
    protected int ANIMATION_SPEED = 25;
    protected int tick;

    public GameScene(MainGUI game) {
        this.game = game;
    }

    public MainGUI getGame() {
        return game;
    }

    protected boolean isAnimation(int spriteID) {
        return game.getTileManage().isSpriteAnimation(spriteID);
    }

    protected void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4)
                animationIndex = 0;
        }
    }

    protected BufferedImage getSprite(int spriteID) {
        return game.getTileManage().getSprite(spriteID);
    }

    protected BufferedImage getSprite(int spriteID, int animationIndex) {
        return game.getTileManage().getAniSprite(spriteID, animationIndex);
    }

}
