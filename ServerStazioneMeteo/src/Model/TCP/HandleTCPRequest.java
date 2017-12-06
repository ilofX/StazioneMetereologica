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
package Model.TCP;

import Model.DataManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class HandleTCPRequest implements Runnable{

    private final Socket s;
    private final DataManager dm;
    private final JList clients;

    public HandleTCPRequest(Socket s, DataManager dm, JList clients) {
        this.s = s;
        this.dm = dm;
        this.clients = clients;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(this.s.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(this.s.getOutputStream()));
            byte[] data = new byte[4096];
            in.read(data);
            String received = new String(data);
            if(received.equals("Dati?")){
                byte[] dati = this.dm.generaArrayDati();
                out.write(dati);
            }
            in.close();
            out.close();
            this.s.close();
            synchronized(this.clients){
                ((DefaultListModel)this.clients.getModel()).removeElement(this.s.getRemoteSocketAddress());
            }
        } catch (IOException ex) {
            Logger.getLogger(HandleTCPRequest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
