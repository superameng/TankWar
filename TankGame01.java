package com.gzq.TankGame;

import javax.swing.*;
import java.awt.*;

/**
 * @author 高志强
 * @version 1.0
 */
//
public class TankGame01 extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }
    public TankGame01(){
        mp = new MyPanel();

        this.add(mp);
        this.setSize(1000,750);//设置面板大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(mp);
        Thread thread = new Thread(mp);
        thread.start();
    }
}
