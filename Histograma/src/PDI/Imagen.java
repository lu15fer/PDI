/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author perez
 */

/*

Adjunto en la carpeta principal, las librerias para poder hacer los histogramas,
no pude agregar los checkbox, pero espero poder agregarlo en alguno de los
siguientes programas, tampoco pude hacer que se visualicen en una sola grafica

*/
public class Imagen extends JFrame{
    public Image imagen;
    public Image tempImage;
    Dimension tamanoPant=Toolkit.getDefaultToolkit().getScreenSize();
    int anchoP=(int)tamanoPant.getWidth(), altoP=(int)tamanoPant.getHeight();
    int ancho=0, alto=0, extra=75;
    JFileChooser file= new JFileChooser();
    BufferedImage bimg;
    String str;
    int xcord=0,ycord=0;
    String rutaImagen="";
    int brilloNum=0,negativoNum=0;
    int matriz;
    Barra2 frame;
    String ext;
    boolean imgGuardada=false;
    String rutaGuardar;
    int[][] matriz3x3,matriz5x5,matriz7x7;
    
    public void main(){
        setImage();
        this.dispose();
        refresh();
        try {
            bimg = ImageIO.read(new File(cambiarTipoRuta(file.getSelectedFile().getPath())));
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        ancho=bimg.getWidth();
        alto=bimg.getHeight();
        if(ancho<anchoP/2){
            ancho=ancho*2;
            alto=alto*2;
        }else if(ancho>anchoP||alto>altoP){
            ancho=(int)(anchoP*0.75);
            alto=(int)(altoP*0.75);
        }else if(ancho==0){
            ancho=(int)(anchoP*.25);
            alto=(int)(altoP*.25);
        }
        this.setSize(ancho+100, alto+100);
        this.setVisible(true);
        this.setTitle("Tarea PDI2");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            }
        );
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if((e.getXOnScreen() >= extra && e.getXOnScreen() <= ancho+extra) && ((e.getYOnScreen() >= extra && e.getYOnScreen() <= alto+extra))){
                    xcord = e.getXOnScreen()-extra;
                    ycord = e.getYOnScreen()-extra;
                    if (tempImage==null) {
                        setPixelData(imagen, xcord, ycord, 1, 1);
                    }else{
                        setPixelData(tempImage, xcord, ycord, 1, 1);
                    }
                    setTitleFrame();
                }
            }
        });
        
        //this.add(abrir,BorderLayout.PAGE_END);
        menubar();
        this.setVisible(true); 
        this.repaint();
    }
    
    public void setImage(){
        imgGuardada=false;
        FileNameExtensionFilter filtro= new FileNameExtensionFilter("imagenes", "PNG","JPG","Jpge","BMP");
        file.setFileFilter(filtro);
        while(true){
        int res=file.showOpenDialog(this);
            if(res==0){
                String ruta=file.getSelectedFile().getPath();
                imagen=Toolkit.getDefaultToolkit().getImage(cambiarTipoRuta(ruta));
                break;
            }else{
                JOptionPane.showMessageDialog(this, "Error no se lecciono imagen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public String cambiarTipoRuta(String ruta){
        char[] rutaAr=ruta.toCharArray();
        String rutaCambio="";
           for (int i = 0; i < rutaAr.length; i++) {
               if(rutaAr[i]=='\\'){
                   rutaAr[i]='/';
               }
               rutaCambio+=rutaAr[i];
            }
           rutaImagen=rutaCambio;
        return rutaCambio;
    }
    
    @Override
    public void paint(Graphics g) {
        if (tempImage==null) {
            g.drawImage(imagen, extra, extra, ancho, alto, this);
            this.setTitle(str);
        }else{
            g.drawImage(tempImage, extra, extra, ancho, alto, this);
            this.setTitle(str);
        }
    }
  
    public void getPixelData(int x, int y, int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red   = (pixel >> 16) & 0xff;
        int green = (pixel >>  8) & 0xff;
        int blue  = (pixel      ) & 0xff;
        str =file.getSelectedFile().getName()+ " Rojo: " + red + " Verde: " + green + " Azul: " + blue + " Coordenada x: " + x + " Coordenada y: " + y;
    }

    public void setPixelData(Image img, int x, int y, int w, int h) {
        int[] pixels = new int[w * h];
        PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            System.err.println("interrupted waiting for pixels!");
            return;
        }
        if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
            System.err.println("image fetch aborted or errored");
            return;
        }
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                getPixelData(x+i, y+j, pixels[j * w + i]);
            }
        }
    }
    
    void setTitleFrame(){
        this.setTitle(str);
    }
    
    private void menubar(){
        setLayout(null);
        JMenuBar mb = new JMenuBar();
        JMenu menu1 = new JMenu("Archivo");
        JMenuItem abrirImagen= new JMenuItem("Abrir"),refresh=new JMenuItem("Recargar");
        menu1.add(abrirImagen);
        menu1.add(refresh);
        abrirImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main();
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                refresh();
            }
        });
        JMenuItem guardar, guardarComo;
        guardar= new JMenuItem("Guardar");
        guardarComo = new JMenuItem("Guardar Cómo");
        guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    saveImage();
                } catch (IOException ex) {
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu1.add(guardar);
        guardarComo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    saveImageAs();
                } catch (IOException ex) {
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu1.add(guardarComo);
        //-------------------------
        mb.add(menu1);
        JMenu menu2 = new JMenu("Histograma");
        JMenuItem Rojo, Verde, Azul, Tres;
        Rojo= new JMenuItem("Rojo");
        Verde= new JMenuItem("Verde");
        Azul= new JMenuItem("Azul");
        Tres=  new JMenuItem("Histogramas");
        menu2.add(Rojo);
        Rojo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pintarHistograma(0);
                } catch (IOException ex) {
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu2.add(Verde);
        Verde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pintarHistograma(1);
                } catch (IOException ex) {
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu2.add(Azul);
        Azul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pintarHistograma(2);
                } catch (IOException ex) {
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        menu2.add(Tres);
        Tres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pintarHistograma(3);
                } catch (IOException ex) {
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        mb.add(menu2);
        
        JMenu menu3 = new JMenu("Filtros");
        JMenuItem Brillo= new JMenuItem("Brillo"), Negativo= new JMenuItem("Negativo");
        
        menu3.add(Brillo);
        Brillo.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
                 try {
                     brillo();
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        }
);
        menu3.add(Negativo);
        Negativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    negativo();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        mb.add(menu3);
        //----------------------------------------------------------------------
        JMenu menu4= new JMenu("Filtros Digitales");
        mb.add(menu4);
        JMenuItem filtros;
        filtros= new JMenuItem("Filtros 3x3");
        menu4.add(filtros);
        
        filtros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarMatriz();
            }
        });
       //----------------------------------------------------------------------
       JMenuItem filtros5x5= new JMenuItem("Filtros 5x5");
       filtros5x5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aplicarMatriz5x5();
            }
        });
       JMenuItem filtros7x7= new JMenuItem("Filtros 7x7");
       filtros7x7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aplicarMatriz7x7();
            }
        });
       menu4.add(filtros5x5);
       menu4.add(filtros7x7);
       
       JMenuItem filtro3x3Per= new JMenuItem("Ingresar Filtro 3x3");
       filtro3x3Per.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aplicarMatriz(18);
            }
        });
       menu4.add(filtro3x3Per);
       //-----------------------------------------------------------------------
       JMenuItem filtro5x5Per= new JMenuItem("Ingresar filtro 5x5");
       filtro5x5Per.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aplicarMatriz(19);
            }
        });
       menu4.add(filtro5x5Per);
       //----------------------------------------------------------------------
       JMenuItem filtro7x7Per= new JMenuItem("Ingresar filtro 7x7");
       filtro7x7Per.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                aplicarMatriz(20);
            }
        });
       menu4.add(filtro7x7Per);
       //-----------------------------------------------------------------------
        this.setJMenuBar(mb);
        mb.setVisible(true);
        this.validate();
        this.repaint();
    }
    
    public void aplicarMatriz7x7(){
        JOptionPane jop= new JOptionPane("Selecionar filtro digital 7x7");
        JPanel panel= new JPanel();
        JCheckBox borde= new JCheckBox("Bordes");
        JCheckBox laplace= new JCheckBox("Lapace");
        JCheckBox SobelHz= new JCheckBox("Sobel Horizontal");
        JCheckBox SobelVt= new JCheckBox("Sobel Vertical");
        JCheckBox PrewittHz= new JCheckBox("Prewitt Horizontal");
        JCheckBox PrewittVt= new JCheckBox("Prewitt Vertical");
        
        borde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (borde.isSelected()) {
                    laplace.setSelected(false);
                    SobelHz.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(12);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(borde);
        
        laplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laplace.isSelected()) {
                    borde.setSelected(false);
                    SobelHz.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(13);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(laplace);
        
        SobelHz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (SobelHz.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(14);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(SobelHz);
        
        SobelVt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (SobelVt.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelHz.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(15);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(SobelVt);
        
        PrewittHz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PrewittHz.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    SobelHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(16);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(PrewittHz);
        
        PrewittVt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PrewittVt.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    SobelHz.setSelected(false);
                    refresh();
                    aplicarMatriz(17);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(PrewittVt);
        
        int res = jop.showConfirmDialog(null, panel,"Selecionar Filtro 7x7",JOptionPane.WARNING_MESSAGE);
        
        if(res==0){
            imagen=tempImage;
            tempImage=null;
        }else{
            tempImage=null;
        }
        this.repaint();
    }
    
    public void aplicarMatriz5x5(){
        JOptionPane jop= new JOptionPane("Selecionar filtro digital 5x5");
        JPanel panel= new JPanel();
        JCheckBox borde= new JCheckBox("Bordes");
        JCheckBox laplace= new JCheckBox("Lapace");
        JCheckBox SobelHz= new JCheckBox("Sobel Horizontal");
        JCheckBox SobelVt= new JCheckBox("Sobel Vertical");
        JCheckBox PrewittHz= new JCheckBox("Prewitt Horizontal");
        JCheckBox PrewittVt= new JCheckBox("Prewitt Vertical");
        
        borde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (borde.isSelected()) {
                    laplace.setSelected(false);
                    SobelHz.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(6); 
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(borde);
        
        laplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laplace.isSelected()) {
                    borde.setSelected(false);
                    SobelHz.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(7);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(laplace);
        
        SobelHz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (SobelHz.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(8);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(SobelHz);
        
        SobelVt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (SobelVt.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelHz.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(9);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(SobelVt);
        
        PrewittHz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PrewittHz.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    SobelHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(10);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(PrewittHz);
        
        PrewittVt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PrewittVt.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    SobelHz.setSelected(false);
                    refresh();
                    aplicarMatriz(11);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(PrewittVt);
        
        int res = jop.showConfirmDialog(null, panel,"Selecionar Filtro 5x5",JOptionPane.WARNING_MESSAGE);
        
        if(res==0){
            imagen=tempImage;
            tempImage=null;
        }else{
            tempImage=null;
        }
        this.repaint();
    }
    
    private void aplicarMatriz(){
        JOptionPane jop= new JOptionPane("Selecionar filtro digital 3x3");
        JPanel panel= new JPanel();
        JCheckBox borde= new JCheckBox("Bordes");
        JCheckBox laplace= new JCheckBox("Lapace");
        JCheckBox SobelHz= new JCheckBox("Sobel Horizontal");
        JCheckBox SobelVt= new JCheckBox("Sobel Vertical");
        JCheckBox PrewittHz= new JCheckBox("Prewitt Horizontal");
        JCheckBox PrewittVt= new JCheckBox("Prewitt Vertical");
        
        borde.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (borde.isSelected()) {
                    laplace.setSelected(false);
                    SobelHz.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(0);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(borde);
        
        laplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (laplace.isSelected()) {
                    borde.setSelected(false);
                    SobelHz.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(1);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(laplace);
        
        SobelHz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (SobelHz.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(2);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(SobelHz);
        
        SobelVt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (SobelVt.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelHz.setSelected(false);
                    PrewittHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(3);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(SobelVt);
        
        PrewittHz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PrewittHz.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    SobelHz.setSelected(false);
                    PrewittVt.setSelected(false);
                    refresh();
                    aplicarMatriz(4);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(PrewittHz);
        
        PrewittVt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (PrewittVt.isSelected()) {
                    laplace.setSelected(false);
                    borde.setSelected(false);
                    SobelVt.setSelected(false);
                    PrewittHz.setSelected(false);
                    SobelHz.setSelected(false);
                    refresh();
                    aplicarMatriz(5);
                }else{
                    tempImage=null;
                    repaint();
                }
            }
        });
        panel.add(PrewittVt);
        
        int res = jop.showConfirmDialog(null, panel,"Selecionar Filtro 3x3",JOptionPane.WARNING_MESSAGE);
        
        if(res==0){
            imagen=tempImage;
            tempImage=null;
        }else{
            tempImage=null;
        }
        this.repaint();
    }
    
    private void aplicarMatriz(int mat){
        switch(mat){
            case 0:
                int[][] borde ={{-1,-1,-1},
                                {-1, 8,-1},
                                {-1,-1,-1}};
                aplicarFiltro(borde);
            break;
            case 1:
                int[][] laplace={{ 0,-1, 0},
                                 {-1, 4,-1},
                                 { 0,-1, 0}};
                aplicarFiltro(laplace);
            break;
            case 2:
                int[][] sobelHz={{-1,-1,-1},
                                 { 0, 0, 0},
                                 { 1, 1, 1}};
                aplicarFiltro(sobelHz);
            break;
            case 3:
                int[][] sobelVt={{-1, 0, 1},
                                 {-1, 0, 1},
                                 {-1, 0, 1}};
                aplicarFiltro(sobelVt);
            break;
            case 4:
                int[][] prewittHz={{-1,-2,-1},
                                   { 0, 0, 0},
                                   { 1, 2, 1}};
                aplicarFiltro(prewittHz);
            break;
            case 5:
                int[][] prewittVt={ {-1, 0, 1},
                                    {-2, 0, 2},
                                    {-1, 0, 1}};
                aplicarFiltro(prewittVt);
            break;
            case 6:
                int[][] borde5x5={{-1,-1,-1,-1,-1},
                                  {-1,-1,-1,-1,-1},
                                  {-1,-1, 8,-1,-1},
                                  {-1,-1,-1,-1,-1},
                                  {-1,-1,-1,-1,-1}};
                aplicarFiltro5x5(borde5x5);
            break;
            case 7:
                int[][] laplace5x5={{ 0, 0,-1, 0, 0},
                                    { 0, 0,-1, 0, 0},
                                    {-1,-1, 4,-1,-1},
                                    { 0, 0,-1, 0, 0},
                                    { 0, 0,-1, 0, 0}};
                aplicarFiltro5x5(laplace5x5);
            break;
            case 8:
                int[][] sobelHz5x5={{-1,-1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1},
                                    { 0, 0, 0, 0, 0},
                                    { 1, 1, 1, 1, 1},
                                    { 1, 1, 1, 1, 1}};
                aplicarFiltro5x5(sobelHz5x5);
            break;
            case 9:
                int[][] sobelVt5x5={{-1,-1, 0, 1, 1},
                                    {-1,-1, 0, 1, 1},
                                    {-1,-1, 0, 1, 1},
                                    {-1,-1, 0, 1, 1},
                                    {-1,-1, 0, 1, 1}};
                aplicarFiltro5x5(sobelVt5x5);
            break;
            case 10:
                int[][] prewittHz5x5={{-1,-1,-2,-1,-1},
                                      {-1,-1,-2,-1,-1},
                                      { 0, 0, 0, 0, 0},
                                      { 1, 1, 2, 1, 1},
                                      { 1, 1, 2, 1, 1}};
                aplicarFiltro5x5(prewittHz5x5);
            break;
            case 11:
                int[][] prewittVt5x5={ {-1,-1, 0, 1, 1},
                                       {-1,-1, 0, 1, 1},
                                       {-2,-2, 0, 2, 2},
                                       {-1,-1, 0, 1, 1},
                                       {-1,-1, 0, 1, 1}};
                aplicarFiltro5x5(prewittVt5x5);
            break;
            case 12:
                int[][] borde7x7={{-1,-1,-1,-1,-1,-1,-1},
                                  {-1,-1,-1,-1,-1,-1,-1},
                                  {-1,-1,-1,-1,-1,-1,-1},
                                  {-1,-1,-1, 8,-1,-1,-1},
                                  {-1,-1,-1,-1,-1,-1,-1},
                                  {-1,-1,-1,-1,-1,-1,-1},
                                  {-1,-1,-1,-1,-1,-1,-1}};
                aplicarFiltro7x7(borde7x7);
            break;
            case 13:
                int[][] laplace7x7={{ 0, 0, 0,-1, 0, 0, 0},
                                    { 0, 0, 0,-1, 0, 0, 0},
                                    { 0, 0, 0,-1, 0, 0, 0},
                                    {-1,-1,-1, 4,-1,-1,-1},
                                    { 0, 0, 0,-1, 0, 0, 0},
                                    { 0, 0, 0,-1, 0, 0, 0},
                                    { 0, 0, 0,-1, 0, 0, 0}};
                aplicarFiltro7x7(laplace7x7);
            break;
            case 14:
                int[][] sobelHz7x7={{-1,-1,-1,-1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1,-1,-1},
                                    {-1,-1,-1,-1,-1,-1,-1},
                                    { 0, 0, 0, 0, 0, 0, 0},
                                    { 1, 1, 1, 1, 1, 1, 1},
                                    { 1, 1, 1, 1, 1, 1, 1},
                                    { 1, 1, 1, 1, 1, 1, 1}};
                aplicarFiltro7x7(sobelHz7x7);
            break;
            case 15:
                int[][] sobelVt7x7={{-1,-1,-1, 0, 1, 1, 1},
                                    {-1,-1,-1, 0, 1, 1, 1},
                                    {-1,-1,-1, 0, 1, 1, 1},
                                    {-1,-1,-1, 0, 1, 1, 1},
                                    {-1,-1,-1, 0, 1, 1, 1},
                                    {-1,-1,-1, 0, 1, 1, 1},
                                    {-1,-1,-1, 0, 1, 1, 1}};
                aplicarFiltro7x7(sobelVt7x7);
            break;
            case 16:
                int[][] prewittHz7x7={{-1,-1,-1,-2,-1,-1,-1},
                                      {-1,-1,-1,-2,-1,-1,-1},
                                      {-1,-1,-1,-2,-1,-1,-1},
                                      { 0, 0, 0, 0, 0, 0, 0},
                                      { 1, 1, 1, 2, 1, 1, 1},
                                      { 1, 1, 1, 2, 1, 1, 1},
                                      { 1, 1, 1, 2, 1, 1, 1}};
                aplicarFiltro7x7(prewittHz7x7);
            break;
            case 17:
                int[][] prewittVt7x7={ {-20,-20,-20, -20, -20, -20, -20},
                                       {-20,-20,-20,  25, -20, -20, -20},
                                       {-20,-20, 25,  25,  25, -20, -20},
                                       {-20, 25, 25,  25,  25,  25, -20},
                                       { 25, 25, 25,  25,  25,  25,  25},
                                       {-20,-20,-20, -20, -20, -20, -20},
                                       {-20,-20,-20, -20, -20, -20, -20}};
                aplicarFiltro7x7(prewittVt7x7);
            break;
            case 18:
                if(getMatrizPer3x3()==true){
                    aplicarFiltro(matriz3x3);
                }
            break;
            case 19:
                if(getMatrizPer5x5()==true){
                    aplicarFiltro(matriz5x5);
                }
            break;
            case 20:
                if(getMatrizPer7x7()==true){
                    aplicarFiltro(matriz7x7);
                }
            break;
        }
        this.repaint();
    }
    
    public void barra(int max){
        frame=new Barra2(max);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void contarBarra(){
        frame.oneMore();
    }
    
    public void eliminarBarra(){
        frame.eliminar();
    }
    
    private void aplicarFiltro5x5(int[][] matriz5x5){
        BufferedImage image = convertToBufferedImage(imagen);
        Color[][] temimage;
        int width = image.getWidth();
        int height = image.getHeight();
        temimage = new Color[width][height];
        
        barra(height-4);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                
                temimage[x][y] = new Color(r,g,b);
                image.setRGB(x, y, pixel);
            }
        }
        
        for (int y = 2; y < height-2; y++) {
            contarBarra();
            for (int x = 2; x < width-2; x++) {
                int pxR=(matriz5x5[0][0]*temimage[x-2][y-2].getRed() + matriz5x5[0][1]*temimage[x-1][y-2].getRed() + matriz5x5[0][2]*temimage[x][y-2].getRed() + matriz5x5[0][3]*temimage[x+1][y-2].getRed() + matriz5x5[0][4]*temimage[x+2][y-2].getRed()+
                         matriz5x5[1][0]*temimage[x-2][y-1].getRed() + matriz5x5[1][1]*temimage[x-1][y-1].getRed() + matriz5x5[1][2]*temimage[x][y-1].getRed() + matriz5x5[1][3]*temimage[x+1][y-1].getRed() + matriz5x5[1][4]*temimage[x+2][y-1].getRed()+ 
                         matriz5x5[2][0]*temimage[x-2][y].getRed()   + matriz5x5[2][1]*temimage[x-1][y].getRed()   + matriz5x5[2][2]*temimage[x][y].getRed()   + matriz5x5[2][3]*temimage[x+1][y].getRed()   + matriz5x5[2][4]*temimage[x+2][y].getRed()  + 
                         matriz5x5[3][0]*temimage[x-2][y+1].getRed() + matriz5x5[3][1]*temimage[x-1][y+1].getRed() + matriz5x5[3][2]*temimage[x][y+1].getRed() + matriz5x5[3][3]*temimage[x+1][y+1].getRed() + matriz5x5[3][4]*temimage[x+2][y+1].getRed()+ 
                         matriz5x5[4][0]*temimage[x-2][y+2].getRed() + matriz5x5[4][1]*temimage[x-1][y+2].getRed() + matriz5x5[4][2]*temimage[x][y+2].getRed() + matriz5x5[4][3]*temimage[x+1][y+2].getRed() + matriz5x5[4][4]*temimage[x+2][y+2].getRed());
                pxR=(int)Math.sqrt(pxR*pxR);
                if (pxR>255) pxR=255;
                if (pxR<0)   pxR=0;
                
                int pxG=(matriz5x5[0][0]*temimage[x-2][y-2].getGreen() + matriz5x5[0][1]*temimage[x-1][y-2].getGreen() + matriz5x5[0][2]*temimage[x][y-2].getGreen() + matriz5x5[0][3]*temimage[x+1][y-2].getGreen() + matriz5x5[0][4]*temimage[x+2][y-2].getGreen()+
                         matriz5x5[1][0]*temimage[x-2][y-1].getGreen() + matriz5x5[1][1]*temimage[x-1][y-1].getGreen() + matriz5x5[1][2]*temimage[x][y-1].getGreen() + matriz5x5[1][3]*temimage[x+1][y-1].getGreen() + matriz5x5[1][4]*temimage[x+2][y-1].getGreen()+ 
                         matriz5x5[2][0]*temimage[x-2][y].getGreen()   + matriz5x5[2][1]*temimage[x-1][y].getGreen()   + matriz5x5[2][2]*temimage[x][y].getGreen()   + matriz5x5[2][3]*temimage[x+1][y].getGreen()   + matriz5x5[2][4]*temimage[x+2][y].getGreen()  + 
                         matriz5x5[3][0]*temimage[x-2][y+1].getGreen() + matriz5x5[3][1]*temimage[x-1][y+1].getGreen() + matriz5x5[3][2]*temimage[x][y+1].getGreen() + matriz5x5[3][3]*temimage[x+1][y+1].getGreen() + matriz5x5[3][4]*temimage[x+2][y+1].getGreen()+ 
                         matriz5x5[4][0]*temimage[x-2][y+2].getGreen() + matriz5x5[4][1]*temimage[x-1][y+2].getGreen() + matriz5x5[4][2]*temimage[x][y+2].getGreen() + matriz5x5[4][3]*temimage[x+1][y+2].getGreen() + matriz5x5[4][4]*temimage[x+2][y+2].getGreen());
                pxG=(int)Math.sqrt(pxG*pxG);
                if (pxG>255) pxG=255;
                if (pxG<0)   pxG=0;
                
                int pxB=(matriz5x5[0][0]*temimage[x-2][y-2].getBlue() + matriz5x5[0][1]*temimage[x-1][y-2].getBlue() + matriz5x5[0][2]*temimage[x][y-2].getBlue() + matriz5x5[0][3]*temimage[x+1][y-2].getBlue() + matriz5x5[0][4]*temimage[x+2][y-2].getBlue()+
                         matriz5x5[1][0]*temimage[x-2][y-1].getBlue() + matriz5x5[1][1]*temimage[x-1][y-1].getBlue() + matriz5x5[1][2]*temimage[x][y-1].getBlue() + matriz5x5[1][3]*temimage[x+1][y-1].getBlue() + matriz5x5[1][4]*temimage[x+2][y-1].getBlue()+ 
                         matriz5x5[2][0]*temimage[x-2][y].getBlue()   + matriz5x5[2][1]*temimage[x-1][y].getBlue()   + matriz5x5[2][2]*temimage[x][y].getBlue()   + matriz5x5[2][3]*temimage[x+1][y].getBlue()   + matriz5x5[2][4]*temimage[x+2][y].getBlue()  + 
                         matriz5x5[3][0]*temimage[x-2][y+1].getBlue() + matriz5x5[3][1]*temimage[x-1][y+1].getBlue() + matriz5x5[3][2]*temimage[x][y+1].getBlue() + matriz5x5[3][3]*temimage[x+1][y+1].getBlue() + matriz5x5[3][4]*temimage[x+2][y+1].getBlue()+ 
                         matriz5x5[4][0]*temimage[x-2][y+2].getBlue() + matriz5x5[4][1]*temimage[x-1][y+2].getBlue() + matriz5x5[4][2]*temimage[x][y+2].getBlue() + matriz5x5[4][3]*temimage[x+1][y+2].getBlue() + matriz5x5[4][4]*temimage[x+2][y+2].getBlue());
                pxB=(int)Math.sqrt(pxB*pxB);
                if (pxB>255) pxB=255;
                if (pxB<0)   pxB=0;
                Color pixel= new Color(pxR, pxG, pxB);
                image.setRGB(x, y, pixel.getRGB());
            }
        }
        tempImage=(Image)image;
        
        eliminarBarra();
        this.repaint();
    }
    
    private void aplicarFiltro7x7(int[][] matriz7x7){
        BufferedImage image = convertToBufferedImage(imagen);
        Color[][] temimage;
        int width = image.getWidth();
        int height = image.getHeight();
        temimage = new Color[width][height];
        
        barra(height-6);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                
                temimage[x][y] = new Color(r,g,b);
                image.setRGB(x, y, pixel);
            }
        }
        
        for (int y = 3; y < height-3; y++) {
            contarBarra();
            for (int x = 3; x < width-3; x++) {
                int pxR=(matriz7x7[0][0]*temimage[x-3][y-3].getRed() + matriz7x7[0][1]*temimage[x-2][y-3].getRed() + matriz7x7[0][2]*temimage[x-1][y-3].getRed() + matriz7x7[0][3]*temimage[x][y-3].getRed() + matriz7x7[0][4]*temimage[x+1][y-3].getRed() + matriz7x7[0][5]*temimage[x+2][y-3].getRed() + matriz7x7[0][6]*temimage[x+3][y-3].getRed()+
                         matriz7x7[1][0]*temimage[x-3][y-2].getRed() + matriz7x7[1][1]*temimage[x-2][y-2].getRed() + matriz7x7[1][2]*temimage[x-1][y-2].getRed() + matriz7x7[1][3]*temimage[x][y-2].getRed() + matriz7x7[1][4]*temimage[x+1][y-2].getRed() + matriz7x7[1][5]*temimage[x+2][y-2].getRed() + matriz7x7[1][6]*temimage[x+3][y-2].getRed()+
                         matriz7x7[2][0]*temimage[x-3][y-1].getRed() + matriz7x7[2][1]*temimage[x-2][y-1].getRed() + matriz7x7[2][2]*temimage[x-1][y-1].getRed() + matriz7x7[2][3]*temimage[x][y-1].getRed() + matriz7x7[2][4]*temimage[x+1][y-1].getRed() + matriz7x7[2][5]*temimage[x+2][y-1].getRed() + matriz7x7[2][6]*temimage[x+3][y-1].getRed()+
                         matriz7x7[3][0]*temimage[x-3][y  ].getRed() + matriz7x7[3][1]*temimage[x-2][y  ].getRed() + matriz7x7[3][2]*temimage[x-1][y  ].getRed() + matriz7x7[3][3]*temimage[x][y  ].getRed() + matriz7x7[3][4]*temimage[x+1][y  ].getRed() + matriz7x7[3][5]*temimage[x+2][y  ].getRed() + matriz7x7[3][6]*temimage[x+3][y  ].getRed()+
                         matriz7x7[4][0]*temimage[x-3][y+1].getRed() + matriz7x7[4][1]*temimage[x-2][y+1].getRed() + matriz7x7[4][2]*temimage[x-1][y+1].getRed() + matriz7x7[4][3]*temimage[x][y+1].getRed() + matriz7x7[4][4]*temimage[x+1][y+1].getRed() + matriz7x7[4][5]*temimage[x+2][y+1].getRed() + matriz7x7[4][6]*temimage[x+3][y+1].getRed()+
                         matriz7x7[5][0]*temimage[x-3][y+2].getRed() + matriz7x7[5][1]*temimage[x-2][y+2].getRed() + matriz7x7[5][2]*temimage[x-1][y+2].getRed() + matriz7x7[5][3]*temimage[x][y+2].getRed() + matriz7x7[5][4]*temimage[x+1][y+2].getRed() + matriz7x7[5][5]*temimage[x+2][y+2].getRed() + matriz7x7[5][6]*temimage[x+3][y+2].getRed()+
                         matriz7x7[6][0]*temimage[x-3][y+3].getRed() + matriz7x7[6][1]*temimage[x-2][y+3].getRed() + matriz7x7[6][2]*temimage[x-1][y+3].getRed() + matriz7x7[6][3]*temimage[x][y+3].getRed() + matriz7x7[6][4]*temimage[x+1][y+3].getRed() + matriz7x7[6][5]*temimage[x+2][y+3].getRed() + matriz7x7[6][6]*temimage[x+3][y+3].getRed());
                pxR=(int)Math.sqrt(pxR*pxR);
                if (pxR>255) pxR=255;
                if (pxR<0)   pxR=0;
                
                int pxG=(matriz7x7[0][0]*temimage[x-3][y-3].getGreen() + matriz7x7[0][1]*temimage[x-2][y-3].getGreen() + matriz7x7[0][2]*temimage[x-1][y-3].getGreen() + matriz7x7[0][3]*temimage[x][y-3].getGreen() + matriz7x7[0][4]*temimage[x+1][y-3].getGreen() + matriz7x7[0][5]*temimage[x+2][y-3].getGreen() + matriz7x7[0][6]*temimage[x+3][y-3].getGreen()+
                         matriz7x7[1][0]*temimage[x-3][y-2].getGreen() + matriz7x7[1][1]*temimage[x-2][y-2].getGreen() + matriz7x7[1][2]*temimage[x-1][y-2].getGreen() + matriz7x7[1][3]*temimage[x][y-2].getGreen() + matriz7x7[1][4]*temimage[x+1][y-2].getGreen() + matriz7x7[1][5]*temimage[x+2][y-2].getGreen() + matriz7x7[1][6]*temimage[x+3][y-2].getGreen()+
                         matriz7x7[2][0]*temimage[x-3][y-1].getGreen() + matriz7x7[2][1]*temimage[x-2][y-1].getGreen() + matriz7x7[2][2]*temimage[x-1][y-1].getGreen() + matriz7x7[2][3]*temimage[x][y-1].getGreen() + matriz7x7[2][4]*temimage[x+1][y-1].getGreen() + matriz7x7[2][5]*temimage[x+2][y-1].getGreen() + matriz7x7[2][6]*temimage[x+3][y-1].getGreen()+
                         matriz7x7[3][0]*temimage[x-3][y  ].getGreen() + matriz7x7[3][1]*temimage[x-2][y  ].getGreen() + matriz7x7[3][2]*temimage[x-1][y  ].getGreen() + matriz7x7[3][3]*temimage[x][y  ].getGreen() + matriz7x7[3][4]*temimage[x+1][y  ].getGreen() + matriz7x7[3][5]*temimage[x+2][y  ].getGreen() + matriz7x7[3][6]*temimage[x+3][y  ].getGreen()+
                         matriz7x7[4][0]*temimage[x-3][y+1].getGreen() + matriz7x7[4][1]*temimage[x-2][y+1].getGreen() + matriz7x7[4][2]*temimage[x-1][y+1].getGreen() + matriz7x7[4][3]*temimage[x][y+1].getGreen() + matriz7x7[4][4]*temimage[x+1][y+1].getGreen() + matriz7x7[4][5]*temimage[x+2][y+1].getGreen() + matriz7x7[4][6]*temimage[x+3][y+1].getGreen()+
                         matriz7x7[5][0]*temimage[x-3][y+2].getGreen() + matriz7x7[5][1]*temimage[x-2][y+2].getGreen() + matriz7x7[5][2]*temimage[x-1][y+2].getGreen() + matriz7x7[5][3]*temimage[x][y+2].getGreen() + matriz7x7[5][4]*temimage[x+1][y+2].getGreen() + matriz7x7[5][5]*temimage[x+2][y+2].getGreen() + matriz7x7[5][6]*temimage[x+3][y+2].getGreen()+
                         matriz7x7[6][0]*temimage[x-3][y+3].getGreen() + matriz7x7[6][1]*temimage[x-2][y+3].getGreen() + matriz7x7[6][2]*temimage[x-1][y+3].getGreen() + matriz7x7[6][3]*temimage[x][y+3].getGreen() + matriz7x7[6][4]*temimage[x+1][y+3].getGreen() + matriz7x7[6][5]*temimage[x+2][y+3].getGreen() + matriz7x7[6][6]*temimage[x+3][y+3].getGreen());
                pxG=(int)Math.sqrt(pxG*pxG);
                if (pxG>255) pxG=255;
                if (pxG<0)   pxG=0;
                
                int pxB=(matriz7x7[0][0]*temimage[x-3][y-3].getBlue() + matriz7x7[0][1]*temimage[x-2][y-3].getBlue() + matriz7x7[0][2]*temimage[x-1][y-3].getBlue() + matriz7x7[0][3]*temimage[x][y-3].getBlue() + matriz7x7[0][4]*temimage[x+1][y-3].getBlue() + matriz7x7[0][5]*temimage[x+2][y-3].getBlue() + matriz7x7[0][6]*temimage[x+3][y-3].getBlue()+
                         matriz7x7[1][0]*temimage[x-3][y-2].getBlue() + matriz7x7[1][1]*temimage[x-2][y-2].getBlue() + matriz7x7[1][2]*temimage[x-1][y-2].getBlue() + matriz7x7[1][3]*temimage[x][y-2].getBlue() + matriz7x7[1][4]*temimage[x+1][y-2].getBlue() + matriz7x7[1][5]*temimage[x+2][y-2].getBlue() + matriz7x7[1][6]*temimage[x+3][y-2].getBlue()+
                         matriz7x7[2][0]*temimage[x-3][y-1].getBlue() + matriz7x7[2][1]*temimage[x-2][y-1].getBlue() + matriz7x7[2][2]*temimage[x-1][y-1].getBlue() + matriz7x7[2][3]*temimage[x][y-1].getBlue() + matriz7x7[2][4]*temimage[x+1][y-1].getBlue() + matriz7x7[2][5]*temimage[x+2][y-1].getBlue() + matriz7x7[2][6]*temimage[x+3][y-1].getBlue()+
                         matriz7x7[3][0]*temimage[x-3][y  ].getBlue() + matriz7x7[3][1]*temimage[x-2][y  ].getBlue() + matriz7x7[3][2]*temimage[x-1][y  ].getBlue() + matriz7x7[3][3]*temimage[x][y  ].getBlue() + matriz7x7[3][4]*temimage[x+1][y  ].getBlue() + matriz7x7[3][5]*temimage[x+2][y  ].getBlue() + matriz7x7[3][6]*temimage[x+3][y  ].getBlue()+
                         matriz7x7[4][0]*temimage[x-3][y+1].getBlue() + matriz7x7[4][1]*temimage[x-2][y+1].getBlue() + matriz7x7[4][2]*temimage[x-1][y+1].getBlue() + matriz7x7[4][3]*temimage[x][y+1].getBlue() + matriz7x7[4][4]*temimage[x+1][y+1].getBlue() + matriz7x7[4][5]*temimage[x+2][y+1].getBlue() + matriz7x7[4][6]*temimage[x+3][y+1].getBlue()+
                         matriz7x7[5][0]*temimage[x-3][y+2].getBlue() + matriz7x7[5][1]*temimage[x-2][y+2].getBlue() + matriz7x7[5][2]*temimage[x-1][y+2].getBlue() + matriz7x7[5][3]*temimage[x][y+2].getBlue() + matriz7x7[5][4]*temimage[x+1][y+2].getBlue() + matriz7x7[5][5]*temimage[x+2][y+2].getBlue() + matriz7x7[5][6]*temimage[x+3][y+2].getBlue()+
                         matriz7x7[6][0]*temimage[x-3][y+3].getBlue() + matriz7x7[6][1]*temimage[x-2][y+3].getBlue() + matriz7x7[6][2]*temimage[x-1][y+3].getBlue() + matriz7x7[6][3]*temimage[x][y+3].getBlue() + matriz7x7[6][4]*temimage[x+1][y+3].getBlue() + matriz7x7[6][5]*temimage[x+2][y+3].getBlue() + matriz7x7[6][6]*temimage[x+3][y+3].getRed());
                pxB=(int)Math.sqrt(pxB*pxB);
                if (pxB>255) pxB=255;
                if (pxB<0)   pxB=0;
                Color pixel= new Color(pxR, pxG, pxB);
                image.setRGB(x, y, pixel.getRGB());
            }
        }
        tempImage=(Image)image;
        
        eliminarBarra();
        this.repaint();
    }
    
    private void aplicarFiltro(int[][] matriz){
        BufferedImage image = convertToBufferedImage(imagen);
        Color[][] temimage;
        int width = image.getWidth();
        int height = image.getHeight();
        temimage = new Color[width][height];
              
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;
                
                temimage[x][y] = new Color(r,g,b);
                image.setRGB(x, y, pixel);
            }
        }
        barra(height-2);
        
        for (int y = 1; y < height-1; y++) {
            contarBarra();
            for (int x = 1; x < width-1; x++) {
                int pxR=(matriz[0][0]*temimage[x-1][y-1].getRed() + matriz[0][1]*temimage[x][y-1].getRed() + matriz[0][2]*temimage[x+1][y-1].getRed() +
                         matriz[1][0]*temimage[x-1][y].getRed()   + matriz[1][1]*temimage[x][y].getRed()   + matriz[1][2]*temimage[x+1][y].getRed() + 
                         matriz[2][0]*temimage[x-1][y+1].getRed() + matriz[2][1]*temimage[x][y+1].getRed() + matriz[2][2]*temimage[x+1][y+1].getRed());
                pxR=(int)Math.sqrt(pxR*pxR);
                if (pxR>255) pxR=255;
                if (pxR<0)   pxR=0;
                
                int pxG=(matriz[0][0]*temimage[x-1][y-1].getGreen() + matriz[0][1]*temimage[x][y-1].getGreen() + matriz[0][2]*temimage[x+1][y-1].getGreen() +
                         matriz[1][0]*temimage[x-1][y].getGreen()   + matriz[1][1]*temimage[x][y].getGreen()   + matriz[1][2]*temimage[x+1][y].getGreen() + 
                         matriz[2][0]*temimage[x-1][y+1].getGreen() + matriz[2][1]*temimage[x][y+1].getGreen() + matriz[2][2]*temimage[x+1][y+1].getGreen());
                pxG=(int)Math.sqrt(pxG*pxG);
                if (pxG>255) pxG=255;
                if (pxG<0)   pxG=0;
                
                int pxB=(matriz[0][0]*temimage[x-1][y-1].getBlue() + matriz[0][1]*temimage[x][y-1].getBlue() + matriz[0][2]*temimage[x+1][y-1].getBlue() +
                         matriz[1][0]*temimage[x-1][y].getBlue()   + matriz[1][1]*temimage[x][y].getBlue()   + matriz[1][2]*temimage[x+1][y].getBlue() + 
                         matriz[2][0]*temimage[x-1][y+1].getBlue() + matriz[2][1]*temimage[x][y+1].getBlue() + matriz[2][2]*temimage[x+1][y+1].getBlue());
                pxB=(int)Math.sqrt(pxB*pxB);
                if (pxB>255) pxB=255;
                if (pxB<0)   pxB=0;
                Color pixel= new Color(pxR, pxG, pxB);
                image.setRGB(x, y, pixel.getRGB());
            }
        }
        tempImage=(Image)image;
        
        eliminarBarra();
        this.repaint();
    }
    
    private void refresh(){
        String ruta=file.getSelectedFile().getPath();
        imagen=Toolkit.getDefaultToolkit().getImage(cambiarTipoRuta(ruta));
        tempImage=null;
        brilloNum=0;
        negativoNum=0;

        repaint();
    }
    
    private int getPixelCoord(BufferedImage bi, int x, int y, char canal){
        int valor=0;
        int pixel= bi.getRGB(x, y);
        switch (canal){
            case 'r':
                valor = (pixel >> 16) & 0xff;
                break;
            case 'g':
                valor = (pixel >> 8) & 0xff;
                break;
            case 'b':
                valor = pixel & 0xff;
                break;
            default:
                break;
        }
        return valor;
    }
    
    private void pintarHistograma(int i) throws IOException{
        JPanel jPanel= new JPanel();
        JPanel jPanel1= new JPanel();
        JPanel jPanel2= new JPanel();
        JPanel jPanel3= new JPanel();
        Histograma h= new Histograma();
        BufferedImage buffer;
        if(tempImage==null){
            buffer= convertToBufferedImage(imagen);
        }else{
            buffer= convertToBufferedImage(tempImage);
        }
            int[][] histograma=h.histograma(buffer);
        JOptionPane optionPane= new JOptionPane("Histograma");
        grafica g= new grafica();
        switch(i){
            case 0:
                int[] histogramaRed= new int[256];
                System.arraycopy(histograma[0], 0, histogramaRed, 0, histograma[0].length);
                g.crearHistograma3D(histogramaRed, jPanel, Color.RED, "Rojo");
                optionPane.showMessageDialog(null, jPanel,"Histograma",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Pintando histograma rojo");
            break;
            case 1:
                int[] histogramaGreen= new int[256];
                System.arraycopy(histograma[0], 0, histogramaGreen, 0, histograma[0].length);
                g.crearHistograma3D(histogramaGreen, jPanel, Color.GREEN, "Verde");
                optionPane.showMessageDialog(null, jPanel,"Histograma",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Pintando histograma verde");
            break;
            case 2:
                int[] histogramaBlue= new int[256];
                System.arraycopy(histograma[0], 0, histogramaBlue, 0, histograma[0].length);
                g.crearHistograma3D(histogramaBlue, jPanel, Color.BLUE, "Azul");
                optionPane.showMessageDialog(null, jPanel,"Histograma",JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Pintando histograma azul");
            break;
            case 3:
                for (int j = 0; j < 3; j++) {
                    int[] histogramaCanal= new int[256];
                System.arraycopy(histograma[j], 0, histogramaCanal, 0, histograma[j].length);
                    switch(j){
                        case 0:
                            g.crearHistograma3D(histogramaCanal, jPanel1, Color.RED, "Rojo");
                        break;
                        case 1:
                            g.crearHistograma3D(histogramaCanal, jPanel2, Color.GREEN, "Verde");
                        break;
                        case 2:
                            g.crearHistograma3D(histogramaCanal, jPanel3, Color.BLUE, "Azul");
                        break;
                    }
                }
                jPanel.add(jPanel1);
                jPanel.add(jPanel2);
                jPanel.add(jPanel3);
                
                optionPane.showMessageDialog(null, jPanel,"Histograma",JOptionPane.INFORMATION_MESSAGE);
            break;
        }
        this.repaint();
    }
    
    public BufferedImage convertToBufferedImage(Image image){
        BufferedImage newImage = new BufferedImage(
            image.getWidth(null), image.getHeight(null),
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }
    
    private void brillo() throws InterruptedException{
        JPanel panel= new JPanel();
        JOptionPane jOptionPane= new JOptionPane((LayoutManager) new FlowLayout());
        JSlider brillo= new JSlider();
        brillo.setMaximum(100);
        brillo.setMinimum(0);
        brillo.setPaintLabels(true);
        brillo.setPaintTicks(true);
        brillo.setMajorTickSpacing(10);
        brillo.setValue(0);
        JLabel label= new JLabel("Brillo:    0%");
        panel.add(label,BorderLayout.NORTH);
        panel.add(brillo,BorderLayout.SOUTH);
        brillo.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent ce) {
                    label.setText("Brillo: "+brillo.getValue()+"%\n");
                    brilloNum=(int)brillo.getValue();
                    int escala=(int)(brilloNum*255/100);
                    try {
                        cambiarBrillo(escala);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
        
        //jOptionPane.showMessageDialog(null, panel,"Selector de brillo",JOptionPane.WARNING_MESSAGE);
        int res=jOptionPane.showConfirmDialog(null, panel,"Selector de brillo",JOptionPane.WARNING_MESSAGE);
        if(res==0){
            int escala=(int)(brilloNum*255/100);
            cambiarBrillo(escala,true);
        }else{
            brilloNum=0;
            tempImage=null;
            repaint();
        }
    }
    
    private void cambiarBrillo(int intencidad) throws InterruptedException{
        int p, rojo, verde, azul;
        int pixeles[]= new int[(int)bimg.getHeight()*bimg.getWidth()];
        PixelGrabber grabber= new PixelGrabber(imagen, 0, 0, (int)bimg.getWidth(), (int)bimg.getHeight(), pixeles,0,(int)bimg.getWidth());
        grabber.grabPixels();
        for (int i = 0; i < pixeles.length; i++) {
            p=pixeles[i];
            rojo  = (0xff & (p>>16)) + intencidad;
            verde = (0xff & (p>>8))  + intencidad;
            azul  = (0xff & p)       + intencidad;
            if(rojo >255) rojo =255;
            if(verde>255) verde=255;
            if(azul >255) azul =255;
            pixeles[i]=(0xff000000|rojo<<16|verde<<8|azul);
        }
        tempImage= createImage(new MemoryImageSource((int)bimg.getWidth(), (int)bimg.getHeight(), pixeles, 0, (int)bimg.getWidth()));
        this.repaint();
    }
    
    private void cambiarBrillo(int intencidad,boolean b) throws InterruptedException{
        int p, rojo, verde, azul;
        int pixeles[]= new int[(int)bimg.getHeight()*bimg.getWidth()];
        PixelGrabber grabber= new PixelGrabber(imagen, 0, 0, (int)bimg.getWidth(), (int)bimg.getHeight(), pixeles,0,(int)bimg.getWidth());
        grabber.grabPixels();
        for (int i = 0; i < pixeles.length; i++) {
            p=pixeles[i];
            rojo  = (0xff & (p>>16)) + intencidad;
            verde = (0xff & (p>>8))  + intencidad;
            azul  = (0xff & p)       + intencidad;
            if(rojo >255) rojo =255;
            if(verde>255) verde=255;
            if(azul >255) azul =255;
            pixeles[i]=(0xff000000|rojo<<16|verde<<8|azul);
        }
        imagen= createImage(new MemoryImageSource((int)bimg.getWidth(), (int)bimg.getHeight(), pixeles, 0, (int)bimg.getWidth()));
        this.repaint();
    }
    
    private void negativo() throws InterruptedException{
        JPanel panel= new JPanel();
        JOptionPane jOptionPane= new JOptionPane((LayoutManager) new FlowLayout());
        JSlider negativo= new JSlider();
        negativo.setMaximum(100);
        negativo.setMinimum(0);
        negativo.setPaintLabels(true);
        negativo.setPaintTicks(true);
        negativo.setMajorTickSpacing(10);
        negativo.setValue(0);
        JLabel label= new JLabel("Negativo:    0%");
        panel.add(label,BorderLayout.NORTH);
        panel.add(negativo,BorderLayout.SOUTH);
        negativo.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent ce) {
                    label.setText("Negativo: "+negativo.getValue()+"%\n");
                    negativoNum=(int)negativo.getValue();
                    int escala=(int)(negativoNum*255/100);
                    try {
                        cambiarNegativo(escala);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
        
        //jOptionPane.showMessageDialog(null, panel,"Selector de negativo",JOptionPane.WARNING_MESSAGE);
        int res=jOptionPane.showConfirmDialog(null, panel,"Selector de negativo",JOptionPane.WARNING_MESSAGE);
        if(res==0){
            int escala=(int)(negativoNum*255/100);
            cambiarNegativo(escala,true);
        }else{
            negativoNum=0;
            tempImage=null;
            repaint();
        }
    }
    
    private void cambiarNegativo(int intencidad) throws InterruptedException{
        int p, rojo, verde, azul;
        int pixeles[]= new int[(int)bimg.getHeight()*bimg.getWidth()];
        PixelGrabber grabber= new PixelGrabber(imagen, 0, 0, (int)bimg.getWidth(), (int)bimg.getHeight(), pixeles,0,(int)bimg.getWidth());
        grabber.grabPixels();
        for (int i = 0; i < pixeles.length; i++) {
            p=pixeles[i];
            rojo  = -(0xff & (p>>16)) + intencidad;
            verde = -(0xff & (p>>8))  + intencidad;
            azul  = -(0xff & p)       + intencidad;
            if(rojo <0) rojo =0;
            if(verde<0) verde=0;
            if(azul <0) azul =0;
            pixeles[i]=(0xff000000|rojo<<16|verde<<8|azul);
        }
        tempImage= createImage(new MemoryImageSource((int)bimg.getWidth(), (int)bimg.getHeight(), pixeles, 0, (int)bimg.getWidth()));
        this.repaint();
    }
    
    private void cambiarNegativo(int intencidad,boolean b) throws InterruptedException{
        int p, rojo, verde, azul;
        int pixeles[]= new int[(int)bimg.getHeight()*bimg.getWidth()];
        PixelGrabber grabber= new PixelGrabber(imagen, 0, 0, (int)bimg.getWidth(), (int)bimg.getHeight(), pixeles,0,(int)bimg.getWidth());
        grabber.grabPixels();
        for (int i = 0; i < pixeles.length; i++) {
            p=pixeles[i];
            rojo  = -(0xff & (p>>16)) + intencidad;
            verde = -(0xff & (p>>8))  + intencidad;
            azul  = -(0xff & p)       + intencidad;
            if(rojo <0) rojo =0;
            if(verde<0) verde=0;
            if(azul <0) azul =0;
            pixeles[i]=(0xff000000|rojo<<16|verde<<8|azul);
        }
        imagen= createImage(new MemoryImageSource((int)bimg.getWidth(), (int)bimg.getHeight(), pixeles, 0, (int)bimg.getWidth()));
        this.repaint();
    }
    
    public void saveImageAs() throws IOException{
        if(tempImage!=null){
            imagen=tempImage;
            tempImage=null;
        }
        JFileChooser jfc= new JFileChooser("Guardar Cómo");
        int res=jfc.showSaveDialog(this);
        getExtensionFile(file.getSelectedFile().getName());
        if(res==0){
            rutaGuardar=cambiarTipoRuta(jfc.getSelectedFile().getPath()+"."+ext);
            File newFile= new File(rutaGuardar);
            ImageIO.write(convertToBufferedImage(imagen), ext, newFile);
        }else{
            JOptionPane.showMessageDialog(this, "No se guardo imagen", "Error", JOptionPane.ERROR_MESSAGE);
        }
        imgGuardada=true;
    }
    
    public void saveImage() throws IOException{
        if(tempImage!=null){
            imagen=tempImage;
            tempImage=null;
        }
        if(imgGuardada==false){
            saveImageAs();
        }else{
            File newFile= new File(rutaGuardar);
            ImageIO.write(convertToBufferedImage(imagen), ext, newFile);
        }
    }
    
    public String getExtensionFile(String name){
        String extension="";
        char[] cs= name.toCharArray();
        int cont=0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i]=='.') {
                 cont++;
            }
        }
        
        int cont2=0;
        for (int i = 0; i < cs.length; i++) {
            if(cont2==cont){
                extension+=cs[i];
            }
            if(cs[i]=='.'){
                cont2++;
            }
        }
        ext=extension;
        return extension;
    }
    
    public boolean getMatrizPer3x3(){
        boolean b=false;
        JPanel panel1= new JPanel();
        JPanel panel2= new JPanel();
        JPanel panel3= new JPanel();
        GroupLayout layout1= new GroupLayout(panel1);
        GroupLayout layout2= new GroupLayout(panel2);
        GroupLayout layout3= new GroupLayout(panel3);
        
        JTextField f1c1= new JTextField(null,"",5);
        JTextField f1c2= new JTextField(null,"",5);
        JTextField f1c3= new JTextField(null,"",5);
        
        layout1.setHorizontalGroup(
                layout1.createSequentialGroup()
                .addComponent(f1c1)
                .addComponent(f1c2)
                .addComponent(f1c3)
        );
        
        JTextField f2c1= new JTextField(null,"",5);
        JTextField f2c2= new JTextField(null,"",5);
        JTextField f2c3= new JTextField(null,"",5);
        
        layout2.setHorizontalGroup(
                layout2.createSequentialGroup()
                .addComponent(f2c1)
                .addComponent(f2c2)
                .addComponent(f2c3)
        );
        
        JTextField f3c1= new JTextField(null,"",5);
        JTextField f3c2= new JTextField(null,"",5);
        JTextField f3c3= new JTextField(null,"",5);
        
        layout3.setHorizontalGroup(
                layout3.createSequentialGroup()
                .addComponent(f3c1)
                .addComponent(f3c2)
                .addComponent(f3c3)
        );
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel1,"Ingresar Filtro 3x3, fila 1",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel2,"Ingresar Filtro 3x3, fila 2",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel3,"Ingresar Filtro 3x3, fila 3",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        try {
        int[][] mat={   {Integer.parseInt(f1c1.getText()),Integer.parseInt(f1c2.getText()),Integer.parseInt(f1c3.getText())},
                        {Integer.parseInt(f2c1.getText()),Integer.parseInt(f2c2.getText()),Integer.parseInt(f2c3.getText())},
                        {Integer.parseInt(f3c1.getText()),Integer.parseInt(f3c2.getText()),Integer.parseInt(f3c3.getText())}};
        matriz3x3=mat;
        b=true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: no se pudo cargar datos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
    
    public boolean getMatrizPer5x5(){
        boolean b=false;
        JPanel panel1= new JPanel();
        JPanel panel2= new JPanel();
        JPanel panel3= new JPanel();
        JPanel panel4= new JPanel();
        JPanel panel5= new JPanel();
        GroupLayout layout1= new GroupLayout(panel1);
        GroupLayout layout2= new GroupLayout(panel2);
        GroupLayout layout3= new GroupLayout(panel3);
        GroupLayout layout4= new GroupLayout(panel4);
        GroupLayout layout5= new GroupLayout(panel5);
        
        JTextField f1c1= new JTextField(null,"",5);
        JTextField f1c2= new JTextField(null,"",5);
        JTextField f1c3= new JTextField(null,"",5);
        JTextField f1c4= new JTextField(null,"",5);
        JTextField f1c5= new JTextField(null,"",5);
        
        layout1.setHorizontalGroup(
                layout1.createSequentialGroup()
                .addComponent(f1c1)
                .addComponent(f1c2)
                .addComponent(f1c3)
                .addComponent(f1c4)
                .addComponent(f1c5)
        );
        
        JTextField f2c1= new JTextField(null,"",5);
        JTextField f2c2= new JTextField(null,"",5);
        JTextField f2c3= new JTextField(null,"",5);
        JTextField f2c4= new JTextField(null,"",5);
        JTextField f2c5= new JTextField(null,"",5);
        
        layout2.setHorizontalGroup(
                layout2.createSequentialGroup()
                .addComponent(f2c1)
                .addComponent(f2c2)
                .addComponent(f2c3)
                .addComponent(f2c4)
                .addComponent(f2c5)
        );
        
        JTextField f3c1= new JTextField(null,"",5);
        JTextField f3c2= new JTextField(null,"",5);
        JTextField f3c3= new JTextField(null,"",5);
        JTextField f3c4= new JTextField(null,"",5);
        JTextField f3c5= new JTextField(null,"",5);
        
        layout3.setHorizontalGroup(
                layout3.createSequentialGroup()
                .addComponent(f3c1)
                .addComponent(f3c2)
                .addComponent(f3c3)
                .addComponent(f3c4)
                .addComponent(f3c5)
        );
        
        JTextField f4c1= new JTextField(null,"",5);
        JTextField f4c2= new JTextField(null,"",5);
        JTextField f4c3= new JTextField(null,"",5);
        JTextField f4c4= new JTextField(null,"",5);
        JTextField f4c5= new JTextField(null,"",5);
        
        layout4.setHorizontalGroup(
                layout4.createSequentialGroup()
                .addComponent(f4c1)
                .addComponent(f4c2)
                .addComponent(f4c3)
                .addComponent(f4c4)
                .addComponent(f4c5)
        );
        
        JTextField f5c1= new JTextField(null,"",5);
        JTextField f5c2= new JTextField(null,"",5);
        JTextField f5c3= new JTextField(null,"",5);
        JTextField f5c4= new JTextField(null,"",5);
        JTextField f5c5= new JTextField(null,"",5);
        
        layout5.setHorizontalGroup(
                layout5.createSequentialGroup()
                .addComponent(f5c1)
                .addComponent(f5c2)
                .addComponent(f5c3)
                .addComponent(f5c4)
                .addComponent(f5c5)
        );
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel1,"Ingresar Filtro 5x5, fila 1",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel2,"Ingresar Filtro 5x5, fila 2",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel3,"Ingresar Filtro 5x5, fila 3",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel4,"Ingresar Filtro 5x5, fila 4",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel5,"Ingresar Filtro 5x5, fila 5",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        try {
        int[][] mat={   {Integer.parseInt(f1c1.getText()),Integer.parseInt(f1c2.getText()),Integer.parseInt(f1c3.getText()),Integer.parseInt(f1c4.getText()),Integer.parseInt(f1c5.getText())},
                        {Integer.parseInt(f2c1.getText()),Integer.parseInt(f2c2.getText()),Integer.parseInt(f2c3.getText()),Integer.parseInt(f2c4.getText()),Integer.parseInt(f2c5.getText())},
                        {Integer.parseInt(f3c1.getText()),Integer.parseInt(f3c2.getText()),Integer.parseInt(f3c3.getText()),Integer.parseInt(f3c4.getText()),Integer.parseInt(f3c5.getText())},
                        {Integer.parseInt(f4c1.getText()),Integer.parseInt(f4c2.getText()),Integer.parseInt(f4c3.getText()),Integer.parseInt(f4c4.getText()),Integer.parseInt(f4c5.getText())},
                        {Integer.parseInt(f5c1.getText()),Integer.parseInt(f5c2.getText()),Integer.parseInt(f5c3.getText()),Integer.parseInt(f5c4.getText()),Integer.parseInt(f5c5.getText())},};
        matriz5x5=mat;
        b=true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: no se pudo cargar datos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
    
    public boolean getMatrizPer7x7(){
        boolean b=false;
        JPanel panel1= new JPanel();
        JPanel panel2= new JPanel();
        JPanel panel3= new JPanel();
        JPanel panel4= new JPanel();
        JPanel panel5= new JPanel();
        JPanel panel6= new JPanel();
        JPanel panel7= new JPanel();
        GroupLayout layout1= new GroupLayout(panel1);
        GroupLayout layout2= new GroupLayout(panel2);
        GroupLayout layout3= new GroupLayout(panel3);
        GroupLayout layout4= new GroupLayout(panel4);
        GroupLayout layout5= new GroupLayout(panel5);
        GroupLayout layout6= new GroupLayout(panel6);
        GroupLayout layout7= new GroupLayout(panel7);
        
        JTextField f1c1= new JTextField(null,"",5);
        JTextField f1c2= new JTextField(null,"",5);
        JTextField f1c3= new JTextField(null,"",5);
        JTextField f1c4= new JTextField(null,"",5);
        JTextField f1c5= new JTextField(null,"",5);
        JTextField f1c6= new JTextField(null,"",5);
        JTextField f1c7= new JTextField(null,"",5);
        
        layout1.setHorizontalGroup(
                layout1.createSequentialGroup()
                .addComponent(f1c1)
                .addComponent(f1c2)
                .addComponent(f1c3)
                .addComponent(f1c4)
                .addComponent(f1c5)
                .addComponent(f1c6)
                .addComponent(f1c7)
        );
        
        JTextField f2c1= new JTextField(null,"",5);
        JTextField f2c2= new JTextField(null,"",5);
        JTextField f2c3= new JTextField(null,"",5);
        JTextField f2c4= new JTextField(null,"",5);
        JTextField f2c5= new JTextField(null,"",5);
        JTextField f2c6= new JTextField(null,"",5);
        JTextField f2c7= new JTextField(null,"",5);
        
        layout2.setHorizontalGroup(
                layout2.createSequentialGroup()
                .addComponent(f2c1)
                .addComponent(f2c2)
                .addComponent(f2c3)
                .addComponent(f2c4)
                .addComponent(f2c5)
                .addComponent(f2c6)
                .addComponent(f2c7)
        );
        
        JTextField f3c1= new JTextField(null,"",5);
        JTextField f3c2= new JTextField(null,"",5);
        JTextField f3c3= new JTextField(null,"",5);
        JTextField f3c4= new JTextField(null,"",5);
        JTextField f3c5= new JTextField(null,"",5);
        JTextField f3c6= new JTextField(null,"",5);
        JTextField f3c7= new JTextField(null,"",5);
        
        layout3.setHorizontalGroup(
                layout3.createSequentialGroup()
                .addComponent(f3c1)
                .addComponent(f3c2)
                .addComponent(f3c3)
                .addComponent(f3c4)
                .addComponent(f3c5)
                .addComponent(f3c6)
                .addComponent(f3c7)
        );
        
        JTextField f4c1= new JTextField(null,"",5);
        JTextField f4c2= new JTextField(null,"",5);
        JTextField f4c3= new JTextField(null,"",5);
        JTextField f4c4= new JTextField(null,"",5);
        JTextField f4c5= new JTextField(null,"",5);
        JTextField f4c6= new JTextField(null,"",5);
        JTextField f4c7= new JTextField(null,"",5);
        
        layout4.setHorizontalGroup(
                layout4.createSequentialGroup()
                .addComponent(f4c1)
                .addComponent(f4c2)
                .addComponent(f4c3)
                .addComponent(f4c4)
                .addComponent(f4c5)
                .addComponent(f4c6)
                .addComponent(f4c7)
        );
        
        JTextField f5c1= new JTextField(null,"",5);
        JTextField f5c2= new JTextField(null,"",5);
        JTextField f5c3= new JTextField(null,"",5);
        JTextField f5c4= new JTextField(null,"",5);
        JTextField f5c5= new JTextField(null,"",5);
        JTextField f5c6= new JTextField(null,"",5);
        JTextField f5c7= new JTextField(null,"",5);
        
        layout5.setHorizontalGroup(
                layout5.createSequentialGroup()
                .addComponent(f5c1)
                .addComponent(f5c2)
                .addComponent(f5c3)
                .addComponent(f5c4)
                .addComponent(f5c5)
                .addComponent(f5c6)
                .addComponent(f5c7)
        );
        
        JTextField f6c1= new JTextField(null,"",5);
        JTextField f6c2= new JTextField(null,"",5);
        JTextField f6c3= new JTextField(null,"",5);
        JTextField f6c4= new JTextField(null,"",5);
        JTextField f6c5= new JTextField(null,"",5);
        JTextField f6c6= new JTextField(null,"",5);
        JTextField f6c7= new JTextField(null,"",5);
        
        layout6.setHorizontalGroup(
                layout6.createSequentialGroup()
                .addComponent(f6c1)
                .addComponent(f6c2)
                .addComponent(f6c3)
                .addComponent(f6c4)
                .addComponent(f6c5)
                .addComponent(f6c6)
                .addComponent(f6c7)
        );
        
        JTextField f7c1= new JTextField(null,"",5);
        JTextField f7c2= new JTextField(null,"",5);
        JTextField f7c3= new JTextField(null,"",5);
        JTextField f7c4= new JTextField(null,"",5);
        JTextField f7c5= new JTextField(null,"",5);
        JTextField f7c6= new JTextField(null,"",5);
        JTextField f7c7= new JTextField(null,"",5);
        
        layout7.setHorizontalGroup(
                layout7.createSequentialGroup()
                .addComponent(f7c1)
                .addComponent(f7c2)
                .addComponent(f7c3)
                .addComponent(f7c4)
                .addComponent(f7c5)
                .addComponent(f7c6)
                .addComponent(f7c7)
        );
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel1,"Ingresar Filtro 7x7, fila 1",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel2,"Ingresar Filtro 7x7, fila 2",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel3,"Ingresar Filtro 7x7, fila 3",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel4,"Ingresar Filtro 7x7, fila 4",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel5,"Ingresar Filtro 7x7, fila 5",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel6,"Ingresar Filtro 7x7, fila 6",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (true) {
            int res=JOptionPane.showConfirmDialog(null, panel7,"Ingresar Filtro 7x7, fila 7",JOptionPane.INFORMATION_MESSAGE);
            if(res==0){
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Error: No se guardaron datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        try {
        int[][] mat={   {Integer.parseInt(f1c1.getText()),Integer.parseInt(f1c2.getText()),Integer.parseInt(f1c3.getText()),Integer.parseInt(f1c4.getText()),Integer.parseInt(f1c5.getText()),Integer.parseInt(f1c6.getText()),Integer.parseInt(f1c7.getText())},
                        {Integer.parseInt(f2c1.getText()),Integer.parseInt(f2c2.getText()),Integer.parseInt(f2c3.getText()),Integer.parseInt(f2c4.getText()),Integer.parseInt(f2c5.getText()),Integer.parseInt(f2c6.getText()),Integer.parseInt(f2c7.getText())},
                        {Integer.parseInt(f3c1.getText()),Integer.parseInt(f3c2.getText()),Integer.parseInt(f3c3.getText()),Integer.parseInt(f3c4.getText()),Integer.parseInt(f3c5.getText()),Integer.parseInt(f3c6.getText()),Integer.parseInt(f3c7.getText())},
                        {Integer.parseInt(f4c1.getText()),Integer.parseInt(f4c2.getText()),Integer.parseInt(f4c3.getText()),Integer.parseInt(f4c4.getText()),Integer.parseInt(f4c5.getText()),Integer.parseInt(f4c6.getText()),Integer.parseInt(f4c7.getText())},
                        {Integer.parseInt(f5c1.getText()),Integer.parseInt(f5c2.getText()),Integer.parseInt(f5c3.getText()),Integer.parseInt(f5c4.getText()),Integer.parseInt(f5c5.getText()),Integer.parseInt(f5c6.getText()),Integer.parseInt(f5c7.getText())},
                        {Integer.parseInt(f6c1.getText()),Integer.parseInt(f6c2.getText()),Integer.parseInt(f6c3.getText()),Integer.parseInt(f6c4.getText()),Integer.parseInt(f6c5.getText()),Integer.parseInt(f6c6.getText()),Integer.parseInt(f6c7.getText())},
                        {Integer.parseInt(f7c1.getText()),Integer.parseInt(f7c2.getText()),Integer.parseInt(f7c3.getText()),Integer.parseInt(f7c4.getText()),Integer.parseInt(f7c5.getText()),Integer.parseInt(f7c6.getText()),Integer.parseInt(f7c7.getText())}};
        matriz7x7=mat;
        b=true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: no se pudo cargar datos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return b;
    }
    
    public static void main(String[] args) {
        Imagen img= new Imagen();
        img.main();
    }
}
