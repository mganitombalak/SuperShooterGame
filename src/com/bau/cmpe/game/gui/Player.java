package com.bau.cmpe.game.gui;

import com.bau.cmpe.game.scene.ISceneObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class Player extends ShapeBase implements ISceneObject, ISprite {

    private static final long serialVersionUID = -2932583077075561460L;
    private static Player mSingleton;
    private static String mAssetName;

    private Player(Point2D initialPosition) {
        super(initialPosition);
    }

    public static Player getInstance(Point2D initialPosition) {
        if (mSingleton == null)
            mSingleton = new Player(initialPosition);
        return mSingleton;
    }

    public static Player getInstance(Point2D initialPosition, String AssetName) {
        mAssetName = AssetName;
        if (mSingleton == null)
            mSingleton = new Player(initialPosition);
        return mSingleton;
    }

    @Override
    public void Animate() {
    }

    @Override
    public void GameLoop(Graphics device) {
        this.Draw(device);
    }

    @Override
    public void Draw(Graphics graphicDevice) {
        graphicDevice.drawImage(new ImageIcon(this.getClass().getResource("/" + mAssetName + ".png")).getImage(),
                (int) getPosition().getX(), (int) getPosition().getY(), null);

    }

    @Override
    public void run() {
        //this.Kill();

    }

}
