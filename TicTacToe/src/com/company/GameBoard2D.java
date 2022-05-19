package com.company;

import javax.swing.*;
import java.awt.*;

import java.util.concurrent.ThreadLocalRandom;

public class GameBoard2D extends JPanel implements Runnable {
    private static final int GAP = 7;
    private GameField2D[][] boardField;
    private GameBufor gameBufor;
    private int boardSize;
    private String whoWin = "-";
    private Boolean gameEnd = false;
    private ImageIcon xWinsImg;
    private ImageIcon oWinsImg;
    private ImageIcon drawImg;
    private JLabel  matchEndLabel;
    private JPanel gamePanel;
    private TurnLabel turnLabel= new TurnLabel(150,150);

    public String getWhoWin() {
        return whoWin;
    }

    public GameBoard2D() {

        gameBufor = new GameBufor();
        this.boardSize = gameBufor.getBoardChoose();



        xWinsImg= new ImageIcon("Icons\\xwins.png");
        oWinsImg= new ImageIcon("Icons\\owins.png");
        drawImg= new ImageIcon("Icons\\Draw!.png");

        matchEndLabel=new JLabel();
        matchEndLabel.setHorizontalAlignment(JLabel.CENTER);
        matchEndLabel.setVerticalAlignment(JLabel.TOP);

        gamePanel = new JPanel(new GridLayout(boardSize, boardSize, GAP, GAP));
        gamePanel.setBorder(BorderFactory.createLineBorder( new Color(246, 235, 220), 5));
        gamePanel.setBackground(new Color(246, 235, 220));
        gamePanel.setOpaque(true);

        boardField = new GameField2D[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                boardField[row][col] = new GameField2D(420 / boardSize);
                gamePanel.add(boardField[row][col]);
            }
        }

        this.setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.CENTER);
        this.setOpaque(false);
    }

    public void checkWinner(){
        if (whoWin.equals("x")){

            System.out.println("X WIN +");
            matchEndLabel.setIcon(xWinsImg);
            this.removeAll();
            this.add(matchEndLabel, BorderLayout.CENTER);
        }
        if (whoWin.equals("o")){
            System.out.println("O WIN +");

            matchEndLabel.setIcon(oWinsImg);
            this.removeAll();
            this.add(matchEndLabel, BorderLayout.CENTER);
        }
        if (whoWin.equals("-")){
            System.out.println("It's a Draw +");
            matchEndLabel.setIcon(drawImg);
            this.removeAll();
            this.add(matchEndLabel, BorderLayout.CENTER);
        }
    }

