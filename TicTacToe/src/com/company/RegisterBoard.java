package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class RegisterBoard extends JLayeredPane implements KeyListener, ActionListener {

    private BackButton backButton;
    private JTextField registerTextField, passwordTextField;
    private String username, password = "";
    private JButton registerButton;
    private JCheckBox registerCheckbox;
    private PlayerBoard playerBoard = new PlayerBoard();

    public BackButton getBackButton() {
        return backButton;
    }


    public RegisterBoard() {


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


//PANEL UP LAYOUT
        panelUp.setLayout(new BorderLayout());

        ImageIcon chooseGameImage = new ImageIcon("Icons\\Register.png");
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
        registerTextField = new JTextField("", 20);
        registerTextField.setBounds(0, 75, 320, 111);

        ImageIcon image = new ImageIcon("Icons\\textfield1.png");
        Image convertImage = image.getImage();
        Image newImage = convertImage.getScaledInstance(320, 200, Image.SCALE_SMOOTH);
        ImageIcon imageTextField = new ImageIcon(newImage);

        JLabel labelTextField = new JLabel();
        labelTextField.setIcon(imageTextField);

        registerTextField.setOpaque(false);
        labelTextField.setOpaque(false);
        registerTextField.setFont(new Font("MV Boli", Font.PLAIN, 30));

        labelTextField.add(registerTextField);
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

        registerCheckbox = new JCheckBox("I'm not a robot");
        registerCheckbox.setForeground(Color.black);
        registerCheckbox.setOpaque(false);
        registerButton = new JButton("Submit");

        JLayeredPane passwordLayer = new JLayeredPane();
        passwordLayer.setBounds(50, 0, 400, 300);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        passwordPanel.add(registerCheckbox);
        passwordPanel.add(registerButton);
        passwordLayer.add(passwordBackground, JLayeredPane.DEFAULT_LAYER);
        passwordLayer.add(passwordPanel, JLayeredPane.DRAG_LAYER);

        panelCenterBottom.add(passwordLayer);


        registerTextField.addKeyListener(this);
        passwordTextField.addKeyListener(this);
        registerButton.addActionListener(this);
        registerCheckbox.addActionListener(this);


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
        this.add(animationPanel, JLayeredPane.DEFAULT_LAYER);

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
        if (registerTextField.isFocusOwner()) {

            if (e.getKeyCode() >= 48 & e.getKeyCode() <= 57 ||
                    e.getKeyCode() >= 65 & e.getKeyCode() <= 90 ||
                    e.getKeyCode() == 46 || e.getKeyCode() == 20 ||
                    e.getKeyCode() == 16 || e.getKeyCode() == 8) {
            } else {
                registerTextField.setText("");
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
        if (e.getSource() == registerButton) {
            if (registerCheckbox.isSelected()) {
                username = registerTextField.getText();
                if (username.equals("") || passwordTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Username and Password!", "Username", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    registerCheckbox.setForeground(Color.black);
                    try {
                        playerRegister();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Welcome "+ username+"!", "Username", JOptionPane.PLAIN_MESSAGE);



                }
            } else {
                registerCheckbox.setForeground(Color.RED);
            }
        }

    }


    public void  playerRegister() throws IOException {
        if(playerBoard.checkPlayer(username)){
            Player newPlayer = new Player(username,passwordTextField.getText());
            newPlayer.welcomePlayer();
            playerBoard.addPlayer(newPlayer);
            registerTextField.setText("");
            passwordTextField.setText("");
            registerCheckbox.setSelected(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "Username already taken!", "Username", JOptionPane.WARNING_MESSAGE);
        }
    }
}