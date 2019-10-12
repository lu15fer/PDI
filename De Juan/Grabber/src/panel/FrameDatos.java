/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import manejoDatos.dameDatos;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author zassu
 */
public class FrameDatos extends javax.swing.JFrame {

    /**
     * Creates new form FrameDatos
     */
    
    BufferedImage bimg;    
    public Image tempImage;
    private int brilloNum=0;
    private int negativoNum=0;
    
    
    private dameDatos datos = new dameDatos();

    public dameDatos getDatos() {
        return datos;
    }

    public void setDatos(dameDatos datos) {
        this.datos = datos;
    }

    public FrameDatos() {
        initComponents();
        this.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showName = new javax.swing.JTextField();
        imgName = new javax.swing.JTextField();
        sizeShow = new javax.swing.JTextField();
        anchoImg = new javax.swing.JTextField();
        largoImg = new javax.swing.JTextField();
        showXmouse = new javax.swing.JTextField();
        posXmosue = new javax.swing.JTextField();
        showYmouse = new javax.swing.JTextField();
        posYmouse = new javax.swing.JTextField();
        showRedImg = new javax.swing.JTextField();
        valRedImg = new javax.swing.JTextField();
        showGreenImg = new javax.swing.JTextField();
        valGreenImg = new javax.swing.JTextField();
        showBlueImg = new javax.swing.JTextField();
        valBlueImg = new javax.swing.JTextField();
        Paneles = new javax.swing.JPanel();
        PanelHisto = new javax.swing.JPanel();
        PanelRojo = new javax.swing.JPanel();
        PanelVerde = new javax.swing.JPanel();
        PanelAzul = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        actionMenuDatos = new javax.swing.JMenu();
        sizeOfImage1 = new javax.swing.JCheckBoxMenuItem();
        nameOfImage1 = new javax.swing.JCheckBoxMenuItem();
        grabberOptn1 = new javax.swing.JMenu();
        Coord1 = new javax.swing.JCheckBoxMenuItem();
        redGrabber1 = new javax.swing.JCheckBoxMenuItem();
        greenGrabber1 = new javax.swing.JCheckBoxMenuItem();
        blueGrabber1 = new javax.swing.JCheckBoxMenuItem();
        Histograma1 = new javax.swing.JMenu();
        redHist1 = new javax.swing.JCheckBoxMenuItem();
        greenHist1 = new javax.swing.JCheckBoxMenuItem();
        blueHist1 = new javax.swing.JCheckBoxMenuItem();

        setTitle("Datos de la Imagen");
        setLocation(new java.awt.Point(70, 70));
        setMaximumSize(new java.awt.Dimension(405, 383));
        setMinimumSize(new java.awt.Dimension(405, 383));
        setName("FrameDatos"); // NOI18N
        setPreferredSize(new java.awt.Dimension(405, 383));
        setResizable(false);
        setSize(new java.awt.Dimension(405, 383));

        showName.setEditable(false);
        showName.setText("Nombre:");
        showName.setEnabled(false);

        imgName.setEditable(false);
        imgName.setEnabled(false);

        sizeShow.setEditable(false);
        sizeShow.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sizeShow.setText("Tamaño");
        sizeShow.setEnabled(false);
        sizeShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeShowActionPerformed(evt);
            }
        });

        anchoImg.setEditable(false);
        anchoImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        anchoImg.setEnabled(false);

        largoImg.setEditable(false);
        largoImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        largoImg.setEnabled(false);

        showXmouse.setEditable(false);
        showXmouse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        showXmouse.setText("X");
        showXmouse.setEnabled(false);

        posXmosue.setEditable(false);
        posXmosue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        posXmosue.setEnabled(false);

        showYmouse.setEditable(false);
        showYmouse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        showYmouse.setText("Y");
        showYmouse.setEnabled(false);

        posYmouse.setEditable(false);
        posYmouse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        posYmouse.setEnabled(false);

        showRedImg.setEditable(false);
        showRedImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        showRedImg.setText("R");
        showRedImg.setEnabled(false);

        valRedImg.setEditable(false);
        valRedImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valRedImg.setEnabled(false);

        showGreenImg.setEditable(false);
        showGreenImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        showGreenImg.setText("G");
        showGreenImg.setEnabled(false);

        valGreenImg.setEditable(false);
        valGreenImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valGreenImg.setEnabled(false);

        showBlueImg.setEditable(false);
        showBlueImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        showBlueImg.setText("B");
        showBlueImg.setEnabled(false);

        valBlueImg.setEditable(false);
        valBlueImg.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valBlueImg.setEnabled(false);

        javax.swing.GroupLayout PanelAzulLayout = new javax.swing.GroupLayout(PanelAzul);
        PanelAzul.setLayout(PanelAzulLayout);
        PanelAzulLayout.setHorizontalGroup(
            PanelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );
        PanelAzulLayout.setVerticalGroup(
            PanelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelVerdeLayout = new javax.swing.GroupLayout(PanelVerde);
        PanelVerde.setLayout(PanelVerdeLayout);
        PanelVerdeLayout.setHorizontalGroup(
            PanelVerdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelVerdeLayout.setVerticalGroup(
            PanelVerdeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelRojoLayout = new javax.swing.GroupLayout(PanelRojo);
        PanelRojo.setLayout(PanelRojoLayout);
        PanelRojoLayout.setHorizontalGroup(
            PanelRojoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelVerde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelRojoLayout.setVerticalGroup(
            PanelRojoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelVerde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelHistoLayout = new javax.swing.GroupLayout(PanelHisto);
        PanelHisto.setLayout(PanelHistoLayout);
        PanelHistoLayout.setHorizontalGroup(
            PanelHistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelRojo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelHistoLayout.setVerticalGroup(
            PanelHistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelRojo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelesLayout = new javax.swing.GroupLayout(Paneles);
        Paneles.setLayout(PanelesLayout);
        PanelesLayout.setHorizontalGroup(
            PanelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PanelHisto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelesLayout.setVerticalGroup(
            PanelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PanelHisto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        actionMenuDatos.setText("Acciones");
        actionMenuDatos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        sizeOfImage1.setText("Tamaño");
        sizeOfImage1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sizeOfImage1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sizeOfImage1ItemStateChanged(evt);
            }
        });
        actionMenuDatos.add(sizeOfImage1);

        nameOfImage1.setText("Nombre");
        nameOfImage1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nameOfImage1ItemStateChanged(evt);
            }
        });
        actionMenuDatos.add(nameOfImage1);

        grabberOptn1.setText("Grabber");

        Coord1.setText("Coordenadas");
        Coord1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Coord1ActionPerformed(evt);
            }
        });
        grabberOptn1.add(Coord1);

        redGrabber1.setText("Rojo");
        grabberOptn1.add(redGrabber1);

        greenGrabber1.setText("Verde");
        greenGrabber1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                greenGrabber1ActionPerformed(evt);
            }
        });
        grabberOptn1.add(greenGrabber1);

        blueGrabber1.setText("Azul");
        grabberOptn1.add(blueGrabber1);

        actionMenuDatos.add(grabberOptn1);

        Histograma1.setText("Histograma");

        redHist1.setText("Rojo");
        redHist1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                redHist1ItemStateChanged(evt);
            }
        });
        Histograma1.add(redHist1);

        greenHist1.setText("Verde");
        greenHist1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                greenHist1ItemStateChanged(evt);
            }
        });
        Histograma1.add(greenHist1);

        blueHist1.setText("Azul");
        blueHist1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                blueHist1ItemStateChanged(evt);
            }
        });
        Histograma1.add(blueHist1);

        actionMenuDatos.add(Histograma1);

        jMenuBar1.add(actionMenuDatos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(largoImg)
                    .addComponent(sizeShow)
                    .addComponent(anchoImg)
                    .addComponent(showName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(posXmosue)
                                .addComponent(showXmouse, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(posYmouse)
                                .addComponent(showYmouse, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(valRedImg)
                                .addComponent(showRedImg, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(valGreenImg)
                                .addComponent(showGreenImg, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(valBlueImg)
                                .addComponent(showBlueImg, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)))
                        .addComponent(imgName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
                    .addComponent(Paneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sizeShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anchoImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showXmouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(posXmosue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showYmouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(posYmouse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(showGreenImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showRedImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(valGreenImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valRedImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(showBlueImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valBlueImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(largoImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Paneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(237, 237, 237))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Coord1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Coord1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Coord1ActionPerformed

    private void greenGrabber1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_greenGrabber1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_greenGrabber1ActionPerformed

    private void sizeShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeShowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeShowActionPerformed

    private void sizeOfImage1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sizeOfImage1ItemStateChanged
        // TODO add your handling code here:
        if (sizeOfImage1.getState() == true) {

            sizeShow.enable(true);
            anchoImg.enable(true);
            largoImg.enable(true);
            anchoImg.setText(Integer.toString(datos.getAncho()));
            largoImg.setText(Integer.toString(datos.getLargo()));
        } else {
            sizeShow.enable(false);
            anchoImg.enable(false);
            largoImg.enable(false);

        }
    }//GEN-LAST:event_sizeOfImage1ItemStateChanged

    private void nameOfImage1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nameOfImage1ItemStateChanged
        // TODO add your handling code here:
        if (nameOfImage1.getState() == true) {
            showName.enable(true);
            imgName.enable(true);
            imgName.setText(datos.getNombre());
        } else {
            showName.enable(false);
            imgName.enable(false);

        }
    }//GEN-LAST:event_nameOfImage1ItemStateChanged

    private void blueHist1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_blueHist1ItemStateChanged
        // TODO add your handling code here:
        if (blueHist1.getState() == true) {
            redHist1.setState(false);
            greenHist1.setState(false);
            this.GeneraHistogramas(1);
        } else if (redHist1.getState() == false && greenHist1.getState() == false && blueHist1.getState() == false) {
            Paneles.setVisible(false);
            Paneles.enable(false);
            Paneles.repaint();
        }
    }//GEN-LAST:event_blueHist1ItemStateChanged

    private void greenHist1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_greenHist1ItemStateChanged
        // TODO add your handling code here:

        if (greenHist1.getState() == true) {
            blueHist1.setState(false);
            redHist1.setState(false);
            this.GeneraHistogramas(2);
        } else if (redHist1.getState() == false && greenHist1.getState() == false && blueHist1.getState() == false) {
            Paneles.setVisible(false);
            Paneles.enable(false);
            Paneles.repaint();
        }

    }//GEN-LAST:event_greenHist1ItemStateChanged

    private void redHist1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_redHist1ItemStateChanged
        // TODO add your handling code here:
        if (redHist1.getState() == true) {
            blueHist1.setState(false);
            greenHist1.setState(false);
            this.GeneraHistogramas(3);
        } else if (redHist1.getState() == false && greenHist1.getState() == false && blueHist1.getState() == false) {
            Paneles.setVisible(false);
            Paneles.enable(false);
            Paneles.repaint();
        }
    }//GEN-LAST:event_redHist1ItemStateChanged

    public void mouseMoved(MouseEvent evento) {
        if (Coord1.getState() == true) {
            showXmouse.enable(true);
            showYmouse.enable(true);
            posXmosue.enable(true);
            posYmouse.enable(true);
            posXmosue.setText(Integer.toString(datos.getEv().getX()));
            posYmouse.setText(Integer.toString(datos.getEv().getY()));
            this.repaint();
        } else {
            showXmouse.enable(false);
            showYmouse.enable(false);
            posXmosue.enable(false);
            posYmouse.enable(false);
            posXmosue.setText("0");
            posYmouse.setText("0");
            this.repaint();
        }
        if (redGrabber1.getState() == true) {
            valRedImg.setText(Integer.toString(datos.getPxgR()));
            showRedImg.enable(true);
            valRedImg.enable(true);
        } else {
            showRedImg.enable(false);
            valRedImg.enable(false);
        }
        if (greenGrabber1.getState() == true) {
            valGreenImg.setText(Integer.toString(datos.getPxgG()));
            showGreenImg.enable(true);
            valGreenImg.enable(true);
        } else {
            showGreenImg.enable(false);
            valGreenImg.enable(false);
        }
        if (blueGrabber1.getState() == true) {
            valBlueImg.setText(Integer.toString(datos.getPxgB()));
            showBlueImg.enable(true);
            valBlueImg.enable(true);
        } else {
            showBlueImg.enable(false);
            valBlueImg.enable(false);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new FrameDatos().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem Coord1;
    private javax.swing.JMenu Histograma1;
    private javax.swing.JPanel PanelAzul;
    private javax.swing.JPanel PanelHisto;
    private javax.swing.JPanel PanelRojo;
    private javax.swing.JPanel PanelVerde;
    private javax.swing.JPanel Paneles;
    private javax.swing.JMenu actionMenuDatos;
    private javax.swing.JTextField anchoImg;
    private javax.swing.JCheckBoxMenuItem blueGrabber1;
    private javax.swing.JCheckBoxMenuItem blueHist1;
    private javax.swing.JMenu grabberOptn1;
    private javax.swing.JCheckBoxMenuItem greenGrabber1;
    private javax.swing.JCheckBoxMenuItem greenHist1;
    private javax.swing.JTextField imgName;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField largoImg;
    private javax.swing.JCheckBoxMenuItem nameOfImage1;
    private javax.swing.JTextField posXmosue;
    private javax.swing.JTextField posYmouse;
    private javax.swing.JCheckBoxMenuItem redGrabber1;
    private javax.swing.JCheckBoxMenuItem redHist1;
    private javax.swing.JTextField showBlueImg;
    private javax.swing.JTextField showGreenImg;
    private javax.swing.JTextField showName;
    private javax.swing.JTextField showRedImg;
    private javax.swing.JTextField showXmouse;
    private javax.swing.JTextField showYmouse;
    private javax.swing.JCheckBoxMenuItem sizeOfImage1;
    private javax.swing.JTextField sizeShow;
    private javax.swing.JTextField valBlueImg;
    private javax.swing.JTextField valGreenImg;
    private javax.swing.JTextField valRedImg;
    // End of variables declaration//GEN-END:variables

    public void tomaDatos(dameDatos data) {

        datos = data;

    }

    public void crearHistograma3D(int[] histograma, JPanel panelHistograma, Color colorBarras, String Nombre) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String serie = "Pixeles";
        for (int i = 0; i < histograma.length; i++) {
            dataset.addValue(histograma[i], serie, "" + i);
        }

        JFreeChart chart = ChartFactory.createBarChart3D(Nombre, null, null, dataset, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colorBarras);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(new Color(214, 217, 223));
        panelHistograma.removeAll();
        panelHistograma.repaint();
        panelHistograma.setLayout(new java.awt.BorderLayout());
        panelHistograma.add(new ChartPanel(chart));
        panelHistograma.validate();
    }

    public void GeneraHistogramas(int dec) {
        switch (dec) {
            case 1:
                int[] histogramaBlue = new int[256];
                System.arraycopy(datos.getBlue(), 0, histogramaBlue, 0, datos.getBlue().length);
                this.crearHistograma3D(histogramaBlue, Paneles, Color.BLUE, "Azul");
                Paneles.repaint();
                Paneles.setVisible(true);
                Paneles.enable(true);
                break;
            case 2:
                int[] histogramaGreen = new int[256];
                System.arraycopy(datos.getGreen(), 0, histogramaGreen, 0, datos.getGreen().length);
                this.crearHistograma3D(histogramaGreen, Paneles, Color.GREEN, "Verde");
                Paneles.repaint();
                Paneles.setVisible(true);
                Paneles.enable(true);
                break;
            case 3:
                int[] histogramaRed = new int[256];
                System.arraycopy(datos.getRed(), 0, histogramaRed, 0, datos.getRed().length);
                this.crearHistograma3D(histogramaRed, Paneles, Color.RED, "Rojo");
                Paneles.repaint();
                Paneles.setVisible(true);
                Paneles.enable(true);
                break;
        }

    }
    


}
