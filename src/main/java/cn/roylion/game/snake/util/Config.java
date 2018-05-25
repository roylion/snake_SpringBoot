package cn.roylion.game.snake.util;

import java.util.concurrent.ThreadLocalRandom;

import static cn.roylion.game.snake.util.SpringUtil.environment;

/**
 * Created by Administrator on 2018/5/17.
 */
public class Config {

    public static final ThreadLocalRandom random;

    public static String name;
    public static int row;
    public static int col;
    public static int size;
    public static int speed;
    public static int frameZ;

    static {
        random = ThreadLocalRandom.current();
        name = environment.getProperty("config.name");
        row = Integer.valueOf(environment.getProperty("config.row"));
        col = Integer.valueOf(environment.getProperty("config.col"));
        size = Integer.valueOf(environment.getProperty("config.size"));
        speed = Integer.valueOf(environment.getProperty("config.speed"));
        frameZ = Integer.valueOf(environment.getProperty("config.frameZ"));
    }
}
