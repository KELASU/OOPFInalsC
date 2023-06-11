package Game_Main;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import Listeners.KeyboardListener;
import Listeners.MouseListeners;

public class Screen extends JPanel {

    private MainGUI game;
    private Dimension size;

    private MouseListeners myMouseListener;
    private KeyboardListener keyboardListener;

    public Screen(MainGUI game) {
        this.game = game;

        setPanelSize();

    }

    public void initInputs() {
        myMouseListener = new MouseListeners(game);
        keyboardListener = new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640, 800);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.getRender().render(g);

    }

}