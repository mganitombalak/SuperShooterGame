package com.bau.cmpe.game.utility;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Utility {
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    public static float getAngle(Point start, Point end) {
        float angle = (float) Math.toDegrees(Math.atan2(end.y - start.y, end.x - start.x));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }
}
