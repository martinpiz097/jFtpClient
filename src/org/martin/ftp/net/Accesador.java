/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;

/**
 *
 * @author martin
 */
public class Accesador {
    
    private FTPClient cliente;
    private String server;
    private String user;
    private String password;
    
    /**
     * 
     * @param server Servidor ftp al cual conectarse
     * @param user Usuario ftp
     * @param password Constraseña del usuario, si el usuario
     * no tiene contraseña este parametro puede ser nulo
     * @throws IOException Excepcion si hay problemas de conexion
     */
    
    public Accesador(String server, String user, String password) throws IOException {
   
        this.server = server;
        this.user = user;
        this.password = password;
        if (this.password == null) this.password = "";
        
        cliente = new FTPClient();
        cliente.connect(server);
        cliente.login(user, password);
    }

    private FTPFileFilter getFilter(Filter filter){
        
        return (FTPFile ftpf) -> {
            if (filter == Filter.FILES_ONLY) return !ftpf.isDirectory();
            
            else if (filter == Filter.DIRECTORIES_ONLY) return true;
            
            else return ftpf.isDirectory();
        };
    }
    
    public String getReplyCode(){
        return cliente.getReplyString();
    }
    
    public void reconnect() throws IOException{
        cliente.connect(server);
        cliente.login(user, password);
        cliente.enterLocalPassiveMode();
    }
    
    public boolean isConnected(){
        return FTPReply.isPositiveCompletion(cliente.getReplyCode());
    }
    
    public void logout() throws IOException{
        cliente.logout();
    }
    
    public void disconnect() throws IOException{
        cliente.disconnect();
    }
    
    /**
     * 
     * @param files Lista a ordenar
     * @return Lista de archivos y carpetas creadas a partir 
     * del parametro, se ubican primero las carpetas y luego los
     * archivos
     * 
     */
    
    public LinkedList<FTPFile> getOrdererFiles(LinkedList<FTPFile> files){
        
        LinkedList<FTPFile> directories = new LinkedList<>();
        LinkedList<FTPFile> files2 = new LinkedList<>();
        files.stream().filter((d) -> d.isDirectory()).forEach((dir) -> directories.add(dir));
        files.stream().filter((f) -> !f.isDirectory()).forEach((file) -> files2.add(file));
        
        return files;
    }
    
    /**
     * 
     * @param directory El directorio del cual se desea 
     * obtener la lista
     * @return Lista de archivos y carpetas creadas a partir 
     * del parametro, se ubican primero las carpetas y luego los
     * archivos
     * @throws IOException 
     */
    
    public LinkedList<FTPFile> getOrderedFiles(String directory) throws IOException{
        
        LinkedList<FTPFile> files = new LinkedList<>();
        files.addAll(Arrays.asList(getDirectories(directory)));
        files.addAll(Arrays.asList(getFiles(directory)));
        return files;
    }
    
    /**
     * 
     * @return Lista que contiene los archivos y carpetas de la ruta actual, 
     * ubicando primero las carpetas y luego los archivos
     * @throws IOException 
     */
    
    public LinkedList<FTPFile> getOrderedFiles() throws IOException{

        LinkedList<FTPFile> files = new LinkedList<>();
        files.addAll(toList(getDirectories()));
        files.addAll(toList(getFiles()));
        return files;
    }
    
    /**
     * 
     * @return El objeto FTPClient actual
     */
    
    public FTPClient getCliente() {
        return cliente;
    }
    
    /**
     * 
     * @param name Nombre del archivo a buscar
     * @return resultado obtenido, si el archivo no se encuentra se retorna null
     * @throws IOException 
     */
    
    public FTPFile searchFile(String name) throws IOException{
        
        Optional<FTPFile> resultado = Arrays.stream(getFilesAndDirectories()).filter(
                (file) -> file.getName().equalsIgnoreCase(name)).findFirst();
        
        if (resultado.isPresent()) return resultado.get();
            
        else return null;
        
    }
    
    /**
     * Se cambia el directorio actual, si el nuevo directorio no existe
     * se mantiene el mismo
     * @param directorio directorio nuevo de trabajo
     * @throws IOException Excepcion ocurrida tras el cambio
     */
    
