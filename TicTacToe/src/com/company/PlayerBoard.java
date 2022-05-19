package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoard{
    private static List<Player> playerArray = new ArrayList<>();
    private static List<String> usernameArray = new ArrayList<>();

    public static List<String> getUsernameArray() {
        return usernameArray;
    }

    public boolean checkPlayer(String username){
        if(usernameArray.contains(username)){
            return false;
        }
        else{
        usernameArray.add(username);
        return true;}
    }

    public static List<Player> getPlayerArray() {
        return playerArray;
    }

    public void addPlayer(Player newPlayer) throws IOException {
        playerArray.add(newPlayer);
        savePlayers();
    }

    public void showPlayers(){
        for(int i=0;i<playerArray.size();i++){
            System.out.println(playerArray.get(i).getPlayerName());}
    }

    public void savePlayers() throws IOException {
        FileOutputStream fileOut= new FileOutputStream("PlayerLog.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(playerArray);
        out.close();
        fileOut.close();
        }
    public void readPlayers() throws IOException, ClassNotFoundException {

            FileInputStream fileIn= new FileInputStream("PlayerLog.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            playerArray=(ArrayList<Player>)in.readObject();
            showPlayers();
            for(int i=0;i<playerArray.size();i++){
                usernameArray.add(playerArray.get(i).getPlayerName());
            }



    }

    public void checkNumOfActive(){
        int activeCounter=0;
        for (Player player: playerArray
             ) {
            if(player.getActive()!=null){
                activeCounter++;
                player.setNumOfActive(activeCounter);
            }
        }


    }

}
