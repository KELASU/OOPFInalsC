import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PixelArtApp extends JFrame {
    private PixelCanvas pixelCanvas;
    private ColorPalette colorPalette;

    public PixelArtApp() {
        setTitle("Pixel Art App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pixelCanvas = new PixelCanvas(20, 20, "#ffffff");
        colorPalette = new ColorPalette(pixelCanvas);

        JPanel canvasPanel = new JPanel(new GridBagLayout());
        canvasPanel.add(pixelCanvas.getCanvas());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(canvasPanel, BorderLayout.CENTER);
        mainPanel.add(colorPalette.getPalette(), BorderLayout.SOUTH);

        JPanel settingsPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pixelCanvas.saveImage();
            }
        });
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pixelCanvas.loadImage();
            }
        });
        JButton resizeButton = new JButton("Resize");
        resizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numPixelsStr = JOptionPane.showInputDialog("Enter the number of pixels per side:");
                int numPixels = Integer.parseInt(numPixelsStr);
                String pixelSizeStr = JOptionPane.showInputDialog("Enter the pixel size:");
                int pixelSize = Integer.parseInt(pixelSizeStr);
                pixelCanvas.resizeCanvas(numPixels, pixelSize);
            }
        });
        settingsPanel.add(saveButton);
        settingsPanel.add(loadButton);
        settingsPanel.add(resizeButton);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(settingsPanel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PixelArtApp();
            }
        });
    }
}
