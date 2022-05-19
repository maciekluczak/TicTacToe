package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends JLayeredPane implements MouseListener  {
    MenuButton labelStart,labelLogin,labelLeaderboard,labelRegister;
    InformationPin statsBoard;
    private PlayerBoard playerBoard= new PlayerBoard();
    private JPanel panelDown;
    private Map<LogTagLabel, Player> playerLogIn = new HashMap<LogTagLabel, Player>();

    public MenuButton getLabelStart() {
        return labelStart;
    }

    public MenuButton getLabelLogin() {
        return labelLogin;
    }

    public MenuButton getLabelLeaderboard() {
        return labelLeaderboard;
    }

    public MenuButton getLabelRegister() {
        return labelRegister;
    }

    public InformationPin getStatsBoard() {
        return statsBoard;
    }


    public HomePage(){



        JPanel panelUp = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelLeft = new JPanel();
        panelDown = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelBackground = new JPanel();


        panelUp.setOpaque(false);
        panelRight.setOpaque(false);
        panelLeft.setOpaque(false);
        panelDown.setOpaque(false);
        panelCenter.setOpaque(false);
        panelBackground.setOpaque(false);


        panelBackground.setBorder(BorderFactory.createLineBorder(Color.black));





        panelUp.setPreferredSize(new Dimension(100,150));
        panelRight.setPreferredSize(new Dimension(200,100));
        panelLeft.setPreferredSize(new Dimension(200,100));
        panelDown.setPreferredSize(new Dimension(100,70));
        panelCenter.setPreferredSize(new Dimension(100,100));
        panelBackground.setSize(1100,700);


        panelBackground.setLayout(new BorderLayout());




//                                                                          panelUp set
        panelUp.setLayout(new GridLayout(1,5,30,50));


        labelStart = new MenuButton("Star Game", 250, 350);
        labelLogin = new MenuButton("Login", 250, 350);
        labelLeaderboard = new MenuButton("Leaderboard", 250, 350);
        labelRegister = new MenuButton("Register", 250, 350);


        ImageIcon ticImage = new ImageIcon("Icons\\TicTac.png");
        JLabel titleLabel1 = new JLabel();
        titleLabel1.setIcon(ticImage);
        titleLabel1.setHorizontalAlignment(JLabel.CENTER);
        titleLabel1.setVerticalAlignment(JLabel.TOP);
        panelCenter.setLayout(new BorderLayout());

        ImageIcon tacImage = new ImageIcon("Icons\\toeImage.png");
        JLabel titleLabel2 = new JLabel();
        titleLabel2.setIcon(tacImage);
        titleLabel2.setHorizontalAlignment(JLabel.CENTER);
        titleLabel2.setVerticalAlignment(JLabel.BOTTOM);
        panelCenter.setLayout(new BorderLayout());

        panelCenter.add(titleLabel1, BorderLayout.NORTH);
        panelCenter.add(titleLabel2, BorderLayout.SOUTH);

        statsBoard =new InformationPin("Information", 150,150);
        panelRight.add(statsBoard);






        panelUp.add(labelStart);
        panelUp.add(labelLogin);
        panelUp.add(labelLeaderboard);
        panelUp.add(labelRegister);

        panelDown.setLayout(new GridLayout(1,4,30,40));
        panelDown.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 80));






        panelBackground.add(panelUp, BorderLayout.NORTH);
        panelBackground.add(panelCenter, BorderLayout.CENTER);
        panelBackground.add(panelRight, BorderLayout.EAST);
        panelBackground.add(panelLeft, BorderLayout.WEST);
        panelBackground.add(panelDown, BorderLayout.SOUTH);


        this.add(panelBackground, JLayeredPane.DRAG_LAYER);

        AnimationPanel animationPanel= new AnimationPanel();
        Thread animationPanelThread = new Thread(animationPanel);
        animationPanelThread.start();

        this.add(animationPanel, JLayeredPane.DEFAULT_LAYER);

        this.setVisible(true);
    }
    public void exitHomePage(){
        panelDown.removeAll();

    }

    public void logTagShow(){

    LogTagLabel player1Tag = new LogTagLabel("-",10,10);
    LogTagLabel player2Tag = new LogTagLabel("-",10,10);
    LogTagLabel player3Tag = new LogTagLabel("-",10,10);
    LogTagLabel player4Tag = new LogTagLabel("-",10,10);
    player1Tag.setVisible(false);
    player2Tag.setVisible(false);
    player3Tag.setVisible(false);
    player4Tag.setVisible(false);
    panelDown.add(player1Tag);
    panelDown.add(player2Tag);
    panelDown.add(player3Tag);
    panelDown.add(player4Tag);


        for (Player player: playerBoard.getPlayerArray()
             ) {
            if(player.getActive()!=null){
                if(player1Tag.getText().equals("-")){
                    player1Tag.setText(player.getPlayerName()+ " (Log Out)");
                    player1Tag.setVisible(true);
                    playerLogIn.put(player1Tag,player);
                    player1Tag.addMouseListener(this);

                }
                else if(player2Tag.getText().equals("-")){
                    player2Tag.setText(player.getPlayerName()+ " (Log Out)");
                    player2Tag.setVisible(true);
                    playerLogIn.put(player2Tag,player);
                    player2Tag.addMouseListener(this);

                }
                else if(player3Tag.getText().equals("-")){
                    player3Tag.setText(player.getPlayerName()+ " (Log Out)");
                    player3Tag.setVisible(true);
                    playerLogIn.put(player3Tag,player);
                    player3Tag.addMouseListener(this);


                }
                else if(player4Tag.getText().equals("-")){
                    player4Tag.setText(player.getPlayerName()+ " (Log Out)");
                    player4Tag.setVisible(true);
                    playerLogIn.put(player4Tag,player);
                    player4Tag.addMouseListener(this);

                }

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Player player =playerLogIn.get(e.getSource());
        player.setActive(null);
        playerLogIn.remove(e.getSource());
        playerBoard.checkNumOfActive();

        exitHomePage();
        logTagShow();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
