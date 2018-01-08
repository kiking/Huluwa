package wuguoquan.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Scorpion extends Player {//蝎子精
    public Scorpion(int x, int y, Field field) {
        super(x, y, field);
        isgood=false;
        URL loc = this.getClass().getClassLoader().getResource("scorpion.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
