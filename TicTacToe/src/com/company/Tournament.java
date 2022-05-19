package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tournament implements Serializable
{
    private  int   gameRound;
    private  Player  player1, player2, player3, player4;
    private  int boardDimension;
    private  boolean guestPlay;
    private int boardChoose;
    private  boolean finished;
    private  List<Player> playerArray = new ArrayList<>();

    public Tournament(Player player1, Player player2, Player player3, Player player4) {

        this.player1= player1;
        this.player2= player2;
        this.player3= player3;
        this.player4= player4;

        finished=false;

        playerArray.add(player1);
        playerArray.add(player2);
        playerArray.add(player3);
        playerArray.add(player4);

    }

    public int getBoardChoose() {
        return boardChoose;
    }

    public  boolean isFinished() {
        return finished;
    }

    public  void setFinished(boolean finished) {
        this.finished = finished;
    }

    public  void setBoardChoose(int boardChoose) {
        this.boardChoose = boardChoose;
    }

    public  int getBoardDimension() {
        return boardDimension;
    }

    public  void setBoardDimension(int boardDimension) {
        this.boardDimension = boardDimension;
    }

    public  boolean isGuestPlay() {
        return guestPlay;
    }

    public  void setGuestPlay(boolean guestPlay) {
        this.guestPlay = guestPlay;
    }

    public  int getGameRound() {
        return gameRound;
    }

    public  void setGameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    public  List<Player> getPlayerArray() {
        return playerArray;
    }

    public  void setPlayerArray(List<Player> playerArray) {
        this.playerArray = playerArray;
    }


    public  Player getPlayer1() {
        return player1;
    }

    public  Player getPlayer2() {
        return player2;
    }

    public  Player getPlayer3() {
        return player3;
    }

    public  Player getPlayer4() {
        return player4;
    }
}
