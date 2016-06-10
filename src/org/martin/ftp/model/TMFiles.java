/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.model;

import java.util.LinkedList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author martin
 */
public class TMFiles implements TableModel{

    private LinkedList<FTPFile> files;

    public TMFiles(LinkedList<FTPFile> files) {
        this.files = files;
    }

    public LinkedList<FTPFile> getFiles(){
        return files;
    }
    
    public FTPFile getFile(int index){
        return files.get(index);
    }
    
    @Override
    public int getRowCount() {
        return files.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        
        if (columnIndex == 0) return "Nombre";
        
        else return "Tamaño";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        if (columnIndex == 0) return Object.class;
            
        else return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        FTPFile file = files.get(rowIndex);
        
        if (columnIndex == 0) return file.getName();
            
        else return file.getSize()*1024 + "kB";
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
