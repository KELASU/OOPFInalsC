import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class PixelCanvas {
    //default pixel size
    private int pixelSize = 10;
    //default number of Pixels per side
    private int numPixels = 64;
    // default color
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
        // adding mouse listener so that it can check if a mouse click on a certain box
        // if mouse click on a certain box with a different color then box color change
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / pixelSize;
                int y = e.getY() / pixelSize;
                pixelArt[x][y] = Color.decode(selectedColor);
                // after mouse sees that a certain box is pressed
                // repaint is used to re render or update the canvas
                // so it now loads in with the new color
                canvas.repaint();
            }
        });
    }
    //geter
    public JPanel getCanvas() {
        return canvas;
    }
    // setter for selected color
    public void setSelectedColor(String colorCode) {
        selectedColor = colorCode;
    }
    // method to change canvas size
    public void resizeCanvas(int newNumPixels, int newPixelSize) {
        // setting new num pixels
        numPixels = newNumPixels;
        // setting new pixelsize
        pixelSize = newPixelSize;
        // looping to create the new canvas
        pixelArt = new Color[numPixels][numPixels];
        for (int i = 0; i < numPixels; i++) {
            for (int j = 0; j < numPixels; j++) {
                //setting the color
                if (i < pixelArt.length && j < pixelArt[i].length) {
                    pixelArt[i][j] = Color.decode(selectedColor);
                }
            }
        }
        canvas.setPreferredSize(new Dimension(pixelSize * numPixels, pixelSize * numPixels));
        // recalculate the layout since tiles are added or deleted
        canvas.revalidate();
        // render in the new canvas
        canvas.repaint();
    }
    // method to saveImage
    public void saveImage() {
        // Create a new BufferedImage object named image with dimensions 
        //pixelSize * numPixels (width) and pixelSize * numPixels (height). 
        //The image is initialized with the RGB color model.
        BufferedImage image = new BufferedImage(pixelSize * numPixels, pixelSize * numPixels, BufferedImage.TYPE_INT_RGB);
        // Create a Graphics2D object named g2d from the image using the createGraphics() method. 
        //This object will be used to draw on the image.
        Graphics2D g2d = image.createGraphics();
        // iterate over the pixelArt
        for (int i = 0; i < numPixels; i++) {
            for (int j = 0; j < numPixels; j++) {
                // Set the color of g2d to the color of the current pixel in the pixelArt array 
                //using g2d.setColor(pixelArt[i][j])
                g2d.setColor(pixelArt[i][j]);
                //Use g2d.fillRect() to fill a rectangle on the image 
                //at position (i * pixelSize, j * pixelSize) 
                //with dimensions pixelSize (width) and pixelSize (height). 
                //This represents one pixel of the pixel art.
                g2d.fillRect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);
            }
        }
        //After drawing all the pixels, dispose of the g2d object using g2d.dispose() to release system resources.
        g2d.dispose();

        //Create a new JFileChooser object named fileChooser. 
        //This allows the user to select a file or specify a filename for saving the image.
        JFileChooser fileChooser = new JFileChooser();
        // Show the save dialog using fileChooser.showSaveDialog(null). 
        //This displays a dialog box where the user can choose the location and name for the file to be saved.
        int result = fileChooser.showSaveDialog(null);
        //Check if the user clicked the "Save" or "OK" button in the dialog 
        //by comparing the return value of showSaveDialog() to JFileChooser.APPROVE_OPTION.
        if (result == JFileChooser.APPROVE_OPTION) {
            //If the user clicked "Save," get the selected file using fileChooser.getSelectedFile()
            File file = fileChooser.getSelectedFile();
            // Use ImageIO.write() to save the image to the selected file as a PNG image. 
            //The file format is specified as "PNG".
            try {
                ImageIO.write(image, "PNG", file);
                // Display a success message using JOptionPane.showMessageDialog() to inform the user that the image was saved successfully.
                JOptionPane.showMessageDialog(null, "Image saved successfully!");
            //If an exception occurs during the file saving process (e.g., file I/O error), 
            //the exception is caught by the catch block, and the stack trace is printed.
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    // load image is similar to save image
    // it's just instead of saving the image you load in the image
    // and when you select the image it will loop through and create the canvas
    // but this code doesn't change the size and adjust to the image loaded
    // at the end it will render the image
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

    // the rest is setters
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