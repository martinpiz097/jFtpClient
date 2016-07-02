/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.config;

import java.awt.Component;
import java.awt.Window;
import java.util.Date;
import javax.swing.JTextField;

/**
 *
 * @author martin
 */
public class Utilities {

    private static final String ZERO = "0";
    
    public static String getTransformedPassword(String passw){
        
        String transformedPassw = "";

        for (int i = 0; i < passw.length(); i++) 
            transformedPassw += "*";
        
        return transformedPassw;
    }
    
    public static void setActivation(JTextField... textFields){
        
        for (JTextField textField : textFields) 
            textField.setEnabled(!textField.isEnabled());
        
    }
    
    public static void disableAll(JTextField... textFields){
      
        for (JTextField textField : textFields)
            textField.setEnabled(false);
    }
    
    public static void cleanTextFields(JTextField... textFields){
        
        for (JTextField textField : textFields) 
            textField.setText(null);
        
    }

    public static boolean isActiveComponents(JTextField... textFields){
        
        for (JTextField textField : textFields) 
            if (textField.isEnabled()) 
                return textField.isEnabled();
        
        return false;
    }

    /**
     * Metodo que quita los espacios que estan solo al comienzo del texto
     * en el jTextField entregado
     * @param textField jTextField a utilizar
     */
    
    private static void adjustTextToLeft(JTextField textField){
        
        String text = textField.getText();
        String newText = "";
        boolean letterFound = false;
        
        for (char c : text.toCharArray()) {
            if (letterFound) newText += c;
            
            else if (c != ' ') {
                letterFound = !letterFound;
                newText += c;
            }
        }
        textField.setText(newText);
        
    }
    
    /**
     * Metodo que quita los espacios que estan solo al final del texto
     * en el jTextField entregado
     * @param textField jTextField a utilizar
     */
    
    private static void adjustTextToRight(JTextField textField){
        
        String text = textField.getText();
        String newText = "";
        boolean segmentWithoutLetters = false;

        for (int i = 0; i < text.length(); i++) {
            if (text.substring(i, text.length()).trim().isEmpty()) 
                segmentWithoutLetters = !segmentWithoutLetters;
            
            if (!segmentWithoutLetters) newText += text.charAt(i);
        }
        
        textField.setText(newText);
    }
    
    /**
     * Metodo que quita los espacios del lado designado
     * @param side Lado a designar
     * @param textFields grupo de jTextFields entregados(puede ser solo 1)
     */
    
    public static void adjustText(Side side, JTextField... textFields){
        
        if (side == Side.LEFT) 
            for (JTextField textField : textFields) 
                adjustTextToLeft(textField);
        
        else
            for (JTextField textField : textFields) 
                adjustTextToRight(textField);
        
    }
    
//    public static String getIconFromPath(String path){
//        
//        String newPath = path.replace("file:", "");
//        System.out.println(newPath);
//        return newPath;
//    }

    public static String getDateToString(Date d){
        
        String day = String.valueOf(d.getDate());
        String month = String.valueOf(d.getMonth());
        String year = String.valueOf(d.getYear() + 1900);
        String hours = String.valueOf(d.getHours());
        String minutes = String.valueOf(d.getMinutes());
        String seconds = String.valueOf(d.getSeconds());
        String slash = "/";
        String doublePoints = ":";
        
        if (d.getDate() < 10) day = ZERO + day;
        if (d.getMonth() < 10) month = ZERO + month;
        if (d.getHours() < 10) hours = ZERO + hours;
        if (d.getMinutes() < 10) minutes = ZERO + minutes;
        if (d.getSeconds() < 10) seconds = ZERO + seconds;
        
        return day + slash + month + slash + year + "  " + 
                hours + doublePoints + minutes + doublePoints + seconds;
    }
    
    public static void openWindow(Window window, Component objectiveLocation){
        window.setSize(window.getPreferredSize());
        window.setLocationRelativeTo(objectiveLocation);
        window.show();
    }
}
