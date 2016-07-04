/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.net;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class Tester {

    public static boolean hasInternetConnection(){
        
        try {
            return new Socket("www.google.cl", 80).isConnected();
        } catch (IOException ex) {
            return false;
        }
    }
    
    public static boolean isConnectedToHost(String host){
    
        System.out.println(host);
        try {
            return new Socket(host, 21).isConnected();
        } catch (IOException ex) {
            try {
                return new Socket(host, 80).isConnected();
            } catch (IOException ex1) {
                return false;
            }
        }
    }
}
