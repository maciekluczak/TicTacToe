package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerCheckbox extends JCheckBox implements MouseListener {

    private ImageIcon disabledImage = new ImageIcon("Icons\\usernotplay64.png");
    private ImageIcon selectedImage = new ImageIcon("Icons\\userplay64.png");
    private MusicBackground musicBackground = new MusicBackground();
    private static int selectedCounter;


    public PlayerCheckbox(){
        this.setOpaque(false);
        this.selectedCounter = 0;
        this.setSelected(false);
        this.setIcon(disabledImage);
        this.setSelectedIcon(selectedImage);
        this.setFocusable(false);
        this.setFont(new Font("MV Boli", Font.PLAIN, 25));

        this.addMouseListener(this);


    }

    public static int getSelectedCounter() {
        return selectedCounter;
    }
    public static void setSelectedCounter(int selectedCounter) {
        PlayerCheckbox.selectedCounter = selectedCounter;
    }
    @Override
    public void mouseClicked(MouseEvent e) {


    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.isSelected()){
            selectedCounter=selectedCounter+1;

            System.out.println(selectedCounter);
            this.setForeground(Color.green);
        }
        if(!this.isSelected()){
            selectedCounter=selectedCounter-1;

            System.out.println(selectedCounter);
            this.setForeground(Color.black);
        }
        musicBackground.playSound();

    }

    @Override
    public void mouseEntered(MouseEvent e) {




    }

    @Override
    public void mouseExited(MouseEvent e) {



    }
}