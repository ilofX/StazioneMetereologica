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
import Model.Pacchetto;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stella Filippo
 */
public class SocketTCP extends Thread{
    
    private Socket s;
    private final String ADDRESS;
    private final Integer PORT;
    private final DataManager dm;

    public SocketTCP(String ADDRESS, Integer PORT, DataManager dm) {
        super("Data Fetcher");
        this.ADDRESS = ADDRESS;
        this.PORT = PORT;
        this.dm = dm;
    }

    @Override
    public void run() {
        try {
            this.s = new Socket(this.ADDRESS, this.PORT);
            DataInputStream in = new DataInputStream(new BufferedInputStream(this.s.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(this.s.getOutputStream()));
            out.write("Dati?".getBytes());
            out.flush();
            byte[] data = new byte[4096];
            in.read(data);
            Map<Calendar, Pacchetto> map = DataManager.estraiMappa(data);
            this.dm.setData(map);
        } catch (IOException ex) {
            Logger.getLogger(SocketTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
