/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.util;

import java.util.Comparator;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.net.FileFiltering;

/**
 *
 * @author martin
 */
public class SearchComparator implements Comparator<FileFiltering>{

    private SortOption option;
    private SortOption order;
    private static final byte idDirectory = 0;
    private static final byte idFile = 1;
    
    public SearchComparator(SortOption option, SortOption order) {
        this.option = option;
        this.order = order;
    }
    
    @Override
    public int compare(FileFiltering o1, FileFiltering o2) {

        FTPFile fileO1 = o1.getFile();
        FTPFile fileO2 = o2.getFile();
        
        if (null != option) switch (option) {
            case DATE:
                if (order == SortOption.UPWARD) 
                    return (int) (fileO1.getTimestamp().getTimeInMillis() - fileO2.getTimestamp().getTimeInMillis());
                
                else
                    return (int) (fileO2.getTimestamp().getTimeInMillis() - fileO1.getTimestamp().getTimeInMillis());
                
            case FORMAT:
                String file1Format = Utilities.getFormat(fileO1.getName());
                String file2Format = Utilities.getFormat(fileO2.getName());
                if(order == SortOption.UPWARD)
                    if (fileO1.isDirectory() || fileO2.isDirectory()) {
                        if (fileO1.isDirectory() && fileO2.isDirectory()) 
                            return idDirectory - idDirectory;
                        else if (fileO1.isDirectory() && !fileO2.isDirectory())
                            return idDirectory - idFile;
                        else return idFile - idDirectory;
                    }
                    else
                        return file1Format.compareToIgnoreCase(file2Format);
                else
                    if (fileO1.isDirectory() || fileO2.isDirectory()) {
                        if (fileO1.isDirectory() && fileO2.isDirectory()) 
                            return idDirectory - idDirectory;
                        else if (fileO1.isDirectory() && !fileO2.isDirectory())
                            return idFile - idDirectory;
                        else return idDirectory - idFile;
                    }
                    else
                        return file2Format.compareToIgnoreCase(file1Format);
                    
            case NAME:
                if(order == SortOption.UPWARD)
                    return fileO1.getName().compareToIgnoreCase(fileO2.getName());
                else
                    return fileO2.getName().compareToIgnoreCase(fileO1.getName());
            
            case SIZE:
                if (order == SortOption.UPWARD) 
                    return (int) (fileO1.getSize() - fileO2.getSize());
                else
                    return (int) (fileO2.getSize() - fileO1.getSize());

            case TYPE:
                if ((fileO1.isDirectory() && fileO2.isDirectory()) || 
                        (!fileO1.isDirectory() && !fileO2.isDirectory())) 
                        return idDirectory - idDirectory;
                
                if (order == SortOption.UPWARD)
                    if(fileO1.isDirectory() && !fileO2.isDirectory()) return idDirectory - idFile;
                    
                    else return idFile-idDirectory;
                else
                    if(fileO1.isDirectory() && !fileO2.isDirectory()) return idFile - idDirectory;
                    
                    else return idDirectory-idFile;
            default:
                return 0;
        }
        
        return 0;
    }
    
}
