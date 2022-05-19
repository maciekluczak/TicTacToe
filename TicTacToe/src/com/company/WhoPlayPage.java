package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.beancontext.BeanContextChild;
import java.util.ArrayList;
import java.util.List;

public class WhoPlayPage extends JLayeredPane implements MouseListener {
    private JPanel panelUp, panelRight, panelLeft, panelDown, panelCenter, panelBackground;
    private BackButton backButton;
    private GameBufor gameBufor;
    private PlayerBoard playerBoard= new PlayerBoard();
    private PlayButton playButton;
    private PlayerCheckbox player1Checkbox, player2Checkbox, player3Checkbox, player4Checkbox;
    private List<PlayerCheckbox> playersCheckboxArray = new ArrayList<>();
    private JLabel  choosePlayerLabel= new JLabel();
    private GuestPlayButton guestPlayButton = new GuestPlayButton(200, 50);
    private ImageIcon choose1PlayerImage = new ImageIcon("Icons\\choose 1 Player.png");
    private ImageIcon choose2PlayerImage = new ImageIcon("Icons\\choose 2 Players.png");
    private ImageIcon choose4PlayerImage = new ImageIcon("Icons\\choose 4 Players.png");

    public BackButton getBackButton() {
        return backButton;
    }

    public PlayButton getPlayButton() {
        return playButton;
    }

    public GuestPlayButton getGuestPlayButton() {
        return guestPlayButton;
    }

    public WhoPlayPage(){


        panelUp = new JPanel();
        panelRight = new JPanel();
        panelLeft = new JPanel();
        panelDown = new JPanel();
        panelCenter = new JPanel();
        panelBackground = new JPanel();


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
        panelUp.setLayout(new GridLayout(2,1));

        ImageIcon whoPlayImage = new ImageIcon("Icons\\WHo's playing_.png");
        JLabel whoPlayLabel = new JLabel();
        whoPlayLabel.setSize(500,200);
        whoPlayLabel.setIcon(whoPlayImage);


        panelUp.add(whoPlayLabel);

        panelUp.add(choosePlayerLabel);


        choosePlayerLabel.setHorizontalAlignment(JLabel.CENTER);
        choosePlayerLabel.setVerticalAlignment(JLabel.CENTER);

        whoPlayLabel.setHorizontalAlignment(JLabel.CENTER);
        whoPlayLabel.setVerticalAlignment(JLabel.CENTER);

        panelCenter.setLayout(new GridLayout(5,1,0,10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        panelCenter.setOpaque(false);



        player1Checkbox = new PlayerCheckbox();
        player2Checkbox = new PlayerCheckbox();
        player3Checkbox = new PlayerCheckbox();
        player4Checkbox = new PlayerCheckbox();



        playersCheckboxArray.add(player1Checkbox);
        playersCheckboxArray.add(player2Checkbox);
        playersCheckboxArray.add(player3Checkbox);
        playersCheckboxArray.add(player4Checkbox);

        backButton=new BackButton(100, 50);
        backButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
        panelLeft.add(Box.createVerticalGlue());
        panelLeft.add(backButton);


        playButton=new PlayButton(100, 50);
        playButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.PAGE_AXIS));
        panelRight.add(Box.createVerticalGlue());
        panelRight.add(playButton);

        playButton.addMouseListener(this);

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

    public void choosePlayer() {
        int countPlayer=0;
        if (gameBufor.getNumOfPlayers()==1){
            choosePlayerLabel.setIcon(choose1PlayerImage);
        }
        else if(gameBufor.getNumOfPlayers()==2){
            choosePlayerLabel.setIcon(choose2PlayerImage);
        }
        else if(gameBufor.getNumOfPlayers()==4){
            choosePlayerLabel.setIcon(choose4PlayerImage);
        }
        for (Player player : playerBoard.getPlayerArray()) {
            if (player.getActive() != null) {
                System.out.println(player.getPlayerName());
                playersCheckboxArray.get(countPlayer).setText(player.getPlayerName());
                playersCheckboxArray.get(countPlayer).setSelected(false);
                playersCheckboxArray.get(countPlayer).setForeground(Color.BLACK);


                panelCenter.add( playersCheckboxArray.get(countPlayer));
                countPlayer=countPlayer+1;
            }
        }
        if(!gameBufor.isPlayTournament()) {
            panelCenter.add(guestPlayButton);
        }
    }

        public void exitPage() {
        gameBufor.setPlayer1(null);
        gameBufor.setPlayer2(null);
        gameBufor.setPlayer3(null);
        gameBufor.setPlayer4(null);

        gameBufor.setGuestPlay(false);
        player1Checkbox.setSelectedCounter(0);
        panelCenter.removeAll();
        }

        public boolean checkPlayerSelected(){
        if(player1Checkbox.getSelectedCounter()==gameBufor.getNumOfPlayers()) {
            return true;
            }
        else
            return false;
        }


    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource().equals(playButton)){
            if (player1Checkbox.getSelectedCounter()== gameBufor.getNumOfPlayers()) {
                for (PlayerCheckbox playerCheckbox : playersCheckboxArray) {
                    if (playerCheckbox.isSelected()) {
                        String playerName = playerCheckbox.getText();
                        for (Player player : playerBoard.getPlayerArray()) {
                            if (player.getPlayerName().equals(playerName)) {

                                if (gameBufor.getPlayer1() == null) {
                                    gameBufor.setPlayer1(player);
                                    playerCheckbox.setSelected(false);
                                    System.out.println("Player 1: " + gameBufor.getPlayer1().getPlayerName());
                                } else if (gameBufor.getPlayer2() == null) {
                                    gameBufor.setPlayer2(player);
                                    playerCheckbox.setSelected(false);
                                    System.out.println("Player 2: " + gameBufor.getPlayer2().getPlayerName());
                                } else if (gameBufor.getPlayer3() == null) {
                                    gameBufor.setPlayer3(player);
                                    playerCheckbox.setSelected(false);
                                    System.out.println("Player 3: " + gameBufor.getPlayer3().getPlayerName());
                                } else if (gameBufor.getPlayer4() == null) {
                                    gameBufor.setPlayer4(player);
                                    playerCheckbox.setSelected(false);
                                    System.out.println("Player 4: " + gameBufor.getPlayer4().getPlayerName());
                                    gameBufor.setTournament(new Tournament(gameBufor.getPlayer1(),gameBufor.getPlayer2(),gameBufor.getPlayer3(),gameBufor.getPlayer4()));
                                    gameBufor.getTournament().setBoardChoose(gameBufor.getBoardChoose());
                                    gameBufor.getTournament().setBoardDimension(gameBufor.getBoardDimension());
                                }

                                break;
                            }

                        }
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Choose "+ gameBufor.getNumOfPlayers() + " players!", "Player select", JOptionPane.WARNING_MESSAGE);
            }

        }

        if(e.getSource().equals(guestPlayButton)){
            gameBufor.setPlayer1(null);
            gameBufor.setPlayer2(null);
            gameBufor.setPlayer3(null);
            gameBufor.setPlayer4(null);
            gameBufor.setGuestPlay(true);

        }
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
