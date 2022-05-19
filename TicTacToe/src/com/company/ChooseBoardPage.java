package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChooseBoardPage extends JLayeredPane  {
    InformationPin label3x3, label4x4, label3x3x3, label4x4x4;
    BackButton backButton;

    public InformationPin getLabel3x3() {
        return label3x3;
    }

    public InformationPin getLabel4x4() {
        return label4x4;
    }

    public InformationPin getLabel3x3x3() {
        return label3x3x3;
    }

    public InformationPin getLabel4x4x4() {
        return label4x4x4;
    }

    public BackButton getBackButton() { return backButton; }

    public ChooseBoardPage(){


        JPanel panelUp = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelDown = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelBackground = new JPanel();


        panelUp.setOpaque(false);
        panelRight.setOpaque(false);
        panelLeft.setOpaque(false);
        panelDown.setOpaque(false);
        panelCenter.setOpaque(false);
        panelBackground.setOpaque(false);

        panelBackground.setBorder(BorderFactory.createLineBorder(Color.black));

        panelUp.setPreferredSize(new Dimension(100,200));
        panelRight.setPreferredSize(new Dimension(350,100));
        panelLeft.setPreferredSize(new Dimension(350,100));
        panelDown.setPreferredSize(new Dimension(100,70));
        panelCenter.setPreferredSize(new Dimension(200,200));
        panelBackground.setSize(1100,700);


        panelBackground.setLayout(new BorderLayout());




//                                                                          panelUp set
        panelUp.setLayout(new BorderLayout());

        ImageIcon chooseGameImage = new ImageIcon("Icons\\Choose board.png");
        JLabel chooseGameLabel = new JLabel();
        chooseGameLabel.setIcon(chooseGameImage);
        panelUp.add(chooseGameLabel);
        chooseGameLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseGameLabel.setVerticalAlignment(JLabel.CENTER);

        panelCenter.setLayout(new GridLayout(2,2,30,50));

        label3x3 = new InformationPin("3 x 3", 150, 150);
        label4x4 = new InformationPin("4 x 4", 150, 150);
        label3x3x3 = new InformationPin("3 x 3 x 3", 150, 150);
        label4x4x4 = new InformationPin("4 x 4 x 4", 150, 150);


// BACK BUTTON POSITION

        backButton=new BackButton(100, 50);
        backButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
        panelLeft.add(Box.createVerticalGlue());
        panelLeft.add(backButton);

        panelCenter.add(label3x3);
        panelCenter.add(label4x4);
        panelCenter.add(label3x3x3);
        panelCenter.add(label4x4x4);

        panelBackground.add(panelUp, BorderLayout.NORTH);
        panelBackground.add(panelCenter, BorderLayout.CENTER);
        panelBackground.add(panelRight, BorderLayout.EAST);
        panelBackground.add(panelLeft, BorderLayout.WEST);
        panelBackground.add(panelDown, BorderLayout.SOUTH);

        AnimationPanel animationPanel= new AnimationPanel();
        Thread animationPanelThread = new Thread(animationPanel);
        animationPanelThread.start();


        this.add(panelBackground, JLayeredPane.DRAG_LAYER);
        this.add(animationPanel, JLayeredPane.DEFAULT_LAYER);

        this.setVisible(true);
    }

}
