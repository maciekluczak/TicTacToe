package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicBackground extends Thread {
    File gameSound = new File("Music\\clicksound2.wav");
    File gameMusic = new File("Music\\Doobly Doo game.wav");
    File fieldSound = new File("Music\\field.wav");
    File winSound = new File("Music\\win.wav");
    File tournamentSound = new File("Music\\tournament.wav");


    void playMusic()
    {
        try
        {

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(gameMusic);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    void playSound(){
        try
        {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(gameSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    void playSoundField(){
        try
        {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(fieldSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
    void playWinSound(){
        try
        {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(winSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }


    void playTournamentSound(){
        try
        {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(tournamentSound);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }



}
