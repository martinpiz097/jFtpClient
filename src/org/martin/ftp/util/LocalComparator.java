/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.util;

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author martin
 */
public class LocalComparator implements Comparator<File>{

    private SortOption option;
    private SortOption order;
    private static final byte idDirectory = 0;
    private static final byte idFile = 1;
    
    public LocalComparator(SortOption option, SortOption order) {
        this.option = option;
        this.order = order;
    }
    
    @Override
    public int compare(File o1, File o2) {
    
        if (null != option) switch (option) {
            case DATE:
                if (order == SortOption.UPWARD) 
                    return (int) (o1.lastModified() - o2.lastModified());
                
                else
                    return (int) (o1.lastModified() - o2.lastModified());
                    
            case FORMAT:
                String file1Format = Utilities.getFormat(o1.getName());
                String file2Format = Utilities.getFormat(o2.getName());
                if(order == SortOption.UPWARD)
                    return file1Format.compareToIgnoreCase(file2Format);
                else
                    return file2Format.compareToIgnoreCase(file1Format);

            case NAME:
                if(order == SortOption.UPWARD)
                    return o1.getName().compareToIgnoreCase(o2.getName());
                else
                    return o2.getName().compareToIgnoreCase(o1.getName());
            
            case SIZE:
                if (order == SortOption.UPWARD) 
                    return (int) (o1.length() - o2.length());
                else
                    return (int) (o2.length() - o1.length());

            case TYPE:
                
                if (order == SortOption.UPWARD)
                    return String.valueOf(o1.isDirectory()).compareToIgnoreCase(String.valueOf(o2.isDirectory()));
                else
                    return String.valueOf(o2.isDirectory()).compareToIgnoreCase(String.valueOf(o1.isDirectory()));
                default:
                    return 0;
        }
        
        return 0;
    }
    
}
