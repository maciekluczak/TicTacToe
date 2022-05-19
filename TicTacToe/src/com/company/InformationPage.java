package com.company;

import javax.swing.*;
import java.awt.*;

public class InformationPage extends JLayeredPane {

    BackButton backButton;

    public BackButton getBackButton() {
        return backButton;
    }



    public InformationPage(){


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

        ImageIcon chooseGameImage = new ImageIcon("Icons\\Information.png");
        JLabel chooseGameLabel = new JLabel();
        chooseGameLabel.setIcon(chooseGameImage);
        panelUp.add(chooseGameLabel);
        chooseGameLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseGameLabel.setVerticalAlignment(JLabel.CENTER);

        panelCenter.setLayout(new GridLayout(1,1,30,50));

        JTextArea informationLabel= new JTextArea("Animation graphics and music License: \n\n\"Doobly Doo\" Kevin MacLeod (incompetech.com)\n" +
                "Licensed under Creative Commons:\n By Attribution 4.0 License\n\n" +
                "http://creativecommons.org/licenses/by/4.0/\n\n"+"https://www.flaticon.com/ Freepik Standard License\n\n Sound effects obtained from https://www.zapsplat.com\n" +
                "Additional sound effects from https://www.zapsplat.com\n" +
                "Music from https://www.zapsplat.com\n\n Author:\n Maciej Luczak");
        informationLabel.setBackground(new Color(164, 231, 255, 80));
        informationLabel.setFont(new Font("MV Boli", Font.PLAIN, 14));
        informationLabel.setOpaque(true);
        panelCenter.add(informationLabel);






//                                                              Back Button set -> do poprawy
        backButton=new BackButton(100, 50);
        backButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
        panelLeft.add(Box.createVerticalGlue());
        panelLeft.add(backButton);


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

