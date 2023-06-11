import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ColorPalette {
    private String[] colors = {"#ffffff", "#000000", "#ff0000", "#00ff00", "#0000ff", "#ffff00", "#ff00ff", "#00ffff", "#ff8000", "#8000ff"};
    private JPanel palette;

    public ColorPalette(PixelCanvas pixelCanvas) {
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