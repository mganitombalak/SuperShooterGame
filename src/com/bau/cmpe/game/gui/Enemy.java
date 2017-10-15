package com.bau.cmpe.game.gui;

import com.bau.cmpe.game.scene.ISceneObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Enemy extends ShapeBase implements ISceneObject, ISprite {
    /**
     *
     */
    private static final long serialVersionUID = -7018970136091495069L;
    private int mCurrentSpriteIndice = 0;
    private int mMaxSpriteCount;
    private String mResourceName;

    public Enemy(Point2D initialPosition) {
        super(initialPosition);
    }

    public Enemy(Point2D initialPosition, int MaxSpriteCount, int StartIndice, String ResourceName, Direction ShapeDirection) {
        super(initialPosition);
        mMaxSpriteCount = MaxSpriteCount;
        if (StartIndice <= MaxSpriteCount)
            mCurrentSpriteIndice = StartIndice;
        mResourceName = ResourceName;
        mShapeDirection = ShapeDirection;
        setBounds((int)initialPosition.getX()+30,(int) initialPosition.getY()+10,80,120);
    }

    @Override
    public void GameLoop(Graphics device) {
        this.Draw(device);
    }

    @Override
    public void Draw(Graphics graphicDevice) {
        if (!isVisible()) return;
        graphicDevice.drawImage(new ImageIcon(this.getClass().getResource("/" + mResourceName + mShapeDirection + mCurrentSpriteIndice + ".png")).getImage(), (int) getPosition().getX(), (int) getPosition().getY(), null);
        Animate();
    }

    @Override
    public void Animate() {
        mCurrentSpriteIndice++;
        if (mCurrentSpriteIndice > mMaxSpriteCount)
            mCurrentSpriteIndice = 0;
    }

    @Override
    public void run() {
        this.Kill();
    }

}
