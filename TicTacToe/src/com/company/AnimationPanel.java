package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AnimationPanel extends JPanel implements ActionListener, Runnable {
    Timer timer;
    int xXVelocity= -10;
    int yXVelocity= 10;
    int xOVelocity= 10;
    int yOVelocity= -10;
    int xX= ThreadLocalRandom.current().nextInt(50, 500 + 1);
    int yX= ThreadLocalRandom.current().nextInt(50, 500 + 1);
    int xO= ThreadLocalRandom.current().nextInt(50, 500 + 1);
    int yO= ThreadLocalRandom.current().nextInt(50, 500 + 1);
    Image cross;
    Image circle;
    Image background;

    public AnimationPanel(){

        this.setSize(1100,700);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        cross= new ImageIcon("Icons\\cancel64.png").getImage();
        circle= new ImageIcon("Icons\\circumference (3).png").getImage();
        background= new ImageIcon("Icons\\notebook2.jpg").getImage();

    }

    public void paint(Graphics g){

        super.paint(g);
        Graphics2D g2d =(Graphics2D) g;
        g2d.drawImage(background,0,0,null);
        g2d.drawImage(cross,xX, yX, null);
        g2d.drawImage(circle,xO, yO, null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(xX >= 1030 || xX<=0){xXVelocity=xXVelocity* -1;}
        if(yX >= 630 || yX <=0){yXVelocity=yXVelocity* -1;}
        yX= yX+ yXVelocity;
        xX= xX+ xXVelocity;

        if(xO >= 1030 || xO<=0){xOVelocity=xOVelocity* -1;}
        if(yO >= 630 || yO <=0){yOVelocity=yOVelocity* -1;}
        yO= yO+ yOVelocity;
        xO= xO+ xOVelocity;
        repaint();
    }

    @Override
    public void run() {
        timer= new Timer(10, this);
        timer.start();
    }
}
