/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    private static final NumberFormat nf = new DecimalFormat("#0.0");
    private static final short BYTE_IN_KILOBYTE = 1024;
    
    public TMFiles(LinkedList<FTPFile> files) {
        this.files = files;
        System.out.println(this.files.size());
        this.files.forEach((file) -> System.out.println(file));
    }
    
    /**
     * Metodo que eleva cualquier numero entregandole el exponente
     * @param number numero a elevar
     * @param numberOfTimes exponente
     * @return resultado de la potencia
     */
    
    private long raise(int number, int numberOfTimes){
        
        for (int i = 1; i < numberOfTimes; i++) 
            number *= number;

        return number;
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
        long tamaño = file.getSize();
        
        if (columnIndex == 0) return file.getName();
            
        else {
            if (tamaño < raise(BYTE_IN_KILOBYTE, 2)) 
                return nf.format((double) tamaño / 1000) + "kB";
            
            else if (tamaño < raise(BYTE_IN_KILOBYTE, 3)) 
                return nf.format((double) tamaño / raise(BYTE_IN_KILOBYTE, 2)) + "MB";
            
            else 
                return nf.format((double) tamaño / raise(BYTE_IN_KILOBYTE, 3)) + "GB";
            
        }
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
