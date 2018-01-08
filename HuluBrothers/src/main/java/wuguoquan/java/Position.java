package wuguoquan.java;

public class Position {
    private int x;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;
    public boolean isempty;

    public Player getHolder() {
        return holder;
    }

    public void setHolder(Player holder) {
        this.holder = holder;
        this.isempty=false;
    }

    private Player holder;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Position(int x,int y){
        super();
        this.x = x;
        this.y = y;
        this.isempty=true;
    }

}
