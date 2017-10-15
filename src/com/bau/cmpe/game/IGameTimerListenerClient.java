package com.bau.cmpe.game;

import com.bau.cmpe.game.listener.OnGameLoopCompleteListener;

public interface IGameTimerListenerClient {
    void addGameLoopListener(OnGameLoopCompleteListener listener);
}
