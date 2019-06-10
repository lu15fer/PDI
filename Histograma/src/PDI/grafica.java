/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDI;

import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author perez
 */
public class grafica {
    public void crearHistograma(int[] histograma, JPanel panelHistograma, Color colorBarras, String Nombre){
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        String serie="Pixeles";
        for (int i = 0; i < histograma.length; i++) {
            dataset.addValue(histograma[i], serie, ""+i);
        }
        
        JFreeChart chart= ChartFactory.createBarChart(Nombre , null, null, dataset, PlotOrientation.VERTICAL, true, true, false);
        
        CategoryPlot plot=(CategoryPlot) chart.getPlot();
        BarRenderer renderer= (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colorBarras);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(new Color(214, 217, 223));
        panelHistograma.removeAll();
        panelHistograma.repaint();
        panelHistograma.setLayout(new java.awt.BorderLayout());
        panelHistograma.add(new ChartPanel(chart));
        panelHistograma.validate();
    }
    
    
    public void crearHistograma3D(int[] histograma, JPanel panelHistograma, Color colorBarras, String Nombre){
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        String serie="Pixeles";
        for (int i = 0; i < histograma.length; i++) {
            dataset.addValue(histograma[i], serie, ""+i);
        }
        
        JFreeChart chart= ChartFactory.createBarChart3D(Nombre, null, null, dataset, PlotOrientation.VERTICAL , true, true, false);
        
        CategoryPlot plot=(CategoryPlot) chart.getPlot();
        BarRenderer renderer= (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, colorBarras);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(new Color(214, 217, 223));
        panelHistograma.removeAll();
        panelHistograma.repaint();
        panelHistograma.setLayout(new java.awt.BorderLayout());
        panelHistograma.add(new ChartPanel(chart));
        panelHistograma.validate();
    }
}
