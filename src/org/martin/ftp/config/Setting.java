/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import static org.martin.ftp.config.Encryptor.encrypt;
import static org.martin.ftp.config.Encryptor.decrypt;

/**
 *
 * @author martin
 */
public class Setting {

    private File logFile;
    private Properties p;
    private FileOutputStream os;
    private FileInputStream is;

    public Setting() throws IOException {
    
        logFile = new File("config.xml");
        p = new Properties();
        
        if (!logFile.exists()) saveConfigs();
        
        else loadConfigs();
    }

    public void addProperty(String key, String value){
        p.put(key, value);
    }

    public boolean isAutoLogin(){

        String property = p.getProperty("autolog");
        
        if(property == null) return false;
        
        return Boolean.valueOf(p.getProperty("autolog"));
    }
    
    public boolean hasSaveConfigs(){
        
        return p.getProperty("host") != null || p.getProperty("user") != null ||
                p.getProperty("password") != null;
    }
    
    public boolean equals(String server, String user, String password){
        return server.equalsIgnoreCase(getHost()) && user.equalsIgnoreCase(getUser()) && 
                password.equalsIgnoreCase(getPassword());
    }
    
    public void deleteSettings() throws IOException{
        p.clear();
        saveConfigs();
    }

    public void setSettings(boolean autoLogin, String server, String user, String password) throws IOException{
        setAutologin(autoLogin);
        setHost(server);
        setUser(user);
        setPassword(password);
    }
    
    public void setSettings(String server, String user, String password) throws IOException{
        setAutologin(true);
        setHost(server);
        setUser(user);
        setPassword(password);
    }
    
    public void setAutologin(boolean autoLogin) throws IOException{
        p.setProperty("autolog", String.valueOf(autoLogin));
        saveConfigs();
    }

    public String getHost(){
        return p.getProperty("host");
    }
    
    public void setHost(String newHost) throws IOException{
        p.setProperty("host", newHost);
        saveConfigs();
    }
    
    public String getUser(){
        return p.getProperty("user");
    }
    
    public void setUser(String newUser) throws IOException{
        p.setProperty("user", newUser);
        saveConfigs();
    }
    
    public String getPassword(){
        return decrypt(p.getProperty("password"));
    }
    
    public void setPassword(String newPassword) throws IOException{
        p.setProperty("password", encrypt(newPassword));
        saveConfigs();
    }
    
    public void loadConfigs() throws IOException{
        is = new FileInputStream(logFile);
        p.loadFromXML(is);
    }
    
    public void saveConfigs() throws IOException{
        os = new FileOutputStream(logFile);
        p.storeToXML(os, "Archivo de configuracion de inicio de sesion automatico");
    }
}
