package wuguoquan.java;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Huluwa  extends Player{

    public Huluwa(int x, int y, Field field,int s) {
        super(x, y, field);

        isgood=true;
        String as;
        switch (s)
        {
            case 1:
               as="huluwa1.png";
               break;
            case 2:
                as="huluwa2.png";
                break;
            case 3:
                as="huluwa3.png";
                break;
            case 4:
                as="huluwa4.png";
                break;
            case 5:
                as="huluwa5.png";
                break;
            case 6:
                as="huluwa6.png";
                break;
            default:
                as="huluwa7.png";
                break;
        }


        URL loc = this.getClass().getClassLoader().getResource(as);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }
}
