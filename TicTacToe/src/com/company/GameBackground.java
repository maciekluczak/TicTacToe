package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameBackground extends JPanel implements ActionListener {
    Timer timer;
    Image background;

    public GameBackground(){

        this.setSize(1100,700);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        background= new ImageIcon("Icons\\notebookgameback.png").getImage();
        timer= new Timer(10, this);
        timer.start();
    }

    public void paint(Graphics g){

        super.paint(g);
        Graphics2D g2d =(Graphics2D) g;
        g2d.drawImage(background,0,0,null);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
