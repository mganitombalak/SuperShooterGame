package com.bau.cmpe.game.scene;

import com.bau.cmpe.game.gui.ISprite;
import com.bau.cmpe.game.gui.ShapeBase;
import com.bau.cmpe.game.utility.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class ExplosionSprite extends ShapeBase implements ISceneObject, ISprite {
    /**
     *
     */
    private static final long serialVersionUID = -6157224662457528728L;
    private static BufferedImage explosionAsset;
    private static int width = 50;
    private static int height = 128;
    private static int rows = 1;
    private static int cols = 6;
    private static BufferedImage[] mFrames;
    private int mCurrentSpriteIndices = 0;

    public ExplosionSprite(Point2D initialPosition) {
        super(initialPosition);
    }

    public static void LoadSprite(String AssetName) {
        explosionAsset = Utility
                .toBufferedImage(new ImageIcon(ExplosionSprite.class.getResource("/" + AssetName + ".png")).getImage());
        mFrames = new BufferedImage[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mFrames[(i * cols) + j] = explosionAsset.getSubimage(j * width, i * height, width, height);
            }
        }
    }

    public synchronized void Draw(Graphics device) {
        if (!this.isVisible())
            return;
        device.drawImage(mFrames[mCurrentSpriteIndices], (int) getPosition().getX(), (int) getPosition().getY(), null);
    }

    @Override
    public synchronized void Animate() {
        mCurrentSpriteIndices++;
        if (mCurrentSpriteIndices > (rows * cols) - 1) {
            mCurrentSpriteIndices = 0;
            this.setVisible(false);
        }
    }

    @Override
    public void GameLoop(Graphics device) {
        this.Draw(device);
        Animate();
    }

    @Override
    public void run() {
        this.Kill();
    }
}
