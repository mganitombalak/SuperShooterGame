package com.bau.cmpe.game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound implements Runnable {
    AudioInputStream audioIn;
    Clip clip;

    public Sound(String AssetName) {
        try {
            URL url = this.getClass().getClassLoader().getResource("StarWars.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void Start() {

        clip.start();
    }

    public void Stop() {

        clip.stop();
    }

    public void Loop(int Loop) {

        clip.loop(Loop);
    }

    @Override
    public void run() {
        this.Loop(Clip.LOOP_CONTINUOUSLY);
    }
}
