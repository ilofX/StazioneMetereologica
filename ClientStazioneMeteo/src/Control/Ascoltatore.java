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
package Control;

import Model.Pacchetto;
import View.FinetraClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Filippo Cecconato
 * @version 0.01
 */
public class Ascoltatore implements ActionListener{
    
    FinetraClient f=null;
    DatagramSocket clientSocket=null;
    public Ascoltatore(FinetraClient f) throws SocketException{
        this.f=f;
        f.AggiungiAscoltatore(this);
        clientSocket = new DatagramSocket(); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==f.getBtnAggiorna()){

            Pacchetto p=new Pacchetto(Double.parseDouble(f.getVelocita_vento().getValue().toString()), f.getProbabilita_precipitazioni().getValue()+0.0, f.getUmidita().getValue()+0.0, Double.parseDouble(f.getIndiceUV().getValue().toString()), Double.parseDouble(f.getTemperatura().getValue().toString()), Double.parseDouble(f.getVelocita_vento().getValue().toString()), Double.parseDouble(f.getPressione().getValue().toString()), Double.parseDouble(f.getVelocita_vento().getValue().toString()) );
            try {
                DatagramPacket sendPacket = p.generaDatagram(InetAddress.getByName(this.f.getjTextField1().getText()), Integer.parseInt(this.f.getjTextField2().getText()));
                clientSocket.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(Ascoltatore.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
