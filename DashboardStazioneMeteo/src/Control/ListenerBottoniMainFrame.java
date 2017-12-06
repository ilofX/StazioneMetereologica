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

import Model.ChartsGenerator;
import Model.DataManager;
import Model.ParametersHolder;
import Model.TCP.SocketTCP;
import View.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class ListenerBottoniMainFrame implements ActionListener{

    private final MainFrame mf;
    private final ChartsGenerator cg;
    private final Executor executors;
    private final ParametersHolder ph;
    private final DataManager dm;

    public ListenerBottoniMainFrame(MainFrame mf, ChartsGenerator cg, ParametersHolder ph, DataManager dm) {
        this.mf = mf;
        this.cg = cg;
        this.ph = ph;
        this.dm = dm;
        this.executors = Executors.newSingleThreadExecutor();
        this.mf.setButtonListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.mf.getjButton1()){
            try {
                this.mf.getjButton1().setEnabled(false);
                this.executors.execute(new SocketTCP(this.ph.getADDRESS(), this.ph.getPORT(), this.dm));
                Thread.sleep(500);
                this.cg.refreshCharts();
            } catch (InterruptedException ex) {
                Logger.getLogger(ListenerBottoniMainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
