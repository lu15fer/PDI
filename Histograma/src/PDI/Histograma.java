/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDI;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author perez
 */

public class Histograma {
    private int calcularMedia(Color c){
        int mediaColor=(int)(c.getRed()+c.getGreen()+c.getBlue());
        return mediaColor;
    }
    
    public int [][] histograma(BufferedImage image){
        Color aux;
        int histogramaR[][]= new int[3][256];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                aux= new Color(image.getRGB(i, j));
                histogramaR[0][aux.getRed()]+=1;
                histogramaR[1][aux.getGreen()]+=1;
                histogramaR[2][aux.getBlue()]+=1;
            }
        }
        return histogramaR;
    }
}
