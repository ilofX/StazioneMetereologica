
import Control.ListenerBottoniMainFrame;
import Control.ListenerBottoniStartup;
import Model.ChartsGenerator;
import Model.DataManager;
import Model.ParametersHolder;
import View.MainFrame;
import View.StartupFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            StartupFrame sf = new StartupFrame();
            MainFrame mf = new MainFrame();
            DataManager dm = new DataManager();
            ParametersHolder ph = new ParametersHolder();
            ChartsGenerator cg = new ChartsGenerator(mf, dm);
            ListenerBottoniStartup lbs = new ListenerBottoniStartup(mf, sf, ph);
            ListenerBottoniMainFrame lbmf = new ListenerBottoniMainFrame(mf, cg, ph, dm);
        }
    }
    
}
