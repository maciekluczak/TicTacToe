package com.company;

import java.io.Serializable;

public class Player implements Serializable {
    private String playerName;
    private String playerPassword;
    private int numOfWins;
    private int numOfPlays;
    private int numOfLosses;
    private int numOfPoints;
    private int numOfTournaments;
    private static transient int numOfActive;
    private transient Boolean isActive;

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Player(String username, String password) {
        this.playerName= username;
        this.playerPassword=password;
        this.isActive=null;
    }
    void welcomePlayer(){
        System.out.println("Hello " + playerName);
    }

    public String getPlayerPassword() {
        return playerPassword;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    public void setNumOfWins(int numOfWins) {
        this.numOfWins = numOfWins;
    }

    public int getNumOfPlays() {
        return numOfPlays;
    }

    public void setNumOfPlays(int numOfPlays) {
        this.numOfPlays = numOfPlays;
    }

    public int getNumOfLosses() {
        return numOfLosses;
    }

    public void setNumOfLosses(int numOfLosses) {
        this.numOfLosses = numOfLosses;
    }

    public int getNumOfPoints() {
        return numOfPoints;
    }

    public void setNumOfPoints(int numOfPoints) {
        this.numOfPoints = numOfPoints;
    }

    public int getNumOfTournaments() {
        return numOfTournaments;
    }

    public static int getNumOfActive() {
        return numOfActive;
    }

    public static void setNumOfActive(int numOfActive) {
        Player.numOfActive = numOfActive;
    }

    public void setNumOfTournaments(int numOfTournaments) {
        this.numOfTournaments = numOfTournaments;
    }
}
