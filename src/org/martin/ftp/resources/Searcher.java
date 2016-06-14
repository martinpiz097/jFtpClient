/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.resources;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.net.Accesador;
import org.martin.ftp.net.TypeSearch;

/**
 *
 * @author martin
 */
public class Searcher {

    private static Accesador accesador;
    private static LinkedList<FTPFile> listaResultados;

    static{
        listaResultados = new LinkedList<>();
    }
    
    public static void search(TypeSearch typeSearch, String path, String filter) throws IOException{
        
        LinkedList<FTPFile> archivosPath = accesador.getOrderedFiles(path);
        
        archivosPath.stream().forEach((file) -> {
            try {
                if (file.isDirectory())
                    search(typeSearch, path, filter);
                
                else if (file.getName().contains(filter)) 
                    listaResultados.add(file);
                
            } catch (IOException ex) {
                Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
