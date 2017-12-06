
import Model.Ascoltatore;
import Model.FinetraClient;
import Model.Pacchetto;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

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


/**
 *
 * @author Filippo Stella
 * @version 0.01
 */
public class Main {

    public static void main(String[] args) throws SocketException, IOException {
        FinetraClient f = new FinetraClient();
        Ascoltatore a = new Ascoltatore(f);
        f.setVisible(true);
        /*
    Pacchetto p= new Pacchetto(2.0, 65.0, 78.0, 45.0, 45.2, 23.5, 26.0, 78.0);
    DatagramSocket clientSocket = new DatagramSocket(); 
    InetAddress IPAddress = InetAddress.getLocalHost();
    p.generaDatagram(IPAddress, 6970);
    DatagramPacket sendPacket = new DatagramPacket(p.generaByteArray(), p.generaByteArray().length, IPAddress, 9876); 
    clientSocket.send(sendPacket);
*/
    }
    
}
