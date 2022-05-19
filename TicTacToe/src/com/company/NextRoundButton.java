package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NextRoundButton extends JLabel implements MouseListener {
    private int ExitButtonWidth;
    private int ExitButtonHeight;
    private ImageIcon imageLabel;
    private ImageIcon imageLabelAnimated;
    private MusicBackground musicBackground = new MusicBackground();
    private boolean playNextRound=false;


    public NextRoundButton(int ExitButtonWidth, int ExitButtonHeight){

        this.ExitButtonHeight=ExitButtonHeight;
        this.ExitButtonWidth= ExitButtonWidth;

        this.setSize(ExitButtonWidth,ExitButtonHeight);


        imageLabel = new ImageIcon("Icons\\next35.png");
        imageLabelAnimated = new ImageIcon("Icons\\Next!.png");


        this.setIcon(imageLabel);
        this.setOpaque(false);

        this.addMouseListener(this);


    }

    public boolean isPlayNextRound() {
        return playNextRound;
    }


    public void setPlayNextRound(boolean playNextRound) {
        this.playNextRound = playNextRound;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        musicBackground.playSound();
        this.setIcon(imageLabel);
        playNextRound=true;

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setIcon(imageLabelAnimated);



    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setIcon(imageLabel);



    }
}