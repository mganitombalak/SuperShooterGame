package com.bau.cmpe.game.scene;

import com.bau.cmpe.game.IGameTimerListenerClient;
import com.bau.cmpe.game.gui.Bullet;
import com.bau.cmpe.game.gui.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;

public class ScreenManager<splashScreenType, landingScreenType, playingScreenType, successScreenType, failedScreenType, settingsScreenType, scoreBoardScreenType> {
    public static ScreenList LastScreen;
    private static ScreenManager singletonInstance;
    private IGameTimerListenerClient accessibleGameObject;
    private JFrame mainWindow;
    private SceneBase splashScreen;
    private SceneBase landingScreen;
    private SceneBase playingScreen;
    private SceneBase successScreen;
    private SceneBase failedScreen;
    private SceneBase settingsScreen;
    private SceneBase scoreBoardScreen;

    private ScreenManager(IGameTimerListenerClient gameObject) {
        LastScreen = ScreenList.FirstRun;
        accessibleGameObject = gameObject;
    }

    public static <splashScreenType, landingScreenType, playingScreenType, successScreenType, failedScreenType, settingsScreenType, scoreBoardScreenType> ScreenManager<splashScreenType, landingScreenType, playingScreenType, successScreenType, failedScreenType, settingsScreenType, scoreBoardScreenType> getInstance(
            IGameTimerListenerClient accessibleGameObject) {
        if (singletonInstance == null)
            singletonInstance = new ScreenManager<splashScreenType, landingScreenType, playingScreenType, successScreenType, failedScreenType, settingsScreenType, scoreBoardScreenType>(
                    accessibleGameObject);
        try {
            singletonInstance.PrepareSreens(null, null, GamePlayScene.class, null, null, null, null, accessibleGameObject);
        } catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();
        }
        return singletonInstance;
    }

    public void PrepareSreens(Class<splashScreenType> splash, Class<landingScreenType> landing,
                              Class<playingScreenType> playing, Class<successScreenType> success, Class<failedScreenType> failed,
                              Class<settingsScreenType> settings, Class<scoreBoardScreenType> scoreBoard,
                              IGameTimerListenerClient accessibleGameObject) throws InstantiationException, IllegalAccessException {
        mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setExtendedState(JFrame.NORMAL);
        mainWindow.setUndecorated(true);
        mainWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    {
                        new Thread(() -> {
                            Point2D p = Player.getInstance(null).getPosition();
                            Bullet b = new Bullet(new Point2D.Double(p.getX() + 30, p.getY() + 30), 25, Color.BLACK);
                            GamePlayScene.getBulletList().add(b);
                        }).start();
                    }
                }
            }
        });

        if (splash != null) {
            splashScreen = (SceneBase) splash.newInstance();
            mainWindow.add(splashScreen);
            if (splashScreen.isMustRegisteredForGameLoop())
                splashScreen.RegisterGameLoop(accessibleGameObject);
        }
        if (landing != null) {
            landingScreen = (SceneBase) landing.newInstance();
            mainWindow.add(landingScreen);
            if (landingScreen.isMustRegisteredForGameLoop())
                landingScreen.RegisterGameLoop(accessibleGameObject);
        }
        if (playing != null) {
            playingScreen = (SceneBase) playing.newInstance();
            mainWindow.add(playingScreen);
            if (playingScreen.isMustRegisteredForGameLoop())
                playingScreen.RegisterGameLoop(accessibleGameObject);
        }
        if (success != null) {
            successScreen = (SceneBase) success.newInstance();
            mainWindow.add(successScreen);
            if (successScreen.isMustRegisteredForGameLoop())
                successScreen.RegisterGameLoop(accessibleGameObject);
        }
        if (failed != null) {
            failedScreen = (SceneBase) failed.newInstance();
            mainWindow.add(failedScreen);
            if (splashScreen.isMustRegisteredForGameLoop())
                failedScreen.RegisterGameLoop(accessibleGameObject);
        }
        if (settings != null) {
            settingsScreen = (SceneBase) settings.newInstance();
            mainWindow.add(settingsScreen);
            if (settingsScreen.isMustRegisteredForGameLoop())
                settingsScreen.RegisterGameLoop(accessibleGameObject);
        }
        if (scoreBoard != null) {
            scoreBoardScreen = (SceneBase) scoreBoard.newInstance();
            mainWindow.add(scoreBoardScreen);
            if (scoreBoardScreen.isMustRegisteredForGameLoop())
                scoreBoardScreen.RegisterGameLoop(accessibleGameObject);
        }

        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();

        if (device.isFullScreenSupported())
            device.setFullScreenWindow(mainWindow);
    }

    public void Show(ScreenList screen) {
        LastScreen = screen;
        switch (screen) {
            case SplashScreen:
                break;
            case LandingScreen:
                break;
            case PlayingScreen:
                playingScreen.Show();
                break;
            case SuccessScreen:
                break;
            case FailedScreen:
                break;
            case SettingsScreen:
                break;
            case ScoreBoardScreen:
                break;
            default:
                break;
        }
    }

    public enum ScreenList {
        FirstRun, SplashScreen, LandingScreen, PlayingScreen, SuccessScreen, FailedScreen, SettingsScreen, ScoreBoardScreen
    }

}