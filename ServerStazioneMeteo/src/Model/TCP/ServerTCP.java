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
package Model.TCP;

import Model.DataManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
public class ServerTCP extends Thread{
    
    private ServerSocket SocketTCP;
    private final Integer PORT;
    private final DataManager dm;
    private final Executor executors;
    private final JList clients;
    private boolean interrupt=false;

    public ServerTCP(Integer PORT, DataManager dm, JList clients) {
        super("ServerTCP"); 
        this.PORT = PORT;
        this.dm = dm;
        this.clients = clients;
        this.executors = Executors.newSingleThreadExecutor();
    }

    public void terminate(){
        try {
            this.interrupt=true;
            this.SocketTCP.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            this.SocketTCP=new ServerSocket(this.PORT);
            while(!this.interrupt){
                Socket s = this.SocketTCP.accept();
                synchronized(this.clients){
                    ((DefaultListModel)this.clients.getModel()).addElement(s.getRemoteSocketAddress().toString());
                }
                this.clients.revalidate();
                this.clients.repaint();
                this.executors.execute(new HandleTCPRequest(s, this.dm, this.clients));
            }
            this.SocketTCP.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
