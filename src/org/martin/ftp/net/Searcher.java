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
import org.martin.ftp.model.TCRSearch;
import org.martin.ftp.model.TMSearch;

/**
 *
 * @author martin
 */
public class Searcher {

    private final Accesador accesador;
    private final JTable resultsTable;
    private static Searcher searcher;
    
    public static Searcher getInstance(Accesador accesador, JTable resultsTable){
        
        if (searcher == null) 
            searcher = new Searcher(accesador, resultsTable);
        return searcher;
    }
     
    public Searcher(Accesador accesador, JTable resultsTable) {
        this.accesador = accesador;
        this.resultsTable = resultsTable;
    }
    
    public void search(String filter) throws IOException{
        
        LinkedList<FileFiltering> resultsList = new LinkedList<>();
        startSearch(resultsList, accesador.getWorkingDirectory(), filter);
    }
    
    private void startSearch(LinkedList<FileFiltering> list, String directory, String filter) throws IOException{
        
        accesador.toList(directory).stream().forEach((file) -> {
        
            if(file.getName().contains(filter)) {
                list.add(new FileFiltering(directory, file));
                addResult(list);
            }
            
            if (file.isDirectory()) {
                try {
                    startSearch(list, directory + "/" + file.getName(), filter);
                } catch (IOException ex) {
                    Logger.getLogger(Searcher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void addResult(LinkedList<FileFiltering> resultsList){
        
        if (resultsTable.getRowHeight() != 25) resultsTable.setRowHeight(25);
        resultsTable.setModel(new TMSearch(resultsList));
        resultsTable.setDefaultRenderer(Object.class, new TCRSearch(resultsList));
        resultsTable.updateUI();
    }
}
