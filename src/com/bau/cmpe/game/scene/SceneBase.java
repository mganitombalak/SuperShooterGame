package com.bau.cmpe.game.scene;

import com.bau.cmpe.game.IGameTimerListenerClient;
import com.bau.cmpe.game.listener.OnGameLoopCompleteListener;

import javax.swing.*;

public abstract class SceneBase extends JPanel implements IScene {
    private static final long serialVersionUID = 1L;
    private boolean mustRegisteredForGameLoop = false;
    private IGameTimerListenerClient gameWorld;

    public SceneBase() {
        this.setVisible(false);
    }

    public void RegisterGameLoop(IGameTimerListenerClient clientAccessible) {
        gameWorld = clientAccessible;
        gameWorld.addGameLoopListener(new OnGameLoopCompleteListener() {
            @Override
            public void GameLoopComplete() {
                GameLoop(null);
            }
        });
    }

    public void Show() {
        this.setVisible(true);
    }

    public void Hide() {
        this.setVisible(false);
    }

    public boolean isMustRegisteredForGameLoop() {
        return mustRegisteredForGameLoop;
    }

    public void setMustRegisteredForGameLoop(boolean mustRegisteredForGameLoop) {
        this.mustRegisteredForGameLoop = mustRegisteredForGameLoop;
    }

}
