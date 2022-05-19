package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameTafel3D extends JLayeredPane implements Runnable, MouseListener {

    private GameBoard3D firstGameBoard3D, secondGameBoard3D, thirdGameBoard3D, fourthGameBoard3D;
    private GameBufor gameBufor = new GameBufor();
    private int boardSize;
    private boolean gameEnd = false;
    private String whoWin = "-";
    private JLabel matchEndLabel;
    private ImageIcon xWinsImg;
    private ImageIcon oWinsImg;
    private ImageIcon drawImg;
    private GameField3D[][][] gameBoards3DS = {{}, {}, {}, {}};


    public GameTafel3D() {

        xWinsImg = new ImageIcon("Icons\\xwins.png");
        oWinsImg = new ImageIcon("Icons\\owins.png");
        drawImg = new ImageIcon("Icons\\Draw!.png");

        boardSize = gameBufor.getBoardChoose();

        this.setBounds(0, 0, 1100, 700);
        addGameBoards();

    }

    public String getWhoWin() {
        return whoWin;
    }

    void addGameBoards() {
        firstGameBoard3D = new GameBoard3D();
        firstGameBoard3D.getPanelUp().addMouseListener(this);
        this.add(firstGameBoard3D, JLayeredPane.MODAL_LAYER);

        secondGameBoard3D = new GameBoard3D();
        this.add(secondGameBoard3D, JLayeredPane.MODAL_LAYER);
        this.moveToFront(secondGameBoard3D);
        secondGameBoard3D.getPanelUp().addMouseListener(this);

        thirdGameBoard3D = new GameBoard3D();
        this.add(thirdGameBoard3D, JLayeredPane.MODAL_LAYER);
        this.moveToFront(thirdGameBoard3D);
        thirdGameBoard3D.getPanelUp().addMouseListener(this);

        if (boardSize==4){
            fourthGameBoard3D = new GameBoard3D();
            this.add(fourthGameBoard3D, JLayeredPane.MODAL_LAYER);
            this.moveToFront(fourthGameBoard3D);
            fourthGameBoard3D.getPanelUp().addMouseListener(this);
        }


    }

    public void checkWinner() {
        JPanel matchEndPanel = new JPanel();
        matchEndLabel= new JLabel();
        matchEndPanel.setLocation(320,67);
        matchEndPanel.setSize(450,515);
        matchEndPanel.setOpaque(false);
        matchEndPanel.add(matchEndLabel);


        if (whoWin.equals("x")) {

            System.out.println("X WIN +");
            matchEndLabel.setIcon(xWinsImg);
            this.removeAll();

            this.add(matchEndPanel, JLayeredPane.MODAL_LAYER);
        }
        if (whoWin.equals("o")) {
            System.out.println("O WIN +");

            matchEndLabel.setIcon(oWinsImg);
            this.removeAll();
            this.add(matchEndPanel, JLayeredPane.MODAL_LAYER);
        }
        if (whoWin.equals("-")) {
            System.out.println("It's a Draw +");
            matchEndLabel.setIcon(drawImg);
            this.removeAll();
            this.add(matchEndPanel, JLayeredPane.MODAL_LAYER);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource().equals(firstGameBoard3D.getPanelUp())) {
            if (this.getPosition(firstGameBoard3D) != 0) {
                firstGameBoard3D.setBackground(Color.green);

                if(boardSize==4){
                    this.moveToFront(thirdGameBoard3D);
                }
                this.moveToFront(secondGameBoard3D);
                this.moveToFront(firstGameBoard3D);
            }
        }

        else if (e.getSource().equals(secondGameBoard3D.getPanelUp())) {
            secondGameBoard3D.setBackground(Color.green);
            this.moveToFront(secondGameBoard3D);
        }

        else if (e.getSource().equals(thirdGameBoard3D.getPanelUp())) {
            if (this.getPosition(thirdGameBoard3D) != 0) {
                thirdGameBoard3D.setBackground(Color.green);
                this.moveToFront(secondGameBoard3D);
                this.moveToFront(thirdGameBoard3D);
            }
        }
        if(boardSize==4){
            if (e.getSource().equals(fourthGameBoard3D.getPanelUp())) {
                if (this.getPosition(fourthGameBoard3D) != 0) {
                    fourthGameBoard3D.setBackground(Color.green);
                    this.moveToFront(secondGameBoard3D);
                    this.moveToFront(thirdGameBoard3D);
                    this.moveToFront(fourthGameBoard3D);
                }
            }
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

    @Override
    public void run() {

        gameBoards3DS[0] = firstGameBoard3D.getBoardField();
        gameBoards3DS[1] = secondGameBoard3D.getBoardField();
        gameBoards3DS[2] = thirdGameBoard3D.getBoardField();
        if(boardSize==4)
        {
            gameBoards3DS[3]=fourthGameBoard3D.getBoardField();
        }

        int matchBound= (boardSize*boardSize*boardSize)+1;
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
                        int botChooseDim = ThreadLocalRandom.current().nextInt(0, boardSize);
                        int botChooseRow = ThreadLocalRandom.current().nextInt(0, boardSize);
                        int botChooseCol = ThreadLocalRandom.current().nextInt(0, boardSize);

                        if (gameBoards3DS[botChooseDim][botChooseRow][botChooseCol].getFieldMark().equals(" ")) {
                            if(botTurn){
                            gameBoards3DS[botChooseDim][botChooseRow][botChooseCol].setFieldMark("x");
                            botTurn = false;}
                        }
                    }
                }
            }
             if (boardSize == 3) {
                 outerloop:
        for (int dim = 0; dim < 3; dim++) {
            for (int row = 0; row < boardSize; row++) {
                for (int col = 0; col < boardSize; col++) {


                        if (
                                //kazda przekatna prawa gora na kazdej tablicy 2d
                                gameBoards3DS[dim][0][0].getFieldMark().equals("x") &
                                gameBoards3DS[dim][1][1].getFieldMark().equals("x") &
                                gameBoards3DS[dim][2][2].getFieldMark().equals("x")
                        )
                        {
                            System.out.println("case: 1");
                            whoWin = "x";
                            gameEnd = true;
                            break outerloop;
                        }


                        else if(                //kazda przekatna prawa gora na kazdej tablicy 2d
                                gameBoards3DS[dim][2][0].getFieldMark().equals("x") &
                                gameBoards3DS[dim][1][1].getFieldMark().equals("x") &
                                gameBoards3DS[dim][0][2].getFieldMark().equals("x")

                        )
                        {
                            System.out.println("case: 2");
                            whoWin = "x";
                            gameEnd = true;
                            break outerloop;
                        }
                        else if(

                                //przekatna prawa gora 3d
                                gameBoards3DS[0][2][0].getFieldMark().equals("x") &
                                gameBoards3DS[1][1][1].getFieldMark().equals("x") &
                                gameBoards3DS[2][0][2].getFieldMark().equals("x")
                        )
                        {
                            System.out.println("case: 3");
                            whoWin = "x";
                            gameEnd = true;
                            break outerloop;
                        }

                               else if(         //przekatna prawa dol 3d
                                gameBoards3DS[0][0][0].getFieldMark().equals("x") &
                                gameBoards3DS[1][1][1].getFieldMark().equals("x") &
                                gameBoards3DS[2][2][2].getFieldMark().equals("x")
                               )
                               {
                                   System.out.println("case: 4");
                                   whoWin = "x";
                                   gameEnd = true;
                                   break outerloop;
                               }
                                else if(
                                //przekatna lewo gora 3d
                                gameBoards3DS[0][2][2].getFieldMark().equals("x") &
                                gameBoards3DS[1][1][1].getFieldMark().equals("x") &
                                gameBoards3DS[2][0][0].getFieldMark().equals("x")
                                )
                                {
                                    System.out.println("case: 5");
                                    whoWin = "x";
                                    gameEnd = true;
                                    break outerloop;
                                }
                                else if(
                                //przekatna lewo dol 3d
                                gameBoards3DS[0][0][2].getFieldMark().equals("x") &
                                gameBoards3DS[1][1][1].getFieldMark().equals("x") &
                                gameBoards3DS[2][2][0].getFieldMark().equals("x")
                                )
                                {
                                    System.out.println("case: 6");
                                    whoWin = "x";
                                    gameEnd = true;
                                    break outerloop;
                                }

                                else  if (      //kazdy wiersz przekatna 3d do prawo
                                gameBoards3DS[0][row][0].getFieldMark().equals("x") &
                                gameBoards3DS[1][row][1].getFieldMark().equals("x") &
                                gameBoards3DS[2][row][2].getFieldMark().equals("x")
                                  )
                                  {
                                      System.out.println("case: 7");
                                      whoWin = "x";
                                      gameEnd = true;
                                      break outerloop;
                                  }

                                else   if(      //kazdy wiersz przekatna 3d do lewo
                                gameBoards3DS[0][row][2].getFieldMark().equals("x")&
                                gameBoards3DS[1][row][1].getFieldMark().equals("x") &
                                gameBoards3DS[2][row][0].getFieldMark().equals("x")
                                  )
                                  {
                                      System.out.println("case: 8");
                                      whoWin = "x";
                                      gameEnd = true;
                                      break outerloop;
                                  }


                                 else     if (   //kazdy kolumna przekatna 3d do dol
                                gameBoards3DS[0][0][col].getFieldMark().equals("x") &
                                gameBoards3DS[1][1][col].getFieldMark().equals("x") &
                                gameBoards3DS[2][2][col].getFieldMark().equals("x")
                                     )
                                     {
                                         System.out.println("case: 9");
                                         whoWin = "x";
                                         gameEnd = true;
                                         break outerloop;
                                     }


                                   else   if (   //kazdy kolumna przekatna 3d do gora
                                gameBoards3DS[0][2][col].getFieldMark().equals("x") &
                                gameBoards3DS[1][1][col].getFieldMark().equals("x") &
                                gameBoards3DS[2][0][col].getFieldMark().equals("x")
                                     )
                                     {
                                         System.out.println("case: 10");
                                         whoWin = "x";
                                         gameEnd = true;
                                         break outerloop;
                                     }


                                    else if (   //kazda kolumna 2d na kazdej tablicy 2d
                                gameBoards3DS[dim][0][col].getFieldMark().equals("x") &
                                gameBoards3DS[dim][1][col].getFieldMark().equals("x") &
                                gameBoards3DS[dim][2][col].getFieldMark().equals("x")
                                     )
                                     {
                                         System.out.println("case: 11");
                                         whoWin = "x";
                                         gameEnd = true;
                                         break outerloop;
                                     }

                                 else if  (//kazdy wiersz 2d na kazdej tablicy 2d
                                gameBoards3DS[dim][row][0].getFieldMark().equals("x") &
                                gameBoards3DS[dim][row][1].getFieldMark().equals("x") &
                                gameBoards3DS[dim][row][2].getFieldMark().equals("x")
                                    )
                                  {
                                      System.out.println("case: 12");
                                      whoWin = "x";
                                      gameEnd = true;
                                      break outerloop;
                                  }


                                  else    if(  //kazdy pion  3d do gora
                                gameBoards3DS[0][row][col].getFieldMark().equals("x") &
                                gameBoards3DS[1][row][col].getFieldMark().equals("x") &
                                gameBoards3DS[2][row][col].getFieldMark().equals("x")
                        ){


//                                                                             X WIN
                            System.out.println("case: 13");
                            whoWin = "x";
                            gameEnd = true;
                            break outerloop;

                        }
//ooooooo
                    if (
                        //kazda przekatna prawa gora na kazdej tablicy 2d
                            gameBoards3DS[dim][0][0].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][1][1].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][2][2].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 1");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }


                    else if(                //kazda przekatna prawa gora na kazdej tablicy 2d
                            gameBoards3DS[dim][2][0].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][1][1].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][0][2].getFieldMark().equals("o")

                    )
                    {
                        System.out.println("case: 2");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }
                    else if(

                        //przekatna prawa gora 3d
                            gameBoards3DS[0][2][0].getFieldMark().equals("o") &
                                    gameBoards3DS[1][1][1].getFieldMark().equals("o") &
                                    gameBoards3DS[2][0][2].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 3");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }

                    else if(         //przekatna prawa dol 3d
                            gameBoards3DS[0][0][0].getFieldMark().equals("o") &
                                    gameBoards3DS[1][1][1].getFieldMark().equals("o") &
                                    gameBoards3DS[2][2][2].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 4");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }
                    else if(
                        //przekatna lewo gora 3d
                            gameBoards3DS[0][2][2].getFieldMark().equals("o") &
                                    gameBoards3DS[1][1][1].getFieldMark().equals("o") &
                                    gameBoards3DS[2][0][0].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 5");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }
                    else if(
                        //przekatna lewo dol 3d
                            gameBoards3DS[0][0][2].getFieldMark().equals("o") &
                                    gameBoards3DS[1][1][1].getFieldMark().equals("o") &
                                    gameBoards3DS[2][2][0].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 6");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }

                    else  if (      //kazdy wiersz przekatna 3d do prawo
                            gameBoards3DS[0][row][0].getFieldMark().equals("o") &
                                    gameBoards3DS[1][row][1].getFieldMark().equals("o") &
                                    gameBoards3DS[2][row][2].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 7");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }

                    else   if(      //kazdy wiersz przekatna 3d do lewo
                            gameBoards3DS[0][row][2].getFieldMark().equals("o")&
                                    gameBoards3DS[1][row][1].getFieldMark().equals("o") &
                                            gameBoards3DS[2][row][0].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 8");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }


                    else     if (   //kazdy kolumna przekatna 3d do dol
                            gameBoards3DS[0][0][col].getFieldMark().equals("o") &
                                    gameBoards3DS[1][1][col].getFieldMark().equals("o") &
                                    gameBoards3DS[2][2][col].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 9");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }


                    else   if (   //kazdy kolumna przekatna 3d do gora
                            gameBoards3DS[0][2][col].getFieldMark().equals("o") &
                                    gameBoards3DS[1][1][col].getFieldMark().equals("o") &
                                    gameBoards3DS[2][0][col].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 10");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }


                    else if (   //kazda kolumna 2d na kazdej tablicy 2d
                            gameBoards3DS[dim][0][col].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][1][col].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][2][col].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 11");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }

                    else if  (//kazdy wiersz 2d na kazdej tablicy 2d
                            gameBoards3DS[dim][row][0].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][row][1].getFieldMark().equals("o") &
                                    gameBoards3DS[dim][row][2].getFieldMark().equals("o")
                    )
                    {
                        System.out.println("case: 12");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;
                    }


                    else    if(  //kazdy pion  3d do gora
                            gameBoards3DS[0][row][col].getFieldMark().equals("o") &
                                    gameBoards3DS[1][row][col].getFieldMark().equals("o") &
                                    gameBoards3DS[2][row][col].getFieldMark().equals("o")
                    ){


//                                                                             X WIN
                        System.out.println("case: 13");
                        whoWin = "o";
                        gameEnd = true;
                        break outerloop;

                    }

                    }

                }
            }
        }
            if (boardSize == 4) {
                outerloop:
                for (int dim = 0; dim < 4; dim++) {
                    for (int row = 0; row < boardSize; row++) {
                        for (int col = 0; col < boardSize; col++) {


                            if (
                                //kazda przekatna prawa gora na kazdej tablicy 2d
                                    gameBoards3DS[dim][0][0].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][1][1].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][2][2].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][3][3].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 1");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (                //kazda przekatna prawa gora na kazdej tablicy 2d
                                    gameBoards3DS[dim][3][0].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][2][1].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][1][2].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][0][3].getFieldMark().equals("x")


                            ) {
                                System.out.println("case: 2");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (

                                //przekatna prawa gora 3d
                                    gameBoards3DS[0][3][0].getFieldMark().equals("x") &
                                            gameBoards3DS[1][2][1].getFieldMark().equals("x") &
                                            gameBoards3DS[2][1][2].getFieldMark().equals("x") &
                                            gameBoards3DS[3][0][3].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 3");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (         //przekatna prawa dol 3d
                                    gameBoards3DS[0][0][0].getFieldMark().equals("x") &
                                            gameBoards3DS[1][1][1].getFieldMark().equals("x") &
                                            gameBoards3DS[2][2][2].getFieldMark().equals("x") &
                                            gameBoards3DS[3][3][3].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 4");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (
                                //przekatna lewo gora 3d
                                    gameBoards3DS[0][3][3].getFieldMark().equals("x") &
                                            gameBoards3DS[1][2][2].getFieldMark().equals("x") &
                                            gameBoards3DS[2][1][1].getFieldMark().equals("x") &
                                            gameBoards3DS[3][0][0].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 5");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (
                                //przekatna lewo dol 3d
                                    gameBoards3DS[0][0][3].getFieldMark().equals("x") &
                                            gameBoards3DS[1][1][2].getFieldMark().equals("x") &
                                            gameBoards3DS[2][2][1].getFieldMark().equals("x") &
                                            gameBoards3DS[3][3][0].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 6");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (      //kazdy wiersz przekatna 3d do prawo
                                    gameBoards3DS[0][row][0].getFieldMark().equals("x") &
                                            gameBoards3DS[1][row][1].getFieldMark().equals("x") &
                                            gameBoards3DS[2][row][2].getFieldMark().equals("x") &
                                            gameBoards3DS[3][row][3].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 7");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (      //kazdy wiersz przekatna 3d do lewo
                                    gameBoards3DS[0][row][3].getFieldMark().equals("x") &
                                            gameBoards3DS[1][row][2].getFieldMark().equals("x") &
                                            gameBoards3DS[2][row][1].getFieldMark().equals("x") &
                                            gameBoards3DS[3][row][0].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 8");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (   //kazdy kolumna przekatna 3d do dol
                                    gameBoards3DS[0][0][col].getFieldMark().equals("x") &
                                            gameBoards3DS[1][1][col].getFieldMark().equals("x") &
                                            gameBoards3DS[2][2][col].getFieldMark().equals("x") &
                                            gameBoards3DS[3][3][col].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 9");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (   //kazdy kolumna przekatna 3d do gora
                                    gameBoards3DS[0][3][col].getFieldMark().equals("x") &
                                            gameBoards3DS[1][2][col].getFieldMark().equals("x") &
                                            gameBoards3DS[2][1][col].getFieldMark().equals("x") &
                                            gameBoards3DS[3][0][col].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 10");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (   //kazda kolumna 2d na kazdej tablicy 2d
                                    gameBoards3DS[dim][0][col].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][1][col].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][2][col].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][3][col].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 11");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (//kazdy wiersz 2d na kazdej tablicy 2d
                                    gameBoards3DS[dim][row][0].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][row][1].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][row][2].getFieldMark().equals("x") &
                                            gameBoards3DS[dim][row][3].getFieldMark().equals("x")
                            ) {
                                System.out.println("case: 12");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;
                            } else if (  //kazdy pion  3d do gora
                                    gameBoards3DS[0][row][col].getFieldMark().equals("x") &
                                            gameBoards3DS[1][row][col].getFieldMark().equals("x") &
                                            gameBoards3DS[2][row][col].getFieldMark().equals("x") &
                                            gameBoards3DS[3][row][col].getFieldMark().equals("x")
                            ) {

                                System.out.println("case: 13");
                                whoWin = "x";
                                gameEnd = true;
                                break outerloop;

                            }
