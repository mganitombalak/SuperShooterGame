package com.bau.cmpe.game;

import com.bau.cmpe.game.listener.OnGameLoopCompleteListener;
import com.bau.cmpe.game.physics.Wind;
import com.bau.cmpe.game.scene.GamePlayScene;
import com.bau.cmpe.game.scene.ScreenManager;
import com.bau.cmpe.game.scene.ScreenManager.ScreenList;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GameWorld implements ActionListener, IGame, IGameTimerListenerClient {
    private static Wind gameWind;
    private static Dimension screenSize;
    private ScreenManager<Object, Object, GamePlayScene, Object, Object, Object, Object> screenManager;
    private Timer gameTimer;
    private Sound gameSound;
    private ArrayList<OnGameLoopCompleteListener> gameLoopCompleteList;

    protected GameWorld(int gameTime) {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        gameLoopCompleteList = new ArrayList<>();
        gameTimer = new Timer(gameTime, this);
        gameWind = new Wind(new Point2D.Double(.1, .1));
        gameSound = new Sound("StarWars.wav");
        screenManager = ScreenManager.getInstance(this);
    }

    public static Point2D getGameWind() {
        return gameWind.getVelocity();
    }

    public static void setGameWindVelocity(Point2D newWind) {
        gameWind.setVelocity(newWind);
    }

    public static Dimension getScreenSize() {
        return screenSize;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameLoopCompleteList != null) {
            for (OnGameLoopCompleteListener listener : gameLoopCompleteList) {
                listener.GameLoopComplete();
            }
        }
    }

    @Override
    public void Start() {
        screenManager.Show(ScreenList.PlayingScreen);
        gameTimer.start();
        gameSound.Loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Override
    public void Pause() {
        gameTimer.stop();
    }

    @Override
    public void Stop() {
    }

    @Override
    public void Reset() {
        // TODO Auto-generated method stub

    }

    @Override
    public void addGameLoopListener(OnGameLoopCompleteListener listener) {
        gameLoopCompleteList.add(listener);
    }
}
