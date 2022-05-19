package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Clock extends JLabel implements Runnable{
    private int ExitButtonWidth;
    private int ExitButtonHeight;
    private GameBufor gameBufor = new GameBufor();

    public Clock(int ExitButtonWidth, int ExitButtonHeight){

        this.ExitButtonHeight=ExitButtonHeight;
        this.ExitButtonWidth= ExitButtonWidth;

        this.setSize(ExitButtonWidth,ExitButtonHeight);
        this.setFont(new Font("MV Boli", Font.PLAIN, 20));



        ImageIcon image = new ImageIcon("Icons\\Time32.png");


        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);

        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        this.setIcon(image);
        this.setOpaque(false);



    }


    @Override
    public void run() {

            outerloop:
            for (int minutes = 0; minutes < 60; minutes++) {
                for (int seconds = 0; seconds < 60; seconds++) {

                    this.setText(String.valueOf(minutes)+" min "+ seconds+" sec");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(!gameBufor.isGameRun()){
                        break outerloop;
                    }

                }
            }

    }

}

