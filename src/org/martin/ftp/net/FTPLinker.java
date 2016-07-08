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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPReply;
import org.martin.ftp.util.Computer;
import org.martin.ftp.util.SortOption;

/**
 *
 * @author martin
 */
public class FTPLinker {
    
    private FTPClient client;
    private String server;
    private String user;
    private String password;
    private static FTPLinker ftpLinker;
        
//    public static FTPLinker getInstance(String server, String user, String password) throws IOException{
//        if(ftpLinker == null) ftpLinker = new FTPLinker(server, user, password);
//        return ftpLinker;
//    }
    
    /**
     * 
     * @param server Servidor ftp al cual conectarse
     * @param user Usuario ftp
     * @param password Constraseña del usuario, si el usuario
     * no tiene contraseña este parametro puede ser nulo
     * @throws IOException Excepcion si hay problemas de conexion
     */
    
    public FTPLinker(String server, String user, String password) throws IOException {
   
        this.server = server;
        this.user = user;
        this.password = password;
        if (this.password == null) this.password = "";
        
        client = new FTPClient();
        client.connect(server);
        client.login(user, password);
        client.setFileType(FTPClient.BINARY_FILE_TYPE);
    }

    public FTPFileFilter getFilter(Filter filter){
        
        return (FTPFile ftpf) -> {

            if (filter != null) {
                switch (filter) {
                    case FILES_ONLY:
                        return ftpf.isFile();
                    case DIRECTORIES_ONLY:
                        return ftpf.isDirectory();
                    default:
                        return true;
                }
            }
            else return true;
        };
    }

    public int getReply(){
        return client.getReplyCode();
    }
    
    public String getReplyCode(){
        return client.getReplyString();
    }
    
    public void reconnect() throws IOException{
        client.connect(server);
        client.login(user, password);
    }
    
    public boolean isConnected(){
        return FTPReply.isPositiveCompletion(client.getReplyCode());
    }
    
    public boolean existsFile(String name, String path) throws IOException{
        return Arrays.stream(getFilesAndDirectories(path)).anyMatch(
                (file) -> file.getName().equalsIgnoreCase(name));
    }
    
    public boolean existsFile(String name) throws IOException{
        return existsFile(name, getWorkingDirectory());
    }
    
    public void closeConnection() throws IOException{
        logout();
        disconnect();
    }
    
    public void logout() throws IOException{
        client.logout();
    }
    
    public void disconnect() throws IOException{
        client.disconnect();
    }

    /**
     * Ordena lista de archivos de acuerdo a los criterios especificados
     * @param files Lista a ordenar
     * @param option Criterio de ordenamiento(nombre, tamaño, formato, etc)
     * @param order Orden: ascendente o descendente;
     */
    
    public void orderFiles(LinkedList<FTPFile> files, SortOption option, SortOption order){
        Computer.orderRemoteFiles(files, option, order);
    }
    
    /**
     * Entrega una lista equivalente a la lista entregada pero con los objetos ordenados
     * segun los criterios definidos
     * @param files Lista a ordenar
     * @param option Criterio de ordenamiento(nombre, tamaño, formato, etc)
     * @param order Orden: ascendente o descendente;
     * @return Lista entregada como parametro pero con los elementos ya ordenados
     */
    
    public LinkedList<FTPFile> getOrderedFiles(LinkedList<FTPFile> files, SortOption option, SortOption order){
        Computer.orderRemoteFiles(files, option, order);
        return files;
    }

    /**
     * Retorna una lista del directorio actual ordenada según los criterios dados
     * @param option Criterio de ordenamiento(nombre, tamaño, formato, etc)
     * @param order Orden: ascendente o descendente;
     * @return Lista ordenada según los criterios de ordenamiento
     * @throws IOException En caso de haber problemas al acceder a los archivos
     */
    
    public LinkedList<FTPFile> getOrderedFiles(SortOption option, SortOption order) throws IOException{
        return getOrderedFiles(getWorkingDirectory());
    }
    
