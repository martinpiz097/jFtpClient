///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.martin.ftp.test;
//
//import java.io.IOException;
//import org.apache.commons.net.ftp.FTPFile;
//import org.martin.ftp.net.Accesador;
//
///**
// *
// * @author martin
// */
//public class Main {
//
//    public static void main(String[] args) throws IOException {
//        
//        Accesador a = new Accesador("192.168.0.40", "pi", "raspberry");
//        System.out.println("Cantidad de archivos: " + a.getCliente().listFiles().length);
//        
//        for (FTPFile file : a.getCliente().listFiles())
//            System.out.println(file.getName());
//        
//    }
//}
