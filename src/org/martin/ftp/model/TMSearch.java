/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.martin.ftp.util.Utilities;
import org.martin.ftp.net.FileFiltering;

/**
 *
 * @author martin
 */
public class TMSearch implements TableModel{

    private LinkedList<FileFiltering> files;
    private static final NumberFormat nf = new DecimalFormat("#0.0");
    private static final short BYTE_IN_KILOBYTE = 1024;
    
    public TMSearch(LinkedList<FileFiltering> files) {
        this.files = files;
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

    public LinkedList<FileFiltering> getFiles(){
        return files;
    }
    
    public FileFiltering getFile(int index){
        return files.get(index);
    }
    
    @Override
    public int getRowCount() {
        return files.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        
        switch (columnIndex) {
            case 0:
                return "Nombre";
            case 1:
                return "Tamaño";
            case 2:
                return "Ultima modificacion";
            default:
                return "Ruta";
        }
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

        FileFiltering file = files.get(rowIndex);
        long tamaño = file.getFile().getSize();
        Date timeStamp = file.getFile().getTimestamp().getTime();
        
        switch (columnIndex) {
            case 0:
                return file.getFile().getName();
            case 1:
                if (tamaño < raise(BYTE_IN_KILOBYTE, 2))
                    return nf.format((double) tamaño / 1000) + "kB";
                
                else if (tamaño < raise(BYTE_IN_KILOBYTE, 3))
                    return nf.format((double) tamaño / raise(BYTE_IN_KILOBYTE, 2)) + "MB";
                
                else
                    return nf.format((double) tamaño / raise(BYTE_IN_KILOBYTE, 3)) + "GB";
                
            case 2: 
                return Utilities.getDateToString(timeStamp);
            
            default:
                return file.getParentDir();
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
