package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class LoginBoard extends JLayeredPane implements KeyListener, ActionListener {

    private BackButton backButton;
    private JTextField loginTextField = new JTextField();
    private JTextField passwordTextField;
    private JButton loginButton = new JButton();
    private String username, password = "";
    private PlayerBoard playerBoard = new PlayerBoard();


    public BackButton getBackButton() {
        return backButton;
    }


    public LoginBoard() {


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

        panelUp.setPreferredSize(new Dimension(100, 200));
        panelRight.setPreferredSize(new Dimension(350, 100));
        panelLeft.setPreferredSize(new Dimension(350, 100));
        panelDown.setPreferredSize(new Dimension(100, 70));
        panelCenter.setPreferredSize(new Dimension(200, 200));
        panelBackground.setSize(1100, 700);


        panelBackground.setLayout(new BorderLayout());

        panelUp.setLayout(new BorderLayout());

        ImageIcon chooseGameImage = new ImageIcon("Icons\\login.png");
        JLabel chooseGameLabel = new JLabel();
        chooseGameLabel.setIcon(chooseGameImage);
        panelUp.add(chooseGameLabel);
        chooseGameLabel.setHorizontalAlignment(JLabel.CENTER);
        chooseGameLabel.setVerticalAlignment(JLabel.CENTER);


//PANEL CENTER LAYOUT
        panelCenter.setLayout(new GridLayout(2, 1, 0, 10));
        JPanel panelCenterTop = new JPanel();
        JPanel panelCenterBottom = new JPanel();

        panelCenterTop.setOpaque(false);
        panelCenterBottom.setOpaque(false);
        panelCenter.add(panelCenterTop);
        panelCenter.add(panelCenterBottom);

//USERNAME PLACE
        loginTextField = new JTextField("", 20);
        loginTextField.setBounds(0, 75, 320, 111);

        ImageIcon image = new ImageIcon("Icons\\textfield1.png");
        Image convertImage = image.getImage();
        Image newImage = convertImage.getScaledInstance(320, 200, Image.SCALE_SMOOTH);
        ImageIcon imageTextField = new ImageIcon(newImage);

        JLabel labelTextField = new JLabel();
        labelTextField.setIcon(imageTextField);

        loginTextField.setOpaque(false);
        labelTextField.setOpaque(false);
        loginTextField.setFont(new Font("MV Boli", Font.PLAIN, 30));

        labelTextField.add(loginTextField);
        panelCenterTop.add(labelTextField);

//PASSWORD PLACE
        panelCenterBottom.setLayout(null);

        ImageIcon image2 = new ImageIcon("Icons\\papersticker.png");
        Image convertImage2 = image2.getImage();
        Image newImage2 = convertImage2.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon passwordImage = new ImageIcon(newImage2);

        JLabel passwordBackground = new JLabel();
        passwordBackground.setBounds(0, 0, 320, 200);
        passwordBackground.setIcon(passwordImage);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        passwordPanel.setBounds(0, 50, 300, 200);
        passwordPanel.setOpaque(false);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordTextField = new JTextField("", 12);


        loginButton = new JButton("Submit");

        JLayeredPane passwordLayer = new JLayeredPane();
        passwordLayer.setBounds(50, 0, 400, 300);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        passwordPanel.add(loginButton);
        passwordLayer.add(passwordBackground, JLayeredPane.DEFAULT_LAYER);
        passwordLayer.add(passwordPanel, JLayeredPane.DRAG_LAYER);

        panelCenterBottom.add(passwordLayer);


        loginTextField.addKeyListener(this);
        passwordTextField.addKeyListener(this);
        loginButton.addActionListener(this);


//BACK BUTTON SET
        backButton = new BackButton(100, 50);
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
        this.add( animationPanel, JLayeredPane.DEFAULT_LAYER);

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (loginTextField.isFocusOwner()) {

            if (e.getKeyCode() >= 48 & e.getKeyCode() <= 57 ||
                    e.getKeyCode() >= 65 & e.getKeyCode() <= 90 ||
                    e.getKeyCode() == 46 || e.getKeyCode() == 20 ||
                    e.getKeyCode() == 16 || e.getKeyCode() == 8) {
            } else {
                loginTextField.setText("");
                JOptionPane.showMessageDialog(null, "Enter only letters and numbers!", "Username", JOptionPane.ERROR_MESSAGE);
            }
        } else if (passwordTextField.isFocusOwner()) {

            if (e.getKeyCode() >= 48 & e.getKeyCode() <= 57 ||
                    e.getKeyCode() >= 65 & e.getKeyCode() <= 90 || e.getKeyCode() == 8) {


            } else if (e.getKeyCode() == 46 || e.getKeyCode() == 20 ||
                    e.getKeyCode() == 16) {
            } else {
                passwordTextField.setText("");

                JOptionPane.showMessageDialog(null, "Enter only letters and numbers!", "Password", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            for (Player player: playerBoard.getPlayerArray())
                  {
                      if(player.getPlayerName().equals(loginTextField.getText()) & player.getPlayerPassword().equals(passwordTextField.getText())){
                          if(player.getNumOfActive()<4) {
                              player.setActive(true);
                             playerBoard.checkNumOfActive();
                              System.out.println(player.getPlayerName() + " Log In!");
                              passwordTextField.setText("");
                              loginTextField.setText("");
                              JOptionPane.showMessageDialog(null, "Logged in!", "Login", JOptionPane.PLAIN_MESSAGE);

                              break;

                          }
                          else{
                              JOptionPane.showMessageDialog(null, "Logged in player limit has been reached! (4 players)", "Password", JOptionPane.WARNING_MESSAGE);

                          }
                      }


            }
        }

    }


}