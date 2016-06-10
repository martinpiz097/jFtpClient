/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.net.Accesador;

/**
 *
 * @author martin
 */
public class LMFiles implements ListModel<String>{

    private Accesador accesador;

    public LMFiles(Accesador accesador) {
        this.accesador = accesador;
    }

    @Override
    public int getSize() {
        try {
            return accesador.getFilesAndDirectories().length;
        } catch (IOException ex) {
            return 0;
        }
    }

    @Override
    public String getElementAt(int index) {
        try {
            return accesador.getFilesAndDirectories()[index].getName();
        } catch (IOException ex) {
            return null;
        }
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
