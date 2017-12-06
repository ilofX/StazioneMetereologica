/*
 * Copyright 2017 Elia.
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elia
 */
public class Ascoltatore implements ActionListener{
    
    FinetraClient f=null;
    DatagramSocket clientSocket=null;
    public Ascoltatore(FinetraClient f) throws SocketException{
        this.f=f;
        f.AggiungiAscoltatore(this);
         //sclientSocket = new DatagramSocket(); 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==f.getBtnAggiorna()){
            
           //Double.parseDouble((String) f.getVelocita_vento().getValue())
            Pacchetto p= new Pacchetto(2.0, 65.0, 78.0, 45.0, 45.2, 23.5, 26.0, 78.0);
           p=new Pacchetto(Double.parseDouble((String) f.getPrecipitazionemillimetri().getValue()), Double.parseDouble((String) f.getProbabilita_precipitazioni().getValue()), Double.parseDouble((String) f.getUmidita().getValue()), Double.parseDouble((String) f.getIndiceUV().getValue()), Double.parseDouble((String) f.getTemperatura().getValue()),(Double)(f.getVelocita_vento().getValue()),(Double)(f.getPressione().getValue()), Double.parseDouble((String) f.getVelocita_vento().getValue()) );
            InetAddress IPAddress;
            try {
                IPAddress = InetAddress.getLocalHost();
            p.generaDatagram(IPAddress, 6970);
            DatagramPacket sendPacket = new DatagramPacket(p.generaByteArray(), p.generaByteArray().length, IPAddress, 9876); 
                clientSocket.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(Ascoltatore.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
    }
    
}
