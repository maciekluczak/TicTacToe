package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoadTournament extends JLayeredPane  {
    private BackButton backButton;
    private MenuButton newTournamentLabel, continueLabel, labelTournament;


    public MenuButton getNewTournamentLabel() {
        return newTournamentLabel;
    }

    public MenuButton getcontinueLabel() {
        return continueLabel;
    }

    public BackButton getBackButton() {
        return backButton;
    }


    public LoadTournament(){


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

        ImageIcon chooseGameImage = new ImageIcon("Icons\\Choose game.png");
        JLabel chooseGameLabel = new JLabel();
        chooseGameLabel.setIcon(chooseGameImage);
        panelUp.add(chooseGameLabel);
        chooseGameLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseGameLabel.setVerticalAlignment(JLabel.CENTER);

        panelCenter.setLayout(new GridLayout(2,1,30,50));


        newTournamentLabel = new MenuButton("New Tournament", 400, 350);
        continueLabel = new MenuButton("Continue", 400, 350);


        panelCenter.add(newTournamentLabel);
        panelCenter.add(continueLabel);


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