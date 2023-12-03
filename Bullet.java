package com.gzq.TankGame;

/**
 * @author 高志强
 * @version 1.0
 */
//子弹类：思路：当发射一颗子弹后，就相当于启动了一个线程
public class Bullet extends Thread{
    private int x;
    private int y;
    private int direct ;
    int speed = 10;
    boolean isAlive = true;
    @Override
    public void run() {//发射
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    y += speed;
                    break;
                case 2:
                    x += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            System.out.println("子弹坐标：" + "x=" +  x + "y=" + y);
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {
                isAlive = false;
                break;
            }
        }
    }

    public Bullet(int x, int y, int direct){
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
