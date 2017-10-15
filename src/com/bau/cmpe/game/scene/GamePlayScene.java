package com.bau.cmpe.game.scene;

import com.bau.cmpe.game.gui.*;
import com.bau.cmpe.game.physics.Physics;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class GamePlayScene extends SceneBase {
    private static final long serialVersionUID = 1L;
    private static List<Bullet> bulletList;
    private static List<Enemy> enemyList;
    private static List<ExplosionSprite> explosionList;
    private static Player player;
    ArrayList<Thread> ts;

    public GamePlayScene() {
        BeforeLoad();
    }

    public static List<Bullet> getBulletList() {
        return bulletList;
    }

    public static List<Enemy> getEnemyList() {
        return enemyList;
    }

    public static List<ExplosionSprite> getExplosionList() {
        return explosionList;
    }

    @Override
    protected void paintComponent(Graphics graphicDevice) {
        super.paintComponent(graphicDevice);
        Graphics2D graphicSpace = (Graphics2D) graphicDevice;
        graphicSpace.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        player.GameLoop(graphicDevice);
        ts.clear();
        for (Bullet bullet : bulletList) {
            ts.add(new Thread(new Physics(bullet)));
            ts.add(new Thread(bullet));
        }
        ts.addAll(enemyList.stream().map(Thread::new).collect(Collectors.toList()));
        ts.forEach(Thread::start);
        for (Thread thread : ts) {
            try {thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (bulletList) {
            Iterator<Bullet> iterator = bulletList.iterator();
            while (iterator.hasNext()) {
                ShapeBase currentSceneItem = iterator.next();
                currentSceneItem.GameLoop(graphicDevice);
            }
        }
        synchronized (enemyList) {
            Iterator<Enemy> iterator = getEnemyList().iterator();
            while (iterator.hasNext()) {
                ShapeBase currentSceneItem = iterator.next();
                currentSceneItem.GameLoop(graphicDevice);
            }
        }
        synchronized (explosionList) {
            Iterator<ExplosionSprite> iterator = getExplosionList().iterator();
            while (iterator.hasNext()) {
                ShapeBase currentSceneItem = iterator.next();
                currentSceneItem.GameLoop(graphicDevice);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void BeforeLoad() {
        ts = new ArrayList<>();
        player = Player.getInstance(new Point2D.Double(200, 500), "cannonball");
        bulletList = Collections.synchronizedList(new ArrayList<Bullet>());
        enemyList = Collections.synchronizedList(new ArrayList<Enemy>());
        explosionList = Collections.synchronizedList(new ArrayList<ExplosionSprite>());
        ExplosionSprite.LoadSprite("explosion4");
		getEnemyList().add(new Enemy(new Point2D.Double(1000,200),20,5,"Shooter",Direction.Right));
		getEnemyList().add(new Enemy(new Point2D.Double(1150,220),20,9,"Shooter",Direction.Right));
		getEnemyList().add(new Enemy(new Point2D.Double(1200,320),20,13,"Shooter",Direction.Right));
		getEnemyList().add(new Enemy(new Point2D.Double(1020,520),20,19,"Shooter",Direction.Right));
		getEnemyList().add(new Enemy(new Point2D.Double(1040,620),20,7,"Shooter",Direction.Right));
		getEnemyList().add(new Enemy(new Point2D.Double(1080,560),20,12,"Shooter",Direction.Right));
        setMustRegisteredForGameLoop(true);
    }

    @Override
    public void Load() {
    }

    @Override
    public void AfterLoad() {
    }

    @Override
    public void UnLoad() {
    }

    @Override
    public void GameLoop(Graphics device) {
        repaint();
    }

    @Override
    public void Success() {
    }

    @Override
    public void Failed() {
    }

    @Override
    public void Reset() {
    }
}