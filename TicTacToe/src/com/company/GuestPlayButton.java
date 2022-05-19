package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuestPlayButton extends JLabel implements MouseListener {

    private int PlayButtonWidth;
    private int PlayButtonHeight;
    private ImageIcon imageLabel;
    private ImageIcon imageLabelAnimated;
    private MusicBackground musicBackground = new MusicBackground();


    public GuestPlayButton(int PlayButtonWidth, int PlayButtonHeight){

        this.PlayButtonHeight=PlayButtonHeight;
        this.PlayButtonWidth= PlayButtonWidth;

        this.setSize(PlayButtonWidth,PlayButtonHeight);


        ImageIcon image = new ImageIcon("Icons\\PlayasGuest32.png");
        Image convertImage = image.getImage();
        Image newImage= convertImage.getScaledInstance(PlayButtonWidth, PlayButtonHeight, Image.SCALE_SMOOTH);
        imageLabel = new ImageIcon(newImage);
        imageLabelAnimated = new ImageIcon("Icons\\PlayasGuest32.png");




        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);

        this.setIcon(imageLabel);
        this.setOpaque(false);

        this.addMouseListener(this);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        musicBackground.playSound();
        this.setIcon(imageLabel);
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
