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

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 *
 * @author Filippo Cecconato
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
        byte buf[]= new byte[]  {   this.precipitazioni.byteValue(),
                                    this.probabilitàPrecipitazioni.byteValue(),
                                    this.umidita.byteValue(),
                                    this.indiceUV.byteValue(),
                                    this.temperatura.byteValue(),
                                    this.velocitaVento.byteValue(),
                                    this.pressione.byteValue(),
                                    this.qualitaAria.byteValue()
                                };
        DatagramPacket pacchetto = new DatagramPacket(buf, buf.length, IP, PORT);
        return pacchetto;
    }
    
    public byte[] generaByteArray(){ //GENERA UN ARRAY DI BYTE PER INVIARE CON IL TCP
        byte buf[] = new byte[]  {  this.precipitazioni.byteValue(),
                                    this.probabilitàPrecipitazioni.byteValue(),
                                    this.umidita.byteValue(),
                                    this.indiceUV.byteValue(),
                                    this.temperatura.byteValue(),
                                    this.velocitaVento.byteValue(),
                                    this.pressione.byteValue(),
                                    this.qualitaAria.byteValue()
                                };
        return buf;
    }
    
    public static Pacchetto pacchettoDaDatagram(DatagramPacket pacchetto){ //GENERE UN OGGETTO PACCHETTO DA UN DATAGRAM PACKET
        byte buf[] = pacchetto.getData();
        Pacchetto p = new Pacchetto(Double.parseDouble(Byte.toString(buf[0])),
                                    Double.parseDouble(Byte.toString(buf[1])),
                                    Double.parseDouble(Byte.toString(buf[2])),
                                    Double.parseDouble(Byte.toString(buf[3])),
                                    Double.parseDouble(Byte.toString(buf[4])),
                                    Double.parseDouble(Byte.toString(buf[5])),
                                    Double.parseDouble(Byte.toString(buf[6])),
                                    Double.parseDouble(Byte.toString(buf[7])));
        return p;
    }
    
    public static Pacchetto pacchettoDaByteArray(byte data[]){ //GENERA UN OGGETTO PACCHETTO DA UN ARRAY DI BYTE
        Pacchetto p = new Pacchetto(Double.parseDouble(Byte.toString(data[0])),
                                    Double.parseDouble(Byte.toString(data[1])),
                                    Double.parseDouble(Byte.toString(data[2])),
                                    Double.parseDouble(Byte.toString(data[3])),
                                    Double.parseDouble(Byte.toString(data[4])),
                                    Double.parseDouble(Byte.toString(data[5])),
                                    Double.parseDouble(Byte.toString(data[6])),
                                    Double.parseDouble(Byte.toString(data[7])));
            
        return p;
    }
    
}
