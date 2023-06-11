package Condition_GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import EnemyC.Enemy;
import UtilityIG.LoadSave;
import Game_Main.MainGUI;
import Managers.EnManage;
import Managers.AtkManager;
import Managers.ManageTower;
import Managers.ManageWave;
import Obj.PathCords;
import Obj.Tower;
import UIparts.ActBar;
import static UtilityIG.Constants.Tiles.GRASS_TILE;

public class InPlay extends GameScene implements SMethods {

    private int[][] lvl;
    private ActBar actionBar;
    private int mouseX, mouseY;
    private EnManage enemyManager;
    private ManageTower towerManager;
    private AtkManager projManager;
    private ManageWave waveManager;
    private PathCords start, end;
    private Tower selectedTower;
    private int goldTick;
    private boolean gamePaused;

    public InPlay(MainGUI game) {
        super(game);
        loadDefaultLevel();

        actionBar = new ActBar(0, 640, 640, 160, this);
        enemyManager = new EnManage(this,start,end);
        towerManager = new ManageTower(this);
        projManager = new AtkManager(this);
        waveManager = new ManageWave(this);
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData();
        ArrayList<PathCords> points = LoadSave.GetLevelPathCordss();
        start = points.get(0);
        end = points.get(1);
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public void update() {
        if (!gamePaused) {
            updateTick();
            waveManager.update();

            // Gold tick
            goldTick++;
            if (goldTick % (60 * 3) == 0)
                actionBar.addGold(1);

            if (isAllEnemiesDead()) {
                if (isThereMoreWaves()) {
                    waveManager.startWaveTimer();
                    if (isWaveTimerOver()) {
                        waveManager.increaseWaveIndex();
                        enemyManager.getEnemies().clear();
                        waveManager.resetEnemyIndex();

                    }
                }
            }

            if (isTimeForNewEnemy()) {
                if (!waveManager.isWaveTimerOver())
                    spawnEnemy();
            }

            enemyManager.update();
            towerManager.update();
            projManager.update();
        }

    }

    private boolean isWaveTimerOver() {

        return waveManager.isWaveTimerOver();
    }

    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();

    }

    private boolean isAllEnemiesDead() {

        if (waveManager.isThereMoreEnemiesInWave())
            return false;

        for (Enemy e : enemyManager.getEnemies())
            if (e.isAlive())
                return false;

        return true;
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if (waveManager.isTimeForNewEnemy()) {
            if (waveManager.isThereMoreEnemiesInWave())
                return true;
        }

        return false;
    }

    public void setSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

    @Override
    public void render(Graphics g) {

        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projManager.draw(g);

        drawSelectedTower(g);
        drawHighlight(g);

    }

    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 32, 32);

    }

    private void drawSelectedTower(Graphics g) {
        if (selectedTower != null)
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
    }

    private void drawLevel(Graphics g) {

        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
                } else
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    public int getTileType(int x, int y) {
        int xCord = x / 32;
        int yCord = y / 32;

        if (xCord < 0 || xCord > 19)
            return 0;
        if (yCord < 0 || yCord > 19)
            return 0;

        int id = lvl[y / 32][x / 32];
        return game.getTileManage().getTile(id).getTileType();
    }

    @Override
    public void mouseClicked(int x, int y) {
        // Below 640y
        if (y >= 640)
            actionBar.mouseClicked(x, y);
        else {
            // Above 640y
            if (selectedTower != null) {
                // Trying to place a tower
                if (isTileGrass(mouseX, mouseY)) {
                    if (getTowerAt(mouseX, mouseY) == null) {
                        towerManager.addTower(selectedTower, mouseX, mouseY);

                        removeGold(selectedTower.getTowerType());

                        selectedTower = null;

                    }
                }
            } else {
                // Not trying to place a tower
                // Checking if a tower exists at x,y
                Tower t = getTowerAt(mouseX, mouseY);
                actionBar.displayTower(t);
            }
        }
    }

    private void removeGold(int towerType) {
        actionBar.payForTower(towerType);

    }

    public void upgradeTower(Tower displayedTower) {
        towerManager.upgradeTower(displayedTower);

    }

    public void removeTower(Tower displayedTower) {
        towerManager.removeTower(displayedTower);
    }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x, y);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y / 32][x / 32];
        int tileType = game.getTileManage().getTile(id).getTileType();
        return tileType == GRASS_TILE;
    }

    public void shootEnemy(Tower t, Enemy e) {
        projManager.newAttack(t, e);

    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            selectedTower = null;
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640)
            actionBar.mouseMoved(x, y);
        else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640)
            actionBar.mousePressed(x, y);

    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    public void rewardPlayer(int enemyType) {
        actionBar.addGold(UtilityIG.Constants.Enemies.GetReward(enemyType));
    }

    public ManageTower getManageTower() {
        return towerManager;
    }

    public EnManage getEnemyManger() {
        return enemyManager;
    }

    public ManageWave getManageWave() {
        return waveManager;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void removeOneLife() {
        actionBar.removeOneLife();
    }

    public void resetEverything() {

        actionBar.resetEverything();

        // Managers
        enemyManager.reset();
        towerManager.reset();
        projManager.reset();
        waveManager.reset();

        mouseX = 0;
        mouseY = 0;

        selectedTower = null;
        goldTick = 0;
        gamePaused = false;

    }

}