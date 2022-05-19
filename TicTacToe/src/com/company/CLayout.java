package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class CLayout implements MouseListener {

    JFrame mainFrame = new JFrame("TicTacToe");

    JPanel contPanel = new JPanel();
    PlayerBoard playerBoard = new PlayerBoard();
    GameBufor gameBufor = new GameBufor();


    HomePage homePage = new HomePage();
    ChooseGamePage chooseGamePage = new ChooseGamePage();
    ChooseBoardPage chooseBoardPage = new ChooseBoardPage();
    RegisterBoard registerBoard = new RegisterBoard();
    LoginBoard loginBoard = new LoginBoard();
    LeaderBoard leaderBoard = new LeaderBoard();
    InformationPage informationPage =new InformationPage();
    CardLayout cl = new CardLayout();
    GamePage2D gamePage2D =new GamePage2D();
    WhoPlayPage whoPlayPage = new WhoPlayPage();
    GamePage3D gamePage3D = new GamePage3D();
    LoadTournament loadTournament= new LoadTournament();



    public CLayout() throws IOException {
        contPanel.setLayout(cl);
        contPanel.add(homePage, "1");
        contPanel.add(chooseGamePage, "2");
        contPanel.add(chooseBoardPage, "3");
        contPanel.add(registerBoard, "4");
        contPanel.add(loginBoard, "5");
        contPanel.add(leaderBoard, "6");
        contPanel.add(gamePage2D, "7");
        contPanel.add(informationPage, "8");
        contPanel.add(whoPlayPage, "9");
        contPanel.add(gamePage3D, "10");
        contPanel.add(loadTournament, "11");




        cl.show(contPanel, "1");

        mainFrame.add(contPanel);
        mainFrame.setTitle("TicTacToe");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1110,730);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setBackground(Color.CYAN);
        ImageIcon img = new ImageIcon("Icons\\oX.jpg");
        mainFrame.setIconImage(img.getImage());
        mainFrame.setVisible(true);

        homePage.getLabelStart().addMouseListener(this);
        homePage.getLabelLogin().addMouseListener(this);
        homePage.getLabelRegister().addMouseListener(this);
        homePage.getLabelLeaderboard().addMouseListener(this);
        homePage.getStatsBoard().addMouseListener(this);

        chooseGamePage.getLabelQuickStart().addMouseListener(this);
        chooseGamePage.getLabelPvP().addMouseListener(this);
        chooseGamePage.getLabelTournament().addMouseListener(this);

        chooseBoardPage.getLabel3x3().addMouseListener(this);
        chooseBoardPage.getLabel4x4().addMouseListener(this);
        chooseBoardPage.getLabel3x3x3().addMouseListener(this);
        chooseBoardPage.getLabel4x4x4().addMouseListener(this);

        loadTournament.getNewTournamentLabel().addMouseListener(this);
        loadTournament.getcontinueLabel().addMouseListener(this);



        chooseGamePage.getBackButton().addMouseListener(this);
        chooseBoardPage.getBackButton().addMouseListener(this);
        registerBoard.getBackButton().addMouseListener(this);
        loginBoard.getBackButton().addMouseListener(this);
        leaderBoard.getBackButton().addMouseListener(this);
        informationPage.getBackButton().addMouseListener(this);
        whoPlayPage.getBackButton().addMouseListener(this);
        loadTournament.getBackButton().addMouseListener(this);


        gamePage2D.getBackButton().addMouseListener(this);
        gamePage3D.getExitButton().addMouseListener(this);

        whoPlayPage.getPlayButton().addMouseListener(this);
        whoPlayPage.getGuestPlayButton().addMouseListener(this);






    }

    @Override
    public void mouseClicked(MouseEvent e) {

//                                                                      Menu Buttons
        if(e.getSource()==homePage.getLabelStart()){
            homePage.exitHomePage();
            cl.show(contPanel, "2");
        }
        else if(e.getSource()==homePage.getLabelLogin()){
            homePage.exitHomePage();
            cl.show(contPanel, "5");
        }
        else if(e.getSource()==homePage.getLabelRegister()){
            homePage.exitHomePage();
            cl.show(contPanel, "4");
        }
        else if(e.getSource()==homePage.getLabelLeaderboard()){
            homePage.exitHomePage();
            leaderBoard.showLeaderboard();
            cl.show(contPanel, "6");

        }
        else if(e.getSource()==homePage.getStatsBoard()){
            homePage.exitHomePage();
            cl.show(contPanel, "8");
        }

//                                                                      GAME Settings
//                                                                                  1 2 4
        if(e.getSource()==chooseGamePage.getLabelQuickStart()){
            gameBufor.setBotPlayer(true);
            gameBufor.setNumOfPlayers(1);
            cl.show(contPanel, "3");
        }
        else if(e.getSource()==chooseGamePage.getLabelPvP()){
            gameBufor.setBotPlayer(false);
            gameBufor.setNumOfPlayers(2);
            cl.show(contPanel, "3");
        }
        else if(e.getSource()==chooseGamePage.getLabelTournament()){
            gameBufor.setBotPlayer(false);
            gameBufor.setPlayTournament(true);
            gameBufor.setNumOfPlayers(4);
            cl.show(contPanel, "11");
        }

//                                                                   CHOOSE BOARD
        if(e.getSource()==chooseBoardPage.getLabel3x3()){
            gameBufor.setBoardChoose(3);
            gameBufor.setBoardDimension(2);

            whoPlayPage.choosePlayer();
            cl.show(contPanel, "9");
        }
        else if(e.getSource()==chooseBoardPage.getLabel4x4()){
            gameBufor.setBoardChoose(4);
            gameBufor.setBoardDimension(2);


            whoPlayPage.choosePlayer();
            cl.show(contPanel, "9");
        }
        else if(e.getSource()==chooseBoardPage.getLabel3x3x3()){
            gameBufor.setBoardChoose(3);
            gameBufor.setBoardDimension(3);

            whoPlayPage.choosePlayer();
            cl.show(contPanel, "9");
        }
        else if(e.getSource()==chooseBoardPage.getLabel4x4x4()){
            gameBufor.setBoardChoose(4);
            gameBufor.setBoardDimension(3);


            whoPlayPage.choosePlayer();
            cl.show(contPanel, "9");
        }

         if(e.getSource()==loadTournament.getNewTournamentLabel()){
            cl.show(contPanel, "3");

        }

//                                                                        BACK BUTTONS
        else if(e.getSource()==chooseGamePage.getBackButton()){
            homePage.logTagShow();
            cl.show(contPanel, "1");}
        else if(e.getSource()==chooseBoardPage.getBackButton()){
            cl.show(contPanel, "2"); }
        else if(e.getSource()==registerBoard.getBackButton()){
            homePage.logTagShow();
            cl.show(contPanel, "1"); }
        else if(e.getSource()==loginBoard.getBackButton()){
            homePage.logTagShow();
            cl.show(contPanel, "1"); }
        else if(e.getSource()==leaderBoard.getBackButton()){
            homePage.logTagShow();
            leaderBoard.exitButtonClear();
            cl.show(contPanel, "1"); }
        else if(e.getSource()==informationPage.getBackButton()){
            homePage.logTagShow();
            cl.show(contPanel, "1"); }

        else if(e.getSource()==whoPlayPage.getBackButton()){
            whoPlayPage.exitPage();
            cl.show(contPanel, "3"); }
        else if(e.getSource()==loadTournament.getBackButton()){
            gameBufor.setPlayTournament(false);
             cl.show(contPanel, "2"); }



        if(e.getSource()==loadTournament.getcontinueLabel()){

             try {
                 gameBufor.readTournament();
             } catch (IOException ioException) {
                 ioException.printStackTrace();
             } catch (ClassNotFoundException classNotFoundException) {
                 classNotFoundException.printStackTrace();
             }

             if(!gameBufor.getTournament().isFinished()) {
                 if (gameBufor.getBoardDimension() == 2) {
                     Thread gameTicTacToe = new Thread(gamePage2D);
                     gameTicTacToe.start();
                     cl.show(contPanel, "7");
                 } else if (gameBufor.getBoardDimension() == 3) {
                     Thread gameTicTacToe = new Thread(gamePage3D);
                     gameTicTacToe.start();
                     cl.show(contPanel, "10");
                 }
             }
             else{
                 JOptionPane.showMessageDialog(null, "No Tournament to play", "Tournament", JOptionPane.WARNING_MESSAGE);

             }
         }



//                                                                  AFTER GAME END
        else if(e.getSource()== gamePage2D.getBackButton()){

            gamePage2D.ExitGame2D();
            whoPlayPage.exitPage();
            homePage.logTagShow();

            cl.show(contPanel, "1"); }

        else if(e.getSource()== gamePage3D.getExitButton()){
            gamePage3D.ExitGame3D();
            whoPlayPage.exitPage();
            homePage.logTagShow();
            cl.show(contPanel, "1"); }

        if (e.getSource()==whoPlayPage.getPlayButton()){
            if (whoPlayPage.checkPlayerSelected()){
                if(gameBufor.getBoardDimension()==2){
            Thread gameTicTacToe = new Thread(gamePage2D);
            gameTicTacToe.start();
            cl.show(contPanel, "7");
                }
           else if (gameBufor.getBoardDimension()==3) {
                    Thread gameTicTacToe = new Thread(gamePage3D);
                    gameTicTacToe.start();
                    cl.show(contPanel, "10");
                }

            }
        }

        if (e.getSource()==whoPlayPage.getGuestPlayButton()){
            gameBufor.setGuestPlay(true);
            if(gameBufor.getBoardDimension()==2){
                Thread gameTicTacToe = new Thread(gamePage2D);
                gameTicTacToe.start();
                cl.show(contPanel, "7");
            }
            else if (gameBufor.getBoardDimension()==3) {
                Thread gameTicTacToe = new Thread(gamePage3D);
                gameTicTacToe.start();
                cl.show(contPanel, "10");
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
}
