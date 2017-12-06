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
package Control;

import Model.DataManager;
import Model.TCP.ServerTCP;
import Model.UDP.ServerUDP;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Stella Filippo
 */
public class MainFrameButtonListener implements ActionListener{

    private final MainFrame mf;
    private ServerTCP serverTCP;
    private ServerUDP serverUDP;
    private final DataManager dm;
    private final StartupButtonListener sbl;

    public MainFrameButtonListener(MainFrame mf, DataManager dm, StartupButtonListener sbl) {
        this.mf = mf;
        this.dm = dm;
        this.sbl = sbl;
        this.mf.setListenerBottoni(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.mf.getjButton5()){
            //Start UDP
            this.serverUDP = new ServerUDP(this.sbl.getUDP_PORT(), this.dm, this.mf.getjList3());
            this.mf.getjButton5().setEnabled(false);
            this.serverUDP.start();
            this.mf.getjProgressBar3().setIndeterminate(true);
            this.mf.getjButton7().setEnabled(true);
        }
        else if(e.getSource()==this.mf.getjButton6()){
            //Start TCP
            this.serverTCP = new ServerTCP(this.sbl.getTCP_PORT(), this.dm, this.mf.getjList4());
            this.mf.getjButton6().setEnabled(false);
            this.serverTCP.start();
            this.mf.getjProgressBar4().setIndeterminate(true);
            this.mf.getjButton8().setEnabled(true);
        }
        else if(e.getSource()==this.mf.getjButton7()){
            //Stop UDP
            this.mf.getjButton7().setEnabled(false);
            this.serverUDP.interrupt();
            this.mf.getjProgressBar3().setIndeterminate(false);
            this.mf.getjButton5().setEnabled(true);
        }
        else if(e.getSource()==this.mf.getjButton8()){
            //Stop TCP
            this.mf.getjButton8().setEnabled(false);
            this.serverTCP.interrupt();
            this.mf.getjProgressBar4().setIndeterminate(false);
            this.mf.getjButton6().setEnabled(true);
        }
    }
    
}
