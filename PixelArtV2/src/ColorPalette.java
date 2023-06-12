import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;


class ColorPalette {
    private JPanel palette;
    // initialize all the color of the color palette
    Colored white = new White();
    Colored red = new Red();
    Colored blue = new Blue();
    Colored Green = new Green();
    Colored black = new Black();
    String[] whiter = white.getHexCode();
    String[] reder = red.getHexCode();
    String[] bluer = blue.getHexCode();
    String[] greener = Green.getHexCode();
    String[] blacker = black.getHexCode();
    // find the total numbers of color and calculate the size needed for the color string array
    int newSize = whiter.length + reder.length + bluer.length + greener.length + blacker.length;
    private String[] colors = new String[newSize];
    // loop through to add the colors to the color string list
    public ColorPalette(PixelCanvas pixelCanvas) {
        for (int i = 0; i< whiter.length; i++){
            colors[i]=whiter[i];}
        for (int i = 0; i< reder.length; i++){
            colors[i+whiter.length]=reder[i];}
        for (int i = 0; i< bluer.length; i++){
            colors[i+whiter.length+reder.length]=bluer[i];}
        for (int i = 0; i< greener.length; i++){
            colors[i+whiter.length+reder.length+bluer.length]=greener[i];}
        for (int i = 0; i< blacker.length; i++){
            colors[i+whiter.length+reder.length+bluer.length+greener.length]=blacker[i];}
        // initialize a new jpanel for the jbuttons or the color palette
        palette = new JPanel(new GridLayout(1, colors.length));
        // loop to create colored buttons for the purpose of changing the selected color
        for (String colorCode : colors) {
            JButton colorButton = new JButton();
            colorButton.setPreferredSize(new Dimension(30, 30));
            colorButton.setBackground(Color.decode(colorCode));
            colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    pixelCanvas.setSelectedColor(colorCode);
                }
            });
            palette.add(colorButton);
        }
    }
    
    // getter
    public JPanel getPalette() {
        return palette;
    }
}