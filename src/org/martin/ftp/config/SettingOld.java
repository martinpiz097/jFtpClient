/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

/**
 *
 * @author martin
 */
public class SettingOld {

    private File logConfig;
    private BufferedReader br;
    private BufferedWriter bw;
    
    public SettingOld() throws IOException {
   
        logConfig = new File("config.cfg");
        if (!logConfig.exists()) logConfig.createNewFile();
        logConfig.setWritable(false);
    }
    
    public void deleteSettings() throws IOException{
        logConfig.setWritable(true);
        logConfig.delete();
        logConfig.createNewFile();
        logConfig.setWritable(false);
    }
    
    public boolean isAutoLogin() throws FileNotFoundException, IOException{
        
        boolean isAutoLogin = false;
        br = new BufferedReader(new FileReader(logConfig));
        Optional<String> filter = br.lines().findFirst();
        String firstLine;
        
        isAutoLogin = filter.isPresent();
        
        /*
        Retorna isAutoLogin si es falso --> en caso de que el archivo 
        este vacio
        */
        if (!isAutoLogin) return isAutoLogin;
        
        firstLine = filter.get();
        isAutoLogin = Boolean.valueOf(firstLine.split(": ")[1]);
        br.close();
        return isAutoLogin;
    }
    
    public Object[] getSettings() throws FileNotFoundException, IOException{
        
        Object[] settings = new Object[4];
        br = new BufferedReader(new FileReader(logConfig));
        int cont = 0;

        for (Iterator<String> it = br.lines().iterator(); it.hasNext();) {
            
            String line = it.next();
            
            if(cont == 0) settings[cont] = Boolean.valueOf(line.split(": ")[1]);
            
            else settings[cont] = line.split(": ")[1];
            
            cont++;
            
        }
        br.close();
        return settings;
    }
    
    public void setSettings(String... settings) throws IOException{
        
        if (settings.length == 3) {
            logConfig.setWritable(true);
            logConfig.delete();
            logConfig.createNewFile();

            bw = new BufferedWriter(new FileWriter(logConfig));
            bw.write("Autologin: " + true + "\n");
            bw.write("Host: " + settings[0] + "\n");
            bw.write("User: " + settings[1] + "\n");
            bw.write("Password: " + settings[2]);
            bw.close();

            logConfig.setWritable(false);
        }
    }
    
}
