package com.bau.cmpe.game.gui;

import com.bau.cmpe.game.scene.ISceneObject;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Bullet extends ShapeBase implements ISceneObject {
    private static final long serialVersionUID = 7241194035505641482L;
    private Ellipse2D.Double mShape;
    private double fRadius;

    public Bullet(Point2D initialPosition, double radius, Color shapeColor) {
        super(initialPosition);
        fRadius = radius;
        setShapeColor(shapeColor);
    }

    public void Draw(Graphics device) {
        if (!this.isVisible()) return;
        Graphics2D graphicDevice = (Graphics2D) device;
        mShape = new Ellipse2D.Double(getPosition().getX(), getPosition().getY(), fRadius, fRadius);
//        graphicDevice.setColor(Color.green);
//        graphicDevice.drawRect((int)mShape.getBounds2D().getX(),(int)mShape.getBounds2D().getY(),(int)mShape.getBounds2D().getWidth(),(int)mShape.getBounds2D().getHeight());
        graphicDevice.setColor(getShapeColor());
        graphicDevice.fill(getShape());
    }

    //	public Ellipse2D.Double getBoundaries()
//	{
//		return mShape;
//	}
    @Override
    public void GameLoop(Graphics device) {
        this.Draw(device);
    }

    @Override
    public void run() {
        this.Kill();
    }

    public double getRadius() {
        return fRadius;
    }

    public Ellipse2D.Double getShape() {
        return mShape;
    }
}