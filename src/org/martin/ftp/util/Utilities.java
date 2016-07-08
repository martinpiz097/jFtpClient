/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.util;

import java.awt.Component;
import java.awt.Window;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author martin
 */
public class Utilities {

    private static final char ZERO = '0';
    
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
    
    /**
     * Ubica una ventana sobre un componente específico, si el componente es 
     * null, ésta es ubicada al centro del monitor
     * @param window Ventana a reubicar
     * @param objetiveLocation Componente objetivo
     */
    
    public static void resize(Window window, Component objetiveLocation){
        window.setSize(window.getPreferredSize());
        window.setLocationRelativeTo(objetiveLocation);
    }
    
    /**
     * Ubica una ventana sobre un componente específico y la redimensiona segun
     * el ancho y alto entregado por parametro
     * @param window Ventana a reubicar
     * @param objetiveLocation Componente objetivo
     * @param width Nuevo ancho de la ventana
     * @param height Nuevo alto de la ventana
     */
    
    public static void resize(Window window, Component objetiveLocation, int width, int height){
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
    }
    
    /**
     * Redimensiona una ventana según el metodo getPreferedSize() de ésta
     * @param window Ventana a redimensionar
     */
    
    public static void resize(Window window){
        window.setSize(window.getPreferredSize());
    }
    
    /**
     * Redimiensiona una ventana segun el ancho y alto entregado por parametro
     * @param window Ventana a redimiensionar
     * @param width Nuevo ancho de la ventana
     * @param height Nuevo alto de la ventana
     */
    
    public static void resize(Window window, int width, int height){
        window.setSize(width, height);
    }
    
    /**
     * Muestra una ventana y la ubica sobre el componente especificado
     * por paramtros. Esta sera redimensionada segun metodo getPreferedSize()
     * @param window Ventana a mostrar
     * @param objectiveLocation Componente objetivo
     */
    
    public static void openWindow(Window window, Component objectiveLocation){
        resize(window, objectiveLocation);
        window.show();
    }
    
    /**
     * Muestra una ventana, la ubica sobre el componente especificado por parametros
     * y la redimensiona segun el ancho y alto especificado
     * @param window Ventana a mostrar y redimensionar
     * @param objetiveLocation Componente objetivo
     * @param width Nuevo ancho de la ventana
     * @param height Nuevo alto de la ventana
     */
    
    public static void openWindow(Window window, Component objetiveLocation, int width, int height){
        resize(window, objetiveLocation, width, height);
        window.show();
    }
//    
//    private static boolean hasNullElements(String[] array){
//        return Arrays.stream(array).allMatch((s) -> s == null);
//    }
//    
//    private static boolean hasNullElements(LinkedList<String> list){
//        return list.stream().allMatch((s) -> s == null);
//    }

    private static LinkedList<String> split(String str, char limit){
        
        LinkedList<String> list = new LinkedList<>();
        char currentChar;
        int currentListIndex = 0;
        
        for (int i = 0; i < str.length(); i++) {

            if(i == 0) list.add("");
            
            currentChar = str.charAt(i);
            System.out.println(currentChar);
            
            if (currentChar != limit) 
                list.set(currentListIndex, list.get(currentListIndex) + currentChar);
            
            else{
                list.add("");
                currentListIndex++;
            }
        }
        
        return list;
    }
    
    public static String getFormat(String fileName){
        
        if(!fileName.contains(".")) return ".a";
        LinkedList<String> split = split(fileName, '.');
        return split.get(split.size()-1);
    }
}
