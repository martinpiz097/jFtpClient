/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.net;

import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author martin
 */
public class FileFiltering {

    private String parentDir;
    private FTPFile file;

    public FileFiltering(String parentDir, FTPFile file) {
        this.parentDir = parentDir;
        if (!this.parentDir.endsWith("/")) this.parentDir += "/";
        this.file = file;
    }

    public String getPath(){
        
        if (file.isDirectory()) return parentDir + file.getName();
        else return parentDir;
    }
    
    public String getParentDir() {
        return parentDir;
    }

    public void setParentDir(String parentDir) {
        this.parentDir = parentDir;
    }

    public FTPFile getFile() {
        return file;
    }

    public void setFile(FTPFile file) {
        this.file = file;
    }
}
