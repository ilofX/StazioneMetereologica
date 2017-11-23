/*
 * Copyright 2017 Alunno.
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
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.Buffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;

/**
 *
 * @author Alunno
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws SocketException {
        // TODO code application logic here
        try{
            ArrayList<Pacchetto> arraypacchetto = new ArrayList<Pacchetto>();
        DatagramSocket ds = new DatagramSocket(6969);
        byte[] buf = new byte[2048];
        while(true){
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            Pacchetto p= Pacchetto.pacchettoDaDatagram(dp);
            arraypacchetto.add(p);
        }
        }catch (SocketException e){}
    }
    
}
