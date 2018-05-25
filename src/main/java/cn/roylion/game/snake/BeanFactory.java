package cn.roylion.game.snake;

import cn.roylion.game.snake.bean.Cell;

/**
 * Created by roylion on 2018/5/17.
 */
public class BeanFactory {

    public static Cell getInstance(int x, int y) {
        return new Cell(x, y);
    }
}
