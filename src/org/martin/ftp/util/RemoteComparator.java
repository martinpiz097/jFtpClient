/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.util;

import java.util.Comparator;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author martin
 */
public class RemoteComparator implements Comparator<FTPFile>{

    private SortOption option;
    private SortOption order;
    private static final byte idDirectory = 0;
    private static final byte idFile = 0;
    
    public RemoteComparator(SortOption option, SortOption order) {
        this.option = option;
        this.order = order;
    }
    
    @Override
    public int compare(FTPFile o1, FTPFile o2) {
    
        if (null != option) switch (option) {
            case DATE:
                if (order == SortOption.UPWARD) 
                    return (int) (o1.getTimestamp().getTimeInMillis() - o2.getTimestamp().getTimeInMillis());
                
                else
                    return (int) (o2.getTimestamp().getTimeInMillis() - o1.getTimestamp().getTimeInMillis());
                
            case FORMAT:
                String file1Format = Utilities.getFormat(o1.getName());
                String file2Format = Utilities.getFormat(o2.getName());
                if(order == SortOption.UPWARD)
                    if (o1.isDirectory() || o2.isDirectory()) {
                        if (o1.isDirectory() && o2.isDirectory()) 
                            return idDirectory - idDirectory;
                        else if (o1.isDirectory() && !o2.isDirectory())
                            return idDirectory - idFile;
                        else return idFile - idDirectory;
                    }
                    else
                        return file1Format.compareToIgnoreCase(file2Format);
                else
                    if (o1.isDirectory() || o2.isDirectory()) {
                        if (o1.isDirectory() && o2.isDirectory()) 
                            return idDirectory - idDirectory;
                        else if (o1.isDirectory() && !o2.isDirectory())
                            return idFile - idDirectory;
                        else return idDirectory - idFile;
                    }
                    else
                        return file2Format.compareToIgnoreCase(file1Format);
                    
            case NAME:
                if(order == SortOption.UPWARD)
                    return o1.getName().compareToIgnoreCase(o2.getName());
                else
                    return o2.getName().compareToIgnoreCase(o1.getName());
            
            case SIZE:
                if (order == SortOption.UPWARD) 
                    return (int) (o1.getSize() - o2.getSize());
                else
                    return (int) (o2.getSize() - o1.getSize());

            case TYPE:
                if ((o1.isDirectory() && o2.isDirectory()) || (!o1.isDirectory() && !o2.isDirectory())) 
                        return idDirectory - idDirectory;
                
                if (order == SortOption.UPWARD)
                    if(o1.isDirectory() && !o2.isDirectory()) return idDirectory - idFile;
                    
                    else return idFile-idDirectory;
                else
                    if(o1.isDirectory() && !o2.isDirectory()) return idFile - idDirectory;
                    
                    else return idDirectory-idFile;
            default:
                return 0;
        }
        
        return 0;
    }
    
}
