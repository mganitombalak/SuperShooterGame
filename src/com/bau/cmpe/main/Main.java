package com.bau.cmpe.main;

import com.bau.cmpe.game.GameSingletonFactory;

public class Main {
    public static void main(String[] args)
    {
        GameSingletonFactory.getInstance(80).Start();
    }
}