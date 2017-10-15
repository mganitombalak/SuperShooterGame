package com.bau.cmpe.game.gui;

public enum Direction {
    Right("Right"),
    Left("Left"),
    Neutral("Neutral");

    private final String name;

    private Direction(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}