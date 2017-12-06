/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.MainFrame;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class ChartsGenerator {
    
    private final MainFrame mf;
    private final DataManager dm;

    public ChartsGenerator(MainFrame mf, DataManager dm) {
        this.mf = mf;
        this.dm = dm;
    }
    
    public void refreshCharts(){
        try {
            this.mf.getjTabbedPane1().removeAll();
            Thread.sleep(500);
            this.generatePrecipitazioni();
            this.generateUmidità();
            this.generateIndiceUV();
            this.generateTemperatura();
            this.generateVento();
            this.generatePressione();
            this.generateQualitaAria();
        } catch (InterruptedException ex) {
            Logger.getLogger(ChartsGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.mf.getjButton1().setEnabled(true);
        }
    }
    
    public void generatePrecipitazioni(){
        ChartPanel chart;
        synchronized (this.dm){
            chart = ChartsGenerator.genertateLineChart("Precipitazioni", "Timestamp", "Quantità", this.dm.getPrecipitazioni());
        }
        chart.setPreferredSize(new Dimension(this.mf.getjTabbedPane1().getWidth(), this.mf.getjTabbedPane1().getHeight()-20));
        this.mf.getjTabbedPane1().addTab("Precipitazioni", chart);
    }
    
    public void generateUmidità(){
        ChartPanel chart;
        synchronized (this.dm){
            chart = ChartsGenerator.genertateLineChart("Precipitazioni", "Timestamp", "Umidità", this.dm.getUmidità());
        }
        chart.setPreferredSize(new Dimension(this.mf.getjTabbedPane1().getWidth(), this.mf.getjTabbedPane1().getHeight()-20));
        this.mf.getjTabbedPane1().addTab("Umidità", chart);
    }
    
    public void generateIndiceUV(){
        ChartPanel chart;
        synchronized (this.dm){
            chart = ChartsGenerator.genertateLineChart("indiceUV", "Time", "Indice Raggi UV", this.dm.getIndiceUV());
        }
        chart.setPreferredSize(new Dimension(this.mf.getjTabbedPane1().getWidth(), this.mf.getjTabbedPane1().getHeight()-20));
        this.mf.getjTabbedPane1().addTab("indice UV", chart);
    }
    
    public void generateTemperatura(){
        ChartPanel chart;
        synchronized (this.dm){
            chart = ChartsGenerator.genertateLineChart("temperatura", "Time", "Temperatura", this.dm.getTemperatura());
        }
        chart.setPreferredSize(new Dimension(this.mf.getjTabbedPane1().getWidth(), this.mf.getjTabbedPane1().getHeight()-20));
        this.mf.getjTabbedPane1().addTab("Temperatura", chart);
    }
    
    public void generateVento(){
        ChartPanel chart;
        synchronized (this.dm){
            chart = ChartsGenerator.genertateLineChart("Vento", "Time", "Velocità vento", this.dm.getVelocitaVento());
        }
        chart.setPreferredSize(new Dimension(this.mf.getjTabbedPane1().getWidth(), this.mf.getjTabbedPane1().getHeight()-20));
        this.mf.getjTabbedPane1().addTab("Velocità Vento", chart);
    }
    
    public void generatePressione(){
        ChartPanel chart;
        synchronized (this.dm){
            chart = ChartsGenerator.genertateLineChart("Pressione", "Time", "Pressione", this.dm.getPressione());
        }
        chart.setPreferredSize(new Dimension(this.mf.getjTabbedPane1().getWidth(), this.mf.getjTabbedPane1().getHeight()-20));
        this.mf.getjTabbedPane1().addTab("pressione", chart);
    }
    
    public void generateQualitaAria(){
        ChartPanel chart;
        synchronized (this.dm){
            chart = ChartsGenerator.genertateLineChart("Qualità Aria", "Time", "Qualità Aria", this.dm.getQualitaAria());
        }
        chart.setPreferredSize(new Dimension(this.mf.getjTabbedPane1().getWidth(), this.mf.getjTabbedPane1().getHeight()-20));
        this.mf.getjTabbedPane1().addTab("Qualità Aria", chart);
    }
    
    public static ChartPanel genertateLineChart(String title, String xAxisLabel, String yAxisLabel, CategoryDataset dataset ){
        JFreeChart chart = ChartFactory.createLineChart(title, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);
        return new ChartPanel(chart);
    } 
    
}
