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

import Model.ParametersHolder;
import View.MainFrame;
import View.StartupFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class ListenerBottoniStartup implements ActionListener{

    private final StartupFrame sf;
    private final ParametersHolder ph;
    private final MainFrame mf;

    public ListenerBottoniStartup(MainFrame mf, StartupFrame sf, ParametersHolder ph) {
        this.sf = sf;
        this.ph = ph;
        this.mf = mf;
        this.sf.setListenerBottoni(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.sf.getjButton1()){
            //CONTINUA
            this.ph.setADDRESS(this.sf.getjTextField1().getText());
            this.ph.setPORT(Integer.parseInt(this.sf.getjTextField2().getText()));
            this.mf.setLocationRelativeTo(this.sf);
            this.sf.dispose();
            this.mf.setVisible(true);
        }
        else if(e.getSource()==this.sf.getjButton2()){
            //ANNULLA
            System.exit(0);
        }
    }
    
}
