/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author martin
 */
public class FilterSelector {
    
    public enum TypeFilter{
        ONLY_FILES, ONLY_DIRECTORIES, 
        FILES_AND_DIRECTORIES, ZIP
    }
    
    public static FileFilter getFilter(TypeFilter typeFilter){
        
        return new FileFilter() {
            @Override
            public boolean accept(File f) {

                switch(typeFilter){
                    case ONLY_FILES:
                        return !f.isDirectory();
                        
                    case ONLY_DIRECTORIES:
                        return f.isDirectory();
                        
                    case ZIP:
                        return f.getName().endsWith(".zip") || f.isDirectory();
                
                    default: return true;
                }
            }

            @Override
            public String getDescription() {

                switch(typeFilter){
                    case ONLY_FILES:
                        return "Solo Archivos";
                        
                    case ONLY_DIRECTORIES:
                        return "Solo directorios";
                        
                    case ZIP:
                        return "*.zip";
                        
                    default: return "Todos los archivos";
                }
            }
        };
    }
}