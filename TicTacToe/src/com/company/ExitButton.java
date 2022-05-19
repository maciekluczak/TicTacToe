package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExitButton extends JLabel implements MouseListener {
    private int ExitButtonWidth;
    private int ExitButtonHeight;
    private ImageIcon imageLabel;
    private ImageIcon imageLabelAnimated;

    public boolean isEscapeLoop() {
        return escapeLoop;
    }

    private boolean escapeLoop;
    private MusicBackground musicBackground = new MusicBackground();


    public ExitButton(int ExitButtonWidth, int ExitButtonHeight){

        this.ExitButtonHeight=ExitButtonHeight;
        this.ExitButtonWidth= ExitButtonWidth;

        this.setSize(ExitButtonWidth,ExitButtonHeight);


        ImageIcon image = new ImageIcon("Icons\\exit32.png");
        Image convertImage = image.getImage();
        Image newImage= convertImage.getScaledInstance(ExitButtonWidth, ExitButtonHeight, Image.SCALE_SMOOTH);
        imageLabel = new ImageIcon(newImage);
        imageLabelAnimated = new ImageIcon("Icons\\exit50.png");




        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.TOP);

        this.setIcon(imageLabel);
        this.setOpaque(false);

        this.addMouseListener(this);


    }


    public void setEscapeLoop(){
        escapeLoop=true;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        setEscapeLoop();
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

