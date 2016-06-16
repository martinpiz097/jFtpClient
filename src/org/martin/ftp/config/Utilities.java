/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.config;

import javax.swing.JTextField;

/**
 *
 * @author martin
 */
public class Utilities {

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
}
