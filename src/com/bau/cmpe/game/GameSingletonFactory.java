package com.bau.cmpe.game;

public class GameSingletonFactory {
    private static GameWorld currentGame;

    public static GameWorld getInstance(int gameTime) {
        if (currentGame != null) return currentGame;
        return currentGame = new GameWorld(gameTime);
    }
}
