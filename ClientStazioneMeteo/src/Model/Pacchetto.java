/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 *
 * @author Filippo Stella
 * @version 0.01
 */
public class Pacchetto {
    
    private final Double precipitazioni,probabilitàPrecipitazioni,umidita,indiceUV,temperatura,velocitaVento,pressione,qualitaAria;

    public Pacchetto(Double precipitazioni, Double probabilitàPrecipitazioni, Double umidita, Double indiceUV, Double temperatura, Double velocitaVento, Double pressione, Double qualitaAria) {
        this.precipitazioni = precipitazioni;
        this.probabilitàPrecipitazioni = probabilitàPrecipitazioni;
        this.umidita = umidita;
        this.indiceUV = indiceUV;
        this.temperatura = temperatura;
        this.velocitaVento = velocitaVento;
        this.pressione = pressione;
        this.qualitaAria = qualitaAria;
    }
    
    public DatagramPacket generaDatagram (InetAddress IP, Integer PORT){
        //ByteBuffer buf = ByteBuffer.
        return null;
    }
    
    
    
}
