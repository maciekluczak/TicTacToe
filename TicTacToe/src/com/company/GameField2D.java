package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameField2D extends JLabel implements MouseListener {
private String fieldMark=" ";
private GameBufor gameBufor;
private MusicBackground musicBackground=new MusicBackground();


    ImageIcon cross;
    ImageIcon circle;


    public GameField2D(int fieldSize){
        gameBufor = new GameBufor();
        Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, fieldSize);
        cross= new ImageIcon("Icons\\cancel128.png");
        circle= new ImageIcon("Icons\\circumference (2).png");
        this.setText("     ");
        this.setHorizontalTextPosition(CENTER);
        this.setVerticalTextPosition(CENTER);
        this.setHorizontalAlignment(this.CENTER);
        this.setVerticalAlignment(this.CENTER);
        this.setFont(LABEL_FONT);
        this.setOpaque(true);
//        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(new Color(255, 255, 255));

        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setHorizontalAlignment(SwingConstants.CENTER);

        this.addMouseListener(this);
    }

    public void setFieldMark(String fieldMark) {
        if(this.fieldMark.equals(" ") ) {
            this.fieldMark = fieldMark;
            if (fieldMark.equals("x")) {
                this.setIcon(cross);
                gameBufor.setTurn(gameBufor.getTurn() + 1);
            } else if (fieldMark.equals("o")) {
                this.setIcon(circle);
                gameBufor.setTurn(gameBufor.getTurn() + 1);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        musicBackground.playSoundField();

            if(gameBufor.getTurn()%2==1){
                setFieldMark("o");
            }
            else if(gameBufor.getTurn()%2==0){
                setFieldMark("x");
            }

        }




    public String getFieldMark() {
        return fieldMark;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(this.fieldMark.equals(" ") ) {
            this.setBackground(Color.GREEN);
        }
        else{
            this.setBackground( new Color(246, 235, 220));
        }
    }


    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(new Color(255, 255, 255));

    }
}