    /**
     * Retorna una lista del directorio dado ordenada según los criterios dados
     * @param directory Ruta del directorio
     * @param option Criterio de ordenamiento(nombre, tamaño, formato, etc)
     * @param order Orden: ascendente o descendente;
     * @return Lista ordenada según los criterios de ordenamiento
     * @throws IOException En caso de haber problemas al acceder a los archivos
     */
    
    public LinkedList<FTPFile> getOrderedFiles(String directory, SortOption option, SortOption order) throws IOException{
        
        LinkedList<FTPFile> files = getOrderedFiles(directory);
        return getOrderedFiles(files, option, order);
    }
    
    /**
     * Retorna una lista ordenada a partir de otra ya entrega, el resultado
     * es una nueva lista en la que se agrupan primero los directorios
     * y luego los archivos
     * @param files Lista a ordenar
     * @return Lista de archivos y carpetas creadas a partir 
     * del parametro, se ubican primero las carpetas y luego los
     * archivos
     */
    
    public LinkedList<FTPFile> getOrdererFiles(LinkedList<FTPFile> files){
        
        LinkedList<FTPFile> directories = new LinkedList<>();
        LinkedList<FTPFile> files2 = new LinkedList<>();
        files.stream().filter((d) -> d.isDirectory()).forEach((dir) -> directories.add(dir));
        files.stream().filter((f) -> !f.isDirectory()).forEach((file) -> files2.add(file));
        
        return files;
    }
    
    /**
     * Retorna una lista de archivos del directorio especificado
     * @param directory Ruta del directorio del cual se desea 
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
     * Retorna una lista con los archivos y carpetas de la ruta actual ordenados
     * primero por directorios y luego los archivos
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
    
    public FTPClient getClient() {
        return client;
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
        client.cwd(directorio);
        //client.changeWorkingDirectory(directorio);
    }
    
    /**
     * Se cambia el directorio actual por el directorio padre
     * @throws IOException 
     */
    
    public void setToParentDirectory() throws IOException{
        client.changeToParentDirectory();
    }
    
    /**
     * Se retorna al directorio anterior
     * @throws IOException 
     * @deprecated  Reemplazado por setToParentDirectory, metodo que realiza
     * la misma operacion
     */
    
    @Deprecated
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

    public void setToRootDirectory() throws IOException{
        client.changeWorkingDirectory("/");
    }
    
    /**
     * 
     * Se obtiene el directorio actual de trabajo
     * @return Directorio actual de trabajo
     * @throws IOException 
     */