//                                                      ADD MORE |Player|SAVE|ITP...!!

    @Override
    public void run() {
        int matchBound= (boardSize*boardSize)+1;
        int oneMoreLoop=1;

        while (!gameEnd) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameBufor.isBotPlayer()) {
                if (gameBufor.getTurn() % 2 == 0 & gameBufor.getTurn() < matchBound) {
                    boolean botTurn = true;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (botTurn) {

                        int botChooseRow = ThreadLocalRandom.current().nextInt(0, boardSize);
                        int botChooseCol = ThreadLocalRandom.current().nextInt(0, boardSize);

                        if (boardField[botChooseRow][botChooseCol].getFieldMark().equals(" ")) {

                            boardField[botChooseRow][botChooseCol].setFieldMark("x");
                            botTurn = false;
                        }
                    }
                }
            }
            if (boardSize == 4) {

            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {

                if (boardField[3][0].getFieldMark().equals("x") &
                        boardField[2][1].getFieldMark().equals("x") &
                        boardField[1][2].getFieldMark().equals("x") &
                        boardField[0][3].getFieldMark().equals("x")) {


//                                                                                 X WIN
                    whoWin = "x";
                    gameEnd = true;
                    break;
                }

                else if (boardField[3][0].getFieldMark().equals("o") &
                        boardField[2][1].getFieldMark().equals("o") &
                        boardField[1][2].getFieldMark().equals("o") &
                        boardField[0][3].getFieldMark().equals("o")) {

//                                                                                 O WIN
                    whoWin = "o";
                    gameEnd = true;
                    break;
                }

                else if (boardField[0][0].getFieldMark().equals("x") &
                        boardField[3][3].getFieldMark().equals("x") &
                        boardField[1][1].getFieldMark().equals("x") &
                        boardField[2][2].getFieldMark().equals("x")) {


//                                                                                 X WIN
                    whoWin = "x";
                    gameEnd = true;
                    break;
                }

                else if (boardField[0][0].getFieldMark().equals("o") &
                        boardField[3][3].getFieldMark().equals("o") &
                        boardField[1][1].getFieldMark().equals("o") &
                        boardField[2][2].getFieldMark().equals("o")) {

//                                                                                 O WIN
                    whoWin = "o";
                    gameEnd = true;
                    break; }

                else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("x") &
                        boardField[row % boardSize][(col + 1) % boardSize].getFieldMark().equals("x") &
                        boardField[row % boardSize][(col + 2) % boardSize].getFieldMark().equals("x") &
                        boardField[row % boardSize][(col + 3) % boardSize].getFieldMark().equals("x")) {

//                                                                                 X WIN

                    whoWin = "x";
                    gameEnd = true;
                    break; }

                else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("o") &
                        boardField[row % boardSize][(col + 1) % boardSize].getFieldMark().equals("o") &
                        boardField[row % boardSize][(col + 2) % boardSize].getFieldMark().equals("o") &
                        boardField[row % boardSize][(col + 3) % boardSize].getFieldMark().equals("o")) {


//                                                                                O WIN
                    whoWin = "o";
                    gameEnd = true;
                    break; }

                else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("x") &
                        boardField[(row + 1) % boardSize][col % boardSize].getFieldMark().equals("x") &
                        boardField[(row + 2) % boardSize][col % boardSize].getFieldMark().equals("x") &
                        boardField[(row + 3) % boardSize][col % boardSize].getFieldMark().equals("x")) {


//                                                                              X WIN
                    whoWin = "x";
                    gameEnd = true;
                    break; }

                else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("o") &
                        boardField[(row + 1) % boardSize][col % boardSize].getFieldMark().equals("o") &
                        boardField[(row + 2) % boardSize][col % boardSize].getFieldMark().equals("o") &
                        boardField[(row + 3) % boardSize][col % boardSize].getFieldMark().equals("o")) {


//                                                                      O WIN
                    whoWin = "o";
                    gameEnd = true;
                    break;
                }
            }
        }
            }
            else if (boardSize == 3) {

                for (int row = 0; row < boardSize; row++) {
                    for (int col = 0; col < boardSize; col++) {

                        if (boardField[2][0].getFieldMark().equals("x") &
                                boardField[1][1].getFieldMark().equals("x") &
                                boardField[0][2].getFieldMark().equals("x")) {


//                                                                                 X WIN
                            whoWin = "x";
                            gameEnd = true;
                            break;
                        } else if (boardField[2][0].getFieldMark().equals("o") &
                                boardField[1][1].getFieldMark().equals("o") &
                                boardField[0][2].getFieldMark().equals("o")) {


//                                                                                 O WIN
                            whoWin = "o";
                            gameEnd = true;
                            break;
                        } else if (boardField[0][0].getFieldMark().equals("x") &
                                boardField[1][1].getFieldMark().equals("x") &
                                boardField[2][2].getFieldMark().equals("x")) {


//                                                                                 X WIN
                            whoWin = "x";
                            gameEnd = true;
                            break;
                        } else if (boardField[0][0].getFieldMark().equals("o") &
                                boardField[1][1].getFieldMark().equals("o") &
                                boardField[2][2].getFieldMark().equals("o")) {


//                                                                                 O WIN
                            whoWin = "o";
                            gameEnd = true;
                            break;
                        } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("x") &
                                boardField[row % boardSize][(col + 1) % boardSize].getFieldMark().equals("x") &
                                boardField[row % boardSize][(col + 2) % boardSize].getFieldMark().equals("x") &
                                boardField[row % boardSize][(col + 3) % boardSize].getFieldMark().equals("x")) {

//                                                                                 X WIN

                            whoWin = "x";
                            gameEnd = true;
                            break;
                        } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("o") &
                                boardField[row % boardSize][(col + 1) % boardSize].getFieldMark().equals("o") &
                                boardField[row % boardSize][(col + 2) % boardSize].getFieldMark().equals("o") &
                                boardField[row % boardSize][(col + 3) % boardSize].getFieldMark().equals("o")) {


//                                                                                O WIN
                            whoWin = "o";
                            gameEnd = true;
                            break;
                        } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("x") &
                                boardField[(row + 1) % boardSize][col % boardSize].getFieldMark().equals("x") &
                                boardField[(row + 2) % boardSize][col % boardSize].getFieldMark().equals("x") &
                                boardField[(row + 3) % boardSize][col % boardSize].getFieldMark().equals("x")) {


//                                                                              X WIN
                            whoWin = "x";
                            gameEnd = true;
                            break;
                        } else if (boardField[row % boardSize][col % boardSize].getFieldMark().equals("o") &
                                boardField[(row + 1) % boardSize][col % boardSize].getFieldMark().equals("o") &
                                boardField[(row + 2) % boardSize][col % boardSize].getFieldMark().equals("o") &
                                boardField[(row + 3) % boardSize][col % boardSize].getFieldMark().equals("o")) {


//                                                                      O WIN
                            whoWin = "o";
                            gameEnd = true;
                            break;
                        }
                    }

                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(gameBufor.getTurn()==matchBound){
                if (oneMoreLoop==0){

                    this.removeAll();
                    gameEnd = true;}
                oneMoreLoop=0;
            }


                }

        checkWinner();}


        }



