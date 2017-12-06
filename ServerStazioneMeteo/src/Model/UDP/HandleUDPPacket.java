/*
 * Copyright 2017 Stella Filippo.
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
package Model.UDP;

import Model.DataManager;
import Model.Pacchetto;
import java.net.DatagramPacket;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class HandleUDPPacket implements Runnable {

    private final DatagramPacket p;
    private final DataManager m;
    private final JList clients;
    
    public HandleUDPPacket(DatagramPacket p, DataManager m, JList clients) {
        this.p = p;
        this.m = m;
        this.clients = clients;
    }
   
    @Override
    public void run() {
        this.m.AggiungiDato(Pacchetto.pacchettoDaDatagram(p));
        synchronized(this.clients){
            ((DefaultListModel)this.clients.getModel()).removeElement(p.getSocketAddress());
        }
    }
    
}
