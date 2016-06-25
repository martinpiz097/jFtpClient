/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.updates;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class UpdatesReviewer {

    private File tester;
    private File currentVersionContent;
    private Properties p;
    private Properties onlineFileProperties;
    private FileInputStream is;
    private FileOutputStream os;

    public UpdatesReviewer() throws IOException {
        tester = new File("version.xml");
        p = new Properties();
        if(!tester.exists()) saveInfo(tester, p);
        
        else loadInfo(tester, p);
        
        onlineFileProperties = new Properties();
    }
    
    public boolean hasNewVersion() throws IOException, MalformedURLException{
        return !getVersion().equalsIgnoreCase(getCurrentVersion());
    }
    
    public String getCurrentVersion() throws MalformedURLException, IOException{
        
        URL url = new URL("https://github.com/martinpiz097/jFtpClient/version.xml");
        System.out.println(url.openConnection().getContentType());
        currentVersionContent = new File("version.xml");
        if(!currentVersionContent.exists()) currentVersionContent.createNewFile();
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(currentVersionContent));
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        br.lines().forEach((line) -> {
            try {
                bw.write(line);
            } catch (IOException ex) {
                Logger.getLogger(UpdatesReviewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        br.close();
        bw.flush();
        bw.close();
        loadInfo(currentVersionContent, onlineFileProperties);
        return onlineFileProperties.getProperty("version");
    }
    
    public String getVersion(){
        return p.getProperty("version");
    }
    
    public void setVersion(String newVersion){
        p.setProperty("version", newVersion);
    }
    
    public void loadInfo(File file, Properties p) throws IOException{
        is = new FileInputStream(file);
        p.loadFromXML(is);
    }
    
    public void saveInfo(File file, Properties p) throws IOException{
        os = new FileOutputStream(file);
        p.storeToXML(os, "Archivo de informacion del software");
    }
}