    public String getWorkingDirectory() throws IOException{
        return client.printWorkingDirectory();
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

    public String[] getNames(String directory) throws IOException{
        return client.listNames(directory);
    }
    
    public String[] getNames() throws IOException{
        return getNames(getWorkingDirectory());
    }
    
    /**
     * Metodo que obtiene un array con solo los archivos del actual directorio
     * @return Archivos del actual directorio
     * @throws IOException 
     */
    
    public FTPFile[] getFiles() throws IOException{
        return getFiles(getWorkingDirectory());
    }

    /**
     * Metodo utilizado para sumar contadores
     * a través de métodos
     * @param counter variable contador
     * @return el valor de la variable más 1
     * Ej: Si el contador vale 4, el retorno será
     * 5
     */
    
    private int increaseCounter(int counter){
        return counter+1;
    }

    /**
     * Metodo que devuelve el nombre de un archivo o 
     * carpeta desde su ruta
     * @param path Ruta del archivo
     * @return Nombre del archivo solicitado
     */
    
    private String getFileName(String path){
        String[] split = path.split("/");
        return split[split.length-1];
    }

    /**
     * Método que retorna la ruta de la carpeta padre
     * del archivo entregado, para esto
     * solo entregaremos la ruta de éste
     * @param filePath Ruta del archivo solicitado
     * @return Ruta de la carpeta padre de éste
     */
    
    private String getParentFolder(String filePath){
        
        String[] split = filePath.split("/");
        int cantSlashs = split.length-1;
        String pathParentFolder = "";
        
        for (int i = 0; i < cantSlashs; i++) 
            pathParentFolder+=(split[i]) + "/";
        
        return pathParentFolder;
    }
    
    /**
     * Metodo que entrega un array con solo los archivos de un directorio
     * en especifico
     * @param directory directorio a analizar
     * @return Array de archivos del directorio;
     * @throws IOException 
     */
    
    public FTPFile[] getFiles(String directory) throws IOException{

        return client.listFiles(directory, getFilter(Filter.FILES_ONLY));
        
//        FTPFile[] archivos = new FTPFile[listFiles.size()];
//        
//        for (int i = 0; i < listFiles.size(); i++) archivos[i] = listFiles.get(i);
//        
//
//        /*
//            Verificar funcionamiento
//            System.arraycopy(filesCliente, 0, arrayFiles, 0, filesCliente.length);
//        */
//        return archivos;
        
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
        return client.listDirectories();
    }
    
    public FTPFile[] getDirectories(String directory) throws IOException{
        return client.listDirectories(directory);
    }
    
    public FTPFile[] getFilesAndDirectories() throws IOException{
        return client.listFiles();
    }
    
    public FTPFile[] getFilesAndDirectories(String directory) throws IOException{
        return client.listFiles(directory);
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
        if (ruta.endsWith("/")) client.makeDirectory(ruta + name);
        else client.makeDirectory(ruta + "/" + name);
    }
    
    public void uploadFile(File f, String directory) throws IOException{
        
        // URL url = new URL("ftp://" + user + ":" + password + "@" + server + directory);
        // Se trabaja con cualquier tipo de archivo
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
        // client.storeFile(f.getName(), bis);
        client.appendFile(f.getName(), bis);
        System.out.println(getReplyCode());
        bis.close();
    }
    
    public void uploadFolder(File folder, String remotePath) throws IOException{
        
        createDirectory(remotePath, folder.getName());
        String newPath = "";
        
        if(remotePath.endsWith("/"))  newPath = remotePath + folder.getName();
        else newPath  = remotePath + "/" + folder.getName();
        
        for (File file : folder.listFiles())
            if(file.isDirectory()) uploadFolder(file, newPath);
            else uploadFile(file, newPath);
    
    }
    public void uploadFolder(File folder) throws IOException{
        uploadFolder(folder, getWorkingDirectory());
    }
    
    public void downloadFile(String name, String destiny) throws IOException{

        client.setFileType(FTP.BINARY_FILE_TYPE);
        FileOutputStream fos = new FileOutputStream(new File(destiny + "/" + name));
        client.retrieveFile(name, fos);    
    }
    
    public void delete(FTPFile file, Type type) throws IOException{
        
        String path = getWorkingDirectory() + "/" + file.getName();
        
        if (type == Type.FILE) 
            client.deleteFile(path);
        else
            client.removeDirectory(path);
    }
    
    public void rename(FTPFile file, String newName) throws IOException{
        client.rename(file.getName(), newName);
        System.out.println(getReplyCode());
    }
    
    private void extractFile(FileOutputStream fos, ZipEntry entry,
         int ready, byte[] buffer, ZipInputStream zis) throws FileNotFoundException, IOException{
         fos = new FileOutputStream(entry.getName());
         ready = 0;
         buffer = new byte[1024];
         while ((ready = zis.read(buffer)) > 0)
            fos.write(buffer, 0, ready);
         fos.close();
         zis.closeEntry();
    }
    
    public void uploadZipFile(File file) throws FileNotFoundException, IOException{

        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        LinkedList<File> filesIn = new LinkedList<>();

        FileOutputStream fos = null;
        int ready = 0;
        byte[] buffer = null;
        HashMap<String, Boolean> entryNames = new HashMap<>();
        // Extraccion de archivos   
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {            
            extractFile(fos, entry, ready, buffer, zis);
            entryNames.put(entry.getName(), entry.isDirectory());
        }
        zis.close();
        // Extraccion de archivos
        
        // Añadir archivos a la lista para su posterior subida y eliminacion local
        
        File f;
        
        for (Iterator<Map.Entry<String, Boolean>> it = entryNames.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Boolean> next = it.next();
            f = new File(file.getAbsolutePath() + "/" + next.getKey());
            if (next.getValue())
                uploadFolder(f, getWorkingDirectory());
            else
                uploadFile(f, getWorkingDirectory());
            f.delete();
        } 
        
        // Añadir archivos a la lista para su posterior subida y eliminacion local
    }
}