/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejoDatos;

import java.awt.event.MouseEvent;

/**
 *
 * @author zassu
 */
public class dameDatos {
    
    private String Nombre;
    private int ancho;
    private int largo;

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
    private int coordX;
    private int coodY;
    private int pxgR;
    private int pxgG;
    private int pxgB;
    
    private java.awt.event.MouseEvent ev;

    public dameDatos(String Nombre, int coordX, int coodY, int pxgR, int pxgG, int an, int lar) {
        this.Nombre = Nombre;
        this.coordX = coordX;
        this.coodY = coodY;
        this.pxgR = pxgR;
        this.pxgG = pxgG;
        this.ancho=an;
        this.largo=lar;
    }
    
    public dameDatos (){
        
    }
    
    public dameDatos(dameDatos dat){
        this.setNombre(dat.getNombre());
        this.setAncho(dat.getAncho());
        this.setLargo(dat.getLargo());
        this.setCoordX(dat.getCoordX());
        this.setCoodY(dat.getCoodY());
        this.setPxgR(dat.getPxgR());
        this.setPxgG(dat.getPxgG());
        this.setPxgB(dat.getPxgB());
    }
    
    public void setEvt(java.awt.event.MouseEvent eb){
        ev=eb;
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
