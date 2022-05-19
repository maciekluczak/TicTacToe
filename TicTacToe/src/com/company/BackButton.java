package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BackButton extends JLabel implements MouseListener {
    private int BackButtonWidth;
    private int BackButtonHeight;
    private ImageIcon imageLabel;
    private ImageIcon imageLabelAnimated;
    private MusicBackground musicBackground = new MusicBackground();


    public BackButton(int BackButtonWidth, int BackButtonHeight){

        this.BackButtonHeight=BackButtonHeight;
        this.BackButtonWidth= BackButtonWidth;

        this.setSize(BackButtonWidth,BackButtonHeight);


        ImageIcon image = new ImageIcon("Icons\\back.png");
        Image convertImage = image.getImage();
        Image newImage= convertImage.getScaledInstance(BackButtonWidth, BackButtonHeight, Image.SCALE_SMOOTH);
        imageLabel = new ImageIcon(newImage);
        imageLabelAnimated = new ImageIcon("Icons\\back.png");


        this.setIcon(imageLabel);
        this.setOpaque(false);

        this.addMouseListener(this);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        musicBackground.playSound();

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