/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoDatos;

import java.awt.Image;
import java.awt.event.MouseEvent;

/**
 *
 * @author zassu
 */
public class dameDatos {

    private String Nombre = "N/A";
    private int ancho = 0;
    private int largo = 0;
    
    private Image imagen;

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
    
    

    private int[] red = new int[256];
    private int[] green = new int[256];
    private int[] blue = new int[256];

    public int[] getRed() {
        return red;
    }

    public void setRed(int[] red) {
        this.red = red;
    }

    public int getRed(int x) {
        return red[x];
    }

    public void setRed(int x) {
        this.red[x]++;
    }

    public int[] getGreen() {
        return green;
    }

    public void setGreen(int[] green) {
        this.green = green;
    }

    public int getGreen(int x) {
        return green[x];
    }

    public void setGreen(int x) {
        this.green[x] += 1;
    }

    public int[] getBlue() {
        return blue;
    }

    public void setBlue(int[] blue) {
        this.blue = blue;
    }

    public int getBlue(int x) {
        return blue[x];
    }

    public void setBlue(int x) {
        this.blue[x] += 1;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }
    private int coordX = 0;
    private int coodY = 0;
    private int pxgR = 0;
    private int pxgG = 0;
    private int pxgB = 0;

    private java.awt.event.MouseEvent ev;

    public dameDatos(String Nombre, int coordX, int coodY, int pxgR, int pxgG, int an, int lar) {
        this.Nombre = Nombre;
        this.coordX = coordX;
        this.coodY = coodY;
        this.pxgR = pxgR;
        this.pxgG = pxgG;
        this.ancho = an;
        this.largo = lar;
    }

    public dameDatos() {

    }

    public dameDatos(dameDatos dat) {
        this.setNombre(dat.getNombre());
        this.setAncho(dat.getAncho());
        this.setLargo(dat.getLargo());
        this.setCoordX(dat.getCoordX());
        this.setCoodY(dat.getCoodY());
        this.setPxgR(dat.getPxgR());
        this.setPxgG(dat.getPxgG());
        this.setPxgB(dat.getPxgB());
    }

    public void setEvt(java.awt.event.MouseEvent eb) {
        ev = eb;
    }

    public MouseEvent getEv() {
        return ev;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoodY() {
        return coodY;
    }

    public void setCoodY(int coodY) {
        this.coodY = coodY;
    }

    public int getPxgR() {
        return pxgR;
    }

    public void setPxgR(int pxgR) {
        this.pxgR = pxgR;
    }

    public int getPxgG() {
        return pxgG;
    }

    public void setPxgG(int pxgG) {
        this.pxgG = pxgG;
    }

    public int getPxgB() {
        return pxgB;
    }

    public void setPxgB(int pxgB) {
        this.pxgB = pxgB;
    }

}
