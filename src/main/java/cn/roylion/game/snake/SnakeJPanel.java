package cn.roylion.game.snake;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

import static cn.roylion.game.snake.util.Config.*;


/**
 * Created by Administrator on 2018/5/17.
 */
@Component("jpanel")
public class SnakeJPanel extends JPanel {

    @Autowired
    private Snake snake;

    public static int state;

    public int count = 0;

    public void init() {

        JFrame frame = new JFrame(name);
        frame.setSize(col*size, row*size);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setUndecorated(true);
        frame.setVisible(true);

        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    /** VK_UP */
                    case 0x26:
                    /** VK_DOWN */
                    case 0x28:
                    /** VK_LEFT */
                    case 0x25:
                    /** VK_RIGHT */
                    case 0x27:
                        if(state == 1) {
                            snake.face = code;
                        }
                        break;
                    case KeyEvent.VK_Q:
                        System.exit(0);
                        break;
                    case KeyEvent.VK_S:
                        if(state == 1) {
                            state = 0;
                        }
                        break;
                    case KeyEvent.VK_C:
                        if(state == 0) {
                            state = 1;
                        }
                        break;
                    case KeyEvent.VK_R:
                        if(state == 2) {
                            snake.init();
                            state = 1;
                        }
                        break;
                }
                repaint();
            }
        });
        this.requestFocus(true);

        snake.init();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(state==1 && count%speed == 0) {
                    synchronized (snake) {
                        snake.move();
                    }
                    repaint();
                }
                count++;
            }
        },0,1000/frameZ);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.black);
        g.fillRect(0,0,col*size,row*size);
        // body
        synchronized (snake) {
            snake.body.stream().forEach(cell -> {
                int x = cell.getX();
                int y = cell.getY();
                paintGrid(g2d, x, y, Color.gray);
            });
        }
        // meat
        paintGrid(g2d, snake.meat.getX(), snake.meat.getY(), Color.red);

        // state
        g.setColor(Color.white);
        g.setFont(Font.getFont("微软雅黑"));
        switch (state) {
            case 0:
                g.drawString("暂停 按‘c’继续游戏",0,10);
                break;
            case 1:
                g.drawString("游戏进行中...",0,10);
                break;
            case 2:
                g.drawString("结束 按‘r’重新开始",0,10);
                break;
        }
//        paintGrid(g2d,0,0,Color.white);
    }

    /**
     * 绘制一个带边框的格子
     * @param g2d
     * @param x 坐标x
     * @param y 坐标y
     * @param color 格子内部颜色
     */
    public void paintGrid(Graphics2D g2d,int x, int y, Color color) {
        int stroke = 2;
        int half = stroke/2;
        int two = 4;

        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(stroke));
//        g2d.drawRect(x*size+half, y*size+half, size-stroke, size-stroke);
        g2d.drawRoundRect(x*size+half, y*size+half, size-stroke, size-stroke,two,two);
        g2d.setColor(color);
//        g2d.fillRect(x*size + stroke, y*size+stroke, size-two ,size-two);
        g2d.fillRoundRect(x*size+half, y*size+half, size-stroke, size-stroke,two,two);
    }
    byte a;
    public static void main(String[] args) {

        SnakeJPanel snakeJPanel = new SnakeJPanel();
        snakeJPanel.a = 120;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                snakeJPanel.a += (byte)1;

                System.out.println(snakeJPanel.a);
                System.out.println(Math.abs(snakeJPanel.a%(byte)3));
            }
        },0,500);
    }
}
