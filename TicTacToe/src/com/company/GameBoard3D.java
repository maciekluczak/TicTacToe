package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameBoard3D extends JPanel implements MouseListener {
    private GameBufor gameBufor = new GameBufor();
    private int boardSize;
    private JPanel panelUp;
    GameField3D[][] boardField;
    private static int numOfBoards;
    ImageIcon cross3= new ImageIcon("Icons\\cancel128.png");
    ImageIcon circle3= new ImageIcon("Icons\\circumference (2).png");
    ImageIcon cross4= new ImageIcon("Icons\\cancel64.png");
    ImageIcon circle4= new ImageIcon("Icons\\circumference (3).png");

    public GameField3D[][] getBoardField() {
        return boardField;
    }

    public void setBoardField(GameField3D[][] boardField) {
        this.boardField = boardField;
    }

    public JPanel getPanelUp() {
        return panelUp;
    }

    public GameBoard3D()  {
        this.boardSize =gameBufor.getBoardChoose();


        Color customBrown = new Color(163, 120, 93);
        this.setBackground(new Color(246, 235, 220));
        this.setLayout(new BorderLayout());
        this.setSize(450,515);
        this.setBorder(BorderFactory.createLineBorder(new Color(123, 86, 73),3));

        if(boardSize==3)
        {
            this.setLocation((numOfBoards*50)+260,(numOfBoards*38)+47);
        }
        else if(boardSize==4)
        {
            this.setLocation((numOfBoards*50)+260,(numOfBoards*25)+47);
        }
        this.addMouseListener(this);
        numOfBoards++;

        panelUp = new JPanel();
        JPanel panelDown = new JPanel();


        panelUp.setPreferredSize(new Dimension(450,55));
        panelDown.setPreferredSize(new Dimension(450,450));

        panelUp.setBackground(customBrown);
        panelUp.addMouseListener(this);

        panelDown.setBackground(new Color(246, 235, 220));

        panelDown.setLayout(new GridLayout(boardSize, boardSize, 7, 7));

        boardField = new GameField3D[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                boardField[row][col] = new GameField3D(300 / boardSize);
                if(boardSize==3){
                    boardField[row][col].setCircle(circle3);
                    boardField[row][col].setCross(cross3);
                }
                else if(boardSize==4){
                    boardField[row][col].setCircle(circle4);
                    boardField[row][col].setCross(cross4);
                }

                panelDown.add(boardField[row][col]);
            }
        }

        this.add(panelUp, BorderLayout.NORTH);
        this.add(panelDown, BorderLayout.SOUTH);


    }

    public static int getNumOfBoards() {
        return numOfBoards;
    }

    public static void setNumOfBoards(int numOfBoards) {
        GameBoard3D.numOfBoards = numOfBoards;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(panelUp)){
            this.setBackground(Color.green);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(panelUp)){
            this.setBackground(new Color(246, 235, 220));
        }

    }

}