//ooooooo
                            if (
                                //kazda przekatna prawa gora na kazdej tablicy 2d
                                    gameBoards3DS[dim][0][0].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][1][1].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][2][2].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][3][3].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 1");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (                //kazda przekatna prawa gora na kazdej tablicy 2d
                                    gameBoards3DS[dim][3][0].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][2][1].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][1][2].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][0][3].getFieldMark().equals("o")


                            ) {
                                System.out.println("case: 2");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (

                                //przekatna prawa gora 3d
                                    gameBoards3DS[0][3][0].getFieldMark().equals("o") &
                                            gameBoards3DS[1][2][1].getFieldMark().equals("o") &
                                            gameBoards3DS[2][1][2].getFieldMark().equals("o") &
                                            gameBoards3DS[3][0][3].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 3");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (         //przekatna prawa dol 3d
                                    gameBoards3DS[0][0][0].getFieldMark().equals("o") &
                                            gameBoards3DS[1][1][1].getFieldMark().equals("o") &
                                            gameBoards3DS[2][2][2].getFieldMark().equals("o") &
                                            gameBoards3DS[3][3][3].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 4");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (
                                //przekatna lewo gora 3d
                                    gameBoards3DS[0][3][3].getFieldMark().equals("o") &
                                            gameBoards3DS[1][2][2].getFieldMark().equals("o") &
                                            gameBoards3DS[2][1][1].getFieldMark().equals("o") &
                                            gameBoards3DS[3][0][0].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 5");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (
                                //przekatna lewo dol 3d
                                    gameBoards3DS[0][0][3].getFieldMark().equals("o") &
                                            gameBoards3DS[1][1][2].getFieldMark().equals("o") &
                                            gameBoards3DS[2][2][1].getFieldMark().equals("o") &
                                            gameBoards3DS[3][3][0].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 6");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (      //kazdy wiersz przekatna 3d do prawo
                                            gameBoards3DS[0][row][0].getFieldMark().equals("o") &
                                            gameBoards3DS[1][row][1].getFieldMark().equals("o") &
                                            gameBoards3DS[2][row][2].getFieldMark().equals("o") &
                                            gameBoards3DS[3][row][3].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 7");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (      //kazdy wiersz przekatna 3d do lewo
                                    gameBoards3DS[0][row][3].getFieldMark().equals("o") &
                                            gameBoards3DS[1][row][2].getFieldMark().equals("o") &
                                            gameBoards3DS[2][row][1].getFieldMark().equals("o") &
                                            gameBoards3DS[3][row][0].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 8");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (   //kazdy kolumna przekatna 3d do dol
                                    gameBoards3DS[0][0][col].getFieldMark().equals("o") &
                                            gameBoards3DS[1][1][col].getFieldMark().equals("o") &
                                            gameBoards3DS[2][2][col].getFieldMark().equals("o") &
                                            gameBoards3DS[3][3][col].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 9");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (   //kazdy kolumna przekatna 3d do gora
                                    gameBoards3DS[0][3][col].getFieldMark().equals("o") &
                                            gameBoards3DS[1][2][col].getFieldMark().equals("o") &
                                            gameBoards3DS[2][1][col].getFieldMark().equals("o") &
                                            gameBoards3DS[3][0][col].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 10");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            } else if (   //kazda kolumna 2d na kazdej tablicy 2d
                                            gameBoards3DS[dim][0][col].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][1][col].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][2][col].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][3][col].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 11");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            }
                             else if (//kazdy wiersz 2d na kazdej tablicy 2d
                                            gameBoards3DS[dim][row][0].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][row][1].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][row][2].getFieldMark().equals("o") &
                                            gameBoards3DS[dim][row][3].getFieldMark().equals("o")
                            ) {
                                System.out.println("case: 12");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;
                            }
                             else if (  //kazdy pion  3d do gora
                                    gameBoards3DS[0][row][col].getFieldMark().equals("o") &
                                            gameBoards3DS[1][row][col].getFieldMark().equals("o") &
                                            gameBoards3DS[2][row][col].getFieldMark().equals("o") &
                                            gameBoards3DS[3][row][col].getFieldMark().equals("o")
                            ) {

                                System.out.println("case: 13");
                                whoWin = "o";
                                gameEnd = true;
                                break outerloop;

                            }
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
//
//
        }
//
        checkWinner();
    }

                }


