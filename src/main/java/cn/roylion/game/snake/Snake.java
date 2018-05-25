package cn.roylion.game.snake;

import cn.roylion.game.snake.bean.Cell;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import static cn.roylion.game.snake.util.Config.*;

/**
 * Created by roylion on 2018/5/17.
 */
@Component
public class Snake {

    public LinkedList<Cell> body;

    public int face;

    public Cell meat;

    public void init() {
        body = new LinkedList<Cell>();
        int x = col/2;
        int y = row/2;

        int len = Math.min(row - y, 4);
        for(int i=0; i<len;i++){
           add(x, y+i);
        }

        generateMeat();
        face = 0x26;


    }

    public void add(int x, int y){
        Cell cell = BeanFactory.getInstance(x, y);
        body.add(cell);
    }

    public void move(){
        Cell head = body.getFirst();
        Cell second = body.get(1);

        int x = head.getX();
        int y = head.getY();
        switch (face) {
            /** VK_UP */
            case 0x26:y--;break;
            /** VK_DOWN */
            case 0x28:y++;break;
            /** VK_LEFT */
            case 0x25:x--;break;
            /** VK_RIGHT */
            case 0x27:x++;break;
        }
        //修正
        if(second!=null && second.compare(x, y)) {
            switch (face) {
                /** VK_UP */
                case 0x26:face=0x28;break;
                /** VK_DOWN */
                case 0x28:face=0x26;break;
                /** VK_LEFT */
                case 0x25:face=0x27;break;
                /** VK_RIGHT */
                case 0x27:face=0x25;break;
            }
            move();
            return;
        }

        // eating food
        if(meat.compare(x, y)) {
            body.addFirst(BeanFactory.getInstance(x, y));
            generateMeat();
            return;
        }

        // outBounds 出界
        if(x<0 || x>=col || y<0 || y>=row) {
            //TODO 游戏结束
            SnakeJPanel.state = 2;
            return;
        }

        // crashBody 身体
        if(contains(x,y)){
            //TODO 游戏结束
            SnakeJPanel.state = 2;
            return;
        }

        Cell tail = body.removeLast();
        tail.init(x, y);
        body.addFirst(tail);
    }

    public void generateMeat(){
        if(body.size() == row*col){
            meat = null;
            return;
        }
        boolean flag;
        int x,y;
        do {
            x = random.nextInt(col);
            y = random.nextInt(row);

            flag = contains(x, y);
        } while(flag);

        if(meat == null) {
            meat = new Cell(x, y);
        } else {
            meat.init(x, y);
        }
    }

    public boolean contains(int x, int y) {
        for(Cell cell:body) {
            if(cell.compare(x, y)){
                return true;
            }
        }
        return false;
    }
}