    public void setWorkingDirectory(String directorio) throws IOException{
        cliente.changeWorkingDirectory(directorio);
    }
    
    /**
     * Se cambia el directorio actual por el directorio padre
     * @throws IOException 
     */
    
    public void setToParentDirectory() throws IOException{
        cliente.changeToParentDirectory();
    }
    
    /**
     * Se retorna al directorio anterior
     * @throws IOException 
     */
    
    public void goToBeforeDirectory() throws IOException{
        setWorkingDirectory("..");
    }
    
    /**
     * 
     * @param directories Array que contiene las carpetas y subcarpetas
     * del directorio a dirigirse (no se deben colocar slash)
     * @throws IOException 
     */
    
    public void goTo(String... directories) throws IOException{
        
        String destino = "";
        for (String directory : directories) destino += ("/" + directory);
        setWorkingDirectory(destino);
        
    }
    
    /**
     * 
     * Se obtiene el directorio actual de trabajo
     * @return Directorio actual de trabajo
     * @throws IOException 
     */

    public String getWorkingDirectory() throws IOException{
        return cliente.printWorkingDirectory();
    }
    
    /**
     * 
     * Se obtiene un archivo o carpeta en una ruta especificada
     * @param ruta ruta del archivo
     * @param name nombre del archivo a obtener
     * @return archivo obtenido, si no se encuentra se retorna null
     * @throws IOException 
     */

    public FTPFile get(String ruta, String name) throws IOException{
        
        FTPFile resultado = null;
        LinkedList<FTPFile> files = toList(ruta);
        Optional<FTPFile> res = files.stream().filter(
                (file) -> file.getName().equalsIgnoreCase(name)).findFirst();
        
        if (res.isPresent()) resultado = res.get();
        
        return resultado;
    }

    
    /**
     * Metodo que obtiene un array con solo los archivos del actual directorio
     * @return Archivos del actual directorio
     * @throws IOException 
     */
    
    public FTPFile[] getFiles() throws IOException{

        // int cantFiles = 0;
        // FTPFile[] filesCliente; 
                
        
//        ArrayList<FTPFile> listFiles = new ArrayList<>();
//        
//        for (FTPFile file : filesCliente) if (file.isFile()) listFiles.add(file);
//        
//        FTPFile[] archivos = new FTPFile[listFiles.size()];
//        
//        for (int i = 0; i < listFiles.size(); i++) archivos[i] = listFiles.get(i);
//        
//        /*
//            Verificar funcionamiento
//            System.arraycopy(filesCliente, 0, arrayFiles, 0, filesCliente.length);
//        */
        return cliente.listFiles(getWorkingDirectory(), getFilter(Filter.FILES_ONLY));
    }
    
    /**
     * Metodo que entrega un array con solo los archivos de un directorio
     * en especifico
     * @param directory directorio a analizar
     * @return Array de archivos del directorio;
     * @throws IOException 
     */
    
    public FTPFile[] getFiles(String directory) throws IOException{

//        int cantFiles = 0;
//        FTPFile[] filesCliente = cliente.listFiles(directory);
//        LinkedList<FTPFile> listFiles = new LinkedList<>();
//        
//        for (FTPFile file : filesCliente) if (file.isFile()) listFiles.add(file);
//        
//        FTPFile[] archivos = new FTPFile[listFiles.size()];
//        
//        for (int i = 0; i < listFiles.size(); i++) archivos[i] = listFiles.get(i);
//        
//        System.out.println("Archivos: "  + archivos.length);
//
//        /*
//            Verificar funcionamiento
//            System.arraycopy(filesCliente, 0, arrayFiles, 0, filesCliente.length);
//        */
//        return archivos;
        return cliente.listFiles(directory, getFilter(Filter.FILES_ONLY));
    }
    
    /**
     * Entrega una lista con los archivos del array que le sea entregado
     * @param array Array de archivos a entregar
     * @return lista con los datos del array entregado
     */
    
    public LinkedList<FTPFile> toList(FTPFile[] array){
        
        LinkedList<FTPFile> list = new LinkedList<>();
        Arrays.stream(array).forEach(list::add);
        return list;
    }
    
