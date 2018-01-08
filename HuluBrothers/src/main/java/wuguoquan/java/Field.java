package wuguoquan.java;

import sun.awt.windows.ThemeReader;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 40;
    public int good;
    public int bad;

    public ArrayList<ArrayList<Position>> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<ArrayList<Position>> positions) {
        this.positions = positions;
    }

    private ArrayList<ArrayList<Position>> positions=new ArrayList<ArrayList<Position>>();

    private ArrayList tiles = new ArrayList();
    private Huluwa[] huluwas = new Huluwa[7];
    private Frog[] frogs=new Frog[6];

    private Grandfather grandfather;
    private Snake snake;
    private Scorpion scorpion;

    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
            ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n" +
                    ".............\n";//11 13

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Tile a;

        for (int i=0;i<15;i++)
        {
            ArrayList<Position> p=new ArrayList<Position>();
            for (int j=0;j<13;j++)
            {
                Position ps=new Position(i,j);
                p.add(ps);
            }
            positions.add(p);
        }


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == '@') {
                //player = new Player(x, y, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }


        grandfather =new Grandfather(0,0,this);
        snake=new Snake(12,0,this);
        scorpion =new Scorpion(12,1,this);

        for (int i=0;i<huluwas.length;i++)
        {
            huluwas[i]=new Huluwa(0,i+1,this,i+1);
        }

        for (int i=0;i<frogs.length;i++)
        {
            frogs[i]=new Frog(12,i+2,this);
        }
        good=8;
        bad=8;

    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);


        world.add(grandfather);
        world.add(snake);
        world.add(scorpion);

        for (int i=0;i<frogs.length;i++)
        {
            world.add(frogs[i]);
        }

        for (int i=0;i<huluwas.length;i++) {
            world.add(huluwas[i]);
        }



        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), ((Player) item).getPosition().getX()*SPACE+OFFSET, ((Player) item).getPosition().getY()*SPACE+OFFSET, this);
                if (((Player) item).isdead)
                {
                    URL loc = item.getClass().getClassLoader().getResource("isdead.png");
                    ImageIcon iia = new ImageIcon(loc);
                    Image image = iia.getImage();
                    g.drawImage(image,((Player) item).getPosition().getX()*SPACE+OFFSET, ((Player) item).getPosition().getY()*SPACE+OFFSET, this);
                }
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_SPACE) {

                for (int i=0;i<huluwas.length;i++)
                {
                    new Thread(huluwas[i]).start();
                }
                for (int i=0;i<frogs.length;i++)
                {
                    new Thread(frogs[i]).start();
                }
                new Thread(snake).start();
                new Thread(scorpion).start();
                new Thread(grandfather).start();

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }


    public void restartLevel() {

        tiles.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}