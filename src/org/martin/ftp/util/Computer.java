/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.util;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author martin
 */
public class Computer {
    
    private static RemoteComparator remote;
    private static LocalComparator local;
    // La idea de este metodo es ordenar los directorios y archivos por nombre ademas del
    // orden ya establecido, añadir opcion de orden inverso, por fecha y tamaño
    
    public static void orderRemoteFiles(LinkedList<FTPFile> files, SortOption option, SortOption order){
        remote = new RemoteComparator(option, order);
        Collections.sort(files, remote);
    }
    
    // o1-o2 --> ascendente
    // o2-o1 --> descendente
    
    public static void orderLocalFiles(LinkedList<File> files, SortOption option, SortOption order){
        local = new LocalComparator(option, order);
        Collections.sort(files, local);
    }
}
