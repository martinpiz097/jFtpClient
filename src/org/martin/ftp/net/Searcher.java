/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.net;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.model.TCRSearch;
import org.martin.ftp.model.TMSearch;

/**
 *
 * @author martin
 */
public class Searcher {

    private final FTPLinker accesador;
    private final JTable resultsTable;
    private static Searcher searcher;
    private LinkedList<FileFiltering> resultsList;
   
    public static Searcher getInstance(FTPLinker accesador, JTable resultsTable){
        
        if (searcher == null) 
            searcher = new Searcher(accesador, resultsTable);
        return searcher;
    }
     
    public Searcher(FTPLinker accesador, JTable resultsTable) {
        this.accesador = accesador;
        this.resultsTable = resultsTable;
    }
    
    public void search(String filter) throws IOException{
        
        resultsList = new LinkedList<>();
        startSearch(accesador.getWorkingDirectory(), filter);
        showResults();
    }
    
    private void startSearch(String directory, String filter) throws IOException{
    
        LinkedList<FTPFile> listSearch = accesador.toList(directory);
        listSearch.stream().forEach((file) -> {
        
            if(file.getName().contains(filter)) {
                resultsList.add(new FileFiltering(directory, file));
            }
            
            if (file.isDirectory()) {
                try {
                    startSearch(directory + "/" + file.getName(), filter);
                } catch (IOException ex) {
                    Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void showResults(){
        
        if (resultsTable.getRowHeight() != 25) resultsTable.setRowHeight(25);
        resultsTable.setModel(new TMSearch(resultsList));
        resultsTable.setDefaultRenderer(Object.class, new TCRSearch(resultsList));
    }
}
