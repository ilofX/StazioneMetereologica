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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class ServerUDP extends Thread{
    
    private DatagramSocket SocketUDP;
    private final Executor executors;
    private final Integer PORT;
    private final DataManager dm;
    private final JList clients;
    private boolean interrupt = false;

    public ServerUDP(Integer PORT, DataManager dm, JList clients) {
        super("ServerUDP");
        this.PORT = PORT;
        this.dm = dm;
        this.clients = clients;
        this.executors = Executors.newSingleThreadExecutor();
    }
    
    public void terminate(){
        this.interrupt=true;
        this.SocketUDP.close();
    }

    @Override
    public void run() {
        try {
            this.SocketUDP = new DatagramSocket(this.PORT);
            while(!this.interrupt){
                byte buf[] = new byte[10000];
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                this.SocketUDP.receive(p);
                synchronized(this.clients){
                    ((DefaultListModel)this.clients.getModel()).addElement(p.getSocketAddress().toString());
                }
                this.clients.revalidate();
                this.clients.repaint();
                this.executors.execute(new HandleUDPPacket(p, this.dm, this.clients));
            }
            this.SocketUDP.close();
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
