package com.company;

import javax.swing.*;
import java.io.IOException;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new CLayout();
        PlayerBoard playerBoard = new PlayerBoard();
        playerBoard.readPlayers();


        MusicBackground musicObject = new MusicBackground();
        musicObject.playMusic();



        System.out.println(Thread.activeCount());
        System.out.println(Thread.currentThread());

    }
}
