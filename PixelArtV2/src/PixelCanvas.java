import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class PixelCanvas {
    private int pixelSize = 10;
    private int numPixels = 64;
    private String selectedColor = "#ffffff";
    private Color[][] pixelArt;
    private JPanel canvas;

    public PixelCanvas(int Size, int num, String s) {
        pixelSize = Size;
        numPixels = num;
        selectedColor = s;
        pixelArt = new Color[numPixels][numPixels];
        for (int i = 0; i < numPixels; i++) {
            for (int j = 0; j < numPixels; j++) {
                pixelArt[i][j] = Color.decode(selectedColor);
            }
        }

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (int i = 0; i < numPixels; i++) {
                    for (int j = 0; j < numPixels; j++) {
                        g.setColor(pixelArt[i][j]);
                        g.fillRect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);
                        g.setColor(Color.BLACK);
                        g.drawRect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);
                    }
                }
            }
        };
        canvas.setPreferredSize(new Dimension(pixelSize * numPixels, pixelSize * numPixels));
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / pixelSize;
                int y = e.getY() / pixelSize;
                pixelArt[x][y] = Color.decode(selectedColor);
                canvas.repaint();
            }
        });
    }

    public JPanel getCanvas() {
        return canvas;
    }

    public void setSelectedColor(String colorCode) {
        selectedColor = colorCode;
    }
    public void resizeCanvas(int newNumPixels, int newPixelSize) {
        numPixels = newNumPixels;
        pixelSize = newPixelSize;
        pixelArt = new Color[numPixels][numPixels];
        for (int i = 0; i < numPixels; i++) {
            for (int j = 0; j < numPixels; j++) {
                if (i < pixelArt.length && j < pixelArt[i].length) {
                    pixelArt[i][j] = Color.decode(selectedColor);
                }
            }
        }
        canvas.setPreferredSize(new Dimension(pixelSize * numPixels, pixelSize * numPixels));
        canvas.revalidate();
        canvas.repaint();
    }
    public void saveImage() {
        BufferedImage image = new BufferedImage(pixelSize * numPixels, pixelSize * numPixels, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        for (int i = 0; i < numPixels; i++) {
            for (int j = 0; j < numPixels; j++) {
                g2d.setColor(pixelArt[i][j]);
                g2d.fillRect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);
            }
        }
        g2d.dispose();

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(image, "PNG", file);
                JOptionPane.showMessageDialog(null, "Image saved successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(file);
                for (int i = 0; i < numPixels; i++) {
                    for (int j = 0; j < numPixels; j++) {
                        pixelArt[i][j] = new Color(image.getRGB(i * pixelSize, j * pixelSize));
                    }
                }
                canvas.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setPixelSize(int pixelSize) {
        this.pixelSize = pixelSize;
    }

    public void setNumPixels(int numPixels) {
        this.numPixels = numPixels;
    }

    public void setPixelArt(Color[][] pixelArt) {
        this.pixelArt = pixelArt;
    }

    public void setCanvas(JPanel canvas) {
        this.canvas = canvas;
    }
}