package com.bau.cmpe.game.physics;

import com.bau.cmpe.game.GameWorld;
import com.bau.cmpe.game.Planet;
import com.bau.cmpe.game.gui.Bullet;
import com.bau.cmpe.game.gui.Enemy;
import com.bau.cmpe.game.scene.ExplosionSprite;
import com.bau.cmpe.game.gui.ShapeBase;
import com.bau.cmpe.game.scene.GamePlayScene;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.logging.Logger;

public class Physics implements Runnable {
    private static Logger l = Logger.getLogger("physics");
    private Bullet mBullet;

    public Physics(Bullet b) {
        mBullet = b;
    }

    public synchronized void ApplyPhysics() {

        l.fine("Bullet x:" + mBullet.getPosition().getX());
        double px = mBullet.getPosition().getX() + mBullet.getVelocity().getX();
        double py = mBullet.getPosition().getY() - mBullet.getVelocity().getY();
        mBullet.getPosition().setLocation(px, py);
        double vx = mBullet.getVelocity().getX() - GameWorld.getGameWind().getX();
        double vy = mBullet.getVelocity().getY() - Planet.EARTH.surfaceGravity() - GameWorld.getGameWind().getY();
        mBullet.getVelocity().setLocation(vx, vy);
        mBullet.setBounds((int)px,(int)py,(int)mBullet.getRadius(),(int)mBullet.getRadius());
    }

    public synchronized void DetectCollision() {
        if(!mBullet.isVisible()) return;
        synchronized (GamePlayScene.getEnemyList()) {
            Iterator<Enemy> iterator = GamePlayScene.getEnemyList().iterator();
            while (iterator.hasNext()) {
                ShapeBase currentSceneItem = iterator.next();
                if (currentSceneItem.getBounds().contains(mBullet.getPosition().getX(), mBullet.getPosition().getY())) {
                    currentSceneItem.setVisible(false);
                    GamePlayScene.getExplosionList().add(new ExplosionSprite(new Point2D.Double(currentSceneItem.getX() + 15, currentSceneItem.getY() + 15)));
                    mBullet.setVisible(false);
                }
            }
        }
    }

        @Override
        public void run () {
            ApplyPhysics();
            DetectCollision();
        }
    }