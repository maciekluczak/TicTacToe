package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogTagLabel extends JLabel implements MouseListener {
    String menuButtonName;
    int menuButtonWidth;
    int menuButtonHeight;
    private MusicBackground musicBackground = new MusicBackground();


    public String getMenuButtonName() {
        return menuButtonName;
    }

    public LogTagLabel(String menuButtonName, int menuButtonWidth, int menuButtonHeight){
        this.menuButtonName=menuButtonName;
        this.menuButtonHeight=menuButtonHeight;
        this.menuButtonWidth= menuButtonWidth;

        ImageIcon image = new ImageIcon("Icons\\greendot.png");
        Image convertImage = image.getImage();
        Image newImage= convertImage.getScaledInstance(menuButtonWidth, menuButtonHeight, Image.SCALE_SMOOTH);
        ImageIcon imageLabel = new ImageIcon(newImage);

        this.setText(menuButtonName);
        this.setFont(new Font("MV Boli", Font.PLAIN, 18));
        this.setHorizontalAlignment(JLabel.LEFT);

        this.setIcon(imageLabel);
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
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
