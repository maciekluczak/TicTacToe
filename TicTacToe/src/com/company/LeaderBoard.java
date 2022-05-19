package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class LeaderBoard extends JLayeredPane  {

    private BackButton backButton;
    private PlayerBoard playerBoard;
    private int NumOfPlayers=1;
    private JPanel scrollPanel = new JPanel();

    private DefaultTableModel model;
    private JTable leaderTable;

    public BackButton getBackButton() {
        return backButton;
    }



    public LeaderBoard(){


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
        panelRight.setPreferredSize(new Dimension(200,100));
        panelLeft.setPreferredSize(new Dimension(200,100));
        panelDown.setPreferredSize(new Dimension(250,120));
        panelCenter.setPreferredSize(new Dimension(200,200));
        panelBackground.setSize(1100,700);




        panelBackground.setLayout(new BorderLayout());




//                                                                          panelUp set
        panelUp.setLayout(new BorderLayout());

        ImageIcon chooseGameImage = new ImageIcon("Icons\\leaderboard.png");
        JLabel chooseGameLabel = new JLabel();
        chooseGameLabel.setIcon(chooseGameImage);
        panelUp.add(chooseGameLabel);
        chooseGameLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseGameLabel.setVerticalAlignment(JLabel.CENTER);

        panelCenter.setLayout(new GridLayout(1,1,30,50));

        model = new DefaultTableModel();

        leaderTable = new JTable(model);
        leaderTable.setRowHeight(40);
        leaderTable.setBackground(new Color(164, 231, 255, 80));
        leaderTable.setFont(new Font("MV Boli", Font.PLAIN, 25));
        leaderTable.setDefaultEditor(Object.class, null);
        leaderTable.setOpaque(false);

        model.addColumn("1");
        model.addColumn("2");
        model.addColumn("3");
        model.addColumn("4");
        model.addColumn("5");



        JScrollPane scrollFrame = new JScrollPane(scrollPanel);
        scrollFrame.setOpaque(false);
        scrollFrame.getViewport().setOpaque(false);


        scrollPanel.setAutoscrolls(true);
        scrollPanel.setPreferredSize(new Dimension( 1500, 40*NumOfPlayers));
        scrollPanel.setOpaque(false);
        scrollPanel.setBackground(Color.BLUE);
        scrollPanel.setLayout(new GridLayout(1,1,10,10));

        SwingUtilities.updateComponentTreeUI(scrollFrame);


        scrollPanel.add(leaderTable);
        panelCenter.add(scrollFrame);






        backButton=new BackButton(100, 50);
        backButton.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
        panelLeft.add(Box.createVerticalGlue());
        panelDown.add(backButton);


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


    public void exitButtonClear(){
        int rows = model.getRowCount();
        NumOfPlayers=1;
        for(int i = rows - 1; i >=0; i--)
        {
            model.removeRow(i);
        }

    }




    public void showLeaderboard(){


        model.addRow(new Object[]{"Username","Score", "Wins", "Losses", "Tournaments" });



        Collections.sort(playerBoard.getPlayerArray(), Comparator.comparing(Player::getNumOfPoints)
                .thenComparingInt(Player::getNumOfPoints));

        Collections.reverse(playerBoard.getPlayerArray());



        for (Player player: PlayerBoard.getPlayerArray()
        ) {
            model.addRow(new Object[]{player.getPlayerName(),player.getNumOfPoints(),
                    player.getNumOfWins(),player.getNumOfLosses(),  player.getNumOfTournaments()});
            NumOfPlayers++;

        }
        scrollPanel.setPreferredSize(new Dimension( 1500, 40*NumOfPlayers));

    }


}