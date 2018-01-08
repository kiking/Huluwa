package wuguoquan.java;


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

public class Player extends Thing2D implements Runnable {
    protected Field field;

    protected final int SPACE = 40;
    protected final int OFFSET = 30;
    public boolean isdead;
    public boolean isgood;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    protected Position position;

    public Player(int x, int y, Field field) {
        super(x, y);

        this.field = field;

        isdead=false;

        position=field.getPositions().get(x).get(y);

        field.getPositions().get(x).get(y).setHolder(this);


    }

    public void move(int x,int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        if (!(nx<0||nx>12||ny<0||ny>10))
        {
            synchronized (this.field.getPositions().get(this.x()).get(this.y()))
            {

                if (this.field.getPositions().get(nx).get(ny).isempty)
                {
                    this.setX(nx);
                    this.setY(ny);
                    synchronized (this.field.getPositions().get(nx).get(ny))
                    {
                        this.position.isempty=true;
                    }
                    this.position=this.field.getPositions().get(nx).get(ny);
                    this.position.setHolder(this);
                }
                else if (this.field.getPositions().get(nx).get(ny).getHolder().isgood!=this.isgood &&
                        this.field.getPositions().get(nx).get(ny).getHolder().isdead==false)
                {
                    Random r = new Random();
                    if (r.nextInt(100)<70)
                    {

                        this.field.getPositions().get(nx).get(ny).getHolder().isdead=true;
                        if (this.field.getPositions().get(nx).get(ny).getHolder().isgood)
                        {
                            this.field.good--;
                        }
                        else
                        {
                            this.field.bad--;
                        }

                    }
                    else
                    {
                        this.isdead=true;
                        if (this.isgood)
                        {
                            this.field.good--;
                        }
                        else
                        {
                            this.field.bad--;
                        }
                    }
                }

            }


        }
    }

    public void run() {
        while (!Thread.interrupted()) {

            if ((!isdead)&&this.field.bad!=0&&this.field.good!=0)
            {
                Random rand = new Random();
                this.move(rand.nextInt(3)-1, rand.nextInt(3)-1);
                try {

                    Thread.sleep(rand.nextInt(1000) + 1000);
                    this.field.repaint();

                } catch (Exception e) {

                }
            }
        }
    }
}