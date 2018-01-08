package wuguoquan.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Snake extends Player {
    public Snake(int x, int y, Field field) {
        super(x, y, field);

        isgood=false;
        URL loc = this.getClass().getClassLoader().getResource("snake.png");
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
