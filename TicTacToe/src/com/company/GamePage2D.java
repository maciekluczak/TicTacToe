package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GamePage2D extends JLayeredPane implements Runnable{


    private ExitButton backButton;
    private JPanel panelCenter, panelLeft;
    private GameBoard2D game2D;
    private GameBufor gameBufor= new GameBufor();
    private JLabel timeLabel, player1Label, player2Label, vsLabel, trophyLabel;
    private JPanel infoPanel, panelRight;
    private ImageIcon starIcon, trophyIcon;
    private PlayerBoard playerBoard= new PlayerBoard();
    private NextRoundButton nextRoundButton= new NextRoundButton(250 ,100);
    private TurnLabel turnLabel;
    private Clock clock;
    private MusicBackground musicBackground = new MusicBackground();



    public ExitButton getBackButton() {
        return backButton;
    }



    public GamePage2D(){

        starIcon= new ImageIcon("Icons\\star.png");
        trophyIcon= new ImageIcon("Icons\\trop.png");


        JPanel panelUp = new JPanel();
        panelRight = new JPanel();
        panelLeft = new JPanel();
        JPanel panelDown = new JPanel();
        panelCenter = new JPanel();
        infoPanel = new JPanel();
        JPanel panelBackground = new JPanel();


        panelUp.setOpaque(false);
        panelRight.setOpaque(false);
        panelLeft.setOpaque(false);
        panelDown.setOpaque(false);
        panelCenter.setOpaque(false);
        infoPanel.setOpaque(false);

        infoPanel.setLayout(null);

        panelBackground.setOpaque(false);

        //FRAME BORDERS SET

        panelBackground.setBorder(BorderFactory.createLineBorder(Color.black));

        panelUp.setPreferredSize(new Dimension(100,50));
        panelRight.setPreferredSize(new Dimension(200,100));
        panelLeft.setPreferredSize(new Dimension(200,100));
        panelDown.setPreferredSize(new Dimension(100,70));
        panelCenter.setPreferredSize(new Dimension(300,150));
        panelBackground.setSize(1100,700);
        infoPanel.setSize(1100, 700);


        panelBackground.setLayout(new BorderLayout());



//                                                                          panelUp set
        panelUp.setLayout(new GridLayout(2,1));


      panelCenter.setLayout(new BorderLayout());


//
        backButton=new ExitButton(100, 50);
        backButton.setAlignmentX(JLabel.BOTTOM_ALIGNMENT);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
        panelLeft.add(Box.createVerticalGlue());



        panelBackground.add(panelUp, BorderLayout.NORTH);
        panelBackground.add(panelCenter, BorderLayout.CENTER);
        panelBackground.add(panelRight, BorderLayout.EAST);
        panelBackground.add(panelLeft, BorderLayout.WEST);
        panelBackground.add(panelDown, BorderLayout.SOUTH);

        this.add(panelBackground, JLayeredPane.PALETTE_LAYER);
        this.add(new GameBackground(), JLayeredPane.DEFAULT_LAYER);


        this.setVisible(true);
    }


    public void ExitGame2D(){
        gameBufor.setPlayer1(null);
        gameBufor.setPlayer2(null);
        gameBufor.setPlayer3(null);
        gameBufor.setPlayer4(null);
        gameBufor.setGuestPlay(false);


        if(gameBufor.isPlayTournament()){
            gameBufor.getTournament().setGameRound(0);
        }

        nextRoundButton.setPlayNextRound(false);
        gameBufor.setPlayTournament(false);
        panelCenter.remove(game2D);
        panelCenter.remove(backButton);
        infoPanel.removeAll();



    }

    @Override
    public void run() {

            gameBufor.setGameRun(true);

            turnLabel = new TurnLabel(150,150);
            Thread turnThread= new Thread(turnLabel);
            panelRight.add(turnLabel);

            clock = new Clock(150,150);
            Thread clockThread= new Thread(clock);
            panelRight.add(clock);

            game2D = new GameBoard2D();
            panelCenter.add(game2D, BorderLayout.CENTER);
            SwingUtilities.updateComponentTreeUI(this);
            game2D.setVisible(true);
            Thread gameThread = new Thread(game2D);
            long startGameTime = System.currentTimeMillis();
            gameThread.start();

            turnThread.start();

            clockThread.start();

            try {
                gameThread.join();

                gameBufor.setGameRun(false);
                panelRight.remove(turnLabel);
                panelRight.remove(clock);

                panelCenter.add(Box.createHorizontalStrut(150));
                panelCenter.add(backButton, BorderLayout.SOUTH);
                SwingUtilities.updateComponentTreeUI(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endGameTime = System.currentTimeMillis();
            float gameTime = (endGameTime - startGameTime) / 1000F;
            System.out.println("Time: " + gameTime + " seconds");

            timeLabel = new JLabel("Time: " + gameTime + " seconds");

            timeLabel.setFont(new Font("MV Boli", Font.PLAIN, 25));
            timeLabel.setBounds(360, 150, 400, 200);
            infoPanel.add(timeLabel);

            vsLabel = new JLabel("vs");
            vsLabel.setFont(new Font("MV Boli", Font.PLAIN, 25));
            vsLabel.setBounds(500, 250, 200, 200);

            if (!gameBufor.isGuestPlay()) {
                player1Label = new JLabel(gameBufor.getPlayer1().getPlayerName());
                player1Label.setFont(new Font("MV Boli", Font.PLAIN, 30));
                player1Label.setBounds(360, 250, 200, 200);
                infoPanel.add(player1Label);
//            infoPanel.add(vsLabel);


                if (!gameBufor.isBotPlayer()) {
                    player2Label = new JLabel(gameBufor.getPlayer2().getPlayerName());
                } else {
                    player2Label = new JLabel("Bot");
                }

                player2Label.setFont(new Font("MV Boli", Font.PLAIN, 30));
                player2Label.setBounds(640, 250, 200, 200);

                infoPanel.add(player2Label);
            } else {
                player1Label = new JLabel("Player 1");
                player1Label.setFont(new Font("MV Boli", Font.PLAIN, 30));
                player1Label.setBounds(360, 250, 200, 200);
                infoPanel.add(player1Label);
//            infoPanel.add(vsLabel);

                if (!gameBufor.isBotPlayer()) {
                    player2Label = new JLabel("Player 2");
                } else {
                    player2Label = new JLabel("Bot");
                }

                player2Label.setFont(new Font("MV Boli", Font.PLAIN, 30));
                player2Label.setBounds(640, 250, 200, 200);

                infoPanel.add(player2Label);
            }



            if (game2D.getWhoWin().equals("x")) {
                player2Label.setIcon(starIcon);
                player2Label.setHorizontalTextPosition(JLabel.CENTER);
                player2Label.setVerticalTextPosition(JLabel.CENTER);


            } else if (game2D.getWhoWin().equals("o")) {
                player1Label.setIcon(starIcon);
                player1Label.setHorizontalTextPosition(JLabel.CENTER);
                player1Label.setVerticalTextPosition(JLabel.CENTER);

            }
            this.add(infoPanel, JLayeredPane.DRAG_LAYER);

            SwingUtilities.updateComponentTreeUI(this);




            if (!gameBufor.isGuestPlay()) {
                if (game2D.getWhoWin().equals("o")) {
                    gameBufor.getPlayer1().setNumOfPlays(gameBufor.getPlayer1().getNumOfPlays() + 1);
                    gameBufor.getPlayer1().setNumOfWins(gameBufor.getPlayer1().getNumOfWins() + 1);
                    gameBufor.getPlayer1().setNumOfPoints(gameBufor.getPlayer1().getNumOfPoints() + 100);


                    if (!gameBufor.isBotPlayer()) {

                        gameBufor.getPlayer2().setNumOfPlays(gameBufor.getPlayer2().getNumOfPlays() + 1);
                        gameBufor.getPlayer2().setNumOfLosses(gameBufor.getPlayer2().getNumOfLosses() + 1);
                        gameBufor.getPlayer2().setNumOfPoints(gameBufor.getPlayer2().getNumOfPoints() - 50);

                    }
                } else if (game2D.getWhoWin().equals("x")) {
                    if (!gameBufor.isBotPlayer()) {
                        gameBufor.getPlayer1().setNumOfPlays(gameBufor.getPlayer1().getNumOfPlays() + 1);
                        gameBufor.getPlayer1().setNumOfLosses(gameBufor.getPlayer1().getNumOfLosses() + 1);
                        gameBufor.getPlayer1().setNumOfPoints(gameBufor.getPlayer1().getNumOfPoints() - 50);

                        gameBufor.getPlayer2().setNumOfPlays(gameBufor.getPlayer2().getNumOfPlays() + 1);
                        gameBufor.getPlayer2().setNumOfWins(gameBufor.getPlayer2().getNumOfWins() + 1);
                        gameBufor.getPlayer2().setNumOfPoints(gameBufor.getPlayer2().getNumOfPoints() + 100);


                    } else if (gameBufor.isBotPlayer()) {
                        gameBufor.getPlayer1().setNumOfPlays(gameBufor.getPlayer1().getNumOfPlays() + 1);
                        gameBufor.getPlayer1().setNumOfLosses(gameBufor.getPlayer1().getNumOfLosses() + 1);
                        gameBufor.getPlayer1().setNumOfPoints(gameBufor.getPlayer1().getNumOfPoints() - 50);
                    }
                } else if (game2D.getWhoWin().equals("-")) {
                    if (!gameBufor.isBotPlayer()) {
                        gameBufor.getPlayer1().setNumOfPlays(gameBufor.getPlayer1().getNumOfPlays() + 1);

                        gameBufor.getPlayer2().setNumOfPlays(gameBufor.getPlayer2().getNumOfPlays() + 1);
                    } else if (gameBufor.isBotPlayer()) {
                        gameBufor.getPlayer1().setNumOfPlays(gameBufor.getPlayer1().getNumOfPlays() + 1);
                    }
                }
                try {
                    playerBoard.savePlayers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if (gameBufor.isPlayTournament()) {



                if (gameBufor.getTournament().getGameRound() < 2) {
                    musicBackground.playWinSound();

                    nextRoundButton.setLocation(440, 400);

                    infoPanel.add(nextRoundButton);

                    if (game2D.getWhoWin().equals("x")) {
                        gameBufor.getTournament().setGameRound(gameBufor.getTournament().getGameRound() + 1);

                        gameBufor.getTournament().getPlayerArray().remove(gameBufor.getTournament().getPlayer1());

                    } else if (game2D.getWhoWin().equals("o")) {
                        gameBufor.getTournament().setGameRound(gameBufor.getTournament().getGameRound() + 1);

                        gameBufor.getTournament().getPlayerArray().remove(gameBufor.getTournament().getPlayer2());

                    }else if (game2D.getWhoWin().equals("-")) {


                    }


                    if (gameBufor.getTournament().getGameRound() == 1) {
                        gameBufor.setPlayer1(gameBufor.getTournament().getPlayer3());
                        gameBufor.setPlayer2(gameBufor.getTournament().getPlayer4());

                    } else if (gameBufor.getTournament().getGameRound() == 2) {
                        gameBufor.setPlayer1(gameBufor.getTournament().getPlayerArray().get(0));
                        gameBufor.setPlayer2(gameBufor.getTournament().getPlayerArray().get(1));
                    }


                    try {
                        gameBufor.saveTournament();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while (!nextRoundButton.isPlayNextRound() || gameBufor.getTournament().getGameRound() == 0) {

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    nextRoundButton.setPlayNextRound(false);
                    panelCenter.remove(game2D);
                    panelCenter.remove(backButton);
                    infoPanel.removeAll();
                    this.run();
                }

                else {

                    gameBufor.getTournament().setFinished(true);
                    musicBackground.playTournamentSound();

                    if (game2D.getWhoWin().equals("x")) {
                        player2Label.setIcon(trophyIcon);
                        gameBufor.getPlayer2().setNumOfTournaments(gameBufor.getPlayer2().getNumOfTournaments() + 1);
                        gameBufor.getPlayer2().setNumOfPoints(gameBufor.getPlayer2().getNumOfPoints() + 300);


                    } else if (game2D.getWhoWin().equals("o")) {
                        player1Label.setIcon(trophyIcon);
                        gameBufor.getPlayer1().setNumOfTournaments(gameBufor.getPlayer1().getNumOfTournaments() + 1);
                        gameBufor.getPlayer1().setNumOfPoints(gameBufor.getPlayer1().getNumOfPoints() + 300);
                    }


                    try {
                        playerBoard.savePlayers();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                try {
                    gameBufor.saveTournament();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else{
                musicBackground.playWinSound();
            }



    }

}