    public LinkedList<FTPFile> toList() throws IOException{
        
        LinkedList<FTPFile> list = new LinkedList<>();
        Arrays.stream(getFilesAndDirectories()).forEach(list::add);
        return list;
    }
    
    public LinkedList<FTPFile> toList(String directory) throws IOException{
        
        LinkedList<FTPFile> list = new LinkedList<>();
        Arrays.stream(getFilesAndDirectories(directory)).forEach(list::add);
        return list;
    }
    
    public FTPFile[] getDirectories() throws IOException{
        return cliente.listDirectories();
    }
    
    public FTPFile[] getDirectories(String directory) throws IOException{
        return cliente.listDirectories(directory);
    }
    
    public FTPFile[] getFilesAndDirectories() throws IOException{
        return cliente.listFiles();
    }
    
    public FTPFile[] getFilesAndDirectories(String directory) throws IOException{
        return cliente.listFiles(directory);
    }
    
//    public HashMap<String, Object> getAll(HashMap<String, Object> lista) throws IOException{
//        
//        if (lista == null) lista = new HashMap();
//
//        HashMap<String, Object> subList;
//        
//        for (FTPFile file : getFilesAndDirectories()) {
//        
//            if (file.isDirectory()) {
//            
//                subList = new HashMap<>();
//                lista.put(file.getLink(), subList);
//                subList = getAll(subList);
//            }
//            
//            else lista.put(file.getLink(), file);
//            
//        }
//        
//        return lista;
//    }
    
    /**
     * 
     * @param nombre Nombre del archivo a buscar
     * @param type Tipo --> si es directorio o archivo
     * @return Archivo obtenido dentro de la ruta actual, sino se encuentra retorna null
     * @throws IOException 
     */
    
    public FTPFile getFile(String nombre, Type type) throws IOException{
        
        FTPFile resultado = null;
        
        if (type == Type.FILE) {
            
            for (FTPFile file : getFiles()) 
                
                if (file.getName().equalsIgnoreCase(nombre)) {
                    resultado = file;
                    break;
                }
            
        }
        
        else{
            
            for (FTPFile dir : getDirectories()) 
                
                if (dir.getName().equalsIgnoreCase(nombre)) {
                    resultado = dir;
                    break;
                }
            
        }
        
        return resultado;
    }
    
    public File getFile(){

        return null;
    }
    
    public FTPFile getFile(String nombre, String directorio, Type type) throws IOException{
    
        FTPFile resultado = null;
        
        if (type == Type.FILE) {
            
            for (FTPFile file : getFiles(directorio)) 
                
                if (file.getName().equalsIgnoreCase(nombre)) {
                    resultado = file;
                    break;
                }
            
        }
        
        else{
            
            for (FTPFile dir : getDirectories(directorio)) 
                
                if (dir.getName().equalsIgnoreCase(nombre)) {
                    resultado = dir;
                    break;
                }
            
        }
        
        return resultado;
    }

    public void updateFile(String textoLog, String ruta) throws MalformedURLException, IOException{
        
        URL url = new URL("ftp://" + user + ":" + password + "@" + server + ruta);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String textoFile = "";
        
        br.lines().forEach((line) -> addText(textoFile, line));
        br.close();
        addText(textoFile, textoLog);
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(url.openConnection().getOutputStream()));
        bw.write(textoFile);
        bw.flush();
        bw.close();
    }
    
    private void addText(String textoInicial, String textoAñadido){
        
        textoInicial += textoAñadido;
    }

    public void createDirectory(String ruta, String name) throws IOException{
        cliente.makeDirectory(ruta + "/" + name);
    }
    
    public void uploadFile(File f, String directory) throws IOException{
        
        // URL url = new URL("ftp://" + user + ":" + password + "@" + server + directory);
        // Se trabaja con cualquier tipo de archivo
        cliente.setFileType(FTP.BINARY_FILE_TYPE);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
        cliente.storeFile(f.getName(), bis);
        bis.close();
    }
    
    public void downloadFile(String name, String destiny) throws IOException{

        cliente.setFileType(FTP.BINARY_FILE_TYPE);
        FileOutputStream fos = new FileOutputStream(new File(destiny + "/" + name));
        cliente.retrieveFile(name, fos);    
    }
    
}