package com.gzq.TankGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author 高志强
 * @version 1.0
 */
//坦克类
public class Tank {
    private int x;
    private int y;
    private int direct;
    Vector<Bullet> bullets = new Vector<>();


    //构造器
    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    //上下左右移动方法
    //shang
    public void moveUp(){
        y -= 3;
    }
    public void moveDown(){
        y += 3;
    }
    public void moveRight(){
        x += 3;
    }
    public void moveLeft(){
        x -= 3;
    }
    //射击方法
    public void shot(){
        Bullet bullet = null;
        switch (direct){
            case 0 :
                 bullet = new Bullet(x + 15, y, direct);
                break;
            case 1:
                 bullet = new Bullet(x + 15, y + 60, direct);
                break;
            case 2:
                bullet = new Bullet(x + 60, y + 15, direct);
                break;
            case 3:
                bullet = new Bullet(x, y + 15, direct);
                break;
        }
        bullets.add(bullet);

        bullet.start();
    }
    //get，Set方法
    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
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

}
