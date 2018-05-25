package cn.roylion.game.snake.bean;

/**
 * 格子，万物皆格子
 * Created by roylion on 2018/5/17.
 */
public class Cell {

    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void init(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean compare(int x, int y) {
        return this.x == x && this.y == y;
    }
}
