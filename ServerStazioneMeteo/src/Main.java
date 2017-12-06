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

import Control.MainFrameButtonListener;
import Control.StartupButtonListener;
import Model.DataManager;
import View.MainFrame;
import View.StartupFrame;

/**
 *
 * @author Filippo Stella
 * @version 0.01
 */
public class Main {

    public static void main(String[] args) {
        DataManager dm = new DataManager();
        MainFrame mf = new MainFrame();
        StartupFrame sf = new StartupFrame();
        StartupButtonListener sbl = new StartupButtonListener(sf, mf);
        MainFrameButtonListener mfbl = new MainFrameButtonListener(mf, dm, sbl);     
    }
    
}
