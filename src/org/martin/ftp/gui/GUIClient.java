/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.martin.ftp.gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.net.ftp.FTPFile;
import org.martin.ftp.util.Encryptor;
import org.martin.ftp.model.TCRFiles;
import org.martin.ftp.model.TMFiles;
import org.martin.ftp.net.FTPLinker;
import org.martin.ftp.net.Tester;
import org.martin.ftp.config.Setting;
import org.martin.ftp.util.Utilities;
import org.martin.ftp.model.TMSearch;
import org.martin.ftp.net.FileFiltering;
import org.martin.ftp.net.HUpdaterConsole;
import org.martin.ftp.net.Searcher;
import org.martin.ftp.updates.UpdatesReviewer;
import org.martin.ftp.util.Computer;
import org.martin.ftp.util.FilterSelector;
import org.martin.ftp.util.SortOption;
import static org.martin.ftp.util.Utilities.openWindow;
import static org.martin.ftp.util.Utilities.resize;
import static org.martin.ftp.util.Utilities.openWindow;
import static org.martin.ftp.util.Utilities.resize;
/**
 *
 * @author martin
 */
public class GUIClient extends javax.swing.JFrame {

    final String pathIconFolder = "/org/martin/ftp/resources/folderMain.png";
    final String pathIconCloud = "/org/martin/ftp/resources/cloud1.png";
    FTPLinker linker;
    String directorioActual;
    JFileChooser fileChoos;
    boolean hasInternetConnection;
    boolean isConnectedToHost;
    Setting settings;
    LinkedList<Thread> threadsUploaders;
    UpdatesReviewer reviewer;
    Searcher searcher;
    Thread tSearcher;
    Thread tUploader;
    int selectedIndex;
    HUpdaterConsole consoleUpdater;
    
    public GUIClient() {

        threadsUploaders = new LinkedList<>();
//        try {
//            reviewer = new UpdatesReviewer();
//        } catch (IOException ex) {
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
        initComponents();
        formManagement.setDefaultCloseOperation(EXIT_ON_CLOSE);
        directorioActual = "/";
        setLocationRelativeTo(null);
        fileChoos = new JFileChooser();
        fileChoos.addChoosableFileFilter(getAllFileFilter());
        fileChoos.addChoosableFileFilter(getDirectoryFilter());
        fileChoos.setMultiSelectionEnabled(true);
        setResizable(false);
        Utilities.resize(dialogNewFolder);
        dialogFileOptions.setResizable(false);
        dialogSearchLoader.setSize(316, 240);
        dialogSearchLoader.setResizable(false);
        dialogUploadOptions.setSize(400, 130);
        dialogUploadOptions.setResizable(false);
        formSearch.setSize(515, 264);
        try {
            settings = new Setting();

            if(settings.hasIconConfigSaved())
                        lblIcon.setIcon(new ImageIcon(getClass().getResource(settings.getIcon())));
            
            if (settings.isAutoLogin()) {
                connect(settings.getHost(), settings.getUser(), settings.getPassword());
                fillFields();
            }else{
                
                if (settings.hasSaveConfigs()) {
                    fillFields();
                    btnConnect.requestFocus();
                }
                show();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private void restoreConfigs() throws IOException{
        settings.deleteSettings();
        lblIcon.setIcon(new ImageIcon(getClass().getResource(pathIconFolder)));
    }
    
    private void fillFields(){
        txtServer.setText(settings.getHost());
        txtUser.setText(settings.getUser());
        txtPassword.setText(settings.getPassword());
    }
    
    // Esconder botones sin funcionalidad
    
    private void hideButtons(JButton... buttons){
        for (JButton button : buttons) button.hide();
    }
    
    private FileFilter getDirectoryFilter(){
        
        return new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }
            @Override
            public String getDescription() {
                return "Solo directorios";
            }
        };
    }
    
    private FileFilter getAllFileFilter(){
        return new FileFilter() {

            @Override
            public boolean accept(File f) {
                return true;
            }

            @Override
            public String getDescription() {
                return "Escoja el archivo";
            }
        };
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formManagement = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNewDir = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFiles = new javax.swing.JTable();
        btnUploadFile = new javax.swing.JButton();
        btnUpdateDirectory = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnAddFolder = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cboOrderOption = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cboOrderType = new javax.swing.JComboBox<>();
        btnOrderFiles = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        itemConsole = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        formSearch = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtSearchFilter = new javax.swing.JTextField();
        btnCancelSearch = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSearch = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        cboOrderOptionS = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cboOrderTypeS = new javax.swing.JComboBox<>();
        btnOrderFilesS = new javax.swing.JButton();
        formConsole = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        dialogNewFolder = new javax.swing.JDialog();
        panelDialog = new javax.swing.JPanel();
        txtNewFolder = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        dialogUploadOptions = new javax.swing.JDialog();
        panelUploadOptions = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnFolderOption = new javax.swing.JButton();
        btnFileOption = new javax.swing.JButton();
        btnZipOption = new javax.swing.JButton();
        dialogViewConfigs = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMyHost = new javax.swing.JTextField();
        txtMyUser = new javax.swing.JTextField();
        txtMyPass = new javax.swing.JTextField();
        btnSetData = new javax.swing.JButton();
        btnResetAll = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnCloseViewConfigs = new javax.swing.JButton();
        dialogFileOptions = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnDeleteFile = new javax.swing.JButton();
        btnRenameFile = new javax.swing.JButton();
        btnDownloadFile = new javax.swing.JButton();
        btnDownloadFile1 = new javax.swing.JButton();
        dialogRename = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        txtNewName = new javax.swing.JTextField();
        dialogSetIcon = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblFolderIcon = new javax.swing.JLabel();
        lblCloudIcon = new javax.swing.JLabel();
        dialogSearchLoader = new javax.swing.JDialog();
        lblSearching = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtServer = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnConnect = new javax.swing.JButton();
        lblIcon = new javax.swing.JLabel();
        chkAuto = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        formManagement.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formManagementWindowClosing(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cambiar Directorio"));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Directorio actual: ");

        txtNewDir.setToolTipText("Si lo desea puede cambiar el directorio actual de trabajo. Cuando termine de escribir la ruta pulse Enter para hacer efectivo el cambio.");
        txtNewDir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewDirKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewDir))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNewDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Archivos y carpetas"));
        jPanel3.setLayout(new java.awt.BorderLayout());

        tblFiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblFilesMouseReleased(evt);
            }
        });
        tblFiles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblFilesKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblFiles);

        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        btnUploadFile.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnUploadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/upload.png"))); // NOI18N
        btnUploadFile.setToolTipText("Subir");
        btnUploadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadFileActionPerformed(evt);
            }
        });

        btnUpdateDirectory.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnUpdateDirectory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/update.png"))); // NOI18N
        btnUpdateDirectory.setToolTipText("Actualizar");
        btnUpdateDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateDirectoryActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/goBack.png"))); // NOI18N
        btnBack.setToolTipText("Volver Atras");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnAddFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/newFolder.png"))); // NOI18N
        btnAddFolder.setToolTipText("Nueva Carpeta");
        btnAddFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFolderActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/exit.png"))); // NOI18N
        jButton1.setToolTipText("Cerrar Sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/search2.png"))); // NOI18N
        btnSearch.setToolTipText("Buscar archivos y/o directorios");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/home.png"))); // NOI18N
        jButton3.setToolTipText("Volver a raiz");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cboOrderOption.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        cboOrderOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Tamaño", "Fecha", "Tipo", "Formato" }));

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel13.setText("Ordenar elementos por: ");

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel14.setText("Orden: ");

        cboOrderType.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        cboOrderType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));

        btnOrderFiles.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnOrderFiles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/orderFiles.png"))); // NOI18N
        btnOrderFiles.setToolTipText("Ordenar");
        btnOrderFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderFilesActionPerformed(evt);
            }
        });

        jMenu3.setText("File");

        jMenuItem6.setText("Cerrar Sesión");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Help");

        itemConsole.setText("Consola");
        itemConsole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsoleActionPerformed(evt);
            }
        });
        jMenu4.add(itemConsole);

        jMenuItem4.setText("Acerca de...");
        jMenuItem4.setEnabled(false);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setText("Comprobar Actualizaciones");
        jMenuItem5.setEnabled(false);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar2.add(jMenu4);

        formManagement.setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout formManagementLayout = new javax.swing.GroupLayout(formManagement.getContentPane());
        formManagement.getContentPane().setLayout(formManagementLayout);
        formManagementLayout.setHorizontalGroup(
            formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formManagementLayout.createSequentialGroup()
                .addGroup(formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formManagementLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(formManagementLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(formManagementLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formManagementLayout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUploadFile, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(formManagementLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboOrderOption, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboOrderType, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnOrderFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        formManagementLayout.setVerticalGroup(
            formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formManagementLayout.createSequentialGroup()
                        .addGroup(formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnUploadFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdateDirectory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(formManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cboOrderOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(cboOrderType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnOrderFiles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );

        formSearch.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formSearchWindowOpened(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Patrones de búsqueda"));

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel12.setText("Filtro:");

        txtSearchFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchFilterKeyReleased(evt);
            }
        });

        btnCancelSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/removeFIle2.png"))); // NOI18N
        btnCancelSearch.setToolTipText("Cancelar Búsqueda");
        btnCancelSearch.setEnabled(false);
        btnCancelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSearchFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));
        jPanel8.setLayout(new java.awt.BorderLayout());

        tblSearch.setModel(new TMSearch(new LinkedList()));
        tblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSearchMouseReleased(evt);
            }
        });
        tblSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblSearchKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblSearch);

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel17.setText("Ordenar por: ");

        cboOrderOptionS.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        cboOrderOptionS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Tamaño", "Fecha", "Tipo", "Formato" }));
        cboOrderOptionS.setEnabled(false);

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel18.setText("Orden: ");

        cboOrderTypeS.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        cboOrderTypeS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        cboOrderTypeS.setEnabled(false);

        btnOrderFilesS.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnOrderFilesS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/orderFiles.png"))); // NOI18N
        btnOrderFilesS.setToolTipText("Ordenar");
        btnOrderFilesS.setEnabled(false);
        btnOrderFilesS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderFilesSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout formSearchLayout = new javax.swing.GroupLayout(formSearch.getContentPane());
        formSearch.getContentPane().setLayout(formSearchLayout);
        formSearchLayout.setHorizontalGroup(
            formSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(formSearchLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboOrderOptionS, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboOrderTypeS, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrderFilesS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formSearchLayout.setVerticalGroup(
            formSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(formSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(cboOrderOptionS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)
                        .addComponent(cboOrderTypeS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnOrderFilesS))
                .addGap(12, 12, 12)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
        );

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola"));

        txtConsole.setEditable(false);
        txtConsole.setBackground(java.awt.Color.black);
        txtConsole.setColumns(20);
        txtConsole.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        txtConsole.setForeground(java.awt.Color.green);
        txtConsole.setRows(5);
        jScrollPane3.setViewportView(txtConsole);

        formConsole.getContentPane().add(jScrollPane3, java.awt.BorderLayout.CENTER);

        panelDialog.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 255, 102), null));
        panelDialog.setPreferredSize(new java.awt.Dimension(404, 65));

        txtNewFolder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewFolderKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel6.setText("Nombre de la carpeta:");

        javax.swing.GroupLayout panelDialogLayout = new javax.swing.GroupLayout(panelDialog);
        panelDialog.setLayout(panelDialogLayout);
        panelDialogLayout.setHorizontalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNewFolder, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDialogLayout.setVerticalGroup(
            panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNewFolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        dialogNewFolder.getContentPane().add(panelDialog, java.awt.BorderLayout.CENTER);

        dialogUploadOptions.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dialogUploadOptionsWindowOpened(evt);
            }
        });

        panelUploadOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 255, 102), null));

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("¿Que desea subir?");

        btnFolderOption.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnFolderOption.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/uploadFolder1.png"))); // NOI18N
        btnFolderOption.setText("Carpetas");
        btnFolderOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFolderOptionActionPerformed(evt);
            }
        });

        btnFileOption.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnFileOption.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/uploadFile.png"))); // NOI18N
        btnFileOption.setText("Archivos");
        btnFileOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileOptionActionPerformed(evt);
            }
        });

        btnZipOption.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnZipOption.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/zipFile.png"))); // NOI18N
        btnZipOption.setText("ZIP");
        btnZipOption.setEnabled(false);
        btnZipOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZipOptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelUploadOptionsLayout = new javax.swing.GroupLayout(panelUploadOptions);
        panelUploadOptions.setLayout(panelUploadOptionsLayout);
        panelUploadOptionsLayout.setHorizontalGroup(
            panelUploadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadOptionsLayout.createSequentialGroup()
                .addGroup(panelUploadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUploadOptionsLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUploadOptionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnFileOption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFolderOption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnZipOption)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        panelUploadOptionsLayout.setVerticalGroup(
            panelUploadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUploadOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(panelUploadOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFileOption)
                    .addComponent(btnFolderOption)
                    .addComponent(btnZipOption))
                .addContainerGap())
        );

        dialogUploadOptions.getContentPane().add(panelUploadOptions, java.awt.BorderLayout.CENTER);

        dialogViewConfigs.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dialogViewConfigsWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dialogViewConfigsWindowClosing(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuraciones de Usuario"));

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel8.setText("Servidor: ");

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel9.setText("Usuario: ");

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jLabel10.setText("Contraseña (encriptada): ");

        txtMyHost.setEnabled(false);
        txtMyHost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMyHostKeyReleased(evt);
            }
        });

        txtMyUser.setEnabled(false);
        txtMyUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMyUserKeyReleased(evt);
            }
        });

        txtMyPass.setEnabled(false);

        btnSetData.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnSetData.setText("Cambiar Datos");
        btnSetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetDataActionPerformed(evt);
            }
        });

        btnResetAll.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnResetAll.setText("Reestablecer Todo");
        btnResetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addComponent(btnResetAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSetData, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(txtMyPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMyUser, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMyHost, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMyHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtMyUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtMyPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSetData)
                    .addComponent(btnResetAll))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dialogViewConfigs.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        btnCloseViewConfigs.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        btnCloseViewConfigs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/close1.png"))); // NOI18N
        btnCloseViewConfigs.setToolTipText("Cerrar Cuadro");
        btnCloseViewConfigs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseViewConfigsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addComponent(btnCloseViewConfigs, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCloseViewConfigs)
                .addContainerGap())
        );

        dialogViewConfigs.getContentPane().add(jPanel10, java.awt.BorderLayout.PAGE_START);

        dialogFileOptions.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dialogFileOptionsWindowOpened(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("¿Que desea hacer?");

        btnDeleteFile.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnDeleteFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/removeFile1.png"))); // NOI18N
        btnDeleteFile.setText("Eliminar");
        btnDeleteFile.setToolTipText("Eliminar");
        btnDeleteFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFileActionPerformed(evt);
            }
        });

        btnRenameFile.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnRenameFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/renameFile.png"))); // NOI18N
        btnRenameFile.setText("Renombrar");
        btnRenameFile.setToolTipText("Renombrar");
        btnRenameFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameFileActionPerformed(evt);
            }
        });

        btnDownloadFile.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnDownloadFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/download.png"))); // NOI18N
        btnDownloadFile.setText("Descargar");
        btnDownloadFile.setToolTipText("Descargar");
        btnDownloadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadFileActionPerformed(evt);
            }
        });

        btnDownloadFile1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        btnDownloadFile1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/goBack.png"))); // NOI18N
        btnDownloadFile1.setText("Regresar");
        btnDownloadFile1.setToolTipText("Regresar");
        btnDownloadFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadFile1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDeleteFile)
                .addGap(18, 18, 18)
                .addComponent(btnRenameFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDownloadFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDownloadFile1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRenameFile)
                    .addComponent(btnDeleteFile)
                    .addComponent(btnDownloadFile)
                    .addComponent(btnDownloadFile1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        dialogFileOptions.getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingrese el nuevo nombre del archivo"));

        txtNewName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewNameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNewName, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtNewName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        dialogRename.getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        dialogSetIcon.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dialogSetIconWindowOpened(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Escoja el icono deseado");

        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        lblFolderIcon.setBackground(java.awt.Color.lightGray);
        lblFolderIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFolderIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/folderMain.png"))); // NOI18N
        lblFolderIcon.setToolTipText("Icono de carpeta");
        lblFolderIcon.setOpaque(true);
        lblFolderIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblFolderIconMouseReleased(evt);
            }
        });
        jPanel11.add(lblFolderIcon);

        lblCloudIcon.setBackground(java.awt.Color.lightGray);
        lblCloudIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCloudIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/cloud1.png"))); // NOI18N
        lblCloudIcon.setToolTipText("Icono de nube");
        lblCloudIcon.setOpaque(true);
        lblCloudIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblCloudIconMouseReleased(evt);
            }
        });
        jPanel11.add(lblCloudIcon);

        javax.swing.GroupLayout dialogSetIconLayout = new javax.swing.GroupLayout(dialogSetIcon.getContentPane());
        dialogSetIcon.getContentPane().setLayout(dialogSetIconLayout);
        dialogSetIconLayout.setHorizontalGroup(
            dialogSetIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(dialogSetIconLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        dialogSetIconLayout.setVerticalGroup(
            dialogSetIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogSetIconLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        dialogSearchLoader.setMinimumSize(new java.awt.Dimension(316, 216));
        dialogSearchLoader.setPreferredSize(new java.awt.Dimension(316, 240));
        dialogSearchLoader.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                dialogSearchLoaderWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dialogSearchLoaderWindowClosing(evt);
            }
        });

        lblSearching.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        lblSearching.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearching.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/searching1.gif"))); // NOI18N
        lblSearching.setText("Buscando...");
        lblSearching.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 255), null));
        lblSearching.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblSearching.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        dialogSearchLoader.getContentPane().add(lblSearching, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Conectar a"));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Servidor:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Contraseña:");

        txtServer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtServerFocusLost(evt);
            }
        });
        txtServer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtServerKeyReleased(evt);
            }
        });

        txtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserFocusLost(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
        });

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        btnConnect.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/connect.png"))); // NOI18N
        btnConnect.setText("Conectar");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/martin/ftp/resources/folderMain.png"))); // NOI18N

        chkAuto.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        chkAuto.setText("Iniciar Sesion automaticamante");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkAuto)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnConnect)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtServer)
                                .addComponent(txtUser)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAuto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConnect)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem3.setText("Ver Configuraciones");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem1.setText("Reestablecer configuraciones");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem7.setText("Cambiar Icono");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed

        String server = txtServer.getText();
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        if (server == null) server = "";
        if (user == null) user = "";
        
        try {
            if (server.isEmpty() || user.isEmpty()) 
                JOptionPane.showMessageDialog(
                        this, 
                        "Por Favor rellene todos los campos", 
                        "Error", 
                        JOptionPane.WARNING_MESSAGE);
            
            else connect(server, user, pass);

        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void txtServerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtServerKeyReleased

        String text = txtServer.getText();
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){ 
            
            txtUser.requestFocus();
            if (!txtUser.getText().isEmpty()) 
                txtUser.selectAll();
            
        }
    }//GEN-LAST:event_txtServerKeyReleased

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased

        String text = txtUser.getText();
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){ 
            
            if (!text.trim().isEmpty()) txtUser.setText(text.trim());
                
            txtPassword.requestFocus();
            if (!txtPassword.getText().isEmpty()) 
                txtPassword.selectAll();
            
        }
        
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
            btnConnect.doClick();
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void tblFilesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFilesMouseReleased

        if (evt.getButton() == 3) {
            
        }
        
        if (evt.getClickCount() == 2) try {
            setWorkingDirectory();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tblFilesMouseReleased

    private void txtNewDirKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewDirKeyReleased

        String dir = txtNewDir.getText().trim();
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) 
            if (!dir.isEmpty()) {
                try {
                    linker.setWorkingDirectory(dir);
                    updateTable();
                } catch (IOException ex) {
                    Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }//GEN-LAST:event_txtNewDirKeyReleased

    private void btnUploadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadFileActionPerformed
        dialogUploadOptions.show();
    }//GEN-LAST:event_btnUploadFileActionPerformed

    private void formManagementWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formManagementWindowClosing

        try {
            linker.logout();
            linker.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formManagementWindowClosing

    private void btnUpdateDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateDirectoryActionPerformed

        try {
            updateTable();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateDirectoryActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            linker.setToParentDirectory();
            updateTable();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void tblFilesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFilesKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) try {
            setWorkingDirectory();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblFilesKeyPressed

    private void txtNewFolderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewFolderKeyReleased

        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            String nameFolder = txtNewFolder.getText().trim();
            
            if(!nameFolder.isEmpty()){
                try {
                    linker.createDirectory(directorioActual, nameFolder);
                    updateTable();
                } catch (IOException ex) {
                    Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            txtNewFolder.setText(null);
            dialogNewFolder.hide();
        }
    }//GEN-LAST:event_txtNewFolderKeyReleased

    private void btnAddFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFolderActionPerformed

        openWindow(dialogNewFolder, null);
        if(dialogNewFolder.isResizable()) dialogNewFolder.setResizable(false);
        if (!txtNewFolder.isFocusable()) txtNewFolder.requestFocus();
        
    }//GEN-LAST:event_btnAddFolderActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        try {
            restoreConfigs();
            JOptionPane.showMessageDialog(this, "Configuraciones reestablecidas");
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            if(hasAliveThreads()){
                
                int opcion = JOptionPane.showConfirmDialog(formManagement, 
                        "Aun quedan operaciones pendientes\n\t¿Desea cancelar todo y salir?", 
                        "Confirmacion", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                
                if (opcion == JOptionPane.YES_OPTION) {
                    cancellAllLoads();
                    closeSession();
                }
                
            }
            
            else closeSession();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dialogUploadOptionsWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogUploadOptionsWindowOpened
        // openWindow(dialogUploadOptions, formManagement);
        dialogUploadOptions.setLocationRelativeTo(formManagement);
    }//GEN-LAST:event_dialogUploadOptionsWindowOpened

    private void btnFileOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileOptionActionPerformed

        dialogUploadOptions.hide();
        fileChoos.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int selection = fileChoos.showOpenDialog(formManagement);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChoos.getSelectedFiles();
            if (selectedFiles != null) {
                Thread t = new Thread(() -> {
                    try {
                        for (File file : selectedFiles)
                            if(file.isFile())
                                linker.uploadFile(file, directorioActual);
                        updateTable();
                    } catch (IOException ex) {
                        Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                t.start();
                threadsUploaders.add(t);
            }
        }
        
    }//GEN-LAST:event_btnFileOptionActionPerformed

    private void btnFolderOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFolderOptionActionPerformed

        dialogUploadOptions.hide();
        fileChoos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChoos.showOpenDialog(formManagement);
        File[] selectedFiles = fileChoos.getSelectedFiles();
        
        if (selectedFiles != null) {
            
            tUploader = new Thread(() -> {
                try {
                    for (File file : selectedFiles){
                        if(file.isDirectory())
                            linker.uploadFolder(file, linker.getWorkingDirectory());
                        
                        else
                            linker.uploadFile(file, directorioActual);
                    }
                    updateTable();
                } catch (IOException ex) {
                    Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            tUploader.start();
            threadsUploaders.add(tUploader);
        }
    }//GEN-LAST:event_btnFolderOptionActionPerformed

    private void dialogViewConfigsWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogViewConfigsWindowOpened
        Utilities.resize(dialogViewConfigs, null);
    }//GEN-LAST:event_dialogViewConfigsWindowOpened

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        if (settings.hasSaveConfigs()) {
            dialogViewConfigs.show();
            txtMyHost.setText(settings.getHost());
            txtMyUser.setText(settings.getUser());
            txtMyPass.setText(
                    Utilities.getTransformedPassword(Encryptor.decrypt(settings.getPassword())));
        }
        else{
            JOptionPane.showMessageDialog(this, 
                    "No existen configuraciones de usuario", 
                    "Mensaje", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void btnSetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetDataActionPerformed

        try {
            if (btnSetData.getText().equalsIgnoreCase("Cambiar Datos")) {
                btnSetData.setText("Confirmar");
                Utilities.setActivation(txtMyHost, txtMyUser, txtMyPass);
                txtMyHost.selectAll();
                txtMyHost.requestFocus();
            }
            
            else{
                int opcion = JOptionPane.showConfirmDialog(dialogViewConfigs, 
                        "¿Ha ingresado los datos correctamente?", 
                        "Confirmacion", 
                        JOptionPane.YES_NO_OPTION);
                int opcionSi = JOptionPane.YES_OPTION;
                
                if (opcion == opcionSi) {
                    btnSetData.setText("Cambiar Datos");
                    settings.setHost(txtMyHost.getText().trim());
                    settings.setUser(txtMyUser.getText());
                    settings.setPassword(Encryptor.encrypt(txtMyPass.getText()));
                    JOptionPane.showMessageDialog(dialogViewConfigs, "Datos modificados exitosamente");
                    Utilities.disableAll(txtMyHost, txtMyUser, txtMyPass);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSetDataActionPerformed

    private void btnResetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetAllActionPerformed

        try {
            restoreConfigs();
            if (Utilities.isActiveComponents(txtMyHost, txtMyUser, txtMyPass))
                Utilities.disableAll(txtMyHost, txtMyUser, txtMyPass);
            Utilities.cleanTextFields(txtMyHost, txtMyUser, txtMyPass);
            clearComponents(txtServer, txtUser, txtPassword);
            
            if (!btnSetData.getText().equalsIgnoreCase("Cambiar Datos")) 
                btnSetData.setText("Cambiar Datos");
            
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }//GEN-LAST:event_btnResetAllActionPerformed

    private void dialogViewConfigsWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogViewConfigsWindowClosing

        if (!btnSetData.getText().equalsIgnoreCase("Cambiar Datos")) 
            btnSetData.setText("Cambiar Datos");
        
    }//GEN-LAST:event_dialogViewConfigsWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            linker.setToRootDirectory();
            updateTable();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

        try {
            if (reviewer.hasNewVersion()) {
                int options = JOptionPane.showConfirmDialog(formManagement,
                        "Hay una nueva version disponible ¿Desea descargarla?",
                        "Actualizacion",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                
                if (options == JOptionPane.YES_OPTION) {
                    
                }
                else{
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void dialogFileOptionsWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogFileOptionsWindowOpened
        Utilities.resize(dialogFileOptions, formManagement);
    }//GEN-LAST:event_dialogFileOptionsWindowOpened

    private void btnDownloadFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadFileActionPerformed
        
        try {
            FTPFile selected = getSelectedFile();
            downloadFile(selected);
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDownloadFileActionPerformed

    private void btnDownloadFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadFile1ActionPerformed

        dialogFileOptions.hide();
    }//GEN-LAST:event_btnDownloadFile1ActionPerformed

    private void btnDeleteFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFileActionPerformed

        try {
            FTPFile selected = getSelectedFile();
            linker.delete(selected, org.martin.ftp.net.Type.FILE);
            updateTable();
            dialogFileOptions.hide();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteFileActionPerformed

    private void btnRenameFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameFileActionPerformed
        
        try {
            openWindow(dialogRename, dialogFileOptions);
            dialogRename.setResizable(false);
            updateTable();
            dialogFileOptions.hide();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRenameFileActionPerformed

    private void txtSearchFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchFilterKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            String filter = txtSearchFilter.getText().trim();
            
            if (!filter.isEmpty()) {
                
                tSearcher = new Thread(() -> {
                    btnCancelSearch.setEnabled(true);
                    openWindow(dialogSearchLoader, formSearch);
                    txtSearchFilter.setEnabled(false);
                    try {
                        Searcher.getInstance(linker, tblSearch).search(filter);
                    } catch (IOException ex) {
                        Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dialogSearchLoader.hide();
                    txtSearchFilter.setEnabled(true);
                    btnCancelSearch.setEnabled(false);
                    if (Searcher.getInstance(linker, tblSearch).hasResults()) {
                        cboOrderOptionS.setEnabled(true);
                        cboOrderTypeS.setEnabled(true);
                        btnOrderFilesS.setEnabled(true);
                    }
                    else if (cboOrderOptionS.isEnabled() && cboOrderTypeS.isEnabled() && btnOrderFilesS.isEnabled()) {
                        cboOrderOptionS.setEnabled(false);
                        cboOrderTypeS.setEnabled(false);
                        btnOrderFilesS.setEnabled(false);
                    }
                    
                    JOptionPane.showMessageDialog(formSearch, "Busqueda Finalizada");
                });
                tSearcher.start();
            }
        }
    }//GEN-LAST:event_txtSearchFilterKeyReleased

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        openWindow(formSearch, formManagement);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void formSearchWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formSearchWindowOpened

    }//GEN-LAST:event_formSearchWindowOpened

    private void txtNewNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewNameKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            String text = txtNewName.getText();
            
            if (!text.isEmpty()) {
                try {
                    FTPFile selected = getFile(selectedIndex);
                    linker.rename(selected, text.trim());
                    updateTable();
                    JOptionPane.showMessageDialog(dialogFileOptions, "Archivo renombrado exitosamente");
                } catch (IOException ex) {
                    Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dialogRename.hide();
        }
    }//GEN-LAST:event_txtNewNameKeyReleased

    private void tblSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSearchKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) try {
            goToFile();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblSearchKeyPressed

    private void tblSearchMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSearchMouseReleased

        if (evt.getClickCount() == 2) try {
            goToFile();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tblSearchMouseReleased

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed

        try {
            if(hasAliveThreads()){
                
                int opcion = JOptionPane.showConfirmDialog(formManagement, 
                        "Aun quedan operaciones pendientes\n\t¿Desea cancelar todo y salir?", 
                        "Confirmacion", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                
                if (opcion == JOptionPane.YES_OPTION) {
                    cancellAllLoads();
                    closeSession();
                }
            }
            
            else closeSession();
        } catch (IOException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void txtMyHostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMyHostKeyReleased

        String text = txtMyHost.getText();
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            
            if (!text.isEmpty()) {
                txtMyHost.setText(text.trim());
            }
            
            if (!txtMyUser.getText().trim().isEmpty()) txtMyUser.selectAll();
            txtMyUser.requestFocus();
        }
    }//GEN-LAST:event_txtMyHostKeyReleased

    private void txtMyUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMyUserKeyReleased

        String text = txtMyUser.getText();
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            
            if (!text.isEmpty()) {
                txtMyUser.setText(text.trim());
            }
            
            if (!txtMyPass.getText().trim().isEmpty()) txtMyPass.selectAll();
            txtMyPass.requestFocus();
        }
    }//GEN-LAST:event_txtMyUserKeyReleased

    private void btnCloseViewConfigsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseViewConfigsActionPerformed

        dialogViewConfigs.hide();
        txtServer.requestFocus();
        txtServer.selectAll();
    }//GEN-LAST:event_btnCloseViewConfigsActionPerformed

    private void lblFolderIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFolderIconMouseReleased
        if (evt.getClickCount() >= 2){
            try {
                lblIcon.setIcon(lblFolderIcon.getIcon());
                settings.setIcon(pathIconFolder);
                lblFolderIcon.setBackground(Color.CYAN);
                dialogSetIcon.hide();
            } catch (IOException ex) {
                Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        else{
            lblFolderIcon.setBackground(Color.CYAN);
            lblCloudIcon.setBackground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_lblFolderIconMouseReleased

    private void lblCloudIconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloudIconMouseReleased

        if (evt.getClickCount() >= 2) {
            try {
                lblIcon.setIcon(lblCloudIcon.getIcon());
                settings.setIcon(pathIconCloud);
                lblCloudIcon.setBackground(Color.CYAN);
                dialogSetIcon.hide();
            } catch (IOException ex) {
                Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            lblCloudIcon.setBackground(Color.CYAN);
            lblFolderIcon.setBackground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_lblCloudIconMouseReleased

    private void dialogSetIconWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogSetIconWindowOpened

        Color gray = Color.LIGHT_GRAY;
        lblCloudIcon.setBackground(gray);
        lblFolderIcon.setBackground(gray);
        
    }//GEN-LAST:event_dialogSetIconWindowOpened

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        openWindow(dialogSetIcon, this);
        dialogSetIcon.setSize(400, 246);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnZipOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZipOptionActionPerformed

        dialogUploadOptions.hide();
        FileFilter filter = FilterSelector.getFilter(FilterSelector.TypeFilter.ZIP);
        fileChoos.addChoosableFileFilter(filter);
        fileChoos.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChoos.setFileFilter(filter);
        int selection = fileChoos.showOpenDialog(formManagement);
        File[] selectedFiles = fileChoos.getSelectedFiles();
        
        if (selectedFiles != null) {
            Thread tUploader = new Thread(() -> {
                
                for (File file : selectedFiles) {
                    if (file.getName().endsWith(".zip")) {
                        try {
                            linker.uploadZipFile(file);
                        } catch (IOException ex) {
                            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            tUploader.start();
            threadsUploaders.add(tUploader);
        }
        
    }//GEN-LAST:event_btnZipOptionActionPerformed

    private void txtServerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtServerFocusLost

        String text = txtServer.getText();
        if (!text.trim().isEmpty()) txtServer.setText(text.trim());
    }//GEN-LAST:event_txtServerFocusLost

    private void txtUserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserFocusLost

        String text = txtUser.getText();
        if (!text.trim().isEmpty()) txtUser.setText(text.trim());
    }//GEN-LAST:event_txtUserFocusLost

    private void dialogSearchLoaderWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogSearchLoaderWindowOpened
       ((ImageIcon) lblSearching.getIcon()).setImageObserver(lblSearching);
    }//GEN-LAST:event_dialogSearchLoaderWindowOpened

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        cancelSearch();
    }//GEN-LAST:event_btnCancelSearchActionPerformed

    private void dialogSearchLoaderWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogSearchLoaderWindowClosing
        cancelSearch();
    }//GEN-LAST:event_dialogSearchLoaderWindowClosing

    private void btnOrderFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderFilesActionPerformed

        SortOption option = SortOption.values()[cboOrderOption.getSelectedIndex()];
        SortOption order = SortOption.values()[cboOrderType.getSelectedIndex()+5];
        LinkedList<FTPFile> modelList = ((TMFiles)tblFiles.getModel()).getFiles();
        linker.orderFiles(modelList, option, order);
        tblFiles.update(tblFiles.getGraphics());
    }//GEN-LAST:event_btnOrderFilesActionPerformed

    private void btnOrderFilesSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderFilesSActionPerformed

        SortOption option = SortOption.values()[cboOrderOptionS.getSelectedIndex()];
        SortOption order = SortOption.values()[cboOrderTypeS.getSelectedIndex()+5];
        LinkedList<FileFiltering> files = ((TMSearch)tblSearch.getModel()).getFiles();
        Computer.orderSearchedFiles(files, option, order);
        tblSearch.update(tblSearch.getGraphics());
    }//GEN-LAST:event_btnOrderFilesSActionPerformed

    private void itemConsoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsoleActionPerformed

        openWindow(formConsole, formManagement, 558, 261);
    }//GEN-LAST:event_itemConsoleActionPerformed

    private void updateTable() throws IOException{
        
        LinkedList<FTPFile> files = linker.getOrderedFiles();
        directorioActual = linker.getWorkingDirectory();
        txtNewDir.setText(directorioActual);

        if (tblFiles.getRowHeight() != 25) tblFiles.setRowHeight(25);
        tblFiles.setModel(new TMFiles(files));
        tblFiles.setDefaultRenderer(Object.class, new TCRFiles(files));
    }
    
    private void setWorkingDirectory() throws IOException{
        FTPFile selected = getSelectedFile();
        selectedIndex = tblFiles.getSelectedRow();
        
        if (selected.isDirectory()) {
            linker.setWorkingDirectory(directorioActual + "/" + selected.getName());
            updateTable();
        }
        else {
            dialogFileOptions.show();
//            
//            
//            int tipo = JOptionPane.INFORMATION_MESSAGE;
//            int tipoOp = JOptionPane.YES_NO_OPTION;
//            int opcionSi = JOptionPane.YES_OPTION;
//            int opcion = JOptionPane.showConfirmDialog(
//                    gestion,
//                    "¿Desea descargar el archivo?",
//                    "Confirmacion de Descarga",
//                    tipoOp,
//                    tipo);
//
//            if (opcion == opcionSi) {
//                try {
//                  downloadFile(selected);
//                } catch (IOException ex) {
//                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        }

    }

    private void connect(String server, String user, String pass) throws IOException{
        isConnectedToHost = Tester.isConnectedToHost(server);
        
        if (isConnectedToHost) {
            
            linker = new FTPLinker(server, user, pass);
            
            if (linker.isConnected()) {
                
                if (chkAuto.isSelected()) 
                    settings.setSettings(server, user, pass);
                
                else if (!settings.equals(server, user, pass)) {
                    
                    int options = JOptionPane.showConfirmDialog(this, 
                            "¿Desea guardar los datos de inicio de sessión?", 
                            "Confirmacion", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.INFORMATION_MESSAGE);
                
                    if (options == JOptionPane.YES_OPTION){
                        settings.setSettings(false, server, user, pass);
                    }
                }
                updateTable();
                if(isVisible()) hide();
                openWindow(formManagement, null);
//                if (consoleUpdater == null) 
//                    consoleUpdater = new HUpdaterConsole(linker, txtConsole);
//                
//                if(!consoleUpdater.isAlive()) consoleUpdater.start();
                
                if(!settings.hasSaveConfigs())
                    clearComponents(txtServer, txtUser, txtPassword);
            }
            
            else {
                JOptionPane.showMessageDialog(this,
                        "Error de conexion, uno o mas datos invalidos\nCodigo de error: " +
                                linker.getReplyCode(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                txtServer.selectAll();
                txtServer.requestFocus();
                
                if (settings.isAutoLogin()) show();
            }
        }
        
        else {

            if(!isVisible()){
                show();
            }
            
            JOptionPane.showMessageDialog(this,
                    "Error de conexion, servidor desconocido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtServer.selectAll();
            txtServer.requestFocus();
        }
    }
    
    private void clearComponents(JComponent... componentes){

        for (JComponent componente : componentes) 
            if (componente instanceof JTextField) 
                ((JTextField)componente).setText(null);
            else if(componente instanceof JPasswordField)
                ((JPasswordField)componente).setText(null);
    }
    
    // Mas adelante incluir la idea de las descargas y cargas en cola
    
    private boolean hasAliveThreads(){
        
        if(threadsUploaders.isEmpty()) return false;
        
        return threadsUploaders.stream().anyMatch((t) -> t.isAlive());
    }

    private void cancellAllLoads(){
        threadsUploaders.stream().forEach((t) -> {
            t.stop();
            t = null;
        });
        threadsUploaders.clear();
        System.gc();
    }
    
    private void closeSession() throws IOException{
        tblFiles.setModel(new DefaultTableModel());
        txtNewDir.setText(null);
        linker.closeConnection();
        linker = null;
        formManagement.setVisible(false);
        setVisible(true);
        txtServer.requestFocus();
        tblSearch.setModel(new TMSearch(new LinkedList<>()));
    }
    
    private void downloadFile(FTPFile selected) throws IOException{
          fileChoos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          fileChoos.showOpenDialog(this);
          File directory = fileChoos.getSelectedFile();
          
          if (directory != null)
              linker.downloadFile(selected.getName(), directory.getPath());
          
    }
    
    private FTPFile getSelectedFile(){
        return ((TMFiles) tblFiles.getModel()).getFile(tblFiles.getSelectedRow());
    }
    
    private FTPFile getFile(int index){
        return ((TMFiles) tblFiles.getModel()).getFile(index);
    }
    
    private FileFiltering getSelectedFileFound(){
        
        TMSearch model = (TMSearch) tblSearch.getModel();
        return model.getFile(tblFiles.getSelectedRow());
    }

    private void goToFile() throws IOException{
        
        FileFiltering selected = getSelectedFileFound();
        
        if (selected.getFile().isDirectory()) {
            linker.setWorkingDirectory(selected.getPath());
            updateTable();
        }
        else{
            linker.setWorkingDirectory(selected.getParentDir());
            updateTable();
            TCRFiles renderer = (TCRFiles) tblFiles.getDefaultRenderer(Object.class);
            TMFiles model = (TMFiles) tblFiles.getModel();
            renderer.paintFoundFile(model.getIndex(selected.getFile().getName()));
            tblFiles.setDefaultRenderer(Object.class, renderer);
            tblFiles.updateUI();
        }
    }
    
    private void cancelSearch(){
        
        if(dialogSearchLoader.isVisible()) dialogSearchLoader.hide();
        
        if(tSearcher != null){
            if(tSearcher.isAlive())
                tSearcher.stop();
            tSearcher = null;
        }
        btnCancelSearch.setEnabled(false);
        Searcher.getInstance(linker, tblSearch).showResults();
        txtSearchFilter.setEnabled(true);
        if (Searcher.getInstance(linker, tblSearch).hasResults()) {
            cboOrderOptionS.setEnabled(true);
            cboOrderTypeS.setEnabled(true);
            btnOrderFilesS.setEnabled(true);
        }
        
        else if (cboOrderOptionS.isEnabled() && cboOrderTypeS.isEnabled() && btnOrderFilesS.isEnabled()) {
            cboOrderOptionS.setEnabled(false);
            cboOrderTypeS.setEnabled(false);
            btnOrderFilesS.setEnabled(false);
        }
        
    }
    
    public static void main(String args[]) {
        try {
            /* Set the Nimbus look and feel */
            javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(GUIClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUIClient();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFolder;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancelSearch;
    private javax.swing.JButton btnCloseViewConfigs;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnDeleteFile;
    private javax.swing.JButton btnDownloadFile;
    private javax.swing.JButton btnDownloadFile1;
    private javax.swing.JButton btnFileOption;
    private javax.swing.JButton btnFolderOption;
    private javax.swing.JButton btnOrderFiles;
    private javax.swing.JButton btnOrderFilesS;
    private javax.swing.JButton btnRenameFile;
    private javax.swing.JButton btnResetAll;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSetData;
    private javax.swing.JButton btnUpdateDirectory;
    private javax.swing.JButton btnUploadFile;
    private javax.swing.JButton btnZipOption;
    private javax.swing.JComboBox<String> cboOrderOption;
    private javax.swing.JComboBox<String> cboOrderOptionS;
    private javax.swing.JComboBox<String> cboOrderType;
    private javax.swing.JComboBox<String> cboOrderTypeS;
    private javax.swing.JCheckBox chkAuto;
    private javax.swing.JDialog dialogFileOptions;
    private javax.swing.JDialog dialogNewFolder;
    private javax.swing.JDialog dialogRename;
    private javax.swing.JDialog dialogSearchLoader;
    private javax.swing.JDialog dialogSetIcon;
    private javax.swing.JDialog dialogUploadOptions;
    private javax.swing.JDialog dialogViewConfigs;
    private javax.swing.JFrame formConsole;
    private javax.swing.JFrame formManagement;
    private javax.swing.JFrame formSearch;
    private javax.swing.JMenuItem itemConsole;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCloudIcon;
    private javax.swing.JLabel lblFolderIcon;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblSearching;
    private javax.swing.JPanel panelDialog;
    private javax.swing.JPanel panelUploadOptions;
    private javax.swing.JTable tblFiles;
    private javax.swing.JTable tblSearch;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JTextField txtMyHost;
    private javax.swing.JTextField txtMyPass;
    private javax.swing.JTextField txtMyUser;
    private javax.swing.JTextField txtNewDir;
    private javax.swing.JTextField txtNewFolder;
    private javax.swing.JTextField txtNewName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSearchFilter;
    private javax.swing.JTextField txtServer;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}

