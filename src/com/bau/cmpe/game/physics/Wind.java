package com.bau.cmpe.game.physics;

import java.awt.geom.Point2D;

public class Wind {
    private Point2D mVelocity;

    public Wind(Point2D Velocity) {
        mVelocity = Velocity;
    }

    public Point2D getVelocity() {
        return mVelocity;
    }

    public void setVelocity(Point2D velocity) {
        mVelocity = velocity;
    }
}