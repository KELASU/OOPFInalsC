import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PixelArtApp extends JFrame {
    private PixelCanvas pixelCanvas;
    private ColorPalette colorPalette;

    public PixelArtApp() {
        setTitle("Pixel Art App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Initialize a new PixelCanvas dimension 20 by 20 with each box sizing 20 pixels with box colors white
        pixelCanvas = new PixelCanvas(20, 20, "#ffffff");
        // initialize colorPalette
        colorPalette = new ColorPalette(pixelCanvas);
        // Initialize gridbaglayout
        // which is to places components in rectangles (cells) in a grid, 
        //and then uses the components' preferred sizes to determine how big the cells should be.
        JPanel canvasPanel = new JPanel(new GridBagLayout());
        // add canvas to the frame
        canvasPanel.add(pixelCanvas.getCanvas());
        //initialize main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        // set canvasPanel alignment or fit to center
        mainPanel.add(canvasPanel, BorderLayout.CENTER);
        // set colorPalette to align or fit to South
        mainPanel.add(colorPalette.getPalette(), BorderLayout.SOUTH);

        // Creates or load in the save, load and resize buttons
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
        // allignment
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(settingsPanel, BorderLayout.NORTH);
        //sizing the frame so that all its contents are at or above their preferred sizes
        pack();
        // sets the location of the window relative to the specified component
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
