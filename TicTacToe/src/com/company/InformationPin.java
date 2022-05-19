package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InformationPin extends JLabel implements MouseListener {
    private String informationPinName;
    private int informationPinWidth;
    private int informationPinHeight;
    private MusicBackground musicBackground = new MusicBackground();


    public InformationPin(String informationPinName, int informationPinWidth, int informationPinHeight){
        this.informationPinName=informationPinName;
        this.informationPinHeight=informationPinHeight;
        this.informationPinWidth= informationPinWidth;

        ImageIcon image = new ImageIcon("Icons\\pinImage.png");
        Image convertImage = image.getImage();
        Image newImage= convertImage.getScaledInstance(informationPinWidth, informationPinHeight, Image.SCALE_SMOOTH);
        ImageIcon imageLabel = new ImageIcon(newImage);

        this.setText(informationPinName);
        this.setFont(new Font("MV Boli", Font.PLAIN, 20));


        this.setIcon(imageLabel);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
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

        this.setForeground(Color.red);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setForeground(Color.black);

    }
}
