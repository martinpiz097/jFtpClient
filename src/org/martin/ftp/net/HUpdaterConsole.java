/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.net;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JTextArea;

/**
 *
 * @author martin
 */
public class HUpdaterConsole extends Thread{

    private FTPLinker linker;
    private JTextArea console;
    private int currentReply;
    private LinkedList<String> listMsg;
    
    public HUpdaterConsole(FTPLinker linker, JTextArea console) {
        this.linker = linker;
        this.console = console;
        this.currentReply = linker.getReply();
        this.listMsg = new LinkedList<>();
    }

    public void addText(String text){
        console.setText(console.getText() + text.trim() + "\n");
    }
    
    public void addText(){
        addText(linker.getReplyCode());
    }

    public boolean isDifferentReply(){
        return currentReply != linker.getReply();
    }

    private int getCantEquals(LinkedList<String> list, String element){
        
        int cantEquals = 0;
        
        for (int i = 0; i < list.size(); i++) 
            if(list.get(i).equalsIgnoreCase(element))
                cantEquals++;
        
        return cantEquals-1;
    }
    
    public void addAllMsg(){
        
        String current = "";
        LinkedList<String> newList = new LinkedList<>();
        int cantEquals = 0;
        
        for (Iterator<String> it = listMsg.iterator(); it.hasNext();) {
            String string = it.next();
            cantEquals = getCantEquals(listMsg, string);
            
            if (cantEquals >= 1) {
                current = string;
                listMsg.removeIf((str) -> str.equalsIgnoreCase(string));
            }
            newList.add(string);
        }
        listMsg.clear();
        listMsg.addAll(newList);
        listMsg.stream().forEach(this::addText);
    }
    
    @Override
    public void run() {
        
        int cont = 0;
        while (true) {            
            try {
                cont++;
                Thread.sleep(10);
                if(isDifferentReply()){
                    addText();
                    currentReply = linker.getReply();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(HUpdaterConsole.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
