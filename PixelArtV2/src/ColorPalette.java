import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;


class ColorPalette {
    private JPanel palette;
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
    int newSize = whiter.length + reder.length + bluer.length + greener.length + blacker.length;
    private String[] colors = new String[newSize];
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

        palette = new JPanel(new GridLayout(1, colors.length));
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
    
    
    public JPanel getPalette() {
        return palette;
    }
}