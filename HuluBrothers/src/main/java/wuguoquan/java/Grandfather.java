package wuguoquan.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Grandfather extends Player {
    public Grandfather(int x, int y, Field field) {
        super(x, y, field);

        isgood=true;

        URL loc = this.getClass().getClassLoader().getResource("grandfather.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
