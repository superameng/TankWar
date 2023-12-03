package com.gzq.TankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 高志强
 * @version 1.0
 */
//绘图区域
public class MyPanel extends JPanel implements KeyListener , Runnable {
    //定义我的坦克
    Hero hero = null;
    //将敌方坦克放到数组当中
    Vector<Enemy> enemies = new Vector<>();
    int enemyTankSize = 3;//敌方坦克的数量
    public MyPanel() {

        hero = new Hero(100,100,0);//初始化自己的坦克
        //初始化敌方坦克
        for (int i = 0; i < enemyTankSize; i++) {
            enemies.add(new Enemy(100 * (i + 1), 0, 0));

        }
        //为敌方坦克添加子弹
        for (int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);
            enemy.setDirect(1);
            Bullet bullet = new Bullet(enemy.getX() + 15, enemy.getY() + 60, enemy.getDirect());
            enemy.bullets.add(bullet);
            bullet.start();
        }
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.fillRect(0,0,1000,750);
        //画出我方坦克
        drawTank(hero.getX(), hero.getY(),g, hero.getDirect(), 0);
        for(Enemy enemy : enemies){
            //画出敌方坦克

            drawTank(enemy.getX(), enemy.getY(),g, enemy.getDirect(), 1);
            for(int j = 0; j < enemy.bullets.size(); j ++){
                Bullet bullet = enemy.bullets.get(j);
                if(bullet.isAlive){
                    System.out.println("敌方发射子弹");
                    g.setColor(Color.blue);
                    g.fillOval(bullet.getX(), bullet.getY(),10,10);
                }
                else{
                    bullet = new Bullet(enemy.getX() + 15, enemy.getY() + 60, enemy.getDirect());
                    bullet.isAlive = true;
                    bullet.start();
                }
            }
        }
        //绘制我方坦克发射的子弹
        if(!hero.bullets.isEmpty()){
            for(int i = 0; i < hero.bullets.size(); i++){
                Bullet bullet = hero.bullets.get(i);
                if(bullet.isAlive){
                    System.out.println("子弹被重绘");
                    g.setColor(Color.blue);
                    g.fillOval(bullet.getX(), bullet.getY(),10,10);
                }
                else {
                    hero.bullets.remove(bullet);
                }
            }
        }
//        for(int i = 0; i < enemies.size(); i++){
//            Enemy enemy = enemies.get(i);
//            while(!enemy.bullets.isEmpty()){
//                for(int j = 0; j < enemy.bullets.size(); j++) {
//                    Bullet bullet = enemy.bullets.get(j);
//                    if (bullet.isAlive) {
//                        System.out.println("子弹被重绘");
//                        g.setColor(Color.blue);
//                        g.fillOval(bullet.getX(), bullet.getY(), 10, 10);
//                    } else {
//                        enemy.bullets.remove(bullet);
//                    }
//                }
//            }
//        }


    }
/*
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direct  坦克方向（上下左右）
     * @param type 坦克类型
 */
    //通过画出四个方向的坦克来实现坦克转向
    public void drawTank(int x, int y, Graphics g, int direct, int type){
        //根据不同的类型，设置不同的颜色
        Color color = Color.orange;
        switch (type){
            case 0://我方坦克
                g.setColor(Color.CYAN);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
        }
        switch (direct){
            case 0://向上
                g.fill3DRect(x, y,10,60,false);
                g.fill3DRect(x + 10, y + 10,20,40,false);
                g.fill3DRect(x + 30, y,10,60,false);
                g.setColor(color);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.fill3DRect(x + 18, y , 4, 30,false);
                break;
            case 1://向下
                g.fill3DRect(x, y,10,60,false);
                g.fill3DRect(x + 10, y + 10,20,40,false);
                g.fill3DRect(x + 30, y,10,60,false);
                g.setColor(color);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.fill3DRect(x + 18, y + 30 , 4, 30,false);
                break;
            case 2://向右
                g.fill3DRect(x, y,60,10,false);
                g.fill3DRect(x + 10, y + 10,40,20,false);
                g.fill3DRect(x , y + 30,60,10,false);
                g.setColor(color);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.fill3DRect(x + 30 , y + 18, 30, 4,false);
                break;
            case 3: //向左
                g.fill3DRect(x, y,60,10,false);
                g.fill3DRect(x + 10, y + 10,40,20,false);
                g.fill3DRect(x , y + 30,60,10,false);
                g.setColor(color);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.fill3DRect(x  , y + 18, 30, 4,false);
                break;

            default:
                System.out.println("暂无处理");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //键盘触发事件，控制坦克移动
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                hero.moveUp();
                hero.setDirect(0);
                repaint();//重绘
                break;
            case KeyEvent.VK_A:
                hero.moveLeft();
                hero.setDirect(3);;
                repaint();
                break;
            case KeyEvent.VK_D:
                hero.moveRight();
                hero.setDirect(2);;
                repaint();
                break;
            case KeyEvent.VK_S:
                hero.moveDown();
                hero.setDirect(1);;
                repaint();
                break;
            case KeyEvent.VK_J:
                hero.shot();
                repaint();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}
