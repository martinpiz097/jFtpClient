/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.model;

import java.awt.Color;
import java.awt.Component;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.net.FileFiltering;

/**
 *
 * @author martin
 */
public class TCRSearch implements TableCellRenderer{

    private LinkedList<FileFiltering> files;

    public TCRSearch(LinkedList<FileFiltering> files) {
        this.files = files;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel lbl = new JLabel();
        ImageIcon icon;
        FTPFile file = files.get(row).getFile();
        
        if (column == 0) {
            if (file.isDirectory()) 
                icon = new ImageIcon(getClass().getResource("/org/martin/ftp/resources/folder.png"));

            else
                icon = new ImageIcon(getClass().getResource("/org/martin/ftp/resources/file.png"));
        
            lbl.setIcon(icon);
            lbl.setText(file.getName());
        }
    
        else lbl.setText(value.toString());

        Color bg = lbl.getBackground();
        
        if (isSelected) lbl.setBackground(Color.CYAN);
            
        else lbl.setBackground(bg);
        
        lbl.setOpaque(true);
        
        return lbl;
        
    }
    
}
