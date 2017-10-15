package com.bau.cmpe.game.gui;

import com.bau.cmpe.game.GameWorld;
import com.bau.cmpe.game.scene.GamePlayScene;
import com.bau.cmpe.game.scene.ISceneObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public abstract class ShapeBase extends Component implements Runnable, ISceneObject {

    private static final long serialVersionUID = -8238141079249916230L;
    protected double mAngle = Math.toRadians(45);
    protected Color mShapeColor;
    protected Point2D mPosition;
    protected Point2D mVelocity;
    protected boolean mCollidable;
    protected boolean mVisible = true;
    protected Direction mShapeDirection;

    public ShapeBase(Point2D initialPosition) {
        mPosition = initialPosition;
        mVelocity = new Point2D.Double(100 * MouseInfo.getPointerInfo().getLocation().getX() / GameWorld.getScreenSize().getWidth(), 80 * ((GameWorld.getScreenSize().getHeight() - MouseInfo.getPointerInfo().getLocation().getY()) / GameWorld.getScreenSize().getHeight()));
    }

    public abstract void Draw(Graphics graphicDevice);

    public void Kill() {
        if (!this.isVisible() || GameWorld.getScreenSize().getWidth() < this.mPosition.getX()
                || GameWorld.getScreenSize().getHeight() < this.mPosition.getY()) {
            this.setVisible(false);
            if (this instanceof Bullet) {
                GamePlayScene.getBulletList().remove(this);
            } else if (this instanceof Enemy) {
                GamePlayScene.getEnemyList().remove(this);
            }
            try {
                this.finalize();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isVisible() {
        return mVisible;
    }

    public void setVisible(boolean visible) {
        this.mVisible = visible;
    }

    public Point2D getVelocity() {
        return mVelocity;
    }

    public void setVelocity(Point2D velocity) {
        this.mVelocity = velocity;
    }

    public boolean isCollidable() {
        return mCollidable;
    }

    public void setCollidable(boolean collidable) {
        this.mCollidable = collidable;
    }

    public Color getShapeColor() {
        return mShapeColor;
    }

    public void setShapeColor(Color shareColor) {
        this.mShapeColor = shareColor;
    }

    public Point2D getPosition() {
        return mPosition;
    }

    public void setPosition(Point2D position) {
        this.mPosition = position;
    }

    public Direction getShapeDirection() {
        return mShapeDirection;
    }

    public void setShapeDirection(Direction shapeDirection) {
        mShapeDirection = shapeDirection;
    }

}
