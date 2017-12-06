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

package Control;

import View.MainFrame;
import View.StartupFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Stella Filippo
 */
public class StartupButtonListener implements ActionListener{

    private final StartupFrame sf;
    private final MainFrame mf;
    private Integer UDP_PORT, TCP_PORT;

    public StartupButtonListener(StartupFrame sf, MainFrame mf) {
        this.sf = sf;
        this.mf = mf;
        this.sf.setButtonListener(this);
    }
    
    public Integer getUDP_PORT() {
        return UDP_PORT;
    }
    public Integer getTCP_PORT() {
        return TCP_PORT;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.sf.getjButton1()){
            this.UDP_PORT = Integer.parseInt(this.sf.getjTextField2().getText());
            this.TCP_PORT = Integer.parseInt(this.sf.getjTextField1().getText());
            this.mf.setLocationRelativeTo(this.sf);
            this.sf.dispose();
            this.mf.setVisible(true);
        }
        else if(e.getSource()==this.sf.getjButton2()){
            System.exit(0);
        }
    }
    
}
