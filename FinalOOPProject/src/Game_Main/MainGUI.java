package Game_Main;

import javax.swing.JFrame;

import UtilityIG.LoadSave;
import Managers.TileManage;
import Condition_GUI.Editing;
import Condition_GUI.GameOver;
import Condition_GUI.Menu;
import Condition_GUI.InPlay;
import Condition_GUI.Settings;

public class MainGUI extends JFrame implements Runnable {

    public Screen gameScreen;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    // Classes
    private Render render;
    private Menu menu;
    private InPlay playing;
    private Settings settings;
    private Editing editing;
    private GameOver gameOver;

    private TileManage tileManager;

    public MainGUI() {

        LoadSave.CreateFolder();

        createDefaultLevel();
        initClasses();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Your Game");
        add(gameScreen);
        pack();
        setVisible(true);

    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for (int i = 0; i < arr.length; i++)
            arr[i] = 0;

        LoadSave.CreateLevel(arr);

    }

    private void initClasses() {
        tileManager = new TileManage();
        render = new Render(this);
        gameScreen = new Screen(this);
        menu = new Menu(this);
        playing = new InPlay(this);
        settings = new Settings(this);
        editing = new Editing(this);
        gameOver = new GameOver(this);

    }

    public void start() {
        gameThread = new Thread(this) {
        };

        gameThread.start();
    }

    private void updateGame() {
        switch (Current_Condition.gameState) {
            case EDIT:
                editing.update();
                break;
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
            case SETTINGS:
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while (true) {
            now = System.nanoTime();

            // Render
            if (now - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = now;
                frames++;
            }

            // Update
            if (now - lastUpdate >= timePerUpdate) {
                updateGame();
                lastUpdate = now;
                updates++;
            }

//			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
//				frames = 0;
//				updates = 0;
//				lastTimeCheck = System.currentTimeMillis();
//			}

        }

    }

    // Getters and setters
    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public InPlay getInPlay() {
        return playing;
    }

    public Settings getSettings() {
        return settings;
    }

    public Editing getEditor() {
        return editing;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public TileManage getTileManage() {
        return tileManager;
    }

}