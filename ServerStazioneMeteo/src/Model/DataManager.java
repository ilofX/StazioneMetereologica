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
package Model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stella Filippo
 * @version 0.01
 */
public class DataManager {
    
    private final ArrayList<Pacchetto> data;

    public DataManager() {
        data = new ArrayList<>();
    }
    
    public void AggiungiDato(Pacchetto p){
        synchronized (this.data){
            this.data.add(p);
        }
    }
    
    public byte[] generaArrayDati(){
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            ObjectOutput outObject = new ObjectOutputStream(outStream);
            synchronized (this.data){
                outObject.writeObject(this.data);
            }
            return outStream.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    return null;
    }
     
}
