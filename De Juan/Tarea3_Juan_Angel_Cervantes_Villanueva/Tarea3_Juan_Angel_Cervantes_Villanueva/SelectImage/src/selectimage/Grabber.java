/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdi_proyecto4;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;

/**
 *
 * @author luisf
 */
public class Grabber {

    public void Grabber() {

    }

    public void handlesinglepixel(String ImageUrl, int x, int y, int w, int h) throws Exception {
        Image imagen = Toolkit.getDefaultToolkit().getImage(ImageUrl);

        PixelGrabber grabber = new PixelGrabber(imagen, x, y, w, h, false);
        
        if (grabber.grabPixels()) {
            Object pixels = grabber.getPixels();
            int red = ColorModel.getRGBdefault().getRed(pixels);
            int green = ColorModel.getRGBdefault().getGreen(pixels);
            int blue = ColorModel.getRGBdefault().getBlue(pixels);
            System.out.println("R:"+red+", G:"+green+", B:"+blue);
            System.out.println("X:"+x+", Y:"+y);
            
            
            
        }
    }

    static boolean isGreyscaleImage(PixelGrabber pg) {
        return pg.getPixels() instanceof byte[];
    }
}

