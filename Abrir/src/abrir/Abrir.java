/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abrir;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

/**
 *
 * @author 
 */
public class Abrir extends Frame {

    public Image imagen;
    int Ancho, largo;
    String cAncho, cLargo;

    public Abrir() { 
        this.setTitle("Aplicacion 1 PDI");
        this.setSize(800,600);
        this.setVisible(true);
            imagen=Toolkit.getDefaultToolkit().getImage("C:/");
            
            this.addWindowListener(
                new WindowAdapter(){
                public void windowClosing(WindowEvent evt){
                    System.exit(0);
                }
                });
    }
    public static void main(String[] args) {
        new Abrir();
    }
    
    public void paint(Graphics g){
    NumberFormat convertir = NumberFormat.getCurrencyInstance();
    
    largo = imagen.getHeight(this);
    Ancho = imagen.getWidth(this);
    g.drawImage(imagen, 10, 10, Ancho,largo, this);
    }
}