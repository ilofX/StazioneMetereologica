/*
 * Copyright 2017 Filippo Stella, Elia Nasato, Lorenzo Zorzini, Cecconato Filippo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package Model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class DataManager {
    
    private Map<Calendar,Pacchetto> data;

    public DataManager() {
        data = new HashMap<>();
        System.out.println(data.toString());
    }
    
    public void setData(Map<Calendar,Pacchetto> data){
        this.data = data; 
        System.out.println(this.printData());
    }
    
    public DefaultCategoryDataset getTemperatura(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                dataset.addValue(p.getTemperatura(), "temperature", d.getTime().toString());
            }
        }
        return dataset;
    }
    
    public DefaultCategoryDataset getPrecipitazioni(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                dataset.addValue(p.getPrecipitazioni(), "precipitazioni", d.getTime().toString());
                dataset.addValue(p.getProbabilitàPrecipitazioni(), "probabilitaprecipitazioni", d.getTime().toString());
            }
        }
        return dataset;
    }
    
    public DefaultCategoryDataset getUmidità(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                dataset.addValue(p.getUmidita(), "umidità", d.getTime().toString());
            }
        }
        return dataset;
    }
    
    public DefaultCategoryDataset getIndiceUV(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                dataset.addValue(p.getIndiceUV(), "indiceuv", d.getTime().toString());
            }
        }
        return dataset;
    }
    
    public DefaultCategoryDataset getVelocitaVento(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                dataset.addValue(p.getVelocitaVento(), "vento", d.getTime().toString());
            }
        }
        return dataset;
    }
    
    public DefaultCategoryDataset getPressione(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                dataset.addValue(p.getPressione(), "pressione", d.getTime().toString());
            }
        }
        return dataset;
    }
    
    public DefaultCategoryDataset getQualitaAria(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                dataset.addValue(p.getQualitaAria(), "qualitaaria", d.getTime().toString());
            }
        }
        return dataset;
    }
    
    public String printData(){
        String ris = "";
        synchronized (this){
            Iterator<Calendar> i = data.keySet().iterator();
            while(i.hasNext()){
                Calendar d = i.next();
                Pacchetto p = this.data.get(d);
                ris+=p.toString();
            }
        }
        return ris;
    }
    
    public static Map<Calendar, Pacchetto> estraiMappa(byte[] dati){
        try {
            Map ris;
            ByteArrayInputStream inByte = new ByteArrayInputStream(dati);
            ObjectInputStream inObject  = new ObjectInputStream(inByte);
            ris = (HashMap<Calendar,Pacchetto>) inObject.readObject();
            inByte.close();
            return ris;
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
            return new HashMap<>();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);    
        }
        return new HashMap<>();
    }
    
}
