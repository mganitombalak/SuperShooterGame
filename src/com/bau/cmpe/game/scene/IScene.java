package com.bau.cmpe.game.scene;

import com.bau.cmpe.game.IGameTimerListenerClient;

import java.awt.*;

public interface IScene {
    void BeforeLoad();

    void Load();

    void GameLoop(Graphics device);

    void RegisterGameLoop(IGameTimerListenerClient clientAccessible);

    void AfterLoad();

    void UnLoad();

    void Success();

    void Failed();

    void Show();

    void Hide();

    void Reset();
}
