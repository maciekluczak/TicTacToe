package com.company;

import java.io.*;
import java.util.ArrayList;

public class GameBufor extends Thread {
    private static int Turn ;
    private static int boardChoose;
    private static boolean botPlayer;
    private static Player player1, player2, player3, player4;
    private static int numOfPlayers;
    private static boolean guestPlay;
    private static int boardDimension;
    private static boolean playTournament;
    private static Tournament tournament;
    private static boolean gameRun;

    public static Tournament getTournament() {
        return tournament;
    }

    public static void setTournament(Tournament tournament) {
        GameBufor.tournament = tournament;
    }

    public GameBufor() {
        Turn= 1;

    }

    public void saveTournament() throws IOException {
        FileOutputStream fileOut= new FileOutputStream("TournamentLog.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(tournament);
        out.close();
        fileOut.close();
    }

    public void readTournament() throws IOException, ClassNotFoundException {

        FileInputStream fileIn= new FileInputStream("TournamentLog.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        tournament= (Tournament) in.readObject();
        if(!tournament.isFinished()){
            boardChoose= tournament.getBoardChoose();
            boardDimension= tournament.getBoardDimension();

            if (this.getTournament().getGameRound() == 0) {
                this.setPlayer1(this.getTournament().getPlayer1());
                this.setPlayer2(this.getTournament().getPlayer2());
            }

            else if (this.getTournament().getGameRound() == 1) {
                this.setPlayer1(this.getTournament().getPlayer3());
                this.setPlayer2(this.getTournament().getPlayer4());

            } else if (this.getTournament().getGameRound() == 2) {
                this.setPlayer1(this.getTournament().getPlayerArray().get(0));
                this.setPlayer2(this.getTournament().getPlayerArray().get(1));
            }
        }

    }

    public static boolean isGameRun() {
        return gameRun;
    }

    public static void setGameRun(boolean gameRun) {
        GameBufor.gameRun = gameRun;
    }

    public static boolean isGuestPlay() {
        return guestPlay;
    }

    public static void setGuestPlay(boolean guestPlay) {
        GameBufor.guestPlay = guestPlay;
    }

    public static int getBoardDimension() {
        return boardDimension;
    }

    public static void setBoardDimension(int boardDimension) {
        GameBufor.boardDimension = boardDimension;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static Player getPlayer3() {
        return player3;
    }

    public static Player getPlayer4() {
        return player4;
    }

    public static int getNumOfPlayers() {
        return numOfPlayers;
    }

    public static void setPlayer1(Player player1) {
        GameBufor.player1 = player1;
    }

    public static void setPlayer2(Player player2) {
        GameBufor.player2 = player2;
    }

    public static void setPlayer3(Player player3) {
        GameBufor.player3 = player3;
    }

    public static void setPlayer4(Player player4) {
        GameBufor.player4 = player4;
    }

    public static void setNumOfPlayers(int numOfPlayers) {
        GameBufor.numOfPlayers = numOfPlayers;
    }

    public static void setBoardChoose(int boardChoose) {
        GameBufor.boardChoose = boardChoose;
    }



    public static int getBoardChoose() {
        return boardChoose;
    }


    public static void setBotPlayer(boolean botPlayer) {
        GameBufor.botPlayer = botPlayer;
    }

    public static boolean isBotPlayer() {
        return botPlayer;
    }

    public static int getTurn() {
        return Turn;
    }

    public static void setTurn(int turn) {
        Turn = turn;
    }

    public static boolean isPlayTournament() {
        return playTournament;
    }

    public static void setPlayTournament(boolean playTournament) {
        GameBufor.playTournament = playTournament;
    }
}

